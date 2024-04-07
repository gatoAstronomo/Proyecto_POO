package proyect;


public class Main {
    public static void main(String[] args){
        DB asignaturasInformatica = new DB();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        asignaturasInformatica.imprimirAsignaturas();      
    }
}
