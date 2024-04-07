package proyect;


public class Main {
    public static void main(String[] args) {
        Asignaturas asignaturasInformatica = new Asignaturas();
        asignaturasInformatica.leerAsignaturas("C:/Users/alvar/OneDrive/Escritorio/Proyecto_POO/asignaturasInformatica.csv");
        asignaturasInformatica.imprimirAsignaturas();      
    }

}
