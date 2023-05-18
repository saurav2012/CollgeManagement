package com.sapient.CollegeManagement.service;

import com.sapient.CollegeManagement.dao.CollegeDao;

import java.util.Scanner;

public class CollegeService {
    public static void main(String[] args) {
        System.out.println("Welcome to application, enter data");
        Scanner scn = new Scanner(System.in);
//        CollegeDao.getAllStudent();
//        CollegeDao.getOverAllPercentage();
//        CollegeDao.topThree();
//        CollegeDao.bottomThree();
//        CollegeDao.selectStudentWhoFails();
//        CollegeDao.getPassPercentage();
//        CollegeDao.getAvgMarks();
        CollegeDao.getPassPercentage();
    }
}
