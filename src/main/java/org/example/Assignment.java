package org.example;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public class Assignment extends AcademicItem{
    private Course course;
    private LocalDateTime dueDate;
    private double maxScore;
    private Map<Student, Submission> submissions;

    public Assignment(String title, String description, Course course, LocalDateTime dueDate, double maxScore) {
        super(title, description);
        this.course = course;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
        this.submissions = new HashMap<>();
    }

    /**
     * method to add a submission to the submissions map
     * @param student given student
     * @param file given file to submit
     */
    public void addSubmission(Student student, File file) {
        submissions.put(student, new Submission(file, LocalDateTime.now()));
    }

    /**
     * method to add feedback to a submission
     * @param student given student
     * @param feedback given feedback
     * @param score given score
     */
    public void addFeedback(Student student, String feedback, double score) {
        if (submissions.containsKey(student)) {
            submissions.get(student).setFeedback(feedback);
            submissions.get(student).setScore(score);
            submissions.get(student).setGraded(true);
            System.out.println("Feedback submitted for " + student.getName());
        } else {
            System.out.println("the student has not submitted the assignment yet");
        }
    }

    /**
     * Assignment's version of getType
     * @return "Assignment"
     */
    @Override
    public String getType() {
        return "Assignment";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assignment that = (Assignment) o;
        return Double.compare(maxScore, that.maxScore) == 0 && Objects.equals(course, that.course) && Objects.equals(dueDate, that.dueDate) && Objects.equals(submissions, that.submissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), course, dueDate, maxScore, submissions);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "course=" + course.getTitle() +
                ", Teacher=" + course.getTeacher().getName() +
                ", dueDate=" + dueDate +
                ", maxScore=" + maxScore +
                ", submissions=" + submissions +
                '}' + super.toString();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public Map<Student, Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Map<Student, Submission> submissions) {
        this.submissions = submissions;
    }
}
