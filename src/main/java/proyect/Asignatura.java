package proyect;

import java.util.ArrayList;

public class Asignatura{
    String nombre;
    String numeroId;
    String nivel;
    String horasSct;
    ArrayList<String> preRequisitos = new ArrayList<>();

    Asignatura(String nombre, String numeroId, String nivel, String horasSct){
        this.nombre = nombre;
        this.numeroId = numeroId;
        this.nivel = nivel;
        this.horasSct = horasSct;
    }
}
