package org.example;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;

public class Submission {
    private File file;
    private LocalDateTime submissionTime;
    private String feedback;
    private double score;
    private boolean graded;

    public Submission(File file, LocalDateTime submissionTime) {
        this.file = file;
        this.submissionTime = submissionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return Double.compare(score, that.score) == 0 && graded == that.graded && Objects.equals(file, that.file) && Objects.equals(submissionTime, that.submissionTime) && Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, submissionTime, feedback, score, graded);
    }

    @Override
    public String toString() {
        return "Submission{" +
                "file=" + file +
                ", submissionTime=" + submissionTime +
                ", feedback='" + feedback + '\'' +
                ", score=" + score +
                ", graded=" + graded +
                '}';
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }
}
