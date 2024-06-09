package data;

import domain.Alumno;
import domain.Asignatura;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataLoader {

    private Map<Integer, Asignatura> asignaturas;
    private Map<String, Alumno> alumnos;

    public DataLoader(String asignaturasPath, String alumnosPath){
        asignaturas = new HashMap<>();
        alumnos = new HashMap<>();
        cargarDatos(asignaturasPath, alumnosPath);
    }

    public void cargarDatos(String asignaturaPath, String alumnoPath){
        cargarAsignaturas(asignaturaPath);
        cargarAlumnos(alumnoPath);
    }

    private void cargarAsignaturas(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                procesarLineaAsignatura(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            System.out.println("Cerrando el programa.......");
            System.exit(1);
        }
        cargarRequisitosAsignaturas();
    }

    private void procesarLineaAsignatura(String linea) {
        String[] datos = linea.split(",");

        String nombre = datos[0];
        int id = Integer.parseInt(datos[1]);
        int nivel = Integer.parseInt(datos[2]);
        int horas = Integer.parseInt(datos[3]);

        Asignatura asignatura = new Asignatura(nombre, id, nivel, horas, leerIdPrerrequisitos(datos));
        this.asignaturas.put(id, asignatura);
    }

    private ArrayList<Integer> leerIdPrerrequisitos(String[] datos) {
        ArrayList<Integer> idPrerrequisitos = new ArrayList<>();
        for (int i = 4; i < datos.length; i++) idPrerrequisitos.add(Integer.parseInt(datos[i]));
        return idPrerrequisitos;
    }

    private void cargarRequisitosAsignaturas(){
        for(Asignatura asignatura : asignaturas.values()){
            cargarRequisitosAsignatura(asignatura);
        }
    }

    private void cargarRequisitosAsignatura(Asignatura asignatura){
        ArrayList<Asignatura> requisitos = new ArrayList<>();
        for(Integer ID : asignatura.getIdRequisitos()){
            requisitos.add(asignaturas.get(ID));
        }
        asignatura.setRequisitos(requisitos);
    }

    private void cargarAlumnos(String archivo){
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
        cargarAsignaturasAprobadasListaAlumnos();
    }

    private void procesarLineaAlumno(String linea){
        String[] datos = linea.split(",");
        String nombre = datos[0];
        String matricula = datos[1];

        Alumno alumno = new Alumno(nombre, matricula, leerIdAsignaturasAprobadas(datos));
        this.alumnos.put(matricula,alumno);
    }

    private ArrayList<Integer> leerIdAsignaturasAprobadas(String[] datos){
        ArrayList<Integer> idAsignaturasAprobadas = new ArrayList<>();
        for (int i = 2; i < datos.length; i++) {
            idAsignaturasAprobadas.add(Integer.parseInt(datos[i]));
        }
        return idAsignaturasAprobadas;
    }

    private void cargarAsignaturasAprobadasListaAlumnos(){
        for(Alumno alumno : alumnos.values()){
            cargarAsignaturasAprobadasAlumno(alumno);
        }
    }

    private void cargarAsignaturasAprobadasAlumno(Alumno alumno){
        ArrayList<Asignatura> asignaturasAprobadas = new ArrayList<>();
        for(Integer idAsignaturaAprobada : alumno.getIdAsignaturasAprobadas()){
            asignaturasAprobadas.add(asignaturas.get(idAsignaturaAprobada));
        }
        alumno.setAsignaturasAprobadas(asignaturasAprobadas);
    }

    public Map<Integer, Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public Map<String, Alumno> getAlumnos() {
        return alumnos;
    }
}
