package domain;
import java.util.ArrayList; 


public class Alumno {
    String nombre;
    String matricula;
    ArrayList<Integer> idAsignaturasAprobadas = new ArrayList<>();
    ArrayList<String> nombreAsignaturasAprobadas = new ArrayList<>();

    public Alumno(){}
    public Alumno(String nombre, String matricula){
        this.nombre = nombre;
        this.matricula = matricula;
    }
    Alumno(String nombre, String matricula, ArrayList<Integer> idAsignaturasAprobadas) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.idAsignaturasAprobadas = idAsignaturasAprobadas;
    }



    public void addAsignaturaAprobada(int id){
        this.idAsignaturasAprobadas.add(id);
    }
    public void addIdAsignaturaAprobada(int idAsignaturaAprobada){
        this.idAsignaturasAprobadas.add(idAsignaturaAprobada);
    }
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
    public ArrayList<Integer> getIdAsignaturasAprobadas(){
        return this.idAsignaturasAprobadas;
    }
    public void setIdAsignaturasAprobadas(ArrayList<Integer> idAsignaturasAprobadas){
        this.idAsignaturasAprobadas = idAsignaturasAprobadas;
    }
    public ArrayList<String> getNombreAsignaturasAprobadas() {
        return nombreAsignaturasAprobadas;
    }
    public void setNombreAsignaturasAprobadas(ArrayList<String> nombreAsignaturasAprobadas) {
        this.nombreAsignaturasAprobadas = nombreAsignaturasAprobadas;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nombre: ").append(this.nombre).append("\n");
        stringBuilder.append("Matrícula: ").append(this.matricula).append("\n");
        stringBuilder.append("Aprobadas: ");
        for (int i = 0; i < this.nombreAsignaturasAprobadas.size(); i++) {
            stringBuilder.append(this.nombreAsignaturasAprobadas.get(i));
            if (i < this.nombreAsignaturasAprobadas.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
