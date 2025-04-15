import java.util.HashMap;
import java.util.Scanner;

public class appMain {
    public static void main(String[] args) {
        // Menu Principal



        Scanner scanner = new Scanner(System.in);
        String[] optionSuperMenu={"Load data manually ",
                "Load data from Json","Exit"};
        String[] optionsMenu={"Entry a new Teacher",
                "Entry a Course","Entry a Student","salir"};
        int userchoice=0;


        while (true){
            System.out.println("Elige una opcion");
            for (int i = 0; i <optionSuperMenu.length ; i++) {
                System.out.println((i+1)+" "+optionSuperMenu[i]);
            }
            // eleccion del usuario
            userchoice = scanner.nextInt();


            if (userchoice<1||userchoice>5){
                System.out.println("Debes introducir una opcion valida");
            }
            switch (userchoice){
                case 1:
                    // return boolean
                    try {
                    SchoolHandler.createSchool();
                    }catch (Exception e){
                        //empty maps
                        SchoolHandler.teachers.clear();
                        SchoolHandler.students.clear();
                        SchoolHandler.courses.clear();
                        System.out.println("School creation failed");
                    }

                case 2:
                    //return boolean
                case 3:
                    //return boolean
                    //createCourses();
                case 4:
                    userchoice=5;
                    break;
            }
        }


    }
}
