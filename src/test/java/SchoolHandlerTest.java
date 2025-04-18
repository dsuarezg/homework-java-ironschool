import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class SchoolHandlerTest {
    Student student;
    Course course;
    public static Map<String, Teacher> teachers;
    public static Map<String, Course> courses;
    public static Map<String, Student> students;

    @BeforeEach
    void setUp() {
        teachers = new HashMap<>();
        courses = new HashMap<>();
        students = new HashMap<>();
        // Initialize the SchoolHandler instance and any necessary data
        // This method will run before each test
        student = new Student("Jane Doe", "123 Main St", "jane.doe@example.com");
        course = new Course("Mathematics", 150.0);


    }

    @Test
    void createSchool() {

    }

    @Test
    @DisplayName("Emulate user input for creating a student")
    void createStudent() {
        String simulatedInput = "Jane Doe\n123 Main St\njane.doe@example.com\n";
        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        Student newStudent = SchoolHandler.createStudent();

        assertNotNull(newStudent);
        assertEquals("Jane Doe", newStudent.getName());
        assertEquals("123 Main St", newStudent.getAddress());
        assertEquals("jane.doe@example.com", newStudent.getEmail());
    }

    @Test
    @DisplayName("Emulate user input for creating a course")
    void createCourse() {
        String simulatedInput = "Mathematics\n150.0\n";
        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        Course newCourse = SchoolHandler.createCourse();

        assertNotNull(newCourse);
        assertEquals("Mathematics", newCourse.getName());
        assertEquals(150.0, newCourse.getPrice());
    }

    @Test
    @DisplayName("Emulate user input for creating a teacher")
    void createTeacher() {
        String simulatedInput = "John Doe\n50000.0\n";
        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        Teacher newTeacher = SchoolHandler.createTeacher();

        assertNotNull(newTeacher);
        assertEquals("John Doe", newTeacher.getName());
        assertEquals(50000.0, newTeacher.getSalary());
    }

    @Test
    @DisplayName("Emulate Command input for enroll a student to a course")
    void enrollStudentToCourse() {

        SchoolHandler.students.put(student.getStudentId(), student);
        SchoolHandler.courses.put(course.getCourseId(), course);

        SchoolHandler.enrollStudentToCourse(student.getStudentId(), course.getCourseId());

        assertTrue(course.getStudents().contains(student));
        assertEquals(course, student.getCourse());
        //assertEquals(150.0, course.getMoneyEarned());
    }

    @Test
    void assignTeacherToCourse() {
    }

    @Test
    void showAllCourses() {


    }

    @Test
    void lookupCourse() {
    }

    @Test
    void showAllStudents() {
    }

    @Test
    void lookupStudent() {
    }

    @Test
    void showAllTeachers() {
    }

    @Test
    void lookupTeacher() {
    }

    @Test
    void showProfits() {

    }

    @Test
    void showStudentsFromCourse() {
    }

    @Test
    void showTotalMoneyEarned() {
    }

    @Test
    void showTotalSalariesPaid() {
    }
}