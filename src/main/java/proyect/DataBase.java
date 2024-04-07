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
    }

    private Asignatura procesarLinea(String linea) {
        String[] datos = linea.split(",");

        Asignatura ramo = new Asignatura();
        ramo.setNombre(datos[0]);
        ramo.setNumeroId(Integer.parseInt(datos[1]));
        ramo.setNivel(Integer.parseInt(datos[2]));
        ramo.setHorasSct(Integer.parseInt(datos[3]));
        ramo.setIdPrerrequisitos(asignarPrerrequisitos(datos, ramo));
        return ramo;
    }

    private ArrayList<Integer> asignarPrerrequisitos(String[] datos, Asignatura ramo) {
        for (int i = 4; i < datos.length; i++) ramo.addIdPrerrequisito(Integer.parseInt(datos[i]));
        return ramo.getIdPrerrequisitos();
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
