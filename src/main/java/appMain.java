import java.util.Scanner;

public class appMain {
    public static void main(String[] args) {
        // Menu Principal


        Scanner scanner = new Scanner(System.in);
        String[] optionSuperMenu = {"Create new School ",
                "Create manually Data (Teacher, Students etc..)", "Comand","Load Data","Save Data",
                "Exit"};
        String[] optionsMenu = {"Entry a new Teacher",
                "Entry a Course", "Entry a Student", "salir"};
        int userchoice = 0;
        boolean handlerSuperMenu = true;

        while (handlerSuperMenu) {
            System.out.println("Elige una opcion");
            for (int i = 0; i < optionSuperMenu.length; i++) {
                System.out.println((i + 1) + " " + optionSuperMenu[i]);
            }
            // eleccion del usuario
            userchoice = scanner.nextInt();


            if (userchoice < 1 || userchoice > 5) {
                System.out.println("Debes introducir una opcion valida");
            }
            switch (userchoice) {
                case 1:
                    // return boolean
                    try {
                        SchoolHandler.createSchool();
                    } catch (Exception e) {
                        //empty maps
                        SchoolHandler.teachers.clear();
                        SchoolHandler.students.clear();
                        SchoolHandler.courses.clear();
                        System.out.println("School creation failed");
                    }

                case 2:
                    boolean handlerSubMenu = true;
                    while (handlerSubMenu) {
                        for (int i = 0; i < optionsMenu.length; i++) {
                            System.out.println((i + 1) + " " + optionsMenu[i]);
                        }
                        // option user choice
                        int userChioceMenuSecond = scanner.nextInt();
                        //
                        switch (userChioceMenuSecond) {
                            // entry teacher
                            case 1:
                                SchoolHandler.createTeacher();
                                continue;
                            case 2:
                                SchoolHandler.createCourse();
                                continue;
                            case 3:
                                SchoolHandler.createStudent();
                                continue;
                            case 4:
                                handlerSubMenu = false;
                                continue;
                            default:
                                System.out.println("Enter a valid Option");

                        }
                    }
                    case 3:
                        AppHandler.commandHandler();
                        break;

                case 4:
                    //return boolean
                    //createCourses();
                    FileHandler.loadData();
                    System.out.println("Data loaded");
                    break;
                case 5:
                    FileHandler.saveData();
                    System.out.println("Data saved");
                    break;
                case 6:
                    handlerSuperMenu = false;
                    break;
            }
        }


    }
}
