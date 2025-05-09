package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Administrator extends User implements Creatable {
    private long adminId;

    private static long nextId = 1;

    public Administrator(String name, int age, Gender gender) {
        super(name, age, gender);
        this.adminId = nextId++;
    }


    /**
     * creates a new Teacher class and adds it to the user list in CourseManagementSystem
     *
     * @param name
     * @param age
     * @param gender
     */
    @Override
    public void createTeacher(String name, int age, Gender gender) {
        CourseManagementSystem.users.add(new Teacher(name, age, gender));
        System.out.println("Teacher successfully created");
    }

    /**
     * creates a new Student class and adds it to the user list in CourseManagementSystem
     *
     * @param name
     * @param age
     * @param gender
     */
    @Override
    public void createStudent(String name, int age, Gender gender) {
        CourseManagementSystem.users.add(new Student(name, age, gender));
        System.out.println("Student successfully created");

    }

    /**
     * Administrator's version of getRole
     *
     * @return "Administrator"
     */
    @Override
    public String getRole() {
        return "Administrator";
    }

    /**
     * method to import users from users.csv file in the Resources folder
     */
    public void importUsers() {
        File file = new File("src/main/java/Resources/users.csv");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(",");
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                User.Gender gender = User.Gender.valueOf(data[3]);

                switch (data[0].toLowerCase()) {
                    case "administrator":
                        CourseManagementSystem.users.add(new Administrator(name, age, gender));
                        break;
                    case "teacher":
                        CourseManagementSystem.users.add(new Teacher(name, age, gender));
                        break;
                    case "student":
                        CourseManagementSystem.users.add(new Student(name, age, gender));
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Administrator's implementation of createCourse method
     *
     * @param title       given title as String
     * @param description given description as String
     * @param teacher     given Teacher
     * @param dateAndTime given map of dates and times
     * @param capacity    given student capacity of the course
     */
    @Override
    public void createCourse(String title, String description, Teacher teacher, Map<LocalDate, LocalTime> dateAndTime, int capacity) {
        if (teacher.getCourses().stream().filter(course -> course.title.equalsIgnoreCase(title)).toList().isEmpty()) {
            Course course = new Course(title, description, teacher, dateAndTime, capacity);
            teacher.getCourses().add(course);
            CourseManagementSystem.courses.add(course);
            System.out.println("course successfully created");
        } else {
            System.out.println("Course creation failed - The teacher already holds a course with the same title");
        }

    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrator that = (Administrator) o;
        return adminId == that.adminId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), adminId);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "adminId=" + adminId +
                '}' + super.toString();
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public static long getNextId() {
        return nextId;
    }

    public static void setNextId(long nextId) {
        Administrator.nextId = nextId;
    }
}
