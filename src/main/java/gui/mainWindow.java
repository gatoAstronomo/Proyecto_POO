package gui;

import javax.swing.*;
import java.awt.*;
/*
 * DOCUMENTACION:
 *
 * - NOMENCLATURA:
 * Todo sufijo _LO hace referencia a LayOut.
 *
 */
public class mainWindow extends JFrame {
    private JPanel curriculumPanel;
    private int mallaColumns;
    private int mallaRows;

    public mainWindow() {
        super("UCM");
        this.mallaRows = 6;
        this.mallaColumns = 8;
        this.config();
    }
    private void config(){
        this.setSize(1200, 720);
        JPanel superContainer = new JPanel(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel matricula_Panel = new JPanel(new GridBagLayout()); // BARRA DE INGRESO MATRICULA
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
/*
        matricula_Panel.setPreferredSize(new Dimension(800, 50));

        matricula_Panel.add(new JTextField(20), constraints);
       // GUIUtils.addToPanel(new JButton("buscar"), new Dimension(200, 20), matricula_Panel);
        JButton buscarButton = new JButton("buscar");
        buscarButton.setPreferredSize(new Dimension(200, 20));
        matricula_Panel.add(buscarButton, constraints); // Agregar el botón con las mismas restricciones

        superContainer.add(matricula_Panel);

 */
        JPanel mallaPanel = new JPanel(new FlowLayout());
        GUIUtils.setBox_LO(mallaPanel, "x");

        cargar_malla(mallaPanel);

        superContainer.add(mallaPanel);
        this.add(superContainer);
        this.pack();
        this.setVisible(true);
    }
    void cargar_malla(JPanel mallaPanel){
        GUIUtils.show(mallaPanel, this.mallaColumns);

    }
}
