package application;

import model.structures.AVLTree;
import model.structures.Node;

public class Program {

    public static void main(String[] args) {
        AVLTree<Integer> avore = new AVLTree<>(1);
        avore.add(avore.getRoot(), 21);
        
        

    }
}