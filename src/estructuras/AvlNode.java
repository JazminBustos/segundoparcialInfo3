package estructuras;

public class AvlNode<AnyType> {
  // Constructors
  AvlNode(AnyType theElement) {
    this(theElement, null, null);
  }

  AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
    element = theElement;
    left = lt;
    right = rt;
    height = 0;
  }

  public AnyType element; // The data in the node
  public AvlNode<AnyType> left; // Left child
  public AvlNode<AnyType> right; // Right child
  int height; // Height
}