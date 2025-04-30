package org.example;

public interface CreateUser {
    User createTeacher(String name, int age, User.Gender gender);
    User createStudent(String name, int age, User.Gender gender);
}
