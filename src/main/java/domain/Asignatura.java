package domain;

import java.util.ArrayList;


public class Asignatura{
    String nombre;
    int numeroId;
    int nivel;
    int horasSct;
    ArrayList<Asignatura> requisitos;
    ArrayList<Integer> idRequisitos = new ArrayList<>();

    public Asignatura(String nombre, int numeroId, int nivel, int horasSct, ArrayList<Integer> idRequisitos){
        this.nombre = nombre;
        this.numeroId = numeroId;
        this.nivel = nivel;
        this.horasSct = horasSct;
        this.idRequisitos = idRequisitos;
    }
    public Asignatura(){

    }

    public static void imprimirListaAsignaturas(ArrayList<Asignatura> listaAsignaturas){
        for(Asignatura asignatura : listaAsignaturas) System.out.println(asignatura);
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getNumeroId(){
        return this.numeroId;
    }

    public int getNivel(){
        return this.nivel;
    }

    public void setNivel(int nivel){ //no se esta ocupando
        this.nivel = nivel;
    }

    public int getHorasSCT(){ //no se esta ocupando
        return this.horasSct;
    }

    public void setHorasSct(int horasSct){ //nose esta ocupando
        this.horasSct = horasSct;
    }

    public ArrayList<Integer> getIdRequisitos(){
        return this.idRequisitos;
    }

    public void setRequisitos(ArrayList<Asignatura> requisitos){
        this.requisitos = requisitos;
    }

    @Override

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Número ID: ").append(numeroId).append("\n");
        sb.append("Nivel: ").append(nivel).append("\n");
        sb.append("Horas SCT: ").append(horasSct).append("\n");
        sb.append("Prerrequisitos: ");
        if (requisitos != null && !requisitos.isEmpty()) {
            sb.append(requisitos.get(0).getNombre());
            for (int i = 1; i < requisitos.size(); i++) {
                sb.append(", ").append(requisitos.get(i).nombre);
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}   
