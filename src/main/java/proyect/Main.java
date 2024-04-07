package proyect;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Asignatura> asignaturas = Asignaturas.leerAsignaturas("C:/Users/alvar/OneDrive/Escritorio/Proyecto_POO/asignaturas.csv");
        Asignaturas.imprimirAsignaturas(asignaturas);        
    }

}
