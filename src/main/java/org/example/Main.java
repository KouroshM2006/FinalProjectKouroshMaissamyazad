package org.example;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        //Demo
        Administrator admin = new Administrator("Bob", 45, User.Gender.MALE);
        CourseManagementSystem.users.add(admin);

        //creating users with admin
        admin.createTeacher("John", 34, User.Gender.MALE);
        admin.createStudent("James", 18, User.Gender.MALE);

        for (User user : CourseManagementSystem.users) {
            System.out.println(user);
        }
        System.out.println();

        //creating Courses
        Teacher teacher = (Teacher) CourseManagementSystem.users.get(1);

        admin.createCourse("English", "english college level", teacher, new TreeMap<>(), 20);
        admin.createCourse("History", "history college level", teacher, new TreeMap<>(), 20);

        for (Course course : CourseManagementSystem.courses) {
            System.out.println(course);
        }
        System.out.println();

        //enrolling student into courses
        Course englishCourse = CourseManagementSystem.courses.get(0);
        Course historyCourse = CourseManagementSystem.courses.get(1);

        Student student = (Student) CourseManagementSystem.users.get(2);
        student.enrollCourse(englishCourse);
        student.enrollCourse(historyCourse);

        for (Course course : student.getCourses()) {
            System.out.println(course);
        }
        System.out.println();

        //downloading students schedule
        student.downloadSchedule();

        //creating assignment
        teacher.createAssignment("final essay", "1000 word essay", englishCourse, LocalDateTime.MAX, 100);

        for (Assignment assignment : student.getAssignments()) {
            System.out.println(assignment);
        }
        System.out.println();

        //submitting assignment
        Assignment assignment = student.getAssignments().get(0);

        student.submitAssignment(assignment, new File("file path"));

        System.out.println(assignment.getSubmissions().get(student));
        System.out.println();

        //grading assignment
        teacher.gradeAssignment(assignment, student, 100, "very good!");

        System.out.println(assignment.getSubmissions().get(student));
        System.out.println();

        //view feedback from teacher
        student.viewFeedback(assignment);

        //searching for users
        List<User> users = CourseManagementSystem.searchForUser("j");
        users.forEach(System.out::println);
        System.out.println();

        //searching for courses
        List<Course> courses = CourseManagementSystem.searchForCourses("history");
        courses.forEach(System.out::println);
        System.out.println();

        //importing users from users.csv in Resources folder
        admin.importUsers();
        CourseManagementSystem.users.forEach(System.out::println);



    }
}