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
        // FILL IN CODE
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
        // FILL IN CODE
        if(root == null){
            return 0;
        }
        if(isLeave(root)){
            return root.data;
        }else{
            return sumLeaves(root.left) + sumLeaves(root.right);
        }
    }

    public boolean isLeave(BSTNode root){
        if(root.right == null && root.left == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the value of the largest element in the binary search tree
     * @param root root of the tree
     * @return value of the largest element
     */
    public int findLargest(BSTNode root) {
        BSTNode current = root;
        // FILL IN CODE: must be iterative
        if(current == null){
            throw new IllegalArgumentException();
        }
        while(current.right != null){
            current = current.right;
        }
        return current.data;
    }

    /**
     * Returns the value of the second largest element in the binary search tree
     * @param root root of the tree
     * @return value of the largest element
     */
    public int findSecondLargest(BSTNode root) {
        // FILL IN CODE:
        ArrayList<Integer> arr = new ArrayList<>();
        inorderTravers(root, arr);
        return arr.get(arr.size() - 2);
    }

    public void inorderTravers(BSTNode root, ArrayList<Integer> arr){
        if(root == null){
            return;
        }
        inorderTravers(root.left, arr);
        arr.add(root.data);
        inorderTravers(root.right, arr);
//        inorderTravers(root.right, arr);
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
