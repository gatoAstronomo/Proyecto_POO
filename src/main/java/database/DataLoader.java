package database;

import domain.Alumno;
import domain.Asignatura;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {

    protected ArrayList<Asignatura> asignaturas = new ArrayList<>();
    protected ArrayList<Alumno> alumnos = new ArrayList<>();

    public DataLoader(String asignaturasPath, String alumnosPath){
        cargarDatos(asignaturasPath, alumnosPath);
    }

    public void cargarDatos(String asignaturaPath, String alumnoPath){
        leerAsignaturas(asignaturaPath);
        leerAlumnos(alumnoPath);
    }

    private void leerAsignaturas(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                procesarLinea(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            System.out.println("Cerrando el programa.......");
            System.exit(1);
        }
        cargarNombrePrerrequisitosAsignatura();
    }
    private void cargarNombrePrerrequisitosAsignatura(){
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
        ramo.setIdRequisitos(asignarIdPrerrequisitos(datos));
        this.asignaturas.add(ramo);
    }
    private ArrayList<Integer> asignarIdPrerrequisitos(String[] datos) {
        ArrayList<Integer> idPrerrequisitos = new ArrayList<>();
        for (int i = 4; i < datos.length; i++) idPrerrequisitos.add(Integer.parseInt(datos[i]));
        return idPrerrequisitos;
    }
    private ArrayList<String> asignarNombrePrerrequisitos(Asignatura ramo){
        ArrayList<Integer> idRequisitos = ramo.getIdRequisitos();
        ArrayList<String> nombreRequisitos = new ArrayList<>();
        for (Integer idRequisito : idRequisitos) {
            Asignatura asignatura = buscarAsignaturaPorId(idRequisito);
            String prerrequisito = asignatura.getNombre();
            nombreRequisitos.add(prerrequisito);
        }
        return nombreRequisitos;
    }
    public Asignatura buscarAsignaturaPorId(int numeroId){
        for (Asignatura asignatura : this.asignaturas) {
            if (asignatura.getNumeroId() == numeroId) return asignatura;
        }
        return null;
    }

    private void leerAlumnos(String archivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                procesarLineaAlumno(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            System.out.println("Cerrando el programa.......");
            System.exit(1);
        }
        cargarNombrePrerrequisitosAlumno();
    }
    private void procesarLineaAlumno(String linea){
        String[] datos = linea.split(",");
        Alumno alumno = new Alumno();
        alumno.setMatricula(datos[0]);
        alumno.setNombre(datos[1]);
        asignarAsignaturasAprobadas(datos, alumno);
        this.alumnos.add(alumno);
    }
    private void asignarAsignaturasAprobadas(String[] datos, Alumno alumno){
        for (int i = 2; i < datos.length; i++) {
            alumno.addIdAsignaturaAprobada(Integer.parseInt(datos[i]));
        }
    }
    private ArrayList<String> asignarNombreAprobadas(Alumno alumno){
        ArrayList<Integer> idAsignaturasAprobadas = alumno.getIdAsignaturasAprobadas();
        ArrayList<String> nombreAprobadas = new ArrayList<>();
        for (Integer idAsignaturasAprobada : idAsignaturasAprobadas) {
            Asignatura asignatura = buscarAsignaturaPorId(idAsignaturasAprobada);
            String prerrequisito = asignatura.getNombre();
            nombreAprobadas.add(prerrequisito);
        }
        return nombreAprobadas;
    }
    private void cargarNombrePrerrequisitosAlumno(){
        for(Alumno alumno : this.alumnos){
            alumno.setNombreAsignaturasAprobadas(asignarNombreAprobadas(alumno));
        }
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }
    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }
}
