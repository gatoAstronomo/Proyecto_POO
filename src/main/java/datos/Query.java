package datos;

import model.Asignatura;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Query {
    DataBase db = new DataBase();
    public ArrayList<Asignatura> getAsignaturas(){
        return db.asignaturas;
    }
}
