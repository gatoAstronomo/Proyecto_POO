package database;

import domain.Alumno;
import domain.Asignatura;

import java.util.ArrayList;

public class DataBase extends DataLoader{

    public DataBase(String asignaturasPath, String alumnosPath) {
        super(asignaturasPath, alumnosPath);
    }

    public ArrayList<Asignatura> buscarCoincidenciasPorNombre(String nombreBuscar) {
        ArrayList<String> palabrasClave = preprocessPalabrasClave(nombreBuscar);
        ArrayList<Asignatura> coincidencias = new ArrayList<>();

        if(palabrasClave.isEmpty()) return coincidencias;

        for (Asignatura asignatura : this.asignaturas) {
            String nombreAsignatura = asignatura.getNombre().toLowerCase().replaceAll("\\s+", "");
            if (contieneTodasLasPalabras(nombreAsignatura, palabrasClave)) {
                coincidencias.add(asignatura);
            }
        }

        return coincidencias;
    }
    private ArrayList<String> preprocessPalabrasClave(String nombreBuscar) {
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

    public Alumno buscarAlumnoPorMatricula(String matricula){
        for (Alumno alumno : this.alumnos) {
            if (alumno.getMatricula().equals(matricula)) return alumno;
        }
        return null;
    }
    public ArrayList<Asignatura> asignaturasAElegir(Alumno alumno){
        ArrayList<Asignatura> asignaturasAElegir = new ArrayList<>();

        for(Asignatura asignatura: asignaturas){
            if (puedeTomarAsignatura(alumno, asignatura)) {
                    asignaturasAElegir.add(asignatura);
                }
            }

        return asignaturasAElegir;
    }
    private static boolean cumpleRequisitos(Alumno alumno, Asignatura asignatura) {
        for (Integer i : asignatura.getIdRequisitos()) {
            if (!alumno.getIdAsignaturasAprobadas().contains(i)) {
                return false;
            }
        }
        return true;
    }
    private static boolean esAsignaturaAprobada(Alumno alumno, Asignatura asignatura){
        return alumno.getIdAsignaturasAprobadas().contains(asignatura.getNumeroId());
    }
    private static boolean puedeTomarAsignatura(Alumno alumno, Asignatura asignatura){
        return !esAsignaturaAprobada(alumno, asignatura) && cumpleRequisitos(alumno, asignatura);
    }
    public boolean esAlumno(Alumno alumno){
        return this.alumnos.contains(alumno);
    }
    public boolean esAlumno(String matricula){
        for(Alumno alumno : this.alumnos){
            if(alumno.getMatricula().equalsIgnoreCase(matricula)) return true;
        }
        return false;
    }

}
