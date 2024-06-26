package data;

import domain.Alumno;
import domain.Asignatura;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.Util.*;

public class DataBase {
    private static final String ASIGNATURAS_PATH = "src/main/java/resources/asignaturas.csv";
    private static final String ALUMNOS_PATH = "src/main/java/resources/alumnos.csv";
    private Map<Integer, Asignatura> asignaturas;
    private Map<String, Alumno> alumnos;

    public DataBase(String asignaturasPath, String alumnosPath) {
        DataLoader dataLoader = new DataLoader(asignaturasPath, alumnosPath);
        asignaturas = dataLoader.getAsignaturas();
        alumnos = dataLoader.getAlumnos();
    }
    public DataBase() {
        DataLoader dataLoader = new DataLoader(ASIGNATURAS_PATH, ALUMNOS_PATH);
        asignaturas = dataLoader.getAsignaturas();
        alumnos = dataLoader.getAlumnos();
    }

    public ArrayList<Asignatura> buscarCoincidenciasPorNombre(String nombreBuscar) {
        ArrayList<Asignatura> coincidencias = new ArrayList<>();

        if(preprocessPalabrasClave(nombreBuscar).isEmpty()) return coincidencias;

        for (Asignatura asignatura : asignaturas.values()) {
            String nombreAsignatura = quitarEspacios(asignatura.getNombre());
            if (contieneTodasLasPalabras(nombreAsignatura, preprocessPalabrasClave(nombreBuscar))) {
                coincidencias.add(asignatura);
            }
        }

        return coincidencias;
    }

    public Alumno buscarAlumnoPorMatricula(String matricula){
        return alumnos.get(matricula);
    }

    public Asignatura buscarAsignaturaPorId(Integer id){
        return asignaturas.get(id);
    }

    public ArrayList<Asignatura> asignaturasAElegir(Alumno alumno){
        ArrayList<Asignatura> asignaturasAElegir = new ArrayList<>();

        for(Asignatura asignatura: asignaturas.values()){
            if (puedeTomarAsignatura(alumno, asignatura)) {
                    asignaturasAElegir.add(asignatura);
                }
            }

        return asignaturasAElegir;
    }
    public ArrayList<Asignatura> buscarAsignaturasPorNivel(int nivel){
         ArrayList<Asignatura> R = asignaturas.values().stream()
                                              .filter(r->r.getNivel() == nivel)
                                              .collect(Collectors.toCollection(ArrayList::new));
        return R;
    }

    public boolean esAlumno(String matricula){
        return alumnos.containsKey(matricula);
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return new ArrayList<>(asignaturas.values());
    }

    public ArrayList<Alumno> getAlumnos() {
        return new ArrayList<>(alumnos.values());
    }
}
