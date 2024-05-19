package cli;

import database.DataBase;
import domain.Alumno;
import domain.AsignaturasManager;
import domain.Asignatura;

import java.util.ArrayList;
import java.util.Scanner;

public class UniversityCourseManager {

    DataBase db;
    Alumno alumno;
    Scanner scanner;
    boolean salir;

    public UniversityCourseManager() {
        scanner = new Scanner(System.in);
        salir = false;
        cargarDatos();
    }
    private void cargarDatos() {
        String asignaturaPath = "src/main/java/resources/asignaturas.csv";
        String alumnoPath = "src/main/java/resources/alumnos.csv";
        db = new DataBase(asignaturaPath, alumnoPath);
    }

    public static void limpiarConsola() {
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls");

            } else {
                new ProcessBuilder("clear");
            }

        } catch (Exception e) {
            System.out.println("Error al limpiar la consola: " + e.getMessage());
        }
    }
    public void bienvenida() {
        String matricula = alumno.getMatricula();
        System.out.printf("Bienvenido %s", db.buscarAlumnoPorMatricula(matricula).getNombre());
    }
    public static void imprimirMenu() {
        System.out.println("\n***** Menú *****");
        System.out.println("1. Ver lista de asignaturas");
        System.out.println("2. Buscar asignatura por ID");
        System.out.println("3. Buscar asignatura por Nombre");
        System.out.println("4. Ver asignaturas a tomar");
        System.out.println("5. Salir");
    }

    public int tomarEntero() {
        return Integer.parseInt(scanner.nextLine());
    }
    public String tomarString() {
        return scanner.nextLine();
    }

    public int dialogPedirEntero(String getDialog, String errorDialog) {
        System.out.println(getDialog);
        while (true) {
            try {
                return tomarEntero();
            } catch (Exception e) {
                System.out.println(errorDialog);
            }
        }
    }
    public String dialogPedirString(String getDialog){
        System.out.println(getDialog);
        return tomarString();
    }

    public int dialogPedirOpcion() {
        return dialogPedirEntero("Ingrese una opción", "¡¡Ingrese una opción valida!!");
    }
    public String dialogPedirMatricula() {
        return dialogPedirString("Ingrese su matricula: ");
    }
    public int dialogPedirIdAsignatura() {
        return dialogPedirEntero("Ingrese el número de ID de la asignatura a buscar: ", "Ingrese un ID valido");
    }

    /* Cada opción del menu devuelve el estado del programa, false es para seguir, true termina el programa*/
    public boolean login() {
        System.out.println("Bienvenido a UCM");
        System.out.println("Ingrese 1 para salir");

        while (true) {
            String matricula = dialogPedirMatricula();
            if (matricula.equals("1")) {
                return exit();
            }
            if (db.esAlumno(matricula)) {
                alumno = db.buscarAlumnoPorMatricula(matricula);
                bienvenida();
                return false;
            }
            System.out.println("Ingrese matricula valida.");
        }
    }
    public boolean imprimirListaAsignaturas() {
        System.out.println("Asignaturas de la carrera de Ingenieria Civil Informatica");
        AsignaturasManager.imprimir(db.getAsignaturas());
        return false;
    }
    public boolean buscarAsignaturaPorId() {
        limpiarConsola();
        int id = dialogPedirIdAsignatura();
        Asignatura asignatura = db.buscarAsignaturaPorId(id);
        if (asignatura != null) {
            System.out.println("\nAsignatura encontrada:");
            System.out.println(asignatura);
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese ID.");
        }
        return false;
    }
    public boolean buscarCoincidenciasAsignaturaPorNombre() {
        limpiarConsola();
        System.out.print("Ingrese el nombre de la asignatura a buscar: ");
        String nombre = tomarString();
        ArrayList<Asignatura> listaAsignaturas = db.buscarCoincidenciasPorNombre(nombre);

        if (!listaAsignaturas.isEmpty()) {
            System.out.println("\nAsignaturas encontradas:");
            AsignaturasManager.imprimir(listaAsignaturas);
        } else {
            System.out.println("\nNo se encontró ninguna asignatura con ese Nombre.");
        }
        return false;
    }
    public boolean imprimirRamosAElegir() {
        limpiarConsola();
        ArrayList<Asignatura> ramosAElegir = db.asignaturasAElegir(alumno);
        if (ramosAElegir != null) {
            System.out.println("Ramos a elegir:");
            AsignaturasManager.imprimir(ramosAElegir);
        } else {
            System.out.println("No hay ramos a elegir");
        }
        return false;
    }
    public static boolean exit() {
        limpiarConsola();
        System.out.println("Saliendo del programa...");
        return true;
    }
    public static boolean defaultOption() {
        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        return false;
    }
    public boolean procesarOption(int option) {
        return switch (option) {
            case 1 -> imprimirListaAsignaturas();
            case 2 -> buscarAsignaturaPorId();
            case 3 -> buscarCoincidenciasAsignaturaPorNombre();
            case 4 -> imprimirRamosAElegir();
            case 5 -> exit();
            default -> defaultOption();
        };
    }

    public void launch() {
        salir = login();

        // Se mantiene el loop hasta que el usuario decida salir
        while (!salir){
            imprimirMenu();
            int opcion = dialogPedirOpcion();
            salir = procesarOption(opcion);
        }

        scanner.close();
    }

}
