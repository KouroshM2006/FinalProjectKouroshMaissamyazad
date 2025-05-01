package org.example;
import java.util.*;

public class Teacher extends User{
    private List<Course> courses;

    public Teacher(String name, int age, Gender gender) {
        super(name, age, gender);
        this.courses = new ArrayList<>();
    }

    /**
     * Teacher's version of getRole
     * @return "Teacher"
     */
    @Override
    public String getRole() {
        return "Teacher";
    }
}
