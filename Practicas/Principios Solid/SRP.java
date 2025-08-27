package Tareas.Tarea1;

//Principio de responsabilidad unica
class Empleado {
   private String nombre;
   private double salarioBase;
   private double horasExtras;

   public Empleado(String nombre, double salarioBase, double horasExtras) {
       this.nombre = nombre;
       this.salarioBase = salarioBase;
       this.horasExtras = horasExtras;
   }

   //Calcula el salario total del empleado y ademas imprime el reporte
   public double calcularSalario() {
       return salarioBase + (horasExtras *10);
   }

   public void imprimirReporte(){
    System.out.println("===Reporte Empleado===");
    System.out.println("Nombre:" + nombre);
    System.out.println("Salario Total:" + calcularSalario());
   }
}
//Refactorizacion
class EmpleadoSRP {
    private String nombre;
    private String salarioBase;
    private String horasExtras;

    public EmpleadoSRP(String nombre, String salarioBase, String horasExtras) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.horasExtras = horasExtras;
    }
    public String getNombre() {
        return nombre;
    }
    public String getSalarioBase() {
        return salarioBase;
    }
    public String getHorasExtras() {
        return horasExtras;
    }
}
class Salario {
    private double salarioBase;
    private double horasExtras;

    public Salario(double salarioBase, double horasExtras) {
        this.salarioBase = salarioBase;
        this.horasExtras = horasExtras;
    }

    public double calcularSalario() {
        return salarioBase + (horasExtras * 10);
    }
}
class Reporte {
    public static void imprimirReporte(EmpleadoSRP empleado, Salario salario) {
        System.out.println("===Reporte Empleado===");
        System.out.println("Nombre:" + empleado.getNombre());
        System.out.println("Salario Total:" + salario.calcularSalario());
    }
}
public class SRP {
    public static void main(String[] args) {
        EmpleadoSRP empleadoSRP = new EmpleadoSRP("Ana", "1200", "8");
        double salarioBase = Double.parseDouble(empleadoSRP.getSalarioBase());
        double horasExtras = Double.parseDouble(empleadoSRP.getHorasExtras());
        Salario salario = new Salario(salarioBase, horasExtras);
        Reporte.imprimirReporte(empleadoSRP, salario);
    }
}