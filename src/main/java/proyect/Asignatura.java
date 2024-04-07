package proyect;

import java.util.ArrayList;

public class Asignatura{
    String nombre;
    int numeroId;
    int nivel;
    int horasSct;
    ArrayList<Integer> idPrerrequisitos = new ArrayList<Integer>();

    Asignatura(String nombre, int numeroId, int nivel, int horasSct, ArrayList<Integer> idPrerrequisitos){
        this.nombre = nombre;
        this.numeroId = numeroId;
        this.nivel = nivel;
        this.horasSct = horasSct;
        this.idPrerrequisitos = idPrerrequisitos;
    }
    Asignatura(){

    }
    public void imprimirAsignatura(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Número ID: " + this.numeroId);
        System.out.println("Nivel: " + this.nivel);
        System.out.println("Horas SCT: " + this.horasSct);
        imprimirPrerrequisitos();
        System.out.println("\n");
    }
    public void imprimirPrerrequisitos(){
        System.out.print("Prerrequisitos: " + this.idPrerrequisitos.get(0));
        for(int i = 1; i < this.idPrerrequisitos.size(); i++) {
            System.out.print(", " + this.idPrerrequisitos.get(i));
        }
    }
    // Getters y Setters
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


}   
