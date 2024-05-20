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

    protected Map<Integer, Asignatura> asignaturas;
    protected Map<String, Alumno> alumnos;

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
        cargarNombrePrerrequisitosAsignatura();
    }
    private void procesarLineaAsignatura(String linea) {
        String[] datos = linea.split(",");

        String nombre = datos[0];
        int id = Integer.parseInt(datos[1]);
        int nivel = Integer.parseInt(datos[2]);
        int horas = Integer.parseInt(datos[3]);

        Asignatura asignatura = new Asignatura(nombre, id, nivel, horas, getIdPrerrequisitos(datos));
        this.asignaturas.put(id, asignatura);
    }
    private ArrayList<Integer> getIdPrerrequisitos(String[] datos) {
        ArrayList<Integer> idPrerrequisitos = new ArrayList<>();
        for (int i = 4; i < datos.length; i++) idPrerrequisitos.add(Integer.parseInt(datos[i]));
        return idPrerrequisitos;
    }
    private ArrayList<String> getNombrePrerrequisitos(Asignatura ramo){
        ArrayList<Integer> idRequisitos = ramo.getIdRequisitos();
        ArrayList<String> nombreRequisitos = new ArrayList<>();
        for (Integer idRequisito : idRequisitos) {
            Asignatura asignatura = asignaturas.get(idRequisito);
            String prerrequisito = asignatura.getNombre();
            nombreRequisitos.add(prerrequisito);
        }
        return nombreRequisitos;
    }
    private void cargarNombrePrerrequisitosAsignatura(){
        for(Asignatura ramo : this.asignaturas.values().stream().toList()){
            ramo.setNombrePrerrequisitos(getNombrePrerrequisitos(ramo));
        }
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
        cargarNombrePrerrequisitosAlumno();
    }
    private void procesarLineaAlumno(String linea){
        String[] datos = linea.split(",");
        String nombre = datos[0];
        String matricula = datos[1];

        Alumno alumno = new Alumno(nombre, matricula, getIdAsignaturasAprobadas(datos));
        this.alumnos.put(matricula,alumno);
    }
    private ArrayList<Integer> getIdAsignaturasAprobadas(String[] datos){
        ArrayList<Integer> idAsignaturasAprobadas = new ArrayList<>();
        for (int i = 2; i < datos.length; i++) {
            idAsignaturasAprobadas.add(Integer.parseInt(datos[i]));
        }
        return idAsignaturasAprobadas;
    }
    private ArrayList<String> getNombreAsignaturasAprobadas(Alumno alumno){
        ArrayList<Integer> idAsignaturasAprobadas = alumno.getIdAsignaturasAprobadas();
        ArrayList<String> nombreAprobadas = new ArrayList<>();
        for (Integer idAsignaturasAprobada : idAsignaturasAprobadas) {
            Asignatura asignatura = asignaturas.get(idAsignaturasAprobada);
            String prerrequisito = asignatura.getNombre();
            nombreAprobadas.add(prerrequisito);
        }
        return nombreAprobadas;
    }
    private void cargarNombrePrerrequisitosAlumno(){
        for(Alumno alumno : alumnos.values()){
            alumno.setNombreAsignaturasAprobadas(getNombreAsignaturasAprobadas(alumno));
        }
    }
}
