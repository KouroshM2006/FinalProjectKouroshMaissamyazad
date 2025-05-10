package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Course extends AcademicItem {
    private long courseId;
    private Teacher teacher;
    private List<Student> students;
    private Map<LocalDate, LocalTime> dateAndTime;
    private int capacity;

    private static long nextId = 1;

    //constructor with auto increasing ID
    public Course(String title, String description, Teacher teacher, Map<LocalDate, LocalTime> dateAndTime, int capacity) {
        super(title, description);
        this.courseId = nextId++;
        this.teacher = teacher;
        this.students = new ArrayList<>();
        this.dateAndTime = dateAndTime;
        this.capacity = capacity;
    }

    /**
     * Course's version of getType
     *
     * @return "Course"
     */
    @Override
    public String getType() {
        return "Course";
    }

    /**
     * adds a student to the students list if the student is not already registered
     *
     * @param student given student to add
     * @return boolean value on whether the student was added or not
     */
    public void addStudent(Student student) {
        if (this.students.size() < capacity) {
            students.add(student);
            student.getCourses().add(this);
            System.out.println("Student successfully enrolled");
        } else {
            System.out.println("the maximum capacity of students has been reached for this course");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return courseId == course.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", teacher=" + teacher.getName() +
                ", students=" + students.stream()
                .map(student -> "(Id: " + student.getStudentId() + ", name: " + student.getName() + ")")
                .collect(Collectors.joining(", ")) +
                ", dateAndTime=" + dateAndTime +
                ", capacity=" + capacity +
                '}' + super.toString();
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Map<LocalDate, LocalTime> getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Map<LocalDate, LocalTime> dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static long getNextId() {
        return nextId;
    }

    public static void setNextId(long nextId) {
        Course.nextId = nextId;
    }

    //CourseComparator subclass
    public static class CourseComparator implements Comparator<Course> {
        private String type;

        public CourseComparator(String type) {
            this.type = type;
        }

        /**
         * compares Course classes based on a given parameter
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return whether the compared object should go first, last or order does not matter
         */
        @Override
        public int compare(Course o1, Course o2) {
            switch (type.toLowerCase()) {
                case "title":
                    return o1.title.compareTo(o2.title);
                case "enrollment count":
                    return o1.getStudents().size() - o2.getStudents().size();
                case "capacity" :
                    return o1.getCapacity() - o2.getCapacity();
                default:
                    return (int) o1.getCourseId() - (int) o2.getCourseId();
            }
        }

    }
}

