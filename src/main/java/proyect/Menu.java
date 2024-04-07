package proyect;


import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");

        while (!salir) {
            System.out.println("***** Menú *****");
            System.out.println("1. Ver lista de asignaturas");
            System.out.println("2. Buscar asignatura por ID");
            System.out.println("3. Salir");
            System.out.print("Ingrese el número de la opción deseada: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Lista de asignaturas:");
                    asignaturasInformatica.imprimirAsignaturas();
                    break;
                case 2:
                    System.out.print("Ingrese el número de ID de la asignatura a buscar: ");
                    int id = scanner.nextInt();
                    Asignatura asignaturaEncontrada = asignaturasInformatica.buscarAsignaturaPorId(id);
                    if (asignaturaEncontrada != null) {
                        System.out.println("Asignatura encontrada:");
                        asignaturaEncontrada.imprimirAsignatura();
                    } else {
                        System.out.println("No se encontró ninguna asignatura con ese ID.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
        scanner.close();
    }
}