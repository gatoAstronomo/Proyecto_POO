package gui;
import domain.Asignatura;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.function.Consumer;

import data.DataBase;

import javax.crypto.spec.PSource;
import javax.swing.*;

public class GUIUtils {

    public static Consumer<? super String> addToPanel(Component element, Dimension dim, Container container) {
        element.setPreferredSize(dim);
        container.add(element);
        return null;
    }
    /*
    public static ArrayList<ArrayList<Asignatura>> adaptarMalla(int mallaColums) {
        ArrayList<Asignatura> asignaturas = new DataBase().getAsignaturas();
        ArrayList<ArrayList<Asignatura>> niveles = new ArrayList<>();

        for (int i = 0; i <= mallaColums; i++) {
            niveles.add(new ArrayList<Asignatura>());
        }
        for (Asignatura asignatura : asignaturas) {
            int nivel = asignatura.getNivel();
            niveles.get(nivel).add(asignatura);
        }

        return niveles;
    }
     */
    public static void show(JPanel p, int columns){
        DataBase db = new DataBase();
        for(int i = 0; i < columns; i++){
            JPanel tmp = new JPanel();
            setBox_LO(tmp, "y");
            db.buscarAsignaturasPorNivel(i + 3).forEach(ramo->addToPanel(new JLabel(ramo.getNombre()), new Dimension(200,50),tmp));
            addToPanel(tmp,new Dimension(150,400), p);

        }
    }
    public static void setBox_LO(Container container, String axis){
        if("x".equalsIgnoreCase(axis)){
            BoxLayout box_LO = new BoxLayout(container, BoxLayout.X_AXIS);
            container.setLayout(box_LO);
        }
        if("y".equalsIgnoreCase(axis)) {
            BoxLayout box_LO = new BoxLayout(container, BoxLayout.Y_AXIS);
            container.setLayout(box_LO);
        }
    }

}
