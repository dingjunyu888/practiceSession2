
/** PrefixTree class */
public class PrefixTree  {

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
     * Find words that end at the internal node, not a leaf node.
     * For instance, if our tree has "app" and "apple", this method should
     * print "app". Since "app" ends at an internal node, not at the leaf.

     */
    public void findWordsThatEndAtInternalNodes() {
        StringBuilder prefixBuilder = new StringBuilder();
        findWordsThatEndAtInternalNodes(prefixBuilder, root);
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

    /**
     * Find words that end at the internal node, not a leaf node.
     * For instance, if our tree has "app" and "apple", this method should
     * print "app".
     * @param prefix string builder that holds the prefix
     * @param node root of the subtree
     */
    private void findWordsThatEndAtInternalNodes(StringBuilder prefix, Node node) {
        // FILL IN CODE
        //base case
        if(node == null){
            return;
        }
        //
        if(node.isWord && !isLeaf(node)){
            System.out.println(prefix.toString());
        }
        for(int i = 0; i < node.children.length; i++){
            char ch = (char)((int)'a'+i);
            StringBuilder sb = new StringBuilder();
            sb.append(prefix);
            sb.append(ch);
            findWordsThatEndAtInternalNodes(sb, node.children[i]);
        }

    }

    private boolean isLeaf (Node node){
        for(int i = 0; i < node.children.length; i++){
            if(node.children[i] != null){
                return false;
            }
        }
        return true;
    }


    /**
     * Counts valid words in the tree
     * @param node root of the subtree
     * @return number of valid words
     */
    private int numValidWords(Node node) {
        int count = 0;
        // FILL IN CODE: count the number valid words in the subtree given by node
        if(node == null){
            return 0;
        }
        if(node.isWord){
            count += 1;
        }
        for(int i = 0; i < node.children.length; i++){
            count += numValidWords(node.children[i]);
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

