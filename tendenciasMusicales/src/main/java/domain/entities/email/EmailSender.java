package domain.entities.email;

public interface EmailSender {

    void enviarMail(String emisor, String destinatario, String asunto, String cuerpo);
}
