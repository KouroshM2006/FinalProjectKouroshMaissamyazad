import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class StudentTest {

    @Test
    public void testDownloadSchedule() {
        Student student = new Student("bob", 22, User.Gender.MALE);
        Map<LocalDate, LocalTime> map = new TreeMap<>();
        map.put(LocalDate.now(), LocalTime.now());
        Teacher teacher = new Teacher("john", 56, User.Gender.MALE);
        student.getSchedule().add(new Course("english", "xxxx", teacher, new ArrayList<>(), map));

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
}
