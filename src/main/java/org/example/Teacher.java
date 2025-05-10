package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Teacher extends User {
    private long teacherId;
    private List<Course> courses;
    private List<Assignment> assignmentsCreated;

    private static long nextId = 1;

    public Teacher(String name, int age, Gender gender) {
        super(name, age, gender);
        this.teacherId = nextId++;
        this.courses = new ArrayList<>();
        this.assignmentsCreated = new ArrayList<>();
    }

    /**
     * Teacher's version of getRole
     *
     * @return "Teacher"
     */
    @Override
    public String getRole() {
        return "Teacher";
    }

    /**
     * method to create a new assignment and add it to students assignment list and the teacher assignmentsCreated list
     *
     * @param title       given title as String
     * @param description given description as String
     * @param course      given Course
     * @param dueDate     given due date as LocalDateTime
     * @param maxScore    given max score as double
     */
    public void createAssignment(String title, String description, Course course, LocalDateTime dueDate, double maxScore) {
        if (course.getTeacher().equals(this)) {
            Assignment assignment = new Assignment(title, description, course, dueDate, maxScore);
            for (Student student : course.getStudents()) {
                student.getAssignments().add(assignment);
            }
            assignmentsCreated.add(assignment);
            System.out.println("Assignment created successfully");
        } else {
            System.out.println("access to course denied");
        }
    }

    /**
     * method to grade an assignment
     *
     * @param assignment given assignment to grade
     * @param student    given student
     * @param score      given score
     * @param feedback   given feedback
     */
    public void gradeAssignment(Assignment assignment, Student student, double score, String feedback) {
        if (assignment.getCourse().getTeacher().equals(this)) {
            assignment.addFeedback(student, feedback, score);
        } else {
            System.out.println("not authorized to grade this assignment");
        }
    }

    /**
     * method to change the due date of an assignment
     *
     * @param assignment given Assignment
     * @param newDueDate given new due date as LocalDateTime;
     */
    public void modifyAssignmentDueDate(Assignment assignment, LocalDateTime newDueDate) {
        if (assignment.getCourse().getTeacher().equals(this)) {
            assignment.setDueDate(newDueDate);
            System.out.println("Due date of the assignment was successfully changed");
        } else {
            System.out.println("Not authorized to access this assignment");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacherId);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", courses=" + courses.stream().map(course -> "(Id: " + course.getCourseId() + ", Title: " + course.getTitle() + ")")
                .collect(Collectors.joining(", ")) +
                ", assignmentsCreated=" + assignmentsCreated.stream()
                .map(AcademicItem::getTitle)
                .collect(Collectors.joining(", ")) +
                '}' + super.toString();
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Assignment> getAssignmentsCreated() {
        return assignmentsCreated;
    }

    public void setAssignmentsCreated(List<Assignment> assignmentsCreated) {
        this.assignmentsCreated = assignmentsCreated;
    }

    public static long getNextId() {
        return nextId;
    }

    public static void setNextId(long nextId) {
        Teacher.nextId = nextId;
    }
}
