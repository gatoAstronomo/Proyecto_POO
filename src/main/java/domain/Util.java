package domain;

import java.util.ArrayList;

public class Util {

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

    public static boolean puedeTomarAsignatura(Alumno alumno, Asignatura asignatura){
        return !esAsignaturaAprobada(alumno, asignatura) && cumpleRequisitos(alumno, asignatura);
    }

    public static String quitarEspacios(String nombreAsignatura){
        return nombreAsignatura.toLowerCase().replaceAll("\\s+", "");
    }

    public static ArrayList<String> preprocessPalabrasClave(String nombreBuscar) {
        String[] palabras = nombreBuscar.split("\\s+");
        ArrayList<String> palabrasClave = new ArrayList<>();
        for (String palabra : palabras) {
            if (!palabra.equalsIgnoreCase("de")) {
                palabrasClave.add(palabra.toLowerCase());
            }
        }
        return palabrasClave;
    }
    public static boolean contieneTodasLasPalabras(String nombreAsignatura, ArrayList<String> palabrasClave) {
        for (String palabra : palabrasClave) {
            if (!nombreAsignatura.contains(palabra)) {
                return false;
            }
        }
        return true;
    }
}
