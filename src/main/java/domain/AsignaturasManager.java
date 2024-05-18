package domain;

import java.util.ArrayList;

public class AsignaturasManager {
    public static void imprimir(ArrayList<Asignatura> listaAsignaturas){
        for (Asignatura asignatura : listaAsignaturas) {
            System.out.println(asignatura);
        }
    }
}
