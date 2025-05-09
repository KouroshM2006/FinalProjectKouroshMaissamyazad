import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class AdministratorTest {
    @Test
    public void createTeacherTest() {
        Administrator admin = new Administrator("xoxo", 56, User.Gender.MALE);
        admin.createTeacher("Bob", 23, User.Gender.MALE);

        Teacher teacher = new Teacher("Bob", 23, User.Gender.MALE);
        teacher.setTeacherId(1);

        Assertions.assertTrue(CourseManagementSystem.users.contains(teacher));
    }

    @Test
    public void createStudentTest() {
        Administrator admin = new Administrator("xoxo", 56, User.Gender.MALE);
        admin.createStudent("bob", 45, User.Gender.MALE);

        Student student = new Student("bob", 45, User.Gender.MALE);
        student.setStudentId(1);

        Assertions.assertTrue(CourseManagementSystem.users.contains(student));
    }

    @Test
    public void importUserTest() {
        Administrator admin = new Administrator("bob", 30, User.Gender.MALE);
        admin.importUsers();

        Teacher teacher = new Teacher("Marvin", 23, User.Gender.MALE);
        teacher.setTeacherId(1);
        Administrator administrator = new Administrator("Josh", 56, User.Gender.MALE);
        administrator.setAdminId(2);
        Student student = new Student("Jack", 12, User.Gender.MALE);
        student.setStudentId(1);

        Assertions.assertTrue(CourseManagementSystem.users.contains(teacher));
        Assertions.assertTrue(CourseManagementSystem.users.contains(administrator));
        Assertions.assertTrue(CourseManagementSystem.users.contains(student));
    }

    @Test
    public void createCourseTest1() {
        Administrator admin = new Administrator("bob", 30, User.Gender.MALE);
        Teacher teacher = new Teacher("josh", 34, User.Gender.MALE);
        admin.createCourse("english", "english", teacher, new TreeMap<>(), 30);

        Course expected = new Course("english", "english", teacher, new TreeMap<>(), 30);
        expected.setCourseId(1);

        Assertions.assertTrue(CourseManagementSystem.courses.contains(expected));
        Assertions.assertTrue(teacher.getCourses().contains(expected));
    }

    @Test
    public void createCourseTest2() {
        Administrator admin = new Administrator("bob", 30, User.Gender.MALE);
        Teacher teacher = new Teacher("josh", 34, User.Gender.MALE);
        admin.createCourse("english", "english", teacher, new TreeMap<>(), 30);

        admin.createCourse("english", "english", teacher, new TreeMap<>(), 30);

        Course expected = new Course("english", "english", teacher, new TreeMap<>(), 30);

        Assertions.assertFalse(CourseManagementSystem.courses.contains(expected));
        Assertions.assertFalse(teacher.getCourses().contains(expected));
    }

}
