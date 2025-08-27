package Tareas.Tarea1;

//Principio de Segregación de Interfaces

//Interfaz demasiado grande
interface Trabajador{
    void trabajar();
    void comer();
    void dormir();
}
class Empleado implements Trabajador{
    @Override
    public void trabajar(){
        System.out.println("El empleado esta trabajando");
    }
    @Override
    public void comer(){
        System.out.println("El empleado esta comiendo");
    }
    @Override
    public void dormir(){
        System.out.println("El empleado esta durmiendo");
    }
}
class Robot implements Trabajador{
    @Override
    public void trabajar(){
        System.out.println("El robot esta trabajando");
    }
    //Estos metodos no deberian existir
    @Override
    public void comer(){
        throw new UnsupportedOperationException("Los robots no comen");
    }
    @Override
    public void dormir(){
        throw new UnsupportedOperationException("Los robots no duermen");
    }
}
//Refactorizacion
interface TrabajadorISP {
    void trabajar();
}

interface ComedorISP {
    void comer();
}

interface DormidorISP {
    void dormir();
}

class EmpleadoISP implements TrabajadorISP, ComedorISP, DormidorISP {
    @Override
    public void trabajar() {
        System.out.println("El empleado esta trabajando");
    }
    @Override
    public void comer() {
        System.out.println("El empleado estacomiendo");
    }
    @Override
    public void dormir() {
        System.out.println("Shhta empleado esta durmiendo");
    }
}

class RobotISP implements TrabajadorISP {
    @Override
    public void trabajar() {
        System.out.println("El robot esta trabajando");
    }
}
public class ISP {
    public static void main(String[] args){
        EmpleadoISP empleado = new EmpleadoISP();
        empleado.trabajar();
        empleado.comer();
        empleado.dormir();

        RobotISP robot = new RobotISP();
        robot.trabajar();
    }
}
