package proyect;


public class Main {
    public static void main(String[] args){
        DataBase asignaturasInformatica = new DataBase();
        asignaturasInformatica.leerAsignaturas("asignaturasInformatica.csv");
        asignaturasInformatica.imprimirAsignaturas();      
    }
}
