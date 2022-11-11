package model.structures;

public class AVLTree <T extends Comparable<T>>{
    private Node<T> root;
    private int height;

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
    public Node<T> getRoot(){
        return root;
    }

    private int height(Node<T> node) {
        if (node != null) {
            return node.getHeight();
        } else {
            return 0;
        }
    }
    public void heightUpdate(Node<T> node) {
        int newHeight = Math.max( height(node.getLeftChild()), height(node.getRightChild()) );
        node.setHeight(newHeight + 1);
    }

    public int balance(Node<T> node) {
        return (root != null) ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    public int getNodeHeight(Node<T> root){
        if (root == null) {
            return 0;

        } else {
            int leftHeight = getNodeHeight(root.getLeftChild()) + 1;
            int rightHeight = getNodeHeight(root.getRightChild()) + 1;
            return Math.max(leftHeight, rightHeight);
        }
    }

    public int getBalanceFactor(Node<T> node) {
        return getNodeHeight(node.getLeftChild()) - getNodeHeight(node.getRightChild());
    }

    public Node<T> treeRebalancing(Node<T> root) {
        if (getBalanceFactor(root) == 2) {
            if(getBalanceFactor(root.getLeftChild()) < 0) {
                doubleRightRotation(root);
            } else {
                return simpleRightRotation(root);
            }
        } else if (getBalanceFactor(root) == -2) {
            if(getBalanceFactor(root.getRightChild()) > 0) {
                return doubleLeftRotation(root);
            } else {
                return simpleLeftRotation(root);
            }
        }

        return root;
    }

    public void printTree(Node<T> root, int height) {
        if (root != null){
            height++;
            System.out.println(makeSpaces(height) + root);
            printTree(root.getLeftChild(),height);
            printTree(root.getRightChild(), height);
        }
    }

    //methods

    public Node<T> add(Node<T> root, T key) {
        if (root == null) {
            root = new Node<T>(key);
            return root;

        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeftChild(add(root.getLeftChild(),key));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRightChild(add(root.getRightChild(),key));
        } else if (key.compareTo(root.getKey()) == 0 && root != null) {
            System.out.println( "THIS NUMBER ALREADY EXIST!");
        }

        return root;
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

    public void inOrder(Node<T> root) {
		if (root != null) {
			inOrder(root.getLeftChild());
			System.out.print(root.getKey() + " ");
			inOrder(root.getRightChild());
        }
	}

    //ROTATIONS
    public Node<T> simpleRightRotation(Node<T> root) {
        Node<T> newRoot = root.getLeftChild();
        Node<T> rootsNewLeftChild = newRoot.getRightChild();
        newRoot.setRightChild(root);
        root.setLeftChild(rootsNewLeftChild);
        heightUpdate(root);
        heightUpdate(newRoot);

        return newRoot;
    }
    public Node<T> simpleLeftRotation(Node<T> root) {
        Node<T> newRoot = root.getRightChild();
        Node<T> rootsNewRightChild = newRoot.getLeftChild();
        newRoot.setLeftChild(root);
        root.setRightChild(rootsNewRightChild);
        heightUpdate(root);
        heightUpdate(newRoot);

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

    public String makeSpaces(int height) {
        String space = "";
        for(int i = 0; i < height; i++) {
            space = space + "    ";
        }
        return space;
    }



}