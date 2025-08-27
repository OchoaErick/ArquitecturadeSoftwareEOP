package Tareas.Tarea1;

//Principio Abierto/Cerrado
class CalculadoraArea{
    public double calcularArea(Object figura){
        if(figura instanceof Circulo){
            Circulo c = (Circulo) figura;
            return Math.PI * c.radio * c.radio;
        } else if (figura instanceof Rectangulo){
            Rectangulo r = (Rectangulo) figura;
            return r.ancho * r.alto;
        }
        return 0;
    }
}
class Circulo{
    double radio;
    public Circulo(double radio){
        this.radio = radio;
    }
}
class Rectangulo{
    double ancho;
    double alto;
    public Rectangulo(double ancho, double alto){
        this.ancho = ancho;
        this.alto = alto;
    }
}
//Refactorizacion (tambien deberiamos separar los metodos de calculo para cumplir con SRP?)
abstract class Figura {
    abstract double calcularArea();
}
class CirculoOCP extends Figura {
    double radio;
    public CirculoOCP(double radio){
        this.radio = radio;
    }
    @Override
    double calcularArea() {
        return Math.PI * radio * radio;
    }
}
class RectanguloOCP extends Figura {
    double ancho;
    double alto;
    public RectanguloOCP(double ancho, double alto){
        this.ancho = ancho;
        this.alto = alto;
    }
    @Override
    double calcularArea() {
        return ancho * alto;
    }
}
class CalculadoraAreaOCP{
    public double calcularArea(Figura figura){
        return figura.calcularArea();
    }
}
public class OCP {
    public static void main(String[] args) {
        CalculadoraAreaOCP calculadora = new CalculadoraAreaOCP();
        Figura circulo = new CirculoOCP(5);
        Figura rectangulo = new RectanguloOCP(4, 6);
        System.out.println("Area del circulo: " + calculadora.calcularArea(circulo));
        System.out.println("Area del rectangulo: " + calculadora.calcularArea(rectangulo));
    }
}