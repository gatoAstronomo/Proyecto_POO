package proyect;

import java.util.Scanner;

public class Menu {

    public static void imprimirMenu(){
        System.out.println("***** Menú *****");
        System.out.println("1. Ver lista de asignaturas");
        System.out.println("2. Buscar asignatura por ID");
        System.out.println("3. Salir");
    }
    
    public static int obtenerOpcion(Scanner scanner){
        try {
            System.out.print("Ingrese una opción: ");
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }    
    }

    public static void procesarOpcion(int opcion, DataBase asignaturasInformatica, Scanner scanner) {
        switch (opcion) {
            case 1:
                System.out.println("Asignaturas de la carrera de Ingenieria Civil Informatica");
                asignaturasInformatica.imprimirAsignaturas();
                break;
            case 2:
                buscarAsignaturaPorId(asignaturasInformatica, scanner);
                break;
            case 3:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;        
        }
    }

    public static void buscarAsignaturaPorId(DataBase asignaturasInformatica, Scanner scanner) {
        System.out.print("Ingrese el número de ID de la asignatura a buscar: ");
        int id = scanner.nextInt();
        Asignatura asignaturaEncontrada = asignaturasInformatica.buscarAsignaturaPorId(id);
        if (asignaturaEncontrada != null) {
            System.out.println("Asignatura encontrada:");
            asignaturaEncontrada.imprimirAsignatura();
        } else {
            System.out.println("No se encontró ninguna asignatura con ese ID.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        while (!salir) {
            imprimirMenu();
            int opcion = obtenerOpcion(scanner);
            procesarOpcion(opcion, asignaturasInformatica, scanner);
            if (opcion == 3) {
                salir = true;
            }
        } 
        scanner.close();  
    }
}

