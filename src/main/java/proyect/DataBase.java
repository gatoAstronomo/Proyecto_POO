package proyect;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class DataBase{
    ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
    ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

    public void leerAsignaturas(String archivo) {
        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                this.asignaturas.add(procesarLinea(linea));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }

        cargarNombrePrerequisitos();
    }

    public void cargarNombrePrerequisitos(){
        for(Asignatura ramo : this.asignaturas){
            ramo.setNombrePrerrequisitos(asignarNombrePrerrequisitos(ramo));
        }
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
        Asignatura.imprimirAsignaturas(this.asignaturas);
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

    public Asignatura buscarAsignaturaPorNombre(String nombreBuscar) {
        for (Asignatura asignatura : this.asignaturas) {
            String nombreAsignatura = asignatura.getNombre().toLowerCase().replaceAll("\\s+", "");
            String nombreBuscarFormated = nombreBuscar.toLowerCase().replaceAll("\\s+", "");
            if (nombreAsignatura.contentEquals(nombreBuscarFormated)){
                return asignatura;
            }
        }
        return null;
    }

    public ArrayList<Asignatura> buscarCoincidenciasPorNombre(String nombreBuscar) {
        ArrayList<String> palabrasClave = preprocesarPalabrasClave(nombreBuscar);
        ArrayList<Asignatura> coincidencias = new ArrayList<>();

        for (Asignatura asignatura : this.asignaturas) {
            String nombreAsignatura = asignatura.getNombre().toLowerCase().replaceAll("\\s+", "");
            if (contieneTodasLasPalabras(nombreAsignatura, palabrasClave)) {
                coincidencias.add(asignatura);
            }
        }

        return coincidencias;
    }

    private ArrayList<String> preprocesarPalabrasClave(String nombreBuscar) {
        String[] palabras = nombreBuscar.split("\\s+");
        ArrayList<String> palabrasClave = new ArrayList<>();
        for (String palabra : palabras) {
            if (!palabra.equalsIgnoreCase("de")) {
                palabrasClave.add(palabra.toLowerCase());
            }
        }
        return palabrasClave;
    }

    private boolean contieneTodasLasPalabras(String nombreAsignatura, ArrayList<String> palabrasClave) {
        for (String palabra : palabrasClave) {
            if (!nombreAsignatura.contains(palabra)) {
                return false;
            }
        }
        return true;
    }
    // Métodos para alumnos

    public void leerAlumnos(String archivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                procesarLineaAlumno(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }
    public void procesarLineaAlumno(String linea){
        String[] datos = linea.split(",");
        Alumno alumno = new Alumno();
        alumno.setMatricula(datos[0]);
        alumno.setNombre(datos[1]);
        asignarAsignaturasAprobadas(datos, alumno);
        this.alumnos.add(alumno);
    }
    public Alumno buscarAlumnoPorMatricula(String matricula){
        for (Alumno alumno : this.alumnos) {
            if (alumno.getMatricula().equals(matricula)) return alumno;
        }
        return null;
    }
    private void asignarAsignaturasAprobadas(String[] datos, Alumno alumno){
        for (int i = 2; i < datos.length; i++) {
            alumno.addIdAsignaturaAprobada(Integer.parseInt(datos[i]));
        }
    }


}
