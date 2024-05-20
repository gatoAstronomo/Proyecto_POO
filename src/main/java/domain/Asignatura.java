package domain;

import java.util.ArrayList;


public class Asignatura{
    String nombre;
    int numeroId;
    int nivel;
    int horasSct;
    ArrayList<Integer> idRequisitos = new ArrayList<>();
    ArrayList<String> nombreRequisitos = new ArrayList<>();

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
    public void imprimirNombreRequisitos(){
        System.out.print("Prerrequisitos: " + this.nombreRequisitos.get(0));
        for(int i = 1; i < this.nombreRequisitos.size(); i++){
            System.out.print(", " + this.nombreRequisitos.get(i));
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
    public ArrayList<Integer> getIdRequisitos(){
        return this.idRequisitos;
    }

    public void setIdRequisitos(ArrayList<Integer> idRequisitos){
        this.idRequisitos = idRequisitos;
    }
    public void addIdPrerrequisito(int idPrerrequisito){
        this.idRequisitos.add(idPrerrequisito);
    }
    public ArrayList<String> getNombrePrerrequisitos(){
        return this.nombreRequisitos;
    }
    public void setNombrePrerrequisitos(ArrayList<String> nombrePrerrequisitos){
        this.nombreRequisitos = nombrePrerrequisitos;
    }
    public void addNombrePrerrequisito(String nombrePrerrequisito){
        this.nombreRequisitos.add(nombrePrerrequisito);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Número ID: ").append(numeroId).append("\n");
        sb.append("Nivel: ").append(nivel).append("\n");
        sb.append("Horas SCT: ").append(horasSct).append("\n");
        sb.append("Prerrequisitos: ");
        if (nombreRequisitos != null && !nombreRequisitos.isEmpty()) {
            sb.append(nombreRequisitos.get(0));
            for (int i = 1; i < nombreRequisitos.size(); i++) {
                sb.append(", ").append(nombreRequisitos.get(i));
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}   
