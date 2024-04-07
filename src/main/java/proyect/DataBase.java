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
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int numeroId = Integer.parseInt(datos[1]);
                int nivel = Integer.parseInt(datos[2]);
                int horasSct = Integer.parseInt(datos[3]);
                ArrayList<Integer> idPrerrequisitos = new ArrayList<Integer>();
                for(int i = 4; i < datos.length; i++){
                    int requisito = Integer.parseInt(datos[i]);
                    idPrerrequisitos.add(requisito);
                }
                    
                Asignatura asignatura = new Asignatura(nombre, numeroId, nivel, horasSct, idPrerrequisitos);
                asignaturas.add(asignatura);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
        this.asignaturas = asignaturas;
    }

    public void imprimirAsignaturas(){
        for (Asignatura asignatura : this.asignaturas) {
            System.out.println("Nombre: " + asignatura.nombre);
            System.out.println("Número ID: " + asignatura.numeroId);
            System.out.println("Nivel: " + asignatura.nivel);
            System.out.println("Horas SCT: " + asignatura.horasSct);
            System.out.print("Prerrequisitos: " + asignatura.idPrerrequisitos.get(0));
            for(int i = 1; i < asignatura.idPrerrequisitos.size(); i++) {
                System.out.print(", " + asignatura.idPrerrequisitos.get(i));
            }
            System.out.println("\n");
        }
    }

    public ArrayList<Asignatura> getAsignaturas(){
        return this.asignaturas;
    }
}
