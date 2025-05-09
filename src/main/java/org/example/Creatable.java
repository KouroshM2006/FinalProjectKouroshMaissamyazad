package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Creatable {
    /**
     * creates a new Teacher class and adds it to the user list in CourseManagementSystem
     *
     * @param name
     * @param age
     * @param gender
     */
    void createTeacher(String name, int age, User.Gender gender);

    /**
     * creates a new Student class and adds it to the user list in CourseManagementSystem
     *
     * @param name
     * @param age
     * @param gender
     */
    void createStudent(String name, int age, User.Gender gender);

    /**
     * method to import users from users.csv file in the Resources folder
     */
    void importUsers();

    /**
     * method to create a course if it does not already exist;
     * @param title given title as String
     * @param description given description as String
     * @param teacher given Teacher
     * @param dateAndTime given map of dates and times
     * @param capacity given student capacity of the course
     */
    void createCourse(String title, String description, Teacher teacher, Map<LocalDate, LocalTime> dateAndTime, int capacity);
}
