package utilidades;

import modelo.Email;

/**
 * Clase para convertir un string a un objeto de tipo modelo.Email
 */
public class Converter {
  /**
   * Convierte una cadena de texto a un objeto de tipo modelo.Email
   * La cadena de texto tiene que estar ordenada de la siguiente forma:
   *  1-date
   *  2-from
   *  3-to
   *  4-subject
   *  5-contenido
   * 
   * @param unConverted cadena de texto a convertir
   * @return objeto modelo.Email construido
   */
  public static Email converted(String unConverted) {
    Email converted = new Email();

    int prop = 0;

    while (converted.getContent() == null) {

      if (converted.getSubject() == null) {
        int initialIndex = unConverted.indexOf(" ") + 1;
        int finalIndex = unConverted.indexOf("\n");
        String aux = unConverted.substring(initialIndex, finalIndex);

        switch (prop) {
          case 0:
            converted.setDate(aux);
            ++prop;
            break;
          case 1:
            converted.setFrom(aux);
            ++prop;
            break;
          case 2:
            converted.setTo(aux);
            ++prop;
            break;
          case 3:
            converted.setSubject(aux);
            ++prop;
            break;
        }

        // System.out.println(aux);
        unConverted = unConverted.substring(finalIndex + 1);
      } else
        converted.setContent(unConverted);

    }

    return converted;
  }
}
