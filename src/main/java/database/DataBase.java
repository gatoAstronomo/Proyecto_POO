package database;

import model.Alumno;
import model.Asignatura;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class DataBase{
    ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
    ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

    public DataBase(){
        leerAsignaturas("src/main/java/resources/asignaturas.csv");
        leerAlumnos("src/main/java/resources/alumnos.csv");
    }

    public void leerAsignaturas(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                procesarLinea(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
        cargarNombrePrerrequisitosAsignatura();
    }

    public void cargarNombrePrerrequisitosAsignatura(){
        for(Asignatura ramo : this.asignaturas){
            ramo.setNombrePrerrequisitos(asignarNombrePrerrequisitos(ramo));
        }
    }

    private void procesarLinea(String linea) {
        String[] datos = linea.split(",");

        Asignatura ramo = new Asignatura();
        ramo.setNombre(datos[0]);
        ramo.setNumeroId(Integer.parseInt(datos[1]));
        ramo.setNivel(Integer.parseInt(datos[2]));
        ramo.setHorasSct(Integer.parseInt(datos[3]));
        ramo.setIdPrerrequisitos(asignarIdPrerrequisitos(datos));
        this.asignaturas.add(ramo);
    }

    private ArrayList<Integer> asignarIdPrerrequisitos(String[] datos) {
        ArrayList<Integer> idPrerrequisitos = new ArrayList<>();
        for (int i = 4; i < datos.length; i++) idPrerrequisitos.add(Integer.parseInt(datos[i]));
        return idPrerrequisitos;
    }

    private ArrayList<String> asignarNombrePrerrequisitos(Asignatura ramo){
        ArrayList<Integer> idRequisitos = ramo.getIdPrerrequisitos();
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
        return asignaturas;
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
        cargarNombrePrerrequisitosAlumno();
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

    private ArrayList<String> asignarNombreAprobadas(Alumno alumno){
        ArrayList<Integer> idAsignaturasAprobadas = alumno.getIdAsignaturasAprobadas();
        ArrayList<String> nombreAprobadas = new ArrayList<String>();
        for(int i = 0; i < idAsignaturasAprobadas.size(); i++){
            Asignatura asignatura = buscarAsignaturaPorId(idAsignaturasAprobadas.get(i));
            String prerrequisito = asignatura.getNombre();
            nombreAprobadas.add(prerrequisito);
        }
        return nombreAprobadas;
    }

    public void cargarNombrePrerrequisitosAlumno(){
        for(Alumno alumno : this.alumnos){
            alumno.setNombreAsignaturasAprobadas(asignarNombreAprobadas(alumno));
        }
    }

    public ArrayList<Asignatura> ramosAElegir(String matricula){
        Alumno alumno = buscarAlumnoPorMatricula(matricula);
        ArrayList<Integer> idAsignaturasAprobadas = alumno.getIdAsignaturasAprobadas();
        ArrayList<Asignatura> asignaturasAElegir = new ArrayList<Asignatura>();

        for(Asignatura asignatura: asignaturas){
            if (!idAsignaturasAprobadas.contains(asignatura.getNumeroId()) && cumpleRequisitos(alumno, asignatura)) {
                    asignaturasAElegir.add(asignatura);
                }
            }

        return asignaturasAElegir;
    }

    public static boolean cumpleRequisitos(Alumno alumno, Asignatura asignatura) {
        for (Integer i : asignatura.getIdPrerrequisitos()) {
            if (!alumno.getIdAsignaturasAprobadas().contains(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean esAlumno(String matricula){
        for(Alumno alumno :this.alumnos)
            if(alumno.getMatricula().contentEquals(matricula))
                return true;
        return false;
    }

}
