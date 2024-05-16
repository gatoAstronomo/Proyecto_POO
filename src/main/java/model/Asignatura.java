package model;

import java.util.ArrayList;


public class Asignatura{
    String nombre;
    int numeroId;
    int nivel;
    int horasSct;
    ArrayList<Integer> idPrerrequisitos = new ArrayList<Integer>();
    ArrayList<String> nombrePrerrequisitos = new ArrayList<String>();

    public Asignatura(String nombre, int numeroId, int nivel, int horasSct, ArrayList<Integer> idPrerrequisitos){
        this.nombre = nombre;
        this.numeroId = numeroId;
        this.nivel = nivel;
        this.horasSct = horasSct;
        this.idPrerrequisitos = idPrerrequisitos;
    }
    public Asignatura(){

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Número ID: ").append(numeroId).append("\n");
        sb.append("Nivel: ").append(nivel).append("\n");
        sb.append("Horas SCT: ").append(horasSct).append("\n");
        sb.append("Prerrequisitos: ");
        if (idPrerrequisitos != null && !idPrerrequisitos.isEmpty()) {
            sb.append(idPrerrequisitos.get(0));
            for (int i = 1; i < idPrerrequisitos.size(); i++) {
                sb.append(", ").append(idPrerrequisitos.get(i));
            }
        }
        sb.append("\n");
        return sb.toString();
    }
    public void imprimirPrerrequisitosNombre(){
        System.out.print("Prerrequisitos: " + this.nombrePrerrequisitos.get(0));
        for(int i = 1; i < this.nombrePrerrequisitos.size(); i++){
            System.out.print(", " + this.nombrePrerrequisitos.get(i));
        }
    }
    public static void imprimirAsignaturas(ArrayList<Asignatura> asignaturas){
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura);
        }
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public int getNumeroId(){
        return this.numeroId;
    }
    public void setNumeroId(int numeroId){
        this.numeroId = numeroId;
    }
    public int getNivel(){
        return this.nivel;
    }
    public void setNivel(int nivel){
        this.nivel = nivel;
    }
    public int getHorasSCT(){
        return this.horasSct;
    }
    public void setHorasSct(int horasSct){
        this.horasSct = horasSct;
    }
    public ArrayList<Integer> getIdPrerrequisitos(){
        return this.idPrerrequisitos;
    }

    public void setIdPrerrequisitos(ArrayList<Integer> idPrerrequisitos){
        this.idPrerrequisitos = idPrerrequisitos;
    }
    public void addIdPrerrequisito(int idPrerrequisito){
        this.idPrerrequisitos.add(idPrerrequisito);
    }
    public ArrayList<String> getNombrePrerrequisitos(){
        return this.nombrePrerrequisitos;
    }
    public void setNombrePrerrequisitos(ArrayList<String> nombrePrerrequisitos){
        this.nombrePrerrequisitos = nombrePrerrequisitos;
    }
    public void addNombrePrerrequisito(String nombrePrerrequisito){
        this.nombrePrerrequisitos.add(nombrePrerrequisito);
    }
    
}   
