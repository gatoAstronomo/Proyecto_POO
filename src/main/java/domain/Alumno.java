package domain;

import java.util.ArrayList; 

public class Alumno {

    private final String nombre;
    private final String matricula;
    private ArrayList<Integer> idAsignaturasAprobadas = new ArrayList<>();
    private ArrayList<Asignatura> asignaturasAprobadas = new ArrayList<>();

    public Alumno(String nombre, String matricula, ArrayList<Integer> idAsignaturasAprobadas) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.idAsignaturasAprobadas = idAsignaturasAprobadas;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getMatricula(){
        return this.matricula;
    }

    public ArrayList<Integer> getIdAsignaturasAprobadas(){
        return this.idAsignaturasAprobadas;
    }

    public void setAsignaturasAprobadas(ArrayList<Asignatura> asignaturasAprobadas) {
        this.asignaturasAprobadas = asignaturasAprobadas;
    }

    public ArrayList<Asignatura> getAsignaturasAprobadas() {
        return this.asignaturasAprobadas;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nombre: ").append(this.nombre).append("\n");
        stringBuilder.append("Matrícula: ").append(this.matricula).append("\n");
        stringBuilder.append("Aprobadas: ");
        for (int i = 0; i < this.asignaturasAprobadas.size(); i++) {
            stringBuilder.append(this.asignaturasAprobadas.get(i).getNombre());
            if (i < this.asignaturasAprobadas.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
