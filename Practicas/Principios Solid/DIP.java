package Tareas.Tarea1;

//Principio de Inversión de Dependencias
class EmailServicio{
    public void enviar(String mensaje){
        System.out.println("Enviando email: " + mensaje);
    }
}
//Clase de alto nivel que depende del detalle
class Notificador{
    private EmailServicio emailServicio = new EmailServicio();
    public void notificar(String mensaje){
        emailServicio.enviar(mensaje);
    }
}
//Refactorizacion
interface ServicioEnvio {
    void enviar(String mensaje);
}

class EmailServicioDIP implements ServicioEnvio {
    public void enviar(String mensaje){
        System.out.println("Enviando email: " + mensaje);
    }
}
interface NotificadorDIP {
    void notificar(String mensaje);
}

class NotificadorEmail implements NotificadorDIP {
    private ServicioEnvio servicioEnvio;
    public NotificadorEmail(ServicioEnvio servicioEnvio){
        this.servicioEnvio = servicioEnvio;
    }
    public void notificar(String mensaje){
        servicioEnvio.enviar(mensaje);
    }
}
public class DIP {
    public static void main(String[] args){
        ServicioEnvio emailServicio = new EmailServicioDIP();
        NotificadorDIP notificador = new NotificadorEmail(emailServicio);
        notificador.notificar("Hola");
    }
}