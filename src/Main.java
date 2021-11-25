import modelo.Email;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try {
      MailManager mailManager = new MailManager("src/bd/mails-20.txt");
      Scanner scan = new Scanner(System.in);
      String key = "";

      while (!key.equals("10")) {
        System.out.println(      "***Elija una opcion***");
        System.out.println("   1 Agregar mails al gestor");
        System.out.println("   2 Borrar mails del gestor");
        System.out.println("   3 Mostrar mails ordenados por fecha");
        System.out.println("   4 Filtrar mails desde, hasta una fecha");
        System.out.println("   5 Mostrar mails ordenados por remitente");
        System.out.println("   6 Buscar mails por remitente ");
        System.out.println("   7 Buscar mails por palabras del texto o asunto");
        System.out.println("   8 Salir");

        System.out.print(" seleccion:");
        key = scan.nextLine();
        switch (key) {
          //NUEVO MAIL
        case "1":
          Email mail = new Email();
          System.out.println("DE: ");
          mail.setFrom(scan.nextLine());
          System.out.println("PARA: ");
          mail.setTo(scan.nextLine());
          System.out.println("ASUNTO: ");
          mail.setSubject(scan.nextLine());
          System.out.println("CONTENIDO: ");
          mail.setContent(scan.nextLine());
          mail.setDate(LocalDateTime.now().toString());
          mailManager.addMail(mail);
          System.out.println(mail);
          break;

        //BORRAR MAIL POR ID
        case "2":
          System.out.println("Borrar por ID: ");
          long id = scan.nextInt();
          mailManager.deleteMail(id);
          break;
          //MOSTRAR MAILS ORDENADOS
        case "3":
          Email [] emails = mailManager.getSortedByDate();
          for (int i = 0; i < emails.length; i++) {
            System.out.println(emails[i]);
          }
          break;

          // Mostar todos por Fecha (desde - hasta)
        case "4":
          System.out.println("DESDE: con el formato: \"2021-11-23 08:10\"");
          String init = scan.nextLine();
          System.out.println("HASTA: con el formato: \"2021-11-23 08:10\"");
          String end = scan.nextLine();
          Email [] emails4 = mailManager.getSortedByDate(init, end);
          for (int i = 0; i < emails4.length; i++) {
            System.out.println(emails4[i]);
          }
          break;
          //Mostrar mails ordenados por remitente
        case "5":
          Email[] emails5 = mailManager.getSortedByFrom();
          for (int i = 0; i < emails5.length; i++) {
            System.out.println(emails5[i]);
          }
          break;
          //BUSCAR MAILS POR REMITENTE
        case "6":
          System.out.println("BUSCAR POR REMITENTE Ej: \"jaazbustos@gmail.com\"");
          Email[] emails6 = mailManager.getByFrom(scan.nextLine());
          for (int i = 0; i < emails6.length; i++) {
            System.out.println(emails6[i]);
          }
          break;
          //Busca por Asunto o Contenido
        case "7":
          System.out.println("BUSCAR POR ASUNTO O CONTENIDO: ");
          Email[] emails7 = mailManager.getByQuery(scan.nextLine());
          for (int i = 0; i < emails7.length; i++) {
            System.out.println(emails7[i]);
          }
          break;
          //SALIR
          case "8":
            System.out.println("SALIR");
            break;
        default:
          System.out.println("NO ES UNA OPCION VALIDA");
          break;
        }
      }
      scan.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
