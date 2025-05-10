import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class AssignmentTest {
    private final ByteArrayOutputStream newOutPut = new ByteArrayOutputStream();
    private final PrintStream originalOutPut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(newOutPut));
        newOutPut.reset();
    }

    @AfterEach
    public void reset() {
        System.setOut(originalOutPut);
    }

    @Test
    public void addSubmissionTest() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        course.addStudent(student);
        Assignment assignment = new Assignment("x", "x", course, LocalDateTime.MAX, 10);

        assignment.addSubmission(student, new File(""));
        Assertions.assertTrue(assignment.getSubmissions().containsKey(student));
    }

    @Test
    public void addFeedbackTest1() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        course.addStudent(student);
        Assignment assignment = new Assignment("x", "x", course, LocalDateTime.MAX, 10);
        student.getAssignments().add(assignment);
        student.submitAssignment(assignment, new File(""));

        assignment.addFeedback(student, "very bad :)", 0);

        Assertions.assertTrue(newOutPut.toString().contains(String.format("Feedback submitted for %s", student.getName())));
        Submission submission = assignment.getSubmissions().get(student);

        Assertions.assertTrue(submission.isGraded());
        Assertions.assertTrue(submission.getScore() == 0);
        Assertions.assertTrue(submission.getFeedback().equals("very bad :)"));
    }

    @Test
    public void addFeedbackTest2() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        course.addStudent(student);
        Assignment assignment = new Assignment("x", "x", course, LocalDateTime.MAX, 10);

        assignment.addFeedback(student, "very bad :)", 0);

        Assertions.assertTrue(newOutPut.toString().contains("the student has not submitted the assignment yet"));
    }
}
