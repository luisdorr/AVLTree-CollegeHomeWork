package application;

import model.structures.AVLTree;
import model.structures.Node;

public class Program {

    public static void main(String[] args) {
        AVLTree<Integer> avore = new AVLTree<>(50);
        avore.add(avore.getRoot(), 21);
        avore.add(avore.getRoot(), 12);
       
        /*avore.add(avore.getRoot(), 60);
        avore.add(avore.getRoot(), 55);
        avore.add(avore.getRoot(), 57);
        avore.add(avore.getRoot(), 70);*/

        
        avore.printTree(avore.getRoot(), 0);
        
        

    }
}