package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import model.Asignatura;
import datos.Query;
public class InitialGUI {
    JFrame mainFrame;
    JPanel curriculumPanel;
    int mallaRows;
    int mallaColumns;
    private List<RamoPanel> ramoPanels;

    public InitialGUI() {
        this.mallaRows = 6;
        this.mallaColumns = 11;
        ramoPanels = new ArrayList<>(); // Inicializar la lista ramoPanels
        setup();
    }

    public void setup(){
        this.mainFrame = new JFrame("UCM");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setPreferredSize(new Dimension(600, 50));

        JTextField searchField = new JTextField(20);
        GUIUtils.addToPanel(searchField, new Dimension(600, 24), searchPanel);

        JButton searchButton = new JButton("Buscar");
        GUIUtils.addToPanel(searchButton, new Dimension(100, 24), searchPanel);
        this.curriculumPanel = new JPanel(new GridLayout(this.mallaRows, this.mallaColumns));
        this.curriculumPanel.setPreferredSize(new Dimension(800, 500));

        mainFrame.add(searchPanel, BorderLayout.NORTH);
        mainFrame.add(curriculumPanel, BorderLayout.CENTER);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = searchField.getText(); // Hacer que busque la matricula -> DataBase::elMetodoQueLoHace :/
                curriculumPanel.removeAll();
                cargarProtoMalla();
                curriculumPanel.revalidate();
                curriculumPanel.repaint();
            }
        });


        mainFrame.setVisible(true);
    }
    void cargarMalla(){
        Query query = new Query();
        List<Asignatura> asignaturas = query.getAsignaturas();

        for (int panel = 0; panel < this.mallaColumns; panel++) {
            for (int k = 0; k < this.mallaRows; k++) {
                RamoPanel ramoPanel = new RamoPanel(asignaturas.get(k));
                System.out.println(asignaturas.get(panel * mallaRows + k).getNombre() );
                drawRamo(ramoPanel);
            }
        }
    }
    void cargarProtoMalla(){
        for(int panel = 0; panel < this.mallaRows; panel++){
            for(int k = 0; k < this.mallaColumns; k++ ){
                JPanel nivel = new JPanel();
                nivel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                nivel.setPreferredSize(new Dimension(150, 50));
                String level = new String(String.valueOf(panel + 1));
                nivel.add(new JLabel(level));
                curriculumPanel.add(nivel);
            }

        }
    }
    void drawRamo(RamoPanel ramoPanel){
        this.ramoPanels.add(ramoPanel);
        GUIUtils.addToPanel(ramoPanel, new Dimension(150, 50), this.curriculumPanel);
    }
    void chamuyo(){

    }

}
