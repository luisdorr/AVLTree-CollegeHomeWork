package model.structures;

public class AVLTree<T extends Comparable<T>> {
    private Node<T> root;

    public AVLTree() {
    }

    public AVLTree(T key) {
        this.root = new Node<T>(key);
    }

    public Node<T> getRoot() {
        return root;
    }

    //get balance factor of the node
    public int getHeight(Node<T> root) 
    {
        if(root != null) 
        {
            return Math.max(getHeight(root.getLeftChild()) + 1, getHeight(root.getRightChild()) + 1);
        }
        return -1;
    }
    public int getBalanceFactorOf(Node<T> root) 
    {
        return getHeight(root.getLeftChild()) - getHeight(root.getRightChild()) ;
    }

    //Rotations
    public Node<T> simpleLeftRotation(Node<T> root) 
    {
        Node<T> rightNode = root.getRightChild();
        Node<T> centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(root);
        root.setRightChild(centerNode);

        return rightNode;
    }
    public Node<T> simpleRightRotation(Node<T> root) 
    {
        Node<T> newRoot = root.getLeftChild();
        Node<T> rootNewLeftChild = newRoot.getRightChild();
        newRoot.setRightChild(root);
        root.setLeftChild(rootNewLeftChild);

        return newRoot;
    }
    public Node<T> doubleLeftRotation(Node<T> root) 
    {
        root.setRightChild(simpleRightRotation(root.getRightChild()));
        return simpleLeftRotation(root);
    }
    public Node<T> doubleRightRotation(Node<T> root)
    {
        root.setLeftChild(simpleLeftRotation(root.getLeftChild()));
        return simpleRightRotation(root);
    }

    public Node<T> balancingTheTree(Node<T> root) 
    {
        if (root != null)
        {
            if(getBalanceFactorOf(root) == 2) 
            {
                return (getBalanceFactorOf(root.getLeftChild()) >= 0 ? simpleRightRotation(root) : doubleRightRotation(root));
            } 
            else if(getBalanceFactorOf(root) == -2)
            {
                return (getBalanceFactorOf(root.getRightChild()) <= 0 ? simpleLeftRotation(root) : doubleLeftRotation(root));
            }
        }
        return root;
    }

    public Node<T> add(T key, Node<T> root) 
    {
        if (root == null) {
            root = new Node<>(key);
            return root;
        } else if (key.compareTo(root.getKey()) == -1) {
            root.setLeftChild(add(key, root.getLeftChild()));
        } else if (key.compareTo(root.getKey()) == 1) {
            root.setRightChild(add(key, root.getRightChild()));
        } else {
            System.out.println("ERROR,  THIS KEY ALREDY EXIST!");
            return root;
        } 
        return balancingTheTree(root);
    }

    public void add(T key) {
        this.root = add(key,root);
    }

    public Node<T> getTheBiggestLeftChild(Node<T> root){
        return goToTheRightChild(root.getLeftChild());
    }

    public Node<T> goToTheRightChild(Node<T> root) {
        if (root.getRightChild() == null) {
            return root;
        } else {
            return goToTheRightChild(root.getRightChild());
        }
    }

    public Node<T> remove(T key, Node<T> root) 
    {
        if (root == null) {
            System.out.println("This key is not in the tree.");
            return root;

        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeftChild(remove(key, root.getLeftChild()));

        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRightChild(remove(key, root.getRightChild()));

        } else if (key.compareTo(root.getKey()) == 0) {
            if(root.getLeftChild() == null || root.getRightChild() == null) {
                root = (root.getLeftChild() == null) ? root.getRightChild() : root.getLeftChild();
            } else {
                T copyKey = getTheBiggestLeftChild(root).getKey();
                root.setKey(copyKey);
                
                root.setLeftChild(remove(copyKey, root.getLeftChild()));
            }
        }

        return balancingTheTree(root);
    }

    public void remove(T key) {
        this.root = remove(key, root);
    }

    public boolean search(T key, Node<T> root) 
    {
        if (root == null) {
            return false;
        } else if (key.compareTo(root.getKey()) == -1) {
            return search(key, root.getLeftChild());
        } else if (key.compareTo(root.getKey()) == 1) {
            return search(key, root.getRightChild());
        } else {
            return true;
        }
    } 

    public String makeSpaces(int height)
    {
        String space = "";
        for(int i = 0; i < height; i++) {
            space = space + "    ";
        }
        return space;
    }

    public void printTree(Node<T> root, int height) 
    {
        if (root != null){
            height++;
            System.out.println(makeSpaces(height) + root);
            printTree(root.getLeftChild(),height);
            printTree(root.getRightChild(), height);
        }
    }

    public void inOrder(Node<T> root) 
    {
		if (root != null) {
			inOrder(root.getLeftChild());
			System.out.print(root.getKey() + " ");
			inOrder(root.getRightChild());
        }
	}
}
