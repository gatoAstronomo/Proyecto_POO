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

}   
