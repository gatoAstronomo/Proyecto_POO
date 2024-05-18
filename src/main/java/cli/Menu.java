package cli;

import database.DataBase;
import domain.AsignaturasManager;
import model.Asignatura;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    String asignaturaPath = "src/main/java/resources/asignaturas.csv";
    String alumnoPath = "src/main/java/resources/alumnos.csv";

    public static void limpiarConsola() {
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            if (osName.contains("windows")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO();
            pb.start().waitFor();
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola: " + e.getMessage());
        }
    }
    public static void bienvenida(String matricula, DataBase db){
        System.out.printf("Bienvenido %s", db.buscarAlumnoPorMatricula(matricula).getNombre());
    }
    public static void imprimirMenu(){
        System.out.println("\n***** Menú *****");
        System.out.println("1. Ver lista de asignaturas");
        System.out.println("2. Buscar asignatura por ID");
        System.out.println("3. Buscar asignatura por Nombre");
        System.out.println("4. Ver asignaturas a tomar");
        System.out.println("5. Salir");
        System.out.print("Ingrese una opción: ");
    }

    public static int obtenerEntero(Scanner scanner){
        return Integer.parseInt(scanner.nextLine());
    }
    public static int pedirEntero(Scanner scanner){
        while(true){
            try{
                return obtenerEntero(scanner);
            }catch(Exception e){
                System.out.println("Ingrese un entero valido");
            }
        }
    }
    public static String obtenerString(Scanner scanner){
        return scanner.nextLine();
    }
    public static String pedirMatricula(Scanner scanner, DataBase db){
        System.out.println("Bienvenido a UCM ingrese su matricula:");
        while (true) {
            String matricula = obtenerString(scanner).toLowerCase();

            if (esMatriculaValida(matricula) && db.esAlumno(matricula)) {
                return matricula;
            }else if(esMatriculaValida(matricula) && !db.esAlumno(matricula)){
                System.out.println("Usted no es alumno de la Universidad de la Frontera");
                System.out.println("Saliendo del programa.......");
                System.exit(0);
            }

            System.out.println("Ingrese una matricula valida");
        }
    }
    public static boolean esMatriculaValida(String matricula){
        String regex = "^(\\d{8,9})[0-9kK](\\d{2})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(matricula);

        return matcher.matches();
    }


    public static int pedirIdAsignatura(Scanner scanner){
        System.out.print("Ingrese el número de ID de la asignatura a buscar: ");
        return pedirEntero(scanner);
    }
    public static void buscarAsignaturaPorId(DataBase db, Scanner scanner) {
        int id = pedirIdAsignatura(scanner);
        Asignatura asignatura = db.buscarAsignaturaPorId(id);
        if (asignatura != null) {
            System.out.println("\nAsignatura encontrada:");
            System.out.println(asignatura);
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese ID.");
        }
    }

    public static String pedirNombreAsignatura(Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        return obtenerString(scanner);
    }
    public static void buscarCoincidenciasAsignaturaPorNombre(DataBase db, Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        String nombre = obtenerString(scanner);
        ArrayList<Asignatura> listaAsignaturas = db.buscarCoincidenciasPorNombre(nombre);

        if (!listaAsignaturas.isEmpty()) {
            System.out.println("\nAsignaturas encontradas:");
            AsignaturasManager.imprimir(listaAsignaturas);
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese Nombre.");
        }
    }
    public static void buscarAsignaturaPorNombre(DataBase db, Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        String nombre = obtenerString(scanner);
        Asignatura asignaturaEncontrada = db.buscarAsignaturaPorNombre(nombre);

        if (asignaturaEncontrada != null) {
            System.out.println("\nAsignatura encontrada:");
            System.out.println(asignaturaEncontrada);
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese Nombre.");
        }
    }


    public static int procesarOption(int option, DataBase db, String matricula, Scanner scanner) {
        switch (option) {
            case 1:
                limpiarConsola();
                System.out.println("Asignaturas de la carrera de Ingenieria Civil Informatica");
                AsignaturasManager.imprimir(db.getAsignaturas());
                return 0;
            case 2:
                limpiarConsola();
                buscarAsignaturaPorId(db, scanner);
                return 0;
            case 3:
                limpiarConsola();
                buscarCoincidenciasAsignaturaPorNombre(db, scanner);
                return 0;
            case 4:
                limpiarConsola();
                AsignaturasManager.imprimir(db.ramosAElegir(matricula));
                return 0;
            case 5:
                limpiarConsola();
                System.out.println("Saliendo del programa...");
                return 1;
            default:
                limpiarConsola();
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                return -1;
        }
    }
    public void launch() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        DataBase db = new DataBase(asignaturaPath,alumnoPath);

        String matricula = pedirMatricula(scanner, db);
        bienvenida(matricula, db);
        while (!salir) {
            imprimirMenu();
            int option = pedirEntero(scanner);

            if (procesarOption(option, db, matricula, scanner) == 1) {
                salir = true;
            }
        }
        scanner.close();
    }

}
