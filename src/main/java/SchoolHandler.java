import java.util.*;

public class SchoolHandler {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    static Scanner scanner = new Scanner(System.in);
    static String schoolName;
    static HashMap<String,Teacher> teachers = new HashMap<>();
    static HashMap<String,Course> courses = new HashMap<>();
    static HashMap<String,Student> students = new HashMap<>();

    private static void showAcceptedCommands() {
        List<String> validCommands = Arrays.asList(
                "ENROLL [STUDENT_ID] [COURSE_ID]",
                "ASSIGN [TEACHER_ID] [COURSE_ID]",
                "LOOKUP COURSE [COURSE_ID]",
                "LOOKUP TEACHER [TEACHER_ID]",
                "LOOKUP STUDENT [STUDENT_ID]",
                "SHOW COURSES",
                "SHOW TEACHERS",
                "SHOW STUDENTS",
                "SHOW STUDENTS [COURSE_ID]",
                "SHOW MONEY EARNED",
                "SHOW MONEY SPENT",
                "SHOW PROFIT"
        );

        System.out.println("Accepted commands:");
        for (String command : validCommands) {
            System.out.println("- " + command);
        }
    }

    public static void commandHandler() throws IllegalArgumentException {
        showAcceptedCommands();

        System.out.println("Enter a command: ");
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("No input provided.");
        }
        String option = scanner.nextLine().trim();

        String[] parts = option.split(" ");
        if (parts.length == 0) {
            throw new IllegalArgumentException("Invalid command. Please try again.");
        }

        CommandActions actionType = CommandActions.valueOf(parts[0]);
        switch (actionType) {
            case ENROLL:
                if (parts.length == 3) {
                    String studentId = parts[1];
                    String courseId = parts[2];
                    if (students.containsKey(studentId) && courses.containsKey(courseId)) {
                        courses.get(courseId).enrollStudent(students.get(studentId));
                        System.out.println("Student enrolled successfully.");
                    } else {
                        throw new IllegalArgumentException("Invalid STUDENT_ID or COURSE_ID.");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid command format. Usage: ENROLL [STUDENT_ID] [COURSE_ID]");
                }
                break;

            case ASSIGN:
                if (parts.length == 3) {
                    String teacherId = parts[1];
                    String courseId = parts[2];
                    if (teachers.containsKey(teacherId) && courses.containsKey(courseId)) {
                        courses.get(courseId).setTeacher(teachers.get(teacherId));
                        System.out.println("Teacher assigned successfully.");
                    } else {
                        throw new IllegalArgumentException("Invalid TEACHER_ID or COURSE_ID.");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid command format. Usage: ASSIGN [TEACHER_ID] [COURSE_ID]");
                }
                break;

            case SHOW:
                if (parts.length == 3) {
                    if ((parts[1].equals("MONEY")) && (parts[2].equals("EARNED"))) {
                        showMoneyEarned(courses);
                        break;
                    } else if ((parts[1].equals("MONEY")) && (parts[2].equals("SPENT"))) {
                        showMoneySpent(teachers);
                        break;
                    } else {
                        String courseId = parts[2];
                        showStudentsByCourse(courseId);
                        break;
                    }
                }
                if (parts.length == 2) {
                    String classTypeInput = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
                    CommandOptions classType = CommandOptions.fromString(classTypeInput);
                    switch (classType) {
                        case COURSES:
                            showAllCourses(courses);
                            break;
                        case STUDENTS:
                            showAllStudents(students);
                            break;
                        case TEACHERS:
                            showAllTeachers(teachers);
                            break;
                        case PROFIT:
                            showProfit(courses, teachers);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid Command. Options: COURSES, STUDENTS, TEACHERS or PROFIT.");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid command format. Usage: SHOW [COURSES|STUDENTS|TEACHERS|PROFIT]");
                }
                break;

            case LOOKUP:
                if (parts.length == 3) {
                    String lookupType = parts[1];
                    String id = parts[2];
                    switch (lookupType) {
                        case "COURSE":
                            if (courses.containsKey(id)) {
                                lookupCourse(courses, id);
                            } else {
                                throw new IllegalArgumentException("Invalid COURSE_ID.");
                            }
                            break;
                        case "STUDENT":
                            if (students.containsKey(id)) {
                                lookupStudent(students, id);
                            } else {
                                throw new IllegalArgumentException("Invalid STUDENT_ID.");
                            }
                            break;
                        case "TEACHER":
                            if (teachers.containsKey(id)) {
                                lookupTeacher(teachers, id);
                            } else {
                                throw new IllegalArgumentException("Invalid TEACHER_ID.");
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid LOOKUP command. Options: COURSE, STUDENT, TEACHER.");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid command format. Usage: LOOKUP [COURSE|STUDENT|TEACHER] [ID]");
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown command. Please try again.");
        }
    }

    private static void continueMenu() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void menuDisplay() {

        System.out.println(GREEN + "\n======== 🏫 Iron School ========" + RESET);
        System.out.println(CYAN + "1. 🎓 Initialize School" + RESET);
        System.out.println(CYAN + "2. 📚 Create individual entities" + RESET);
        System.out.println(CYAN + "3. 🖥️ Use accepted commands" + RESET); // Icono de consola
        System.out.println(CYAN + "4. 📂 Load data from file" + RESET);
        System.out.println(CYAN + "5. 💾 Save data to file" + RESET);
        System.out.println(GREEN + "---------------------------------" + RESET);
        System.out.println(RED + "6. ❌ Exit" + RESET);
        System.out.println(GREEN + "=================================" + RESET);
        System.out.print("Choose an option: ");
    }

    public static void menuHandler() {

        boolean running = true;

        while (running) {
            try {
                menuDisplay();
                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        createSchool();
                        continueMenu();
                        break;
                    case "2":
                        subMenuHandler();
                        break;
                    case "3":
                        commandHandler();
                        continueMenu();
                        break;
                    case "4":
                        FileHandler.loadData();
                        break;
                    case "5":
                        FileHandler.saveData();
                        break;
                    case "6", "exit":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect argument: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Element not found: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    // create School Course and Student
    public static void createSchool() throws IllegalArgumentException{
        System.out.println("Enter the name of the school: ");
        schoolName = scanner.nextLine();

        System.out.println("How many teachers do you want to add to the school?");
        int numberOfTeachers = Integer.parseInt(scanner.nextLine());
        teachers = new HashMap<>(numberOfTeachers);
        for (int i = 0; i < numberOfTeachers; i++) {
            createTeacher(teachers, scanner);
        }

        System.out.println("How many courses do you want to add?");
        int numberOfCourses = Integer.parseInt(scanner.nextLine());
        courses = new HashMap<>(numberOfCourses);
        for (int i = 0; i < numberOfCourses; i++) {
            createCourse(courses, scanner);
        }

        System.out.println("How many students do you want to add?");
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        students = new HashMap<>(numberOfStudents);
        for (int i = 0; i < numberOfStudents; i++) {
            createStudent(students, scanner);
        }
    }

    public static void subMenuDisplay() {

        System.out.println(GREEN + "\n======== 🏫 Iron School ========" + RESET);
        System.out.println(CYAN + "1. 🧑‍🎓 Add new Student" + RESET);
        System.out.println(CYAN + "2. 👩‍🏫 Add new Teacher" + RESET);
        System.out.println(CYAN + "3. 📘 Add new Course" + RESET);
        System.out.println(GREEN + "---------------------------------" + RESET);
        System.out.println(YELLOW + "4. 🔙 Return to main menu" + RESET);
        System.out.println(GREEN + "=================================" + RESET);
        System.out.print("Choose an option: ");
    }

    public static void subMenuHandler() {

        boolean running = true;

        while (running) {
            subMenuDisplay();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    createStudent(students, scanner);
                    continue;
                case "2":
                    createTeacher(teachers, scanner);
                    continue;
                case "3":
                    createCourse(courses, scanner);
                    continue;
                case "4", "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // CREO QUE PODRÍAMOS METER AL OBJETO EN EL MAP DESDE CREATE Y NO DESDE EL FOR COMO ARRIBA
    // DE CARA A LOS TESTS ME PARECE MÁS SENCILLO

    public static void createStudent(Map<String, Student> students, Scanner scanner) {
        System.out.print("Enter the name of the student: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        System.out.print("Enter the address of the student: ");
        String address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            throw new IllegalArgumentException("Student address cannot be empty.");
        }

        System.out.print("Enter the email of the student: ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Student email cannot be empty.");
        }

        Student student = new Student(name, address, email);
        students.put(student.getStudentId(), student);
        System.out.println("Student created successfully!");
    }

    public static void createCourse(Map<String, Course> courses, Scanner scanner) {
        System.out.print("Enter the name of the course: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }

        System.out.print("Enter the price of the course: ");
        String priceInput = scanner.nextLine().trim();
        try {
            double price = Double.parseDouble(priceInput);
            Course course = new Course(name, price);
            courses.put(course.getCourseId(), course);
            System.out.println("Course created successfully!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid course price format.");
        }
    }

    public static void createTeacher(Map<String, Teacher> teachers, Scanner scanner) {
        System.out.print("Enter the name of the teacher: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be empty.");
        }

        System.out.print("Enter the salary of the teacher: ");
        String salaryInput = scanner.nextLine().trim();
        try {
            double salary = Double.parseDouble(salaryInput);
            Teacher teacher = new Teacher(name, salary);
            teachers.put(teacher.getTeacherId(), teacher);
            System.out.println("Teacher created successfully!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid salary format.");
        }
    }

//    public static void enrollStudentToCourse(String studentId, String courseId) throws NoSuchElementException{
//        if (students.containsKey(studentId) && courses.containsKey(courseId)){
//            Course course=courses.get(courseId);
//            Student student=students.get(studentId);
//            course.enrollStudent(student);
//            course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());
//            student.setCourse(course);
//        }else{
//            throw new NoSuchElementException();
//        }
//
//
//    }
//
//    public static void assignTeacherToCourse(String teacherID, String courseID) throws NoSuchElementException{
//        if (teachers.containsKey(teacherID) && courses.containsKey(courseID)){
//            Course course=courses.get(courseID);
//            Teacher teacher=teachers.get(teacherID);
//            course.setTeacher(teacher);
//        }else{
//            throw new NoSuchElementException();
//        }
//    }
//
//    public static void showAllCourses(){
//        System.out.println("Courses");
//        for (String courseId : courses.keySet()) {
//            Course course = courses.get(courseId);
//            System.out.println(course.getName());
//        }
//    }
//
//    public static void lookupCourse(String courseID) throws NoSuchElementException{
//        if (courses.containsKey(courseID)){
//            Course course=courses.get(courseID);
//            System.out.println("Course ID: "+course.getCourseId());
//            System.out.println("Course Name: "+course.getName());
//            System.out.println("Course Price: "+course.getPrice());
//            System.out.println("Teacher: "+course.getTeacher().getName());
//            System.out.println("Students: ");
//            for (Student student : course.getStudents()) {
//                System.out.println(student.getName());
//            }
//        }else{
//            throw new NoSuchElementException();
//        }
//
//    }
//
//    public static void showAllStudents(){
//        System.out.println("Students");
//        for (String studentId : students.keySet()) {
//            Student student = students.get(studentId);
//            System.out.println(student.getName());
//        }
//    }
//
//    public static void lookupStudent(String studentID) throws NoSuchElementException{
//        if (students.containsKey(studentID)){
//            Student student=students.get(studentID);
//            System.out.println("Student ID: "+student.getStudentId());
//            System.out.println("Student Name: "+student.getName());
//            System.out.println("Student Address: "+student.getAddress());
//            System.out.println("Student Email: "+student.getEmail());
//            System.out.println("Course: "+student.getCourse().getName());
//        }else{
//            throw new NoSuchElementException();
//        }
//
//
//    }
//
//    public static void showAllTeachers(){
//        System.out.println("Teachers");
//        for (String teacherId : teachers.keySet()) {
//            Teacher teacher = teachers.get(teacherId);
//            System.out.println(teacher.getName());
//        }
//    }
//
//    public static void lookupTeacher(String teacherID) throws NoSuchElementException{
//        if (teachers.containsKey(teacherID)){
//            Teacher teacher=teachers.get(teacherID);
//            System.out.println("Teacher ID: "+teacher.getTeacherId());
//            System.out.println("Teacher Name: "+teacher.getName());
//            System.out.println("Teacher Salary: "+teacher.getSalary());
//        }else{
//            throw new NoSuchElementException();
//        }
//
//    }
//
//    public static void showProfits(){
//
//        int totalEarned=0;
//        int totalSalaries=0;
//        for (String courseId : courses.keySet()) {
//            Course course = courses.get(courseId);
//            totalEarned += course.getMoneyEarned();
//
//        }
//        for (String teacherId : teachers.keySet()) {
//            Teacher teacher = teachers.get(teacherId);
//            totalSalaries += teacher.getSalary();
//
//        }
//        System.out.println("Profits : " + (totalEarned - totalSalaries));
//    }
//
//    public static void showStudentsFromCourse(String courseID) throws NoSuchElementException{
//        if (courses.containsKey(courseID)){
//            Course course=courses.get(courseID);
//            System.out.println("Students from course: "+course.getName());
//            for (Student student : course.getStudents()) {
//                System.out.println(student.getName());
//            }
//        }else{
//            throw new NoSuchElementException();
//        }
//
//    }
//
//    public static void showTotalMoneyEarned(){
//        int totalEarned=0;
//        for (String courseId : courses.keySet()) {
//            Course course = courses.get(courseId);
//            totalEarned += course.getMoneyEarned();
//        }
//        System.out.println("Total Money Earned: " + totalEarned);
//    }
//
//    public static void showTotalSalariesPaid(){
//        int totalSalaries=0;
//        for (String teacherId : teachers.keySet()) {
//            Teacher teacher = teachers.get(teacherId);
//            totalSalaries += teacher.getSalary();
//        }
//        System.out.println("Total Salaries Paid: " + totalSalaries);
//    }


    ////////////////////////////////////////////////////////



    public static void showAllStudents(Map<String, Student> students) throws NullPointerException {
        for (Student student : students.values()) {
            System.out.println(CYAN + "Student ID: " + GREEN + student.getStudentId() + RESET);
            System.out.println(CYAN + "Name: " + GREEN + student.getName() + RESET);
            System.out.println(CYAN + "Address: " + GREEN + student.getAddress() + RESET);
            System.out.println(CYAN + "Email: " + GREEN + student.getEmail() + RESET);
            System.out.println(CYAN + "-----------------------------" + RESET);
        }
    }

    public static void showAllTeachers(Map<String, Teacher> teachers) throws NullPointerException {
        for (Teacher teacher : teachers.values()) {
            System.out.println(CYAN + "Teacher ID: " + GREEN + teacher.getTeacherId() + RESET);
            System.out.println(CYAN + "Name: " + GREEN + teacher.getName() + RESET);
            System.out.println(CYAN + "Salary: " + GREEN + teacher.getSalary() + RESET);
            System.out.println(CYAN + "-----------------------------" + RESET);
        }
    }

    public static void showAllCourses(Map<String, Course> courses) throws NullPointerException {
        for (Course course : courses.values()) {
            System.out.println(CYAN + "Course ID: " + GREEN + course.getCourseId() + RESET);
            System.out.println(CYAN + "Name: " + GREEN + course.getName() + RESET);
            System.out.println(CYAN + "Price: " + GREEN + course.getPrice() + RESET);
            System.out.println(CYAN + "Teacher: " + GREEN +
                    (course.getTeacher() != null ? course.getTeacher().getName() : "No teacher assigned") + RESET);
            System.out.println(CYAN + "Students enrolled: " + GREEN +
                    (course.getStudents() != null ? course.getStudents().size() : 0) + RESET);
            System.out.println(CYAN + "-----------------------------" + RESET);
        }
    }

    public static void showProfit(Map<String, Course> courses, Map<String, Teacher> teachers) throws NullPointerException {
        // Lambda expression (->)
        //double totalEarnings = courses.values().stream().mapToDouble(course -> course.getMoneyEarned()).sum();
        // Method reference (::)
        double totalEarnings = courses.values().stream().mapToDouble(Course::getMoneyEarned).sum();
        //double totalSalaries = teachers.values().stream().mapToDouble(teacher -> teacher.getSalary()).sum();
        double totalSalaries = teachers.values().stream().mapToDouble(Teacher::getSalary).sum();
        double totalProfit = totalEarnings - totalSalaries;
        if (totalProfit < 0) {
            System.out.println(CYAN + "Profit: " + RED + (totalProfit) + RESET);
        }else{
            System.out.println(CYAN + "Profit: " + GREEN + (totalProfit) + RESET);
        }
    }

    public static void showStudentsByCourse(String courseId) throws NoSuchElementException, NullPointerException {
        if (courses.containsKey(courseId)) {
            Course course = courses.get(courseId);
            System.out.println(CYAN + "Students enrolled in " + GREEN + course.getName() + CYAN + ":" + RESET);
            for (Student student : course.getStudents()) {
                System.out.println(CYAN + "Student ID: " + GREEN + student.getStudentId() + RESET);
                System.out.println(CYAN + "Name: " + GREEN + student.getName() + RESET);
                System.out.println(CYAN + "Address: " + GREEN + student.getAddress() + RESET);
                System.out.println(CYAN + "Email: " + GREEN + student.getEmail() + RESET);
                System.out.println(CYAN + "-----------------------------" + RESET);
            }
        } else {
            throw new NoSuchElementException("Course not found.");
        }
    }

    public static void showMoneySpent(Map<String, Teacher> teachers) {
        Double totalSpent = 0.0;
        for (Teacher teacher : teachers.values()) {
            totalSpent += teacher.getSalary();
        }
        System.out.println("Total money spent on teachers: " + totalSpent);
    }

    public static void showMoneyEarned(Map<String, Course> courses) {
        Double totalEarned = 0.0;
        for (Course course : courses.values()) {
            totalEarned += course.getMoneyEarned();
        }
        System.out.println("Total money earned from courses: " + totalEarned);
    }

    // LOOKUP METHODS
    public static void lookupCourse(Map<String, Course> courses, String courseId) throws NoSuchElementException {
        if (courses.containsKey(courseId)) {
            Course course = courses.get(courseId);
            System.out.println(course.toString());
        } else {
            throw new NoSuchElementException("Course not found.");
        }
    }

    public static void lookupStudent(Map<String, Student> students, String studentId) throws NoSuchElementException {
        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            System.out.println(student.toString());
        } else {
            throw new NoSuchElementException("Student not found.");
        }
    }

    public static void lookupTeacher(Map<String, Teacher> teachers, String teacherId) throws NoSuchElementException {
        if (teachers.containsKey(teacherId)) {
            Teacher teacher = teachers.get(teacherId);
            System.out.println(teacher.toString());
        } else {
            throw new NoSuchElementException("Teacher not found.");
        }
    }



}
