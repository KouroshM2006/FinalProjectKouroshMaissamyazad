import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CourseManagementSystemTest {
    @Test
    public void searchForUserTest() {
        Administrator admin = new Administrator("James", 45, User.Gender.MALE);
        Teacher teacher = new Teacher("John", 34, User.Gender.MALE);
        Student student = new Student("jenny", 12, User.Gender.FEMALE);

        CourseManagementSystem.users.add(admin);
        CourseManagementSystem.users.add(teacher);
        CourseManagementSystem.users.add(student);

        List<User> expected = new ArrayList<>(List.of(admin, teacher, student));

        Assertions.assertEquals(expected, CourseManagementSystem.searchForUser("j"));
    }

    @Test
    public void searchForCourses() {
        Teacher teacher = new Teacher("John", 34, User.Gender.MALE);
        Course c1 = new Course("Programming 101", "xxx", teacher, new TreeMap<>(), 10);
        Course c2 = new Course("Programming and data structure", "xxx", teacher, new TreeMap<>(), 10);
        Course c3 = new Course("Introduction to programming", "xxx", teacher, new TreeMap<>(), 10);

        CourseManagementSystem.courses.add(c1);
        CourseManagementSystem.courses.add(c2);
        CourseManagementSystem.courses.add(c3);

        List<Course> expected = new ArrayList<>(List.of(c1, c2, c3));

        Assertions.assertEquals(expected, CourseManagementSystem.searchForCourses("programming"));
    }
}
