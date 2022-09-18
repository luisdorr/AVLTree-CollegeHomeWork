package model.structures;

public class AVLTree <T extends Comparable<T>>{
    private Node<T> root;
    private int hieght;

    //constructors
    public AVLTree() {
        this.root = null;
    }
    public AVLTree(Node<T> root) {
        this.root = root;
    }
    public AVLTree(int key) {
        this.root = new Node(key);
    }

    //methods
    public int treeBalanceFactor() {
       return root.getBalanceFactor(root, 0);
    }

    public Node<T> add(Node<T> root, T key) {
        if (root == null) {
            root = new Node<T>(key);
            return root;

        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeftChild(add(root.getLeftChild(),key));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRightChild(add(root.getRightChild(),key));
        }
        // deve checar se esta desbalanceada, se estiver, deve rotacionar "Ha de implementar!"
        return root;
    }
   
    /* tentativa de procurar, mas sem recurcao
    public boolean lookFor(T key) {
        Node<T> node = this.root;

        while (node != null && key != node.getKey()) {
            if (key.compareTo(node.getKey()) < 0) {
                node = node.getLeftChild();
            } else if (key.compareTo(node.getKey()) > 0) {
                node = node.getRightChild();
            }
        }
        if (root == null) {
            return false;
        } else {
            return true;
        }
    }
     */
    
    public Node<T> simpleRightRotation(Node<T> root) {
        Node<T> newRoot = root.getLeftChild();
        Node<T> rootsNewLeftChild = newRoot.getRightChild();
        newRoot.setRightChild(root);
        root.setLeftChild(rootsNewLeftChild);

        return newRoot;
    }

    public Node<T> simpleLeftRotation(Node<T> root) {
        Node<T> newRoot = root.getRightChild();
        Node<T> rootsNewRightChild = newRoot.getLeftChild();
        newRoot.setLeftChild(root);
        root.setRightChild(rootsNewRightChild);

        return newRoot;
    }

    public Node<T> doubleRightRotation(Node<T> root) {
        simpleLeftRotation(root.getLeftChild());
        return simpleRightRotation(root);
    }
    public Node<T> doubleLeftRotation(Node<T> root) {
        simpleRightRotation(root.getRightChild());
        return simpleLeftRotation(root);
    }
    // aqui e onde vamos optar qual forma de rotacao teremos que fazer
    public Node<T> treeRebalancing(Node<T> root) {
        int balanceFactor = root.getBalanceFactor(root, 0);
        if (balanceFactor == 2) {
            if(root.getLeftChild().getBalanceFactor(root, 0) < 0) {
                doubleRightRotation(root);
            } else {
                simpleRightRotation(root);
            }
        } else if (balanceFactor == -2) {
            if(root.getLeftChild().getBalanceFactor(root, 0) > 0) {
                doubleLeftRotation(root);
            } else {
                simpleLeftRotation(root);
            }
        }
        return root;
    }

}