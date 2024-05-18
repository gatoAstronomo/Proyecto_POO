package cli;

import database.DataBase;
import domain.AsignaturasManager;
import domain.Asignatura;

import java.util.ArrayList;
import java.util.Scanner;

import static domain.MatriculaManager.esMatriculaValida;

public class Menu {
    String asignaturaPath = "src/main/java/resources/asignaturas.csv";
    String alumnoPath = "src/main/java/resources/alumnos.csv";
    DataBase db = new DataBase(asignaturaPath,alumnoPath);
    Scanner scanner;
    boolean salir = false;

    public Menu(){
        scanner = new Scanner(System.in);
    }

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
    public static String obtenerString(Scanner scanner){
        return scanner.nextLine();
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
    public static int pedirIdAsignatura(Scanner scanner){
        System.out.print("Ingrese el número de ID de la asignatura a buscar: ");
        return pedirEntero(scanner);
    }
    public static String pedirNombreAsignatura(Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        return obtenerString(scanner);
    }

    public static int imprimirListaAsignaturas(DataBase db){
        System.out.println("Asignaturas de la carrera de Ingenieria Civil Informatica");
        AsignaturasManager.imprimir(db.getAsignaturas());
        return 0;
    }
    public static int buscarAsignaturaPorId(DataBase db, Scanner scanner) {
        int id = pedirIdAsignatura(scanner);
        Asignatura asignatura = db.buscarAsignaturaPorId(id);
        if (asignatura != null) {
            System.out.println("\nAsignatura encontrada:");
            System.out.println(asignatura);
            return 0;
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese ID.");
            return -1;
        }
    }
    public static int buscarCoincidenciasAsignaturaPorNombre(DataBase db, Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        String nombre = obtenerString(scanner);
        ArrayList<Asignatura> listaAsignaturas = db.buscarCoincidenciasPorNombre(nombre);

        if (!listaAsignaturas.isEmpty()) {
            System.out.println("\nAsignaturas encontradas:");
            AsignaturasManager.imprimir(listaAsignaturas);
            return 0;
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese Nombre.");
            return -1;
        }
    }
    public static int imprimirRamosAElegir(DataBase db, String matricula){
        ArrayList<Asignatura> ramosAElegir = db.ramosAElegir(matricula);
        if(ramosAElegir != null) {
            System.out.println("Ramos a elegir:");
            AsignaturasManager.imprimir(ramosAElegir);
        }else{
            System.out.println("No hay ramos a elegir");
        }
        return 0;
    }
    public static int buscarAsignaturaPorNombre(DataBase db, Scanner scanner){
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        String nombre = obtenerString(scanner);
        Asignatura asignaturaEncontrada = db.buscarAsignaturaPorNombre(nombre);

        if (asignaturaEncontrada != null) {
            System.out.println("\nAsignatura encontrada:");
            System.out.println(asignaturaEncontrada);
            return 0;
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese Nombre.");
            return -1;
        }
    }
    public static int exit(){
        System.out.println("Saliendo del programa...");
        return 1;
    }
    public static int defaultOption(){
        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        return -1;
    }

    public static int procesarOption(int option, DataBase db, String matricula, Scanner scanner) {
        switch (option) {
            case 1:
                limpiarConsola();
                return imprimirListaAsignaturas(db);
            case 2:
                limpiarConsola();
                return buscarAsignaturaPorId(db, scanner);
            case 3:
                limpiarConsola();
                return buscarCoincidenciasAsignaturaPorNombre(db, scanner);
            case 4:
                limpiarConsola();
                return imprimirRamosAElegir(db, matricula);
            case 5:
                limpiarConsola();
                return exit();
            default:
                limpiarConsola();
                return defaultOption();
        }
    }
    public void launch() {

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
