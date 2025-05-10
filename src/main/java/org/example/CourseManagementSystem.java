package org.example;

import java.util.ArrayList;
import java.util.List;

public class CourseManagementSystem {
    public static List<User> users = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();

    /**
     * method to search for users based on a keyword in their name (case-insensitive)
     *
     * @param keyword given keyword as a String
     * @return List of users with the keyword in their name
     */
    public static List<User> searchForUser(String keyword) {
        return users.stream()
                .filter(user -> user.name.toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * method to search for courses based on a keyword in their title (case-insensitive)
     *
     * @param keyword given keyword as a String
     * @return List of courses with the keyword in their title
     */
    public static List<Course> searchForCourses(String keyword) {
        return courses.stream()
                .filter(course -> course.title.toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }



}
