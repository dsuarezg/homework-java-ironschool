import java.util.HashMap;
import java.util.Scanner;

public class appMain {
    public static void main(String[] args) {
        // Menu Principal

        HashMap<String,Teacher> teachers = new HashMap<>();
        HashMap<String,Course> courses = new HashMap<>();
        HashMap<String,Student> students = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        String[] optionSuperMenu={"Program Start","Submenu","Comands"};
        String[] optionsMenu={"Nombre de la Escuela","numero de Profesores",
                "numero de cursos","detalles del curso","salir"};
        int userchoice=0;


        while (userchoice!=5){
            System.out.println("Elige una opcion");
            for (int i = 0; i < optionSuperMenu.length; i++) {
                System.out.println((i+1)+" "+optionSuperMenu[i]);
            }
            // eleccion del usuario
            userchoice = scanner.nextInt();
            System.out.println(userchoice);
            if (userchoice<1||userchoice>5){
                System.out.println("Debes introducir una opcion valida");
            }
//            switch (userchoice){
//                case 1:
//                    // return boolean
//                    createSchool();
//                case 2:
//                    //return boolean
//                    createTeacher();
//                case 3:
//                    //return boolean
//                    //createCourses();
//                case 4:
//            }
        }

        System.out.println("salio");
    }
}
