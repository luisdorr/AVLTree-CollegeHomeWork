package model.structures;

import javax.management.RuntimeErrorException;

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

    //GETTERS
    public Node<T> getRoot(){return root;}
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
        } else if (key.compareTo(root.getKey()) == 0 && root != null) {
            throw new RuntimeErrorException(null, "THIS NUMBER ALREADY EXIST!");
        }
       
        return treeRebalancing(root);
    }

    public Node<T> remove(Node<T> root, T key) {
        if (root == null) {
            System.out.println("This key is not in the tree.");
            return root;

        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeftChild(remove(root.getLeftChild(),key));

        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRightChild(remove(root.getRightChild(),key));

        } else if (key.compareTo(root.getKey()) == 0) {
            if(root.getLeftChild() == null || root.getRightChild() == null) {
                root = (root.getLeftChild() == null) ? root.getRightChild() : root.getLeftChild();
            } else {
                T copyKey = getTheBiggestLeftChild(root).getKey();
                root.setKey(copyKey);
                
                root.setLeftChild(remove(root.getLeftChild(), key));
            }
        }

        return treeRebalancing(root);
    }  

    public Node<T> search(Node<T> root, T key) {
        
        if (root == null) {
            System.out.println("This key is not in the tree.");
            return root;
        } else if (key.compareTo(root.getKey()) < 0) {
            return search(root.getLeftChild(),key);

        } else if (key.compareTo(root.getKey()) > 0) {
            return search(root.getRightChild(),key);

        } else if (key.compareTo(root.getKey()) == 0) {
            return root;
        }

        return root;
    }


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

}