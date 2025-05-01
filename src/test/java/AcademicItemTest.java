import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

public class AcademicItemTest {
    @Test
    public void getTypeTest() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Course course = new Course("english", "xxxx", teacher, new ArrayList<>(), new TreeMap<>());
        Assertions.assertEquals(course.getType(), "Course");
    }

    @Test
    public void compareToTest() {
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        Course c1 = new Course("english", "xxxx", teacher, new ArrayList<>(), new TreeMap<>());
        Course c2 = new Course("english", "xxxx", teacher, new ArrayList<>(), new TreeMap<>());
        Course c3 = new Course("english", "xxxx", teacher, new ArrayList<>(), new TreeMap<>());

        c1.setCreatedDate(LocalDate.now());
        c2.setCreatedDate(LocalDate.of(2025, 1, 3));
        c3.setCreatedDate(LocalDate.of(2024, 1, 3));

        List<AcademicItem> result = new ArrayList<>();
        result.add(c2);
        result.add(c3);
        result.add(c1);

        Collections.sort(result);

        List<AcademicItem> expected = new ArrayList<>();
        expected.add(c3);
        expected.add(c2);
        expected.add(c1);

        Assertions.assertEquals(expected, result);
    }


}
