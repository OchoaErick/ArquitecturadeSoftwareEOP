package Tareas.Tarea1;

//Principio de sustitución de Liskov
class Ave{
    public void volar(){
        System.out.println("Estoy volando");
    }
}
class Gorrion extends Ave{
    @Override
    public void volar(){
        System.out.println("El gorrion esta volando");
    }
}
class Pinguino extends Ave{
    //Este metodo no beria existir
    @Override
    public void volar(){
        throw new UnsupportedOperationException("Los pinguinos no vuelan");
    }
}
//Refactorizacion
abstract class AveLSP{
    abstract void comer();
}
class AveVoladora extends AveLSP{
    @Override
    void comer() {
        System.out.println("Estoy comiendo");
    }
    void volar() {
        System.out.println("Estoy volando");
    }
}
class AveNoVoladora extends AveLSP{
    @Override
    void comer() {
        System.out.println("Estoy comiendo");
    }
}
class GorrionLSP extends AveVoladora{
    @Override
    void comer() {
        System.out.println("El gorrion esta comiendo");
    }
    @Override
    void volar() {
        System.out.println("El gorrion esta volando");
    }
}
class PinguinoLSP extends AveNoVoladora{
    @Override
    void comer() {
        System.out.println("El pinguino esta comiendo");
    }
}
public class LSP {
    public static void main(String[] args){
        AveVoladora gorrion = new GorrionLSP();
        gorrion.comer();
        gorrion.volar();

        AveNoVoladora pinguino = new PinguinoLSP();
        pinguino.comer();
    }
}