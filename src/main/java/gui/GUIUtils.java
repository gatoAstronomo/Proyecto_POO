package gui;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

public class GUIUtils {
    public static void addToPanel(Component element, Dimension dim, Container container) {
        element.setPreferredSize(dim);
        container.add(element);
    }
}
