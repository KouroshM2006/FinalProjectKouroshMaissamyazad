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

public class TeacherTest {
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
    public void createAssignmentTest1() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        course.addStudent(student);

        teacher.createAssignment("newAssignment", "xxx", course, LocalDateTime.MAX, 10);

        Assertions.assertTrue(newOutPut.toString().contains("Assignment created successfully"));
        Assertions.assertTrue(student.getAssignments().get(0).getTitle().equals("newAssignment"));
    }

    @Test
    public void createAssignmentTest2() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", new Teacher("x", 45, User.Gender.MALE), new TreeMap<>(), 10);
        course.addStudent(student);

        teacher.createAssignment("newAssignment", "xxx", course, LocalDateTime.MAX, 10);

        Assertions.assertTrue(newOutPut.toString().contains("access to course denied"));
    }

    @Test
    public void gradeAssignmentTest1() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        course.addStudent(student);
        Assignment assignment = new Assignment("x", "x", course, LocalDateTime.MAX, 10);
        student.getAssignments().add(assignment);
        student.submitAssignment(assignment, new File(""));

        teacher.gradeAssignment(assignment, student, 10, "very good");
        Submission submission = assignment.getSubmissions().get(student);

        Assertions.assertTrue(submission.isGraded());
        Assertions.assertTrue(submission.getScore() == 10);
        Assertions.assertTrue(submission.getFeedback().equals("very good"));

    }

    @Test
    public void gradeAssignmentTest2() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", new Teacher("x", 34, User.Gender.MALE), new TreeMap<>(), 10);
        Assignment assignment = new Assignment("x", "x", course, LocalDateTime.MAX, 10);


        teacher.gradeAssignment(assignment, student, 10, "very good");

        Assertions.assertTrue(newOutPut.toString().contains("not authorized to grade this assignment"));
    }

    @Test
    public void modifyAssignmentDueDateTest1() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("x", "x", course, LocalDateTime.MIN, 10);

        teacher.modifyAssignmentDueDate(assignment, LocalDateTime.MAX);
        Assertions.assertEquals(assignment.getDueDate(), LocalDateTime.MAX);
        Assertions.assertTrue(newOutPut.toString().contains("Due date of the assignment was successfully changed"));
    }

    @Test
    public void modifyAssignmentDueDateTest2() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Course course = new Course("english", "xxx", new Teacher("x", 30, User.Gender.MALE), new TreeMap<>(), 10);
        Assignment assignment = new Assignment("x", "x", course, LocalDateTime.MIN, 10);

        teacher.modifyAssignmentDueDate(assignment, LocalDateTime.MAX);
        Assertions.assertTrue(newOutPut.toString().contains("Not authorized to access this assignment"));
    }
}
