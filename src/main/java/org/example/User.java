package org.example;

import java.util.Objects;

public abstract class User {
    protected long id;
    protected String name;
    protected int age;
    protected Gender gender;

    private static long nextId = 1;

    public enum Gender {
        MALE, FEMALE
    }


    public User(String name, int age, Gender gender) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public User(long id, String name, int age, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /**
     * returns the role of the user (Student, Teacher) as a string
     * @return string
     */
    public abstract String getRole();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && gender == user.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static long getNextId() {
        return nextId;
    }

    public static void setNextId(long nextId) {
        User.nextId = nextId;
    }


}
