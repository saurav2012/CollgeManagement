package com.sapient.CollegeManagement.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    static Connection connection;
    public static Connection createConnection(){
        try{
            // load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection
            String user = "root";
            String password = "Kumar@123";
            String url = "jdbc:mysql://localhost:3306/collegemanagement";
            connection = DriverManager.getConnection(url,user,password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
