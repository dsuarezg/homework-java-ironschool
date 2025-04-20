import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;

public class FileHandler {

    private static final Gson gson = new Gson();
    private static final String PATH = "src/main/resources/";
    private static final String TEACHERS = PATH + "teachers.json";
    private static final String STUDENTS = PATH + "students.json";
    private static final String COURSES = PATH + "courses.json";


    public static void saveTeachers() {
        try (Writer writer = new FileWriter(TEACHERS)) {
            gson.toJson(AppHandler.teachers.values(), writer);
            System.out.println("Data saved successfully to " + TEACHERS);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static void saveStudents() {
        try (Writer writer = new FileWriter(STUDENTS)) {
            gson.toJson(AppHandler.students.values(), writer);
            System.out.println("Data saved successfully to " + STUDENTS);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static void saveCourses() {
        try (Writer writer = new FileWriter(COURSES)) {
            gson.toJson(AppHandler.courses.values(), writer);
            System.out.println("Data saved successfully to " + COURSES);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadTeachers() {
        try (FileReader reader = new FileReader(TEACHERS)) {
            Type collectionType = new TypeToken<Collection<Teacher>>() {
            }.getType();
            Collection<Teacher> teachers = gson.fromJson(reader, collectionType);
            AppHandler.teachers = new HashMap<>();
            for (Teacher teacher : teachers) {
                AppHandler.teachers.put(teacher.getTeacherId(), teacher);
            }
            System.out.println("Teachers loaded successfully from " + TEACHERS);
        } catch (IOException e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
    }

    public static void loadStudents() {
        try (FileReader reader = new FileReader(STUDENTS)) {
            Type collectionType = new TypeToken<Collection<Student>>() {
            }.getType();
            Collection<Student> students = gson.fromJson(reader, collectionType);
            AppHandler.students = new HashMap<>();
            for (Student student : students) {
                AppHandler.students.put(student.getStudentId(), student);
            }
            System.out.println("Students loaded successfully from " + STUDENTS);
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }

    public static void loadCourses() {
        try (FileReader reader = new FileReader(COURSES)) {
            Type collectionType = new TypeToken<Collection<Course>>() {
            }.getType();
            Collection<Course> courses = gson.fromJson(reader, collectionType);
            AppHandler.courses = new HashMap<>();
            for (Course course : courses) {
                AppHandler.courses.put(course.getCourseId(), course);
            }
            System.out.println("Courses loaded successfully from " + COURSES);
        } catch (IOException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        }
    }

    public static void loadData() {
        loadTeachers();
        loadStudents();
        loadCourses();
    }

    public static void saveData() {
        saveTeachers();
        saveStudents();
        saveCourses();
    }

}