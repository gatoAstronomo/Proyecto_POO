package proyect;
import java.util.ArrayList; 


public class Alumno {
    String nombre;
    String matricula;
    ArrayList<Integer> idAsignaturasAprobadas = new ArrayList<Integer>();
    ArrayList<String> nombrePrerrequisitos = new ArrayList<String>();

    public Alumno(String nombre, String matricula){
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public Alumno(){}

    Alumno(String nombre, String matricula, ArrayList<Integer> idAsignaturasAprobadas) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.idAsignaturasAprobadas = idAsignaturasAprobadas;
    }

    public void agregarAsignaturaAprobada(int id){
        this.idAsignaturasAprobadas.add(id);
    }

    public void addIdAsignaturaAprobada(int idAsignaturaAprobada){
        this.idAsignaturasAprobadas.add(idAsignaturaAprobada);
    }
    // Setters y Getters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    public String getMatricula(){
        return this.matricula;
    }
    public void setIdAsignaturasAprobadas(ArrayList<Integer> idAsignaturasAprobadas){
        this.idAsignaturasAprobadas = idAsignaturasAprobadas;
    }
    public ArrayList<Integer> getIdAsignaturasAprobadas(){
        return this.idAsignaturasAprobadas;
    }

}
