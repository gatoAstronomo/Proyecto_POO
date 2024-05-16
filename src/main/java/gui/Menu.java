package gui;

import database.DataBase;
import model.Asignatura;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

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
        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                System.out.println("Ingrese un entero valido");
            }
        }
    }

    public static String obtenerString(Scanner scanner){
        return scanner.nextLine();
    }

    public static void buscarAsignaturaPorId(DataBase db, Scanner scanner) {
        System.out.print("Ingrese el número de ID de la asignatura a buscar: ");
        int id = obtenerEntero(scanner);
        Asignatura asignaturaEncontrada = db.buscarAsignaturaPorId(id);
        if (asignaturaEncontrada != null) {
            System.out.println("\nAsignatura encontrada:");
            System.out.printf(String.valueOf(asignaturaEncontrada));
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese ID.");
        }
    }

    public static void buscarAsignaturaPorNombre(DataBase db, Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        String nombre = obtenerString(scanner);
        Asignatura asignaturaEncontrada = db.buscarAsignaturaPorNombre(nombre);
        
        if (asignaturaEncontrada != null) {
            System.out.println("\nAsignatura encontrada:");
            asignaturaEncontrada.toString();
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese Nombre.");
        }
    }

    public static void buscarCoincidenciasAsignaturaPorNombre(DataBase db, Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        String nombre = obtenerString(scanner);
        ArrayList<Asignatura> asignaturasEncontradas = db.buscarCoincidenciasPorNombre(nombre);

        if (asignaturasEncontradas != null) {
            System.out.println("\nAsignaturas encontradas:");
            Asignatura.imprimirAsignaturas(asignaturasEncontradas);
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese Nombre.");
        }
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

        if (matcher.matches())
            return true;
        return false;
    }

    public static void  binvenida(String matricula, DataBase db){
        System.out.printf("Bienvenido %s", db.buscarAlumnoPorMatricula(matricula).getNombre());
    }

    public static void launch() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        String asignaturaPath = "src/main/java/resources/asignaturas.csv";
        String alumnoPath = "src/main/java/resources/alumnos.csv";
        DataBase db = new DataBase(asignaturaPath,alumnoPath);

        String matricula = pedirMatricula(scanner, db);
        binvenida(matricula, db);
        while (!salir) {
            imprimirMenu();
            int opcion = obtenerEntero(scanner);
            procesarOpcion(opcion, db, matricula, scanner);
            if (opcion == 5) {
                salir = true;
            }
        }
        scanner.close();
    }

    public static void procesarOpcion(int opcion, DataBase db, String matricula, Scanner scanner) {
        switch (opcion) {
            case 1:
                limpiarConsola();
                System.out.println("Asignaturas de la carrera de Ingenieria Civil Informatica");
                Asignatura.imprimirAsignaturas(db.getAsignaturas());
                break;
            case 2:
                limpiarConsola();
                buscarAsignaturaPorId(db, scanner);
                break;
            case 3:
                limpiarConsola();
                buscarCoincidenciasAsignaturaPorNombre(db, scanner);
                break;
            case 4:
                limpiarConsola();
                Asignatura.imprimirAsignaturas(db.ramosAElegir(matricula));
                break;
            case 5:
                limpiarConsola();
                System.out.println("Saliendo del programa...");
                break;
            default:
                limpiarConsola();
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    }
}
