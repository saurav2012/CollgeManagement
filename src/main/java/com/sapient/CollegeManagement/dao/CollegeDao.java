package com.sapient.CollegeManagement.dao;

import com.sapient.CollegeManagement.utility.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CollegeDao {
    public static String insertStudentToDB(int id,String name,int marks,int courseId){

        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "insert into students(id,name,marks,course_id) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,marks);
            preparedStatement.setInt(4,courseId);

            //update query
            preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "Data add successfully";
    }
    public static void getAllStudent(){

        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select * from students";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name  = resultSet.getString(2);
                int marks = resultSet.getInt(3);
                int courseId = resultSet.getInt(4);
                System.out.println(id+", " + name + ", " + marks + ", " + courseId);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // Overall percentage each student...
    public static void getOverAllPercentage(){
        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select sum(marks)/6, name from students group by name";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String name  = resultSet.getString(2);
                int marks = resultSet.getInt(1);
                System.out.println(name + " scores " + marks + " % marks.");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    // Top 3 students in each course along with course details
    public static void topThree(){
        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select * from (select students.id, students.name, students.marks,students.course_id, courses.name as course_name, courses.pass_marks, row_number() over (partition by courses.id order by marks desc) student_rank from students join courses on students.course_id = courses.id) marks where student_rank<=3";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name  = resultSet.getString(2);
                int marks = resultSet.getInt(3);
                int courseId = resultSet.getInt(4);
                String courseName = resultSet.getString(5);
                String passMarks = resultSet.getString(5);
                System.out.println("[" + id +", " + name + ", " + marks + ", " + courseId + ", " + courseName+ ", " +passMarks + "]");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Bottom 3 students in each course along with course details

    public static void bottomThree(){
        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select * from (select students.id, students.name, students.marks,students.course_id, courses.name as course_name, courses.pass_marks, " +
                    "" + "from students join courses on students.course_id = courses.id) marks where student_rank<=3";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name  = resultSet.getString(2);
                int marks = resultSet.getInt(3);
                int courseId = resultSet.getInt(4);
                String courseName = resultSet.getString(5);
                String passMarks = resultSet.getString(6);
                System.out.println("[" + id +", " + name + ", " + marks + ", " + courseId + ", " + courseName+ ", " +passMarks + "]");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //List of students who failed in at least one subject along with course and prof details

    public static void selectStudentWhoFails(){
        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select * from students join courses on students.course_id = courses.id " +
                    "join professors on courses.professor_id = professors.id where students.marks < courses.pass_marks";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name  = resultSet.getString(2);
                int marks = resultSet.getInt(3);
                int courseId = resultSet.getInt(4);
                String courseName = resultSet.getString(6);
                String passMarks = resultSet.getString(7);
                int profId = resultSet.getInt(8);
                String profName = resultSet.getString(10);
                String profdeg = resultSet.getString(11);
                System.out.println("[" + id +", " + name + ", " + marks + ", " + courseId + ", " + courseName+ ", " +passMarks  + ", " +profId + ", " +profName +", " +profdeg + "]");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Pass percentage of each course along with course and professor details
    public static void getPassPercentage(){
        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select courses.id,courses.name as course_name,professors.id,professors.name as professor_name, count(case when students.marks >= courses.pass_marks then 1 end)*100/count(*) as pass_students from students join courses on students.course_id = courses.id join professors on courses.professor_id = professors.id group by courses.id";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String courseName = resultSet.getString(2);
                int profId = resultSet.getInt(3);
                String profName = resultSet.getString(4);
                int passPercentage = resultSet.getInt(5);
                System.out.println("[" + id +", " + courseName + ", " + profId + ", " + profName + ", " + passPercentage + "]");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Professor details along with the average marks obtained in the course he/she taught
    public static void getAvgMarks(){
        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select courses.id,courses.name as course_name,professors.id,professors.name as professor_name, avg(students.marks) as avg_marks from students join courses on students.course_id = courses.id join professors on courses.professor_id = professors.id group by courses.id";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String courseName = resultSet.getString(2);
                int profId = resultSet.getInt(3);
                String profName = resultSet.getString(4);
                int avgMarks = resultSet.getInt(5);
                System.out.println("[" + id +", " + courseName + ", " + profId + ", " + profName + ", " + avgMarks + "]");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Professor details along with course details who have at least 50% pass percentage in their subject
    public static void getMoreThan50PassPercentage(){
        try{
            Connection connection = ConnectionProvider.createConnection();
            String query = "select * from (select courses.id as course_id,courses.name as course_name,professors.id as professor_id,professors.name as professor_name, count(case when students.marks >= courses.pass_marks then 1 end)*100/count(*) as pass_percentage from students join courses on students.course_id = courses.id join professors on courses.professor_id = professors.id group by courses.id) pass_student where pass_percentage>50";
            Statement statement = connection.prepareStatement(query);

            //update query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String courseName = resultSet.getString(2);
                int profId = resultSet.getInt(3);
                String profName = resultSet.getString(4);
                int passPercentage = resultSet.getInt(5);
                System.out.println("[" + id +", " + courseName + ", " + profId + ", " + profName + ", " + passPercentage + "]");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
