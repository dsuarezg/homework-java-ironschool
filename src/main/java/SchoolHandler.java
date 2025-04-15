import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
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

    public static void enrollStudentToCourse(String studentId, String courseId) throws NoSuchElementException{
        if (students.containsKey(studentId) && courses.containsKey(courseId)){
            Course course=courses.get(courseId);
            Student student=students.get(studentId);
            course.enrollStudent(student);
            course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());
            student.setCourse(course);
        }else{
            throw new NoSuchElementException();
        }


    }

    public static void assignTeacherToCourse(String teacherID, String courseID) throws NoSuchElementException{
        if (teachers.containsKey(teacherID) && courses.containsKey(courseID)){
            Course course=courses.get(courseID);
            Teacher teacher=teachers.get(teacherID);
            course.setTeacher(teacher);
        }else{
            throw new NoSuchElementException();
        }
    }

    public static void showAllCourses(){
        System.out.println("Courses");
        for (String courseId : courses.keySet()) {
            Course course = courses.get(courseId);
            System.out.println(course.getName());
        }
    }

    public void lookupCourse(String courseID) throws NoSuchElementException{
        if (courses.containsKey(courseID)){
            Course course=courses.get(courseID);
            System.out.println("Course ID: "+course.getCourseId());
            System.out.println("Course Name: "+course.getName());
            System.out.println("Course Price: "+course.getPrice());
            System.out.println("Teacher: "+course.getTeacher().getName());
            System.out.println("Students: ");
            for (Student student : course.getStudents()) {
                System.out.println(student.getName());
            }
        }else{
            throw new NoSuchElementException();
        }

    }

    public void showAllStudents(){
        System.out.println("Students");
        for (String studentId : students.keySet()) {
            Student student = students.get(studentId);
            System.out.println(student.getName());
        }
    }

    public void lookupStudent(String studentID) throws NoSuchElementException{
        if (students.containsKey(studentID)){
            Student student=students.get(studentID);
            System.out.println("Student ID: "+student.getStudentId());
            System.out.println("Student Name: "+student.getName());
            System.out.println("Student Address: "+student.getAddress());
            System.out.println("Student Email: "+student.getEmail());
            System.out.println("Course: "+student.getCourse().getName());
        }else{
            throw new NoSuchElementException();
        }


    }

    public void showAllTeachers(){
        System.out.println("Teachers");
        for (String teacherId : teachers.keySet()) {
            Teacher teacher = teachers.get(teacherId);
            System.out.println(teacher.getName());
        }
    }

    public void lookupTeacher(String teacherID) throws NoSuchElementException{
        if (teachers.containsKey(teacherID)){
            Teacher teacher=teachers.get(teacherID);
            System.out.println("Teacher ID: "+teacher.getTeacherId());
            System.out.println("Teacher Name: "+teacher.getName());
            System.out.println("Teacher Salary: "+teacher.getSalary());
        }else{
            throw new NoSuchElementException();
        }

    }

    public void showProfits(){

        int totalEarned=0;
        int totalSalaries=0;
        for (String courseId : courses.keySet()) {
            Course course = courses.get(courseId);
            totalEarned += course.getMoneyEarned();

        }
        for (String teacherId : teachers.keySet()) {
            Teacher teacher = teachers.get(teacherId);
            totalSalaries += teacher.getSalary();

        }
        System.out.println("Profits : " + (totalEarned - totalSalaries));
    }

    public void showStudentsFromCourse(String courseID) throws NoSuchElementException{
        if (courses.containsKey(courseID)){
            Course course=courses.get(courseID);
            System.out.println("Students from course: "+course.getName());
            for (Student student : course.getStudents()) {
                System.out.println(student.getName());
            }
        }else{
            throw new NoSuchElementException();
        }

    }

    public void showTotalMoneyEarned(){
        int totalEarned=0;
        for (String courseId : courses.keySet()) {
            Course course = courses.get(courseId);
            totalEarned += course.getMoneyEarned();
        }
        System.out.println("Total Money Earned: " + totalEarned);
    }

    public void showTotalSalariesPaid(){
        int totalSalaries=0;
        for (String teacherId : teachers.keySet()) {
            Teacher teacher = teachers.get(teacherId);
            totalSalaries += teacher.getSalary();
        }
        System.out.println("Total Salaries Paid: " + totalSalaries);
    }
}
