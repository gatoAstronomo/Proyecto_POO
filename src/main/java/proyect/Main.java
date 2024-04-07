package proyect;


public class Main {
    public static void main(String[] args) {
        Asignaturas asignaturasInformatica = new Asignaturas();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        asignaturasInformatica.imprimirAsignaturas();
    }

}
