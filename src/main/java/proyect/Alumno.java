package proyect;
import java.util.ArrayList;

public class Alumno {
    String nombre;
    String matricula;
    ArrayList<Asignatura> idAsignaturasAprobadas = new ArrayList<>();

    Alumno(String nombre, String matricula){
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public void agregarAsignaturaAprobada(){}
}
