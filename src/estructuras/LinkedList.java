package estructuras;


// LinkedList class
//
// CONSTRUCTION: with no initializer
// Access is via LinkedListIterator class
//
// ******************PUBLIC OPERATIONS*********************
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// LinkedListIterator zeroth( )
//                        --> Return position to prior to first
// LinkedListIterator first( )
//                        --> Return first position
// void insert( x, p )    --> Insert x after current iterator position p
// void remove( x )       --> Remove x
// LinkedListIterator find( x )
//                        --> Return position that views x
// LinkedListIterator findPrevious( x )
//                        --> Return position prior to x
// ******************ERRORS********************************
// No special errors

/**
 * Linked list implementation of the list using a header node. Access to the
 * list is via LinkedListIterator.
 *
 * @author Mark Allen Weiss
 * @see LinkedListIterator
 */
public class LinkedList<AnyType> {
  private ListNode<AnyType> header;

  /**
   * Construct the list
   */
  public LinkedList() {
    header = new ListNode<AnyType>(null);
  }

  /**
   * metodo que pasa los elementos de la lista a un arreglo
   * El arreglo a pasar necesita tener el mismo tamaño que la lista
   * 
   * @param <AnyType>   lista de donde sacar elementos
   * @param <AnyTpye>[] arreglo a donde ponerlos
   */
  public void toArray(LinkedList<AnyType> theList, AnyType[] arr) {
    LinkedListIterator<AnyType> itr;
    
    int i = 0;
    for (itr = theList.first(); itr.isValid(); itr.advance()) {
      arr[i] = itr.retrieve();
      i++;
    }

  }

  /**
   * Test if the list is logically empty.
   *
   * @return true if empty, false otherwise.
   */
  public boolean isEmpty() {
    return header.next == null;
  }

  /**
   * Make the list logically empty.
   */
  public void makeEmpty() {
    header.next = null;
  }

  /**
   * Return an iterator representing the header node.
   */
  public LinkedListIterator<AnyType> zeroth() {
    return new LinkedListIterator<AnyType>(header);
  }

  /**
   * Return an iterator representing the first node in the list. This operation is
   * valid for empty lists.
   */
  public LinkedListIterator<AnyType> first() {
    return new LinkedListIterator<AnyType>(header.next);
  }

  /**
   * Insert after p.
   *
   * @param x the item to insert.
   * @param p the position prior to the newly inserted item.
   */
  public void insert(AnyType x, LinkedListIterator<AnyType> p) {
    if (p != null && p.current != null)
      p.current.next = new ListNode<AnyType>(x, p.current.next);
  }

  /**
   * Return iterator corresponding to the first node containing an item.
   *
   * @param x the item to search for.
   * @return an iterator; iterator is not valid if item is not found.
   */
  public LinkedListIterator<AnyType> find(AnyType x) {
    ListNode<AnyType> itr = header.next;

    while (itr != null && !itr.element.equals(x))
      itr = itr.next;

    return new LinkedListIterator<AnyType>(itr);
  }

  /**
   * Return iterator prior to the first node containing an item.
   *
   * @param x the item to search for.
   * @return appropriate iterator if the item is found. Otherwise, the iterator
   *         corresponding to the last element in the list is returned.
   */
  public LinkedListIterator<AnyType> findPrevious(AnyType x) {
    ListNode<AnyType> itr = header;

    while (itr.next != null && !itr.next.element.equals(x))
      itr = itr.next;

    return new LinkedListIterator<AnyType>(itr);
  }

  /**
   * Remove the first occurrence of an item.
   *
   * @param x the item to remove.
   */
  public void remove(AnyType x) {
    LinkedListIterator<AnyType> p = findPrevious(x);
    remove(p);
  }

  public void remove(LinkedListIterator<AnyType> p) {
    if (p.current.next != null)
      p.current.next = p.current.next.next; // Bypass deleted node
  }

  // Simple print method
  public static <AnyType> void printList(LinkedList<AnyType> theList) {
    if (theList.isEmpty())
      System.out.print("Empty list");
    else {
      LinkedListIterator<AnyType> itr = theList.first();
      for (; itr.isValid(); itr.advance())
        System.out.print(itr.retrieve() + " ");
    }

    System.out.println();
  }

  // In this routine, LinkedList and LinkedListIterator are the
  // classes written in Section 17.2.
  public static <AnyType> int listSize(LinkedList<AnyType> theList) {
    LinkedListIterator<AnyType> itr;
    int size = 0;

    for (itr = theList.first(); itr.isValid(); itr.advance())
      size++;

    return size;
  }
  /*
   * @Override public String toString() { return header; }
   */
}
