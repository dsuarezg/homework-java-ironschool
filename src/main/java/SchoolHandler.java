import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class SchoolHandler {
    static Scanner scanner = new Scanner(System.in);
    static String schoolName;
    static HashMap<String,Teacher> teachers = new HashMap<>();
    static HashMap<String,Course> courses = new HashMap<>();
    static HashMap<String,Student> students = new HashMap<>();

    // create School
    public static void createSchool(){
        System.out.println("Introduce l nombre de la escuela");
        schoolName=scanner.nextLine();
        System.out.println("indica el numero de profesores");
        int numberTeacher=scanner.nextInt();
        for (int i = 0; i < numberTeacher; i++) {
            Teacher teacher=createTeacher();
            teachers.put(teacher.getTeacherId(),teacher);
        }
        System.out.println("Introduce el numero de cursos que quieres añadir");
        int numberOfCourses=scanner.nextInt();
        for (int i = 0; i < numberOfCourses; i++) {
            Course course=createCourse();
            courses.put(course.getCourseId(), course);
        }

        System.out.println("Introduce el numero de Estudiantes");
        int numberOfStudent=scanner.nextInt();
        for (int i = 0; i < numberOfStudent; i++) {
            Student student=createStudent();
            students.put(student.getStudentId(),student);
        }



    }
}
