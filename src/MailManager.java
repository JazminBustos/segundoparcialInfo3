import modelo.Email;
import estructuras.*;
import utilidades.*;

public class MailManager {
  AvlTree<Pair<String, LinkedList<Email>>> treeDate = new AvlTree<>();
  AvlTree<Pair<String, LinkedList<Email>>> treeFrom = new AvlTree<>();
  AvlTree<Pair<Long, Email>> treeId = new AvlTree<>();

  /**
   * Constructor de la clase la cual necesita el la ruta hacia el archivo que
   * contenga a los Mails
   * 
   * @param path
   * @throws Exception
   */
  MailManager(String path) {
    MailReader.fileReader(path, treeId, treeDate, treeFrom);
  }

  /**
   * Agrega un mail al gestor
   *
   * @param m mail a agregar
   * @throws Exception
   */
  public void addMail(Email m) throws Exception {
    long idTmp = treeId.findMax().getValor().getId() + 1;
    m.setId(idTmp);
    Insert.insert(m, treeId, treeDate, treeFrom);
  }

  /**
   * Elimina un mail del gestor
   *
   * @param id identificador del mail a borrar
   * @throws Exception
   */
  public void deleteMail(long id) throws Exception {
    Email mail = new Email();
    Pair<Long, Email> pair = treeId.find(new Pair(id, null));
    mail = (Email) pair.getValor();
    treeId.remove(new Pair(id, null));
    treeDate.remove(new Pair(mail.getDate(), null));
    treeFrom.remove(new Pair(mail.getFrom(), null));
  }

  /**
   * Devuelve una lista de mails ordenados por fecha
   *
   * @return lista de mails ordenados
   */
  public Email[] getSortedByDate() {
    return getSortedByIniDate("0000-00-00 00:00");
  }

  /**
   * Devuelve una lista de mails ordenados desde una fecha en adelante
   *
   * @return lista de mails ordenados
   */
  public Email[] getSortedByIniDate(String desde) {

    LinkedList<Email> list = new LinkedList<>();

    getSortedByIniDate(treeId.getRoot(), desde, list);

    Email[] emails = new Email[LinkedList.listSize(list)];
    list.toArray(list, emails);

    return emails;
  }

  private void getSortedByIniDate(AvlNode<Pair<Long, Email>> node, String desde, LinkedList<Email> list) {

    if (node == null)
      return;

    // Recurcion en el hijo izquierdo
    getSortedByIniDate(node.left, desde, list);

    // inserto los nodos a la lista si la date es mayor al desde
    if (node.element.getValor().getDate().compareTo(desde) >= 0) {
      list.insert(node.element.getValor(), list.zeroth());
    }

    // Recurcion en el hijo izquierdo
    getSortedByIniDate(node.right, desde, list);

  }

  /**
   * Devuelve una lista de mails ordenados hasta una fecha
   *
   * @return lista de mails ordenados
   */
  public Email[] getSortedByEndDate(String hasta) {

    LinkedList<Email> list = new LinkedList<>();

    getSortedByEndDate(treeId.getRoot(), hasta, list);

    Email[] emails = new Email[LinkedList.listSize(list)];
    list.toArray(list, emails);

    return emails;
  }

  private void getSortedByEndDate(AvlNode<Pair<Long, Email>> node, String hasta, LinkedList<Email> list) {

    if (node == null)
      return;

    // Recurcion en el hijo izquierdo
    getSortedByIniDate(node.left, hasta, list);

    // inserto los nodos a la lista si la date es mayor al desde
    if (node.element.getValor().getDate().compareTo(hasta) <= 0) {
      list.insert(node.element.getValor(), list.zeroth());
    }

    // Recurcion en el hijo izquierdo
    getSortedByIniDate(node.right, hasta, list);

  }

  /**
   * Devuelve una lista de mails oredenados por fecha que estan en el rango desde
   * - hasta
   *
   * @param desde Fecha desde donde buscar
   * @param hasta Fecha hasta donde buscar
   * @return lista de mails ord-enados
   */
  public Email[] getSortedByDate(String desde, String hasta) {
    LinkedList<Email> list = new LinkedList<>();

    getSortedByDate2(treeId.getRoot(), desde, hasta, list);
    //LinkedList.printList(list);

    Email[] emails = new Email[LinkedList.listSize(list)];
    list.toArray(list, emails);

    return emails;
  }

  private void getSortedByDate2(AvlNode<Pair<Long, Email>> node, String desde, String hasta, LinkedList<Email> list) {
    if (node == null)
      return;

    // Recurcion en el hijo izquierdo
    getSortedByDate2(node.left, desde, hasta, list);

    // inserto los nodos a la lista si la date se encuentra entre desde y hasta
    if (node.element.getValor().getDate().compareTo(desde) >= 0
        && node.element.getValor().getDate().compareTo(hasta) <= 0) {
      list.insert(node.element.getValor(), list.zeroth());
    }

    // Recurcion en el hijo derecho
    getSortedByDate2(node.right, desde, hasta, list);

  }

  /**
   * Devuelve una lista de mails ordenados por Remitente
   *
   * @return lista de mails ordenados
   */
  public Email[] getSortedByFrom() {
    LinkedList<Pair<String, LinkedList<Email>>> list = treeFrom.getList();
    LinkedList<Email> listtmp = new LinkedList<>();
    Email[] emails = new Email[LinkedList.listSize(treeId.getList())];

    Pair<String, LinkedList<Email>>[] pares = new Pair[LinkedList.listSize(list)];

    list.toArray(list, pares);

    for (int i = 0; i < pares.length; i++) {
      Email[] tmp = new Email[LinkedList.listSize(pares[i].getValor())];
      LinkedList<Email> tmp2 = pares[i].getValor();
      tmp2.toArray(tmp2, tmp);
      for (int k = 0; k < tmp.length; k++) {
        listtmp.insert(tmp[k], listtmp.zeroth());
      }

    }

    listtmp.toArray(listtmp, emails);

    return emails;
  }

  /**
   * Devuelve una lista de mails de un determinado remitente
   *
   * @param from String con direccion del remitente
   * @return lista de mails del remitente
   */
  public Email[] getByFrom(String from) {

    Pair<String, LinkedList<Email>> pairAux = treeFrom.find(new Pair(from, null));
    LinkedList<Email> list = pairAux.getValor();
    Email[] emails = new Email[LinkedList.listSize(list)];
    list.toArray(list, emails);
    return emails;
  }

  /**
   * Devuelve una lista de mails que contengan las palabras de 'query' en su
   * asunto o en su contenido
   *
   * @param query String con palabra/s a buscar
   * @return lista de mails que contienen dicha/s palabra/s
   */
  public Email[] getByQuery(String query) {
    LinkedList<Email> list = new LinkedList<>();
    getByQuery(treeId.getRoot(), query, list);
    Email[] emails = new Email[LinkedList.listSize(list)];
    list.toArray(list, emails);
    return emails;
  }

  private void getByQuery(AvlNode<Pair<Long, Email>> node, String query, LinkedList<Email> list) {
    if (node == null)
      return;

    // Recurcion en el hijo izquierdo
    getByQuery(node.left, query, list);

    // inserto los nodos a la lista si la query se encuentra en el contenido o en el
    // asunto
    if (node.element.getValor().getContent().contains(query) || node.element.getValor().getSubject().contains(query)) {
      if((list.find(node.element.getValor()) != null)){
        list.insert(node.element.getValor(), list.zeroth());
      }

    }

    // Recurcion en el hijo izquierdo
    getByQuery(node.right, query, list);
  }
}
  