package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Student extends User {
    private List<Course> schedule;
    private List<Assignment> assignments;

    public Student(String name, int age, Gender gender) {
        super(name, age, gender);
        this.schedule = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    /**
     * Students version of getRole
     * @return "Student"
     */
    @Override
    public String getRole() {
        return "Student";
    }

    /**
     * writes the content of each course that the Student is registered in into a csv file
     */
    public void downloadSchedule() {
        File file = new File(String.format("src/main/java/Resources/%s_schedule.csv", super.name));

        try (FileWriter fw = new FileWriter(file)) {
            fw.write("courseId,Title,Teacher,DateAndTime\n");
            for (Course course : schedule) {
                fw.write(String.format("%s,%s,%s,%s\n", course.getCourseId(), course.getTitle(), course.getTeacher().getName(), course.getDateAndTime()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(schedule, student.schedule) && Objects.equals(assignments, student.assignments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schedule, assignments);
    }

    @Override
    public String toString() {
        return "Student{" +
                "schedule=" + schedule +
                ", assignments=" + assignments +
                '}' + super.toString();
    }

    public List<Course> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Course> schedule) {
        this.schedule = schedule;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
