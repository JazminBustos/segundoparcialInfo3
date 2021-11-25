package modelo;

/**
 * Clase con los datos de un email. Puede tener mas propiedades o metodos
 */
public class Email implements Comparable<Email> {

  private long id; // Id del Mail
  private String from; // Remitente del mail
  private String to; // destinatario del mail
  private String date; // Fecha de envio
  private String subject; // Asunto del mail
  private String content; // Contenido del mail.

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "modelo.Email [content=" + content + "\n, date=" + date + "\n, from=" + from + "\n, subject=" + subject + "\n, to="
        + to + "]";
  }

  @Override
  public int compareTo(Email arg0) {
    return this.getId() > arg0.getId() ? 1 : this.getId() < arg0.getId() ? -1 : 0;
  }

}
