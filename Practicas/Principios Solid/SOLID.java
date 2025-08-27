package Tareas.Tarea1;
//No cumplia con los principios de responsabilidad unica y abierto/cerrado
interface CanalNotificacion {
    void enviar(String mensaje);
}

class EmailNotificacion implements CanalNotificacion {
    public void enviar(String mensaje) {
        System.out.println("Enviando Email: " + mensaje);
    }
}

class SMSNotificacion implements CanalNotificacion {
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}

class WhatsAppNotificacion implements CanalNotificacion {
    public void enviar(String mensaje) {
        System.out.println("Enviando WhatsApp: " + mensaje);
    }
}

public class SOLID {
    public static void main(String[] args) {
        CanalNotificacion email = new EmailNotificacion();
        CanalNotificacion sms = new SMSNotificacion();
        CanalNotificacion whatsapp = new WhatsAppNotificacion();

        email.enviar("Hola por Email");
        sms.enviar("Hola por SMS");
        whatsapp.enviar("Hola por WhatsApp");
    }
}
