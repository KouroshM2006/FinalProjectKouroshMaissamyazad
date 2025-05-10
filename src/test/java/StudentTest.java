import org.example.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class StudentTest {
    private final PrintStream originalOutPut = System.out;
    private final ByteArrayOutputStream newOutPut = new ByteArrayOutputStream();

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
    public void downloadScheduleTest() {
        Student student = new Student("bob", 22, User.Gender.MALE);
        Map<LocalDate, LocalTime> map = new TreeMap<>();
        map.put(LocalDate.now(), LocalTime.now());
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        student.getCourses().add(new Course("english", "xxxx", teacher, map, 7));

        student.downloadSchedule();

        File file = new File("src/main/java/Resources/bob_schedule.csv");
        Assertions.assertTrue(file.exists());

        try {
            String content = Files.readString(file.toPath());
            Assertions.assertTrue(content.contains("john"));
            Assertions.assertTrue(content.contains("english"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.delete();
    }

    @Test
    public void enrollCourseTest1() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Student student = new Student("james", 12, User.Gender.MALE);

        student.enrollCourse(course);
        Assertions.assertTrue(course.getStudents().contains(student));
        Assertions.assertTrue(student.getCourses().contains(course));

        student.enrollCourse(course);
        Assertions.assertTrue(newOutPut.toString().contains("Student is already registered to the course"));
        Assertions.assertFalse(student.getCourses().stream().count() == 2);
    }

    @Test
    public void enrollCourseTest2() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);

        for (int i = 0; i < 8; i++) {
            student.enrollCourse(new Course("english", "xxx", teacher, new TreeMap<>(), 10));
        }

        Course course = new Course("french", "XXX", teacher, new TreeMap<>(), 9);
        student.enrollCourse(course);

        Assertions.assertTrue(newOutPut.toString().contains("maximum capacity of courses has been reached"));
        Assertions.assertFalse(student.getCourses().contains(course));
        Assertions.assertFalse(course.getStudents().contains(student));
    }

    @Test
    public void submitAssignmentTest1() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("xxx", "xxx", course, LocalDateTime.MAX, 10);
        student.getAssignments().add(assignment);

        student.submitAssignment(assignment, new File(""));

        Assertions.assertTrue(newOutPut.toString().contains("Assignment submitted successfully"));
        Assertions.assertTrue(assignment.getSubmissions().containsKey(student));
    }

    @Test
    public void submitAssignmentTest2() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("xxx", "xxx", course, LocalDateTime.MAX, 10);
        student.submitAssignment(assignment, new File(""));

        Assertions.assertTrue(newOutPut.toString().contains("Assignment does not exist"));
        Assertions.assertFalse(assignment.getSubmissions().containsKey(student));
    }

    @Test
    public void submitAssignmentTest3() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("xxx", "xxx", course, LocalDateTime.MIN, 10);
        student.getAssignments().add(assignment);
        student.submitAssignment(assignment, new File(""));

        Assertions.assertTrue(newOutPut.toString().contains("Submission denied - past deadline!"));
        Assertions.assertFalse(assignment.getSubmissions().containsKey(student));
    }

    @Test
    public void viewFeedbackTest1() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("xxx", "xxx", course, LocalDateTime.MAX, 10);
        student.getAssignments().add(assignment);
        student.submitAssignment(assignment, new File(""));

        assignment.getSubmissions().get(student).setGraded(true);
        assignment.getSubmissions().get(student).setScore(10);
        assignment.getSubmissions().get(student).setFeedback("xxx");

        Submission submission = assignment.getSubmissions().get(student);
        student.viewFeedback(assignment);
        Assertions.assertTrue(newOutPut.toString().contains(String.format("Your grade: %s\nFeedback: %s", submission.getScore(), submission.getFeedback())));
    }

    @Test
    public void viewFeedbackTest2() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("xxx", "xxx", course, LocalDateTime.MAX, 10);
        student.getAssignments().add(assignment);
        student.submitAssignment(assignment, new File(""));

        student.viewFeedback(assignment);
        Assertions.assertTrue(newOutPut.toString().contains("No feedback available yet"));
    }

    @Test
    public void viewFeedbackTest3() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("xxx", "xxx", course, LocalDateTime.MAX, 10);

        student.submitAssignment(assignment, new File(""));

        student.viewFeedback(assignment);
        Assertions.assertTrue(newOutPut.toString().contains("Assignment does not exist"));
    }

    @Test
    public void viewFeedbackTest4() {
        Teacher teacher = new Teacher("john", 34, User.Gender.MALE);
        Student student = new Student("james", 12, User.Gender.MALE);
        Course course = new Course("english", "xxx", teacher, new TreeMap<>(), 10);
        Assignment assignment = new Assignment("xxx", "xxx", course, LocalDateTime.MAX, 10);
        student.getAssignments().add(assignment);

        student.viewFeedback(assignment);
        Assertions.assertTrue(newOutPut.toString().contains("Assignment not submitted yet!"));
    }
}
