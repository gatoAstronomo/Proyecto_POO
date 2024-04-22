package proyect;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        Asignatura asignatura = asignaturasInformatica.buscarAsignaturaPorId(15);
        assertEquals(asignatura.getNombre(), "Calculo Multivariable");

    }

    @Test
    void buscarAsignaturaPorNombre() {
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        Asignatura asignatura = asignaturasInformatica.buscarAsignaturaPorNombre("Calculo Multivariable");
        assertEquals(asignatura.getNumeroId(), 15);
    }

    @Test
    void buscarCoincidenciasPorNombre() {
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        assertEquals(asignaturasInformatica.buscarCoincidenciasPorNombre("Calculo Multivariable").size(), 1);
    }

    @Test
    void buscarCoincidenciasPorNombre2() {
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        assertNotEquals(asignaturasInformatica.buscarCoincidenciasPorNombre("Calculo").size(), 2);
    }

    @Test
    void noesAlumno() {
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        asignaturasInformatica.leerAlumnos("alumnosInformatica.csv");
        assertFalse(asignaturasInformatica.esAlumno("201800000"));
    }

    @Test
    void esAlumno() {
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        asignaturasInformatica.leerAlumnos("alumnosInformatica.csv");
        assertTrue(asignaturasInformatica.esAlumno("22123123720"));
    }

}