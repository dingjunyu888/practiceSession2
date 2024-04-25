package solution;

import java.util.ArrayList;
import java.util.List;

class BinarySearchTree {

    /** An inner class representing a node in a binary search tree */
    private class BSTNode {
        int data; // value stored at the node
        BSTNode left; // left subtree
        BSTNode right; // right subtree

        BSTNode(int newdata) {
            data = newdata;
        }
    }

    private BSTNode root; // the root of the tree, an instance variable of class BinarySearchTree

    BinarySearchTree() {
        root = null; // initially, the tree is empty
    }


    /**
     * Check if the two trees, given by root1 and root2, are equal
     * Returns true if the trees have the same structure,
     * and the values at the corresponding nodes are the same.
     * @param root1 root of the first tree
     * @param root2 root of the second tree
     * @return
     */
    public static boolean checkIfIdentical(BSTNode root1, BSTNode root2) {
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1.data != root2.data){
            return false;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        else{
            return checkIfIdentical(root1.left, root2.left) && checkIfIdentical(root1.right, root2.right);
        }
    }

    /**
     * Computes the sum of the leaf values in the tree with the given root
     * @param root root of the tree
     * @return sum of leaf values
     */
    public int sumLeaves(BSTNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return root.data;
        }
        return sumLeaves(root.left) + sumLeaves(root.right);
    }


    /**
     * Returns the value of the largest element in the binary search tree
     * @param root root of the tree
     * @return value of the largest element
     */
    public int findLargest(BSTNode root) {
        BSTNode curr = root;
        while(curr.right != null){
            curr = curr.right;
        }
        return curr.data;
    }

    /**
     * Returns the value of the second largest element in the binary search tree
     * @param root root of the tree
     * @return value of the largest element
     */
    public int findSecondLargest(BSTNode root) {
        // Run inorder traversal, then take second from the end
        List<Integer> nodeValuesInorderTraversal = inorder(root);

        return nodeValuesInorderTraversal.get(nodeValuesInorderTraversal.size() - 2); // inorder is sorted for BST, the second largest is last to last
    }

    /** Runs inorder traversal, and places values in a list
     *
     * @param node root of the subtree
     * @return a list of BST values in order
     */
    private List<Integer> inorder(BSTNode node) {
        List<Integer> list = new ArrayList<>();
        if (node != null) {
            list.addAll(inorder(node.left));
            list.add(node.data);
            list.addAll(inorder(node.right));
        }
        return list;
    }

    /**
     * Insert a given element into the BST tree
     * @param elem element to insert into the BST tree
     */
    public void insert(int elem) {
        root = insert(root, elem);
    }

    /**
     * Insert elem into the tree with the given root
     * @param tree root of a tree
     * @param elem element to insert
     * @return the root of a tree that contains the new node
     */
    private BSTNode insert(BSTNode tree, int elem) {
        if (tree == null) {
            return new BSTNode(elem);
        }
        if (elem < tree.data) {
            tree.left = insert(tree.left, elem);
            return tree;
        } else {
            tree.right = insert(tree.right, elem);
            return tree;
        }
    }
    
    public static void main(String[] args) {
        BinarySearchTree tree1 = new BinarySearchTree();
        tree1.insert(4);
        tree1.insert(12);
        tree1.insert(10);
        tree1.insert(25);
        tree1.insert(3);
        tree1.insert(1);
        /* tree1:
               4
          3       12
       1       10    25

        */

        BinarySearchTree tree2 = new BinarySearchTree();
        tree2.insert(4);
        tree2.insert(12);
        tree2.insert(10);
        tree2.insert(25);
        tree2.insert(3);
        tree2.insert(1);

        BinarySearchTree tree3 = new BinarySearchTree();
        tree3.insert(4);
        tree3.insert(1);
        tree3.insert(3);
        tree3.insert(25);
        tree3.insert(10);
        tree3.insert(12);

        System.out.println(checkIfIdentical(tree1.root, tree2.root)); //true
        System.out.println(checkIfIdentical(tree1.root, tree3.root)); // false

        System.out.println(tree1.sumLeaves(tree1.root)); //36
        System.out.println(tree1.findLargest(tree1.root)); //25
        System.out.println(tree1.findSecondLargest(tree1.root)); //12
    }
}
