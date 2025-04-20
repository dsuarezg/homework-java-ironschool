import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppHandlerTest {

    private Map<String, Teacher> teachers;
    private Map<String, Student> students;
    private Map<String, Course> courses;

    @BeforeEach
    void setUp() {
        teachers = new HashMap<>();
        students = new HashMap<>();
        courses = new HashMap<>();
    }

    @Test
    @DisplayName("Test createTeacher with valid input")
    void createTeacher_validInput() {
        String input = "John Doe\n50000\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AppHandler.createTeacher(teachers, scanner);

        assertEquals(1, teachers.size());
        assertTrue(teachers.values().stream().anyMatch(t -> t.getName().equals("John Doe")));
    }

    @Test
    @DisplayName("Test createTeacher with empty name")
    void createTeacher_emptyName() {
        String input = "\n50000\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(IllegalArgumentException.class, () -> AppHandler.createTeacher(teachers, scanner));
    }

    @Test
    @DisplayName("Test createStudent with valid input")
    void createStudent_validInput() {
        String input = "Alice Johnson\n123 Main St\nalice@example.com\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AppHandler.createStudent(students, scanner);

        students.values().stream()
                .filter(student -> student.getName().equals("Alice Johnson"))
                .forEach(System.out::println);

        assertEquals(1, students.size());
        assertTrue(students.values().stream().anyMatch(s -> s.getEmail().equals("alice@example.com")));
    }

    @Test
    @DisplayName("Test createStudent with empty email")
    void createStudent_emptyEmail() {
        String input = "Alice Johnson\n123 Main St\n\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(IllegalArgumentException.class, () -> AppHandler.createStudent(students, scanner));
    }

    @Test
    @DisplayName("Test createCourse with valid input")
    void createCourse_validInput() {
        String input = "Mathematics\n200\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AppHandler.createCourse(courses, scanner);

        assertEquals(1, courses.size());
        assertTrue(courses.values().stream().anyMatch(c -> c.getName().equals("Mathematics")));
    }

    @Test
    @DisplayName("Test createCourse with invalid price")
    void createCourse_invalidPrice() {
        String input = "Mathematics\ninvalid_price\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(IllegalArgumentException.class, () -> AppHandler.createCourse(courses, scanner));
    }

    @Test
    @DisplayName("Test createTeacher with invalid salary")
    void createTeacher_invalidSalary() {
        String input = "John Doe\ninvalid_salary\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(IllegalArgumentException.class, () -> AppHandler.createTeacher(teachers, scanner));
    }

    @Test
    @DisplayName("Test createStudent with empty address")
    void createStudent_emptyAddress() {
        String input = "Alice Johnson\n\nalice@example.com\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(IllegalArgumentException.class, () -> AppHandler.createStudent(students, scanner));
    }

    @Test
    @DisplayName("Test createCourse with empty name")
    void createCourse_emptyName() {
        String input = "\n200\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertThrows(IllegalArgumentException.class, () -> AppHandler.createCourse(courses, scanner));
    }

    @Test
    @DisplayName("Test lookupTeacher with valid ID")
    void lookupTeacher_validId() {
        Teacher teacher = new Teacher("John Doe", 50000.0);
        teachers.put(teacher.getTeacherId(), teacher);

        assertDoesNotThrow(() -> AppHandler.lookupTeacher(teachers, teacher.getTeacherId()));
    }

    @Test
    @DisplayName("Test lookupTeacher with invalid ID")
    void lookupTeacher_invalidId() {
        assertThrows(NoSuchElementException.class, () -> AppHandler.lookupTeacher(teachers, "invalid_id"));
    }

    @Test
    @DisplayName("Test lookupStudent with valid ID")
    void lookupStudent_validId() {
        Student student = new Student("Alice Johnson", "123 Main St", "alice@example.com");
        String studentId = student.getStudentId();

        students.put(studentId, student);


        AppHandler.lookupStudent(students, studentId);

        assertDoesNotThrow(() -> AppHandler.lookupStudent(students, studentId));
    }

    @Test
    @DisplayName("Test lookupStudent with invalid ID")
    void lookupStudent_invalidId() {
        assertThrows(NoSuchElementException.class, () -> AppHandler.lookupStudent(students, "invalid_id"));
    }

    @Test
    @DisplayName("Test lookupCourse with valid ID")
    void lookupCourse_validId() {
        Course course = new Course("Mathematics", 200.0);
        courses.put(course.getCourseId(), course);

        assertDoesNotThrow(() -> AppHandler.lookupCourse(courses, course.getCourseId()));
    }

    @Test
    @DisplayName("Test lookupCourse with invalid ID")
    void lookupCourse_invalidId() {
        assertThrows(NoSuchElementException.class, () -> AppHandler.lookupCourse(courses, "invalid_id"));
    }

    @Test
    @DisplayName("Test showProfit with no data")
    void showProfit_noData() {
        assertDoesNotThrow(() -> AppHandler.showProfit(courses, teachers));
    }

    @Test
    @DisplayName("Test showProfit with data")
    void showProfit_withData() {
        Teacher teacher = new Teacher("John Doe", 50000.0);
        teachers.put(teacher.getTeacherId(), teacher);

        Course course = new Course("Mathematics", 200.0);
        course.setMoneyEarned(1000.0);
        courses.put(course.getCourseId(), course);

        //assertDoesNotThrow(AppHandler::showProfit);
        assertDoesNotThrow(() -> AppHandler.showProfit(courses, teachers));
    }

    @Test
    @DisplayName("Test showStudentsByCourse with valid course ID")
    void showStudentsByCourse_validCourseId() {
        Course course = new Course("Mathematics", 200.0);
        String courseId = course.getCourseId();
        Student student = new Student("Alice Johnson", "123 Main St", "alice@example.com");
        course.enrollStudent(student);
        AppHandler.courses.put(courseId, course);
        assertDoesNotThrow(() -> AppHandler.showStudentsByCourse(course.getCourseId()));
    }

    @Test
    @DisplayName("Test showStudentsByCourse with invalid course ID")
    void showStudentsByCourse_invalidCourseId() {
        assertThrows(NoSuchElementException.class, () -> AppHandler.showStudentsByCourse("invalid_id"));
    }

    @Test
    @DisplayName("Test showMoneyEarned with data")
    void showMoneyEarned_withData() {
        Course course1 = new Course("Mathematics", 200.0);
        course1.setMoneyEarned(1000.0);
        courses.put(course1.getCourseId(), course1);

        Course course2 = new Course("Science", 300.0);
        course2.setMoneyEarned(1500.0);
        courses.put(course2.getCourseId(), course2);

        assertDoesNotThrow(() -> AppHandler.showMoneyEarned(courses));
    }

    @Test
    @DisplayName("Test showMoneySpent with data")
    void showMoneySpent_withData() {
        Teacher teacher1 = new Teacher("John Doe", 50000.0);
        teachers.put(teacher1.getTeacherId(), teacher1);

        Teacher teacher2 = new Teacher("Jane Smith", 60000.0);
        teachers.put(teacher2.getTeacherId(), teacher2);

        assertDoesNotThrow(() -> AppHandler.showMoneySpent(teachers));
    }
}