import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class SchoolHandler {
    static Scanner scanner = new Scanner(System.in);
    static String schoolName;
    static HashMap<String,Teacher> teachers = new HashMap<>();
    static HashMap<String,Course> courses = new HashMap<>();
    static HashMap<String,Student> students = new HashMap<>();

    // create School Course and Student
    public static void createSchool() throws IllegalArgumentException{
        System.out.println("Introduce l nombre de la escuela");
        schoolName=scanner.nextLine();
        // se debe de guardar schoolName
        System.out.println("indica el numero de profesores");
        int numberTeacher=Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberTeacher; i++) {
            Teacher teacher=createTeacher();
            teachers.put(teacher.getTeacherId(),teacher);
        }
        System.out.println("Introduce el numero de cursos que quieres añadir");
        int numberOfCourses=Integer.parseInt(scanner.nextLine());
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

    private static Student createStudent() {
        System.out.println("Name of the Student");
        String name=scanner.nextLine();
        System.out.println("Address of the Student");
        String address=scanner.nextLine();
        System.out.println("Email of the Student");
        String email=scanner.nextLine();
        return new Student(name,address,email);
    }

    public static Course createCourse() {
        System.out.println("Name of the Course");
        String name=scanner.nextLine();
        System.out.println("Price of the Course");
        Double salary=Double.parseDouble(scanner.nextLine());
        return new Course(name,salary);
    }

    public static Teacher createTeacher() {
        //
        System.out.println("Name of the Teacher");
        String name=scanner.nextLine();
        System.out.println("salary of the Teacher");
        Double salary=Double.parseDouble(scanner.nextLine());
        return new Teacher(name,salary);
    }
}
