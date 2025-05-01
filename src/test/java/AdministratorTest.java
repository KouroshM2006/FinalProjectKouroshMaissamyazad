import org.example.Administrator;
import org.example.Student;
import org.example.Teacher;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class AdministratorTest {
    @Test
    public void createTeacherTest() {
        Administrator admin = new Administrator("xoxo", 56, User.Gender.MALE);

        User expected = new Teacher("bob", 45, User.Gender.MALE);
        User result = admin.createTeacher("bob", 45, User.Gender.MALE);

        Assertions.assertEquals(result.getName(), expected.getName());
        Assertions.assertEquals(result.getAge(), expected.getAge());
        Assertions.assertEquals(result.getGender(), expected.getGender());
        Assertions.assertEquals(result.getRole(), expected.getRole());
    }

    @Test
    public void createTest() {
        Administrator admin = new Administrator("xoxo", 56, User.Gender.MALE);

        User expected = new Student("bob", 45, User.Gender.MALE);
        User result = admin.createStudent("bob", 45, User.Gender.MALE);

        Assertions.assertEquals(result.getName(), expected.getName());
        Assertions.assertEquals(result.getAge(), expected.getAge());
        Assertions.assertEquals(result.getGender(), expected.getGender());
        Assertions.assertEquals(result.getRole(), expected.getRole());
    }


}
