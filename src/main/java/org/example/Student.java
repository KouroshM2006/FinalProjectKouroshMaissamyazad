package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Student extends User {
    private long studentId;
    private List<Course> courses;
    private List<Assignment> assignments;
    private final int capacity = 8;

    private static int nextId = 1;

    public Student(String name, int age, Gender gender) {
        super(name, age, gender);
        this.studentId = nextId++;
        this.courses = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }


    /**
     * Students version of getRole
     *
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
            for (Course course : courses) {
                fw.write(String.format("%s,%s,%s,%s\n", course.getCourseId(), course.getTitle(), course.getTeacher().getName(), course.getDateAndTime()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * method to enroll the student into a course
     * @param course given Course
     */
    public void enrollCourse(Course course) {
        if (courses.size() >= capacity) {
            System.out.println("maximum capacity of courses has been reached");
        } else if (courses.contains(course)) {
            System.out.println("Student is already registered to the course");
        } else {
            course.addStudent(this);
        }
    }

    /**
     * method to submit an assignment
     *
     * @param assignment     given assignment to be submitted
     * @param submissionFile given submissionFile
     */
    public void submitAssignment(Assignment assignment, File submissionFile) {
        if (!assignments.contains(assignment)) {
            System.out.println("Assignment does not exist");
        } else if (assignment.getDueDate().isAfter(LocalDateTime.now())) {
            assignment.addSubmission(this, submissionFile);
            System.out.println("Assignment submitted successfully");
        } else {
            System.out.println("Submission denied - past deadline!");
        }
    }

    /**
     * method to view the feedback left by the teacher on an assignment
     *
     * @param assignment given assignment to view
     */
    public void viewFeedback(Assignment assignment) {
        if (!this.assignments.contains(assignment)) {
            System.out.println("Assignment does not exist");
        } else if (!assignment.getSubmissions().containsKey(this)) {
            System.out.println("Assignment not submitted yet!");
        } else {
            Submission submission = assignment.getSubmissions().get(this);
            if (submission != null && submission.isGraded()) {
                System.out.println("Your grade: " + submission.getScore() + "\nFeedback: " + submission.getFeedback());
            } else {
                System.out.println("No feedback available yet");
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", courses=" + courses.stream()
                .map(course -> "(Id: " + course.getCourseId() + ", Title: " + course.getTitle() + ")")
                .collect(Collectors.joining(", ")) +
                ", assignments=" + assignments.stream()
                .map(AcademicItem::getTitle)
                .collect(Collectors.joining(", ")) +
                '}' + super.toString();
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }
}
