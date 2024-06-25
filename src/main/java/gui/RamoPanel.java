package gui;

import javax.swing.*;
import java.awt.*;
import model.Asignatura;

public class RamoPanel extends JPanel {
    private Asignatura asignatura;
/*
    public RamoPanel(Asignatura asignatura) {
        this.asignatura = asignatura;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(150, 50));
        add(new JLabel(asignatura.getNombre()));
    }
 */
    public RamoPanel(Asignatura asignatura) {
        this.asignatura = asignatura;
        setPreferredSize(new Dimension(150, 50));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(asignatura.getNombre(), 10, 20);
    }
    public void setColor(Color color) {
        setBackground(color);
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }
}
