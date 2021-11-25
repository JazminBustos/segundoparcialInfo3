package estructuras;

// Basic node stored in a linked list
// Note that this class is not accessible outside
// of package weiss.nonstandard

class TreeNode<AnyType extends Comparable<AnyType>> {
    public AnyType element;
    public AnyType key;
    public TreeNode<AnyType> right;
    public TreeNode<AnyType> left;

    // Constructors
    public TreeNode(AnyType theElement) {
        this(theElement, null, null);
    }

    public TreeNode(AnyType theElement, TreeNode<AnyType> r, TreeNode<AnyType> l) {
        element = theElement;
        right = r;
        left = l;
    }
}
