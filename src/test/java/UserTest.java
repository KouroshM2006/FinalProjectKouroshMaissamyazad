import org.example.Administrator;
import org.example.Student;
import org.example.Teacher;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void getRoleTest() {
        Student student = new Student("xxx", 12, User.Gender.MALE);
        Teacher teacher = new Teacher("xxx", 35, User.Gender.MALE);
        Administrator administrator = new Administrator("xxx", 56, User.Gender.MALE);

        Assertions.assertEquals(student.getRole(), "Student");
        Assertions.assertEquals(teacher.getRole(), "Teacher");
        Assertions.assertEquals(administrator.getRole(), "Administrator");
    }

}
