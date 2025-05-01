import org.example.Course;
import org.example.Student;
import org.example.Teacher;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

public class CourseTest {

    @Test
    public void addStudentTest1() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Course course = new Course("english", "xxxx", teacher, new ArrayList<>(), new TreeMap<>());

        Student input = new Student("john", 12, User.Gender.MALE);

        Assertions.assertTrue(course.addStudent(input));
        Assertions.assertTrue(course.getStudents().contains(input));
    }

    @Test
    public void addStudentTest2() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Course course = new Course("english", "xxxx", teacher, new ArrayList<>(), new TreeMap<>());

        Student input = new Student("john", 12, User.Gender.MALE);
        course.addStudent(input);

        Assertions.assertFalse(course.addStudent(input));
        Assertions.assertTrue(course.getStudents().contains(input));
    }
}
