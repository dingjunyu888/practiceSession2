package solution;

/** PrefixTree class */
public class PrefixTree {

    // --------- Private class Node ------------
    // Represents a node in a  prefix tree
    private class Node {
        Node children[]; // array of children (26 children)
        boolean isWord; // true if by concatenating "edges" on the path from the root to this node, we get a valid word

        Node() {
            isWord = false;
            children = new Node[26]; // initialize the array of children
        }
    }

    private Node root; // the root of the tree

    /**
     * Default constructor
     */
    public PrefixTree() {
        root = new Node();
    }

    /**
     * Adds a given word to the dictionary.
     *
     * @param word the word to add to the dictionary
     */
    public void add(String word) {
        add(word.toLowerCase(), root);
    }

    /**
     * Returns a number of valid words in the tree
     * @return number of words
     */
    public int numValidWords() {
        return numValidWords(root);
    }


    /**
     * A private add method that adds a given string to the tree
     *
     * @param s    the string to add
     * @param node the root of a  subtree where we want to add a new string
     */
    private void add(String s, Node node) {
        if (s.isEmpty()) {
            node.isWord = true;
            return;
        }

        int index = (int) s.charAt(0) - (int) 'a';
        if (node.children[index] == null)
            node.children[index] = new Node();
        add(s.substring(1), node.children[index]);
    }

    /** The node is a leaf if all the children are null
     *
     * @param node root of the subtree
     * @return true if this node is a leaf, false otherwise.
     */
    private boolean isLeaf(Node node) {
        for (int i = 0; i < node.children.length; i++)
            if (node.children[i] != null)
                return false;
        return true;
    }

    private void findWordsThatEndAtInternalNodes(StringBuilder prefix, Node node) {
        if (node == null)
            return;

        if (node.isWord && !isLeaf(node)) {
            System.out.println(prefix);
            // Note: we should not return here, there could be other non-leaf "true" nodes below this one
        }
        for (int i = 0; i < node.children.length; i++) {
            char ch = (char)((int)'a' + i);
            StringBuilder childSB = new StringBuilder(); // need to reset the StringBuilder for each child to contain only the prefix from above, not from other children
            childSB.append(prefix);
            childSB.append(ch);
            findWordsThatEndAtInternalNodes(childSB, node.children[i]);
        }

    }


    private int numValidWords(Node node) {
        int count = 0;
        if (node == null)
            return 0;
        if (node.isWord)
            count +=1;
        for (Node child: node.children) {
            count += numValidWords(child);
        }
        return count;
    }


    public static void main(String[] args) {
        PrefixTree tree = new PrefixTree();
        tree.add("apple");
        tree.add("app");
        tree.add("dog");
        tree.add("doge");
        tree.add("document");
        tree.add("cat");
        tree.add("caterpillar");
        tree.add("belong");
        tree.add("belonging");
        tree.add("belongings");

        System.out.println(tree.numValidWords()); // 10
        StringBuilder sb = new StringBuilder();
        tree.findWordsThatEndAtInternalNodes(sb, tree.root);
        /*
          app
          belong
          belonging
          cat
          dog
         */
    }

}

