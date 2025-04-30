package org.example;

public class Administrator extends User implements CreateUser{

    public Administrator(String name, int age, Gender gender) {
        super(name, age, gender);
    }

    @Override
    public User createTeacher(String name, int age, Gender gender) {
        return new Teacher(name, age, gender);
    }

    @Override
    public User createStudent(String name, int age, Gender gender) {
        return new Student(name, age, gender);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
}
