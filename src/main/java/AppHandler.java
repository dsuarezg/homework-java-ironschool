import java.util.*;

public class AppHandler {
    static String schoolName;
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Teacher> teachers;
    static Map<String, Course> courses;
    static Map<String, Student> students;

    private static Teacher createTeacher() {
        System.out.println("Enter the name of the teacher: ");
        String name = scanner.nextLine();
        System.out.println("Enter the salary of the teacher: ");
        Double salary = Double.parseDouble(scanner.nextLine());
        return new Teacher(name, salary);
    }

    private static Course createCourse() {
        System.out.println("Enter the name of the course: ");
        String name = scanner.nextLine();
        System.out.println("Enter the price of the course: ");
        Double price = Double.parseDouble(scanner.nextLine());
        return new Course(name, price);
    }

    private static Student createStudent() {
        System.out.println("Enter the name of the student: ");
        String name = scanner.nextLine();
        System.out.println("Enter the address of the student: ");
        String address = scanner.nextLine();
        System.out.println("Enter the email of the student: ");
        String email = scanner.nextLine();
        return new Student(name, address, email);
    }

    // COMMANDS METHODS
    private static void showAcceptedCommands() {
        List<String> validCommands = Arrays.asList(
                "ENROLL [STUDENT_ID] [COURSE_ID]",
                "ASSIGN [TEACHER_ID] [COURSE_ID]",
                "SHOW COURSES",
                "LOOKUP COURSE [COURSE_ID]",
                "SHOW STUDENTS",
                "LOOKUP STUDENT [STUDENT_ID]",
                "SHOW TEACHERS",
                "LOOKUP TEACHER [TEACHER_ID]",
                "SHOW PROFIT"
        );

        System.out.println("Accepted commands:");
        for (String command : validCommands) {
            System.out.println("- " + command);
        }
    }

    public static void commandHandler() {
        showAcceptedCommands();

        System.out.println("Enter a command: ");
        String option = scanner.nextLine().trim();

        String[] parts = option.split(" ");
        if (parts.length == 0) {
            System.out.println("Invalid command. Please try again.");
            return;
        }

        CommandActions actionType = CommandActions.valueOf(parts[0]);
        switch (actionType) {
            case ENROLL:
                if (parts.length == 3) {
                    String studentId = parts[1];
                    String courseId = parts[2];
                    if (students.containsKey(studentId) && courses.containsKey(courseId)) {
                        SchoolHandler.enrollStudentToCourse(studentId, courseId);
                        System.out.println("Student enrolled successfully.");
                    } else {
                        System.out.println("Invalid STUDENT_ID or COURSE_ID.");
                    }
                } else {
                    System.out.println("Invalid command format. Usage: ENROLL [STUDENT_ID] [COURSE_ID]");
                }
                break;

            case ASSIGN:
                if (parts.length == 3) {
                    String teacherId = parts[1];
                    String courseId = parts[2];
                    if (teachers.containsKey(teacherId) && courses.containsKey(courseId)) {
                        SchoolHandler.assignTeacherToCourse(teacherId, courseId);
                        System.out.println("Teacher assigned successfully.");
                    } else {
                        System.out.println("Invalid TEACHER_ID or COURSE_ID.");
                    }
                } else {
                    System.out.println("Invalid command format. Usage: ASSIGN [TEACHER_ID] [COURSE_ID]");
                }
                break;

            case SHOW:
                if (parts.length == 3) {
                    String courseId = parts[2];
                    SchoolHandler.showStudentsFromCourse(courseId);
                    break;
                }

                if (parts.length == 2) {
                    CommandOptions classType = CommandOptions.valueOf(parts[1]);
                    switch (classType) {
                        case COURSES:
                            SchoolHandler.showAllCourses();
                            break;
                        case STUDENTS:
                            SchoolHandler.showAllStudents();
                            break;
                        case TEACHERS:
                            SchoolHandler.showAllTeachers();
                            break;
                        case PROFIT:
                            SchoolHandler.showProfits();
                            break;
                        case MONEY_EARNED:
                            SchoolHandler.showTotalMoneyEarned();
                            break;
                        case MONEY_SPENT:
                            SchoolHandler.showTotalSalariesPaid();
                            break;
                        default:
                            System.out.println("Invalid Command. Options: COURSES, STUDENTS, TEACHERS or PROFIT.");
                    }
                } else {
                    System.out.println("Invalid command format. Usage: SHOW [COURSES|STUDENTS|TEACHERS|PROFIT]");
                }
                break;

            case LOOKUP:
                if (parts.length == 3) {
                    String lookupType = parts[1];
                    String id = parts[2];
                    switch (lookupType) {
                        case "COURSE":
                            if (courses.containsKey(id)) {
                                SchoolHandler.lookupCourse(id);
                            } else {
                                System.out.println("Invalid COURSE_ID.");
                            }
                            break;
                        case "STUDENT":
                            if (students.containsKey(id)) {
                                SchoolHandler.lookupStudent(id);
                            } else {
                                System.out.println("Invalid STUDENT_ID.");
                            }
                            break;
                        case "TEACHER":
                            if (teachers.containsKey(id)) {
                                SchoolHandler.lookupTeacher(id);
                            } else {
                                System.out.println("Invalid TEACHER_ID.");
                            }
                            break;
                        default:
                            System.out.println("Invalid LOOKUP command. Options: COURSE, STUDENT, TEACHER.");
                    }
                } else {
                    System.out.println("Invalid command format. Usage: LOOKUP [COURSE|STUDENT|TEACHER] [ID]");
                }
                break;


            default:
                System.out.println("Unknown command. Please try again.");
        }
    }

    public static void newSchool() {
        System.out.println("Enter the name of the school: ");
        schoolName = scanner.nextLine();

        System.out.println("How many teachers do you want to add to the school?");
        int numberOfTeachers = Integer.parseInt(scanner.nextLine());
        teachers = new HashMap<>(numberOfTeachers);
        for (int i = 0; i < numberOfTeachers; i++) {
            Teacher teacher = createTeacher();
            teachers.put(teacher.getTeacherId(), teacher);
        }

        System.out.println("How many courses do you want to add?");
        int numberOfCourses = Integer.parseInt(scanner.nextLine());
        courses = new HashMap<>(numberOfCourses);
        for (int i = 0; i < numberOfCourses; i++) {
            Course course = createCourse();
            courses.put(course.getCourseId(), course);
        }

        System.out.println("How many students do you want to add?");
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        students = new HashMap<>(numberOfStudents);
        for (int i = 0; i < numberOfStudents; i++) {
            Student student = createStudent();
            students.put(student.getStudentId(), student);
        }
    }


    public static void menuDisplay() {
        System.out.println("\n======== Iron School ========");
        System.out.println("1. Create new School");
        //System.out.println("2. Generate random characters");
        //System.out.println("3. Create your own characters");
        System.out.println("2. Exit");
        System.out.println("=============================");
        System.out.print("Choose an option: ");
    }

    public static void menuHandler(){

        boolean running = true;

        while (running) {
            menuDisplay();
            String option = scanner.nextLine();


            switch (option) {
                case "1":
                    newSchool();
                    break;

                //case "2":
           /*         System.out.println("Enter a odd number of characters to generate (each battle must be between two characters): ");
                    try {
                        int numberOfCharacters = Integer.parseInt(scanner.nextLine());
                        battle(charactersFactory(numberOfCharacters));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid number");
                    }
                    break;*/

                //case "3":
                  /*  System.out.println("Select the type of characters to create:");
                    System.out.println("Enter 1 for Wizard or 2 for Warrior");
                    var character1= new Character();
                    String type = scanner.nextLine();
                    if(type.equals("1")) {
                        System.out.println("Enter the Wizard name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the health points of the Wizard between 50 and 100: ");
                        String hp = scanner.nextLine();
                        System.out.println("Enter the intelligence of the Wizard between 1 and 50: ");
                        String intelligence = scanner.nextLine();
                        System.out.println("Enter the mana point of the Wizard between 10 and 50: ");
                        String mana = scanner.nextLine();
                        character1 = new Wizard(name, Integer.parseInt(hp), Integer.parseInt(intelligence), Integer.parseInt(mana));
                    }else if(type.equals("2")){
                        System.out.println("Enter the Warrior name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the health points of the Warrior between 100 and 200: ");
                        String hp = scanner.nextLine();
                        System.out.println("Enter the stamina of the Warrior between 10 and 50: ");
                        String stamina = scanner.nextLine();
                        System.out.println("Enter the strength point of the Warrior between 1 and 10: ");
                        String strength = scanner.nextLine();
                        character1 = new Warrior(name, Integer.parseInt(hp), Integer.parseInt(stamina), Integer.parseInt(strength));
                    }else{
                        System.out.println("Invalid option. Please try again.");
                        break;
                    }

                    System.out.println("Select the type of the second character to create:");
                    System.out.println("Enter 1 for Wizard or 2 for Warrior");
                    var character2 = new Character();
                    type = scanner.nextLine();
                    if (type.equals("1")) {
                        System.out.println("Enter the Wizard name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the health points of the Wizard between 50 and 100: ");
                        String hp = scanner.nextLine();
                        System.out.println("Enter the intelligence of the Wizard between 1 and 50: ");
                        String intelligence = scanner.nextLine();
                        System.out.println("Enter the mana point of the Wizard between 10 and 50: ");
                        String mana = scanner.nextLine();
                        character2 = new Wizard(name, Integer.parseInt(hp), Integer.parseInt(intelligence), Integer.parseInt(mana));
                    }else if(type.equals("2")){
                        System.out.println("Enter the Warrior name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the health points of the Warrior between 100 and 200: ");
                        String hp = scanner.nextLine();
                        System.out.println("Enter the stamina of the Warrior between 10 and 50: ");
                        String stamina = scanner.nextLine();
                        System.out.println("Enter the strength point of the Warrior between 1 and 10: ");
                        String strength = scanner.nextLine();
                        character2 = new Warrior(name, Integer.parseInt(hp), Integer.parseInt(stamina), Integer.parseInt(strength));
                    }else{
                        System.out.println("Invalid option. Please try again.");
                        break;
                    }

                    battle(character1, character2);
                    break;
*/
                case "2", "exit":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

}




