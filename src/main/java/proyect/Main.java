package proyect;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Asignatura> asignaturas = Asignaturas.leerAsignaturas("C:/Users/alvar/OneDrive/Escritorio/Proyecto_POO/asignaturas.csv");

        for (Asignatura asignatura : asignaturas) {
            System.out.println("Nombre: " + asignatura.nombre);
            System.out.println("Número ID: " + asignatura.numeroId);
            System.out.println("Nivel: " + asignatura.nivel);
            System.out.println("Horas SCT: " + asignatura.horasSct);
            System.out.println();
        }
    }
}
