package utilidades;


import modelo.Email;
import estructuras.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Clase para leer los archivos donde se encuentran los mails
 */
public class MailReader {
  /**
   * Metodo que lee los arichivos donde se encuentran los mails
   * 
   * @param path
   * @return retorna una estructura de datos en la cual se encuentran contenidos
   *         los mails
   * @throws Exception
   */
  public static void fileReader(String path, AvlTree<Pair<Long, Email>> treeId,
      AvlTree<Pair<String, LinkedList<Email>>>... trees) {
    try {

      FileInputStream fstream;
      fstream = new FileInputStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

      String strLine;
      String mail = "";
      long id = 0;

      while ((strLine = br.readLine()) != null) {

        mail += (!strLine.equals("-.-.-:-.-.-") ? strLine + "\n" : "");

        if (strLine.equals("-.-.-:-.-.-") && !mail.equals("")) {
          Email email = Converter.converted(mail);
          email.setId(++id);
          Insert.insert(email, treeId, trees);
          mail = "";
        }
      }

      fstream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
