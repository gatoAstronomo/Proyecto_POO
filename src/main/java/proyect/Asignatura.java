package proyect;

import java.util.ArrayList;

public class Asignatura{
    String nombre;
    int numeroId;
    int nivel;
    int horasSct;
    ArrayList<int> idPrerrequisitos = new ArrayList<int>();

    Asignatura(String nombre, int numeroId, int nivel, int horasSct){
        this.nombre = nombre;
        this.numeroId = numeroId;
        this.nivel = nivel;
        this.horasSct = horasSct;
    }
}
