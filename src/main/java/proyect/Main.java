package proyect;


public class Main {
    public static void main(String[] args) {
        Asignaturas asignaturasInformatica = new Asignaturas();
        asignaturasInformatica.leerAsignaturas("resource/asignaturasInformatica.csv");
        asignaturasInformatica.imprimirAsignaturas();
    }

}
