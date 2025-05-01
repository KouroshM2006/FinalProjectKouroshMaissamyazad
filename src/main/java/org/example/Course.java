package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Course extends AcademicItem {
    private long courseId;
    private Teacher teacher;
    private List<Student> students;
    private Map<LocalDate, LocalTime> DateAndTime;

    private static long nextId = 1;

    //constructor with auto increasing ID
    public Course(String title, String description, Teacher teacher, List<Student> students, Map<LocalDate, LocalTime> dateAndTime) {
        super(title, description);
        this.courseId = nextId++;
        this.teacher = teacher;
        this.students = students;
        DateAndTime = dateAndTime;
    }

    //all arguments constructor
    public Course(String title, String description, long courseId, Teacher teacher, List<Student> students, Map<LocalDate, LocalTime> dateAndTime) {
        super(title, description);
        this.courseId = courseId;
        this.teacher = teacher;
        this.students = students;
        DateAndTime = dateAndTime;
    }

    /**
     * Course's version of getType
     * @return "Course"
     */
    @Override
    public String getType() {
        return "Course";
    }

    /**
     * adds a student to the students list if the student is not already registered
     * @param student given student to add
     * @return boolean value on whether the student was added or not
     */
    public boolean AddStudent(Student student) {
        if (students.contains(student)) return false;
        students.add(student);
        return true;
    }

    // Todo: add CourseComparator class

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return courseId == course.courseId && Objects.equals(teacher, course.teacher) && Objects.equals(students, course.students) && Objects.equals(DateAndTime, course.DateAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseId, teacher, students, DateAndTime);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", teacher=" + teacher +
                ", students=" + students +
                ", DateAndTime=" + DateAndTime +
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
        return DateAndTime;
    }

    public void setDateAndTime(Map<LocalDate, LocalTime> dateAndTime) {
        DateAndTime = dateAndTime;
    }

    public static long getNextId() {
        return nextId;
    }

    public static void setNextId(long nextId) {
        Course.nextId = nextId;
    }
}
