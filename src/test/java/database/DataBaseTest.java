package proyect;

import database.DataBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Asignatura;


import static org.junit.jupiter.api.Assertions.*;
class DataBaseTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void buscarAsignaturaPorId() {
        DataBase db = new DataBase("/src/main/java/resources/asignaturas.csv",null);
        Asignatura asignatura = db.buscarAsignaturaPorId(15);
        assertEquals(asignatura.getNombre(), "Calculo Multivariable");

    }

    @Test
    void buscarAsignaturaPorNombre() {
        DataBase db = new DataBase("/src/main/java/resources/asignaturas.csv",null);
        Asignatura asignatura = db.buscarAsignaturaPorNombre("Calculo Multivariable");
        assertEquals(asignatura.getNumeroId(), 15);
    }

    @Test
    void buscarCoincidenciasPorNombre() {
        DataBase db = new DataBase("/src/main/java/resources/asignaturas.csv",null);
        assertEquals(db.buscarCoincidenciasPorNombre("Calculo Multivariable").size(), 1);
    }

    @Test
    void buscarCoincidenciasPorNombre2() {
        DataBase db = new DataBase("/src/main/java/resources/asignaturas.csv",null);
        assertNotEquals(db.buscarCoincidenciasPorNombre("Calculo").size(), 2);
    }

    @Test
    void noesAlumno() {
        DataBase db = new DataBase("/src/main/java/resources/asignaturas.csv","/src/main/java/resources/alumnos.csv");
        assertFalse(db.esAlumno("201800000"));
    }

    @Test
    void esAlumno() {
        DataBase db = new DataBase("/src/main/java/resources/asignaturas.csv","/src/main/java/resources/alumnos.csv");
        assertTrue(db.esAlumno("22123123720"));
    }

}