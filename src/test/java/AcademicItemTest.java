import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

public class AcademicItemTest {
    @Test
    public void getTypeTest() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Course course = new Course("english", "xxxx", teacher, new ArrayList<>(), new TreeMap<>());
        Assertions.assertEquals(course.getType(), "Course");
    }


}
