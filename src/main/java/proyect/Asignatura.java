package proyect;

import java.util.ArrayList;

public class Asignatura{
    String nombre;
    int numeroId;
    int nivel;
    int horasSct;
    ArrayList<Integer> idPrerrequisitos = new ArrayList<Integer>();

    Asignatura(String nombre, int numeroId, int nivel, int horasSct){
        this.nombre = nombre;
        this.numeroId = numeroId;
        this.nivel = nivel;
        this.horasSct = horasSct;
    }

}   
