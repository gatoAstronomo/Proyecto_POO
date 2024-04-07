package proyect;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class DataBase{
    ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();

    public void leerAsignaturas(String archivo) {
        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                asignaturas.add(procesarLinea(linea));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
        this.asignaturas = asignaturas;
        
        for(Asignatura ramo : asignaturas){
            ramo.setNombrePrerrequisitos(asignarNombrePrerrequisitos(ramo)); 
        }

        this.asignaturas = asignaturas;
    }

    private Asignatura procesarLinea(String linea) {
        String[] datos = linea.split(",");

        Asignatura ramo = new Asignatura();
        ramo.setNombre(datos[0]);
        ramo.setNumeroId(Integer.parseInt(datos[1]));
        ramo.setNivel(Integer.parseInt(datos[2]));
        ramo.setHorasSct(Integer.parseInt(datos[3]));
        ramo.setIdPrerrequisitos(asignarIdPrerrequisitos(datos));
        return ramo;
    }

    private ArrayList<Integer> asignarIdPrerrequisitos(String[] datos) {
        ArrayList<Integer> idPrerrequisitos = new ArrayList<>();
        for (int i = 4; i < datos.length; i++) idPrerrequisitos.add(Integer.parseInt(datos[i]));
        return idPrerrequisitos;
    }

    private ArrayList<String> asignarNombrePrerrequisitos(Asignatura ramo){
        ArrayList<Integer> idRequisitos = ramo.idPrerrequisitos;
        ArrayList<String> nombreRequisitos = new ArrayList<String>();
        for(int i = 0; i < idRequisitos.size(); i++){
            Asignatura asignatura = buscarAsignaturaPorId(idRequisitos.get(i));
            String prerrequisito = asignatura.getNombre();
            nombreRequisitos.add(prerrequisito); 
        }
        return nombreRequisitos;
    }

    public void imprimirAsignaturas(){
        for (Asignatura asignatura : this.asignaturas) {
            asignatura.imprimirAsignatura();
        }
    }

    public ArrayList<Asignatura> getAsignaturas(){
        return this.asignaturas;
    }
    public Asignatura buscarAsignaturaPorId(int numeroId){
        for (Asignatura asignatura : this.asignaturas) {
            if (asignatura.getNumeroId() == numeroId) return asignatura;
        }
        return null;
    }
}
