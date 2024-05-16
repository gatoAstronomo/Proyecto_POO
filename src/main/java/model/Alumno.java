package model;
import java.util.ArrayList; 


public class Alumno {
    String nombre;
    String matricula;
    ArrayList<Integer> idAsignaturasAprobadas = new ArrayList<Integer>();
    ArrayList<String> nombreAsignaturasAprobadas = new ArrayList<String>();

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

    public void imprimirAlumno(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Matricula: " + this.matricula);
        imprimirAprobadasNombre();
        System.out.println("\n");
    }

    public void imprimirAprobadasNombre(){
        System.out.print("Aprobadas: " + this.nombreAsignaturasAprobadas.get(0));
        for(int i = 1; i < this.nombreAsignaturasAprobadas.size(); i++){
            System.out.print(", " + this.nombreAsignaturasAprobadas.get(i));
        }
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

    public void setNombreAsignaturasAprobadas(ArrayList<String> nombreAsignaturasAprobadas) {
        this.nombreAsignaturasAprobadas = nombreAsignaturasAprobadas;
    }

    public ArrayList<String> getNombreAsignaturasAprobadas() {
        return nombreAsignaturasAprobadas;
    }
}
