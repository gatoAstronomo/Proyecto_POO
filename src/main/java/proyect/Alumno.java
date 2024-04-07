package proyect;
import java.util.ArrayList; 


public class Alumno {
    String nombre;
    String matricula;
    ArrayList<Integer> idAsignaturasAprobadas = new ArrayList<Integer>();

    Alumno(String nombre, String matricula){
        this.nombre = nombre;
        this.matricula = matricula;
    }

    Alumno(String nombre, String matricula, ArrayList<Integer> idAsignaturasAprobadas) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.idAsignaturasAprobadas = idAsignaturasAprobadas;
    }
}


