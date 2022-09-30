package model.structures;

public class Node <T extends Comparable<T>>{
    private Node<T> leftChild, rightChild;
    private T key;
    private int height;

    private int balanceFactor = 0;

    public Node(T key) {
        this.key = key;
    }

    //Getters & Setters
    public Node<T> getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(Node<T> leftChid) {
        this.leftChild = leftChid;
    }
    public Node<T> getRightChild() {
        return rightChild;
    }
    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
    public T getKey() {
        return key;
    }
    public void setKey(T key) {
        this.key = key;
    }
    public int getHeight(Node<T> root, int height) {
        if (root == null) {
            return height;
        } else {
            height++;
            setHeight(Math.max(
                root.getLeftChild().getHeight(root.getLeftChild(), height),
                root.getRightChild().getHeight(root.getRightChild(), height)
                ) - 1);
            return this.height;
        }
    }
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

    public int getBalanceFactor(Node<T> node, int height) {
        if (node == null) {
            return height;

        } else {
            height++;
            return getBalanceFactor(leftChild, height) - getBalanceFactor(rightChild, height);
        }
    }
}
