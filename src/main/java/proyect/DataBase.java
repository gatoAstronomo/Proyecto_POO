package proyect;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class DataBase{
    ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();

    public void leerAsignaturas(String archivo) {
        //ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                Asignatura ramo = new Asignatura();
                ramo.setNombre(datos[0]);
                ramo.setNumeroId(Integer.parseInt(datos[1]));
                ramo.setNivel(Integer.parseInt(datos[2]));
                ramo.setHorasSct(Integer.parseInt(datos[3]));

                for(int i = 4; i < datos.length; i++){
                    int requisito = Integer.parseInt(datos[i]);
                    ramo.addIdPrerrequisito(requisito);
                }
                this.asignaturas.add(ramo);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public void imprimirAsignaturas(){
        for (Asignatura asignatura : this.asignaturas) {
            System.out.println("Nombre: " + asignatura.getNombre());
            System.out.println("Número ID: " + asignatura.getNumeroId());
            System.out.println("Nivel: " + asignatura.getNivel());
            System.out.println("Horas SCT: " + asignatura.getHorasSCT());
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
