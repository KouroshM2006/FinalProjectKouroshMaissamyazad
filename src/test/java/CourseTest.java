import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CourseTest {

    @Test
    public void addStudentTest1() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Course course = new Course("english", "xxxx", teacher, new TreeMap<>(), 0);

        Student input = new Student("john", 12, User.Gender.MALE);
        course.addStudent(input);

        Assertions.assertFalse(course.getStudents().contains(input));
    }

    @Test
    public void addStudentTest2() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Course course = new Course("english", "xxxx", teacher, new TreeMap<>(), 7);

        Student input = new Student("john", 12, User.Gender.MALE);
        course.addStudent(input);

        Assertions.assertTrue(course.getStudents().contains(input));
    }

    @Test
    public void courseComparatorClassTest() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Student student = new Student("bob", 12, User.Gender.MALE);

        Course c1 = new Course("english", "xxx", teacher, new TreeMap<>(), 14);
        Course c2 = new Course("french", "xxx", teacher, new TreeMap<>(), 30);
        Course c3 = new Course("programming", "xxx", teacher, new TreeMap<>(), 20);

        c1.addStudent(student);

        c2.addStudent(student);
        c2.addStudent(student);

        c3.addStudent(student);
        c3.addStudent(student);
        c3.addStudent(student);

        List<Course> courses = new ArrayList<>(List.of(c2, c3, c1));
        List<Course> expected = new ArrayList<>(List.of(c1, c2, c3));

        Collections.sort(courses, new Course.CourseComparator("title"));

        Assertions.assertEquals(expected, courses);

        Collections.sort(courses, new Course.CourseComparator("enrollment count"));

        Assertions.assertEquals(expected, courses);

        Collections.sort(courses, new Course.CourseComparator("capacity"));
        expected = new ArrayList<>(List.of(c1, c3, c2));

        Assertions.assertEquals(expected, courses);

        Collections.sort(courses, new Course.CourseComparator(""));
        expected= new ArrayList<>(List.of(c1, c2, c3));

        Assertions.assertEquals(expected, courses);

    }
}
