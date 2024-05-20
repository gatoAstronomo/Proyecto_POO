package data;

import domain.Alumno;
import domain.Asignatura;
import java.util.ArrayList;
import static domain.util.*;

public class DataBase extends DataLoader{

    public DataBase(String asignaturasPath, String alumnosPath) {
        super(asignaturasPath, alumnosPath);
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
    public boolean esAlumno(String matricula){
        return alumnos.containsKey(matricula);
    }

    public ArrayList<Asignatura> getAsignaturas(){
        return new ArrayList<>(asignaturas.values());
    }

}
