package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree {
    // Variables for reading the table and generating the tree
    ListArrayBased frqtable = new ListArrayBased();
    TreeNode newNodes;
    String s;
    String[] starr;
    String encodeRes;

    // Treenode variable for the encode method
    TreeNode treeNode;

    // Read frequency table method
    public void readFreqTable() throws IOException {
        // Declare variables for reading the textfile located in its respective directory
        File file = new File("Textfiles/LetterCountAscending.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Beginning of while loop for the reading of the file
        int i = 1;

        // While the line is not null
        while ((s = bufferedReader.readLine()) != null) {
            // String array is equals to the string on the text file with a space in between the letter and frequency
            starr = s.split("\t", 2);

            // The letters are stored in index 0 so initialise it to that index
            String letters = starr[0];

            // The numbers are stored in index 1 so initialise it to that index
            int numbers = Integer.parseInt(starr[1]);

            // Initialise the letters and numbers
            HuffItem freqItems = new HuffItem(letters, numbers);

            // New nodes are equal to what's in the Huff Item class
            newNodes = new TreeNode(freqItems);

            // Add the nodes to the List array for later calculations
            frqtable.add(i++, newNodes);
        }

        // End the buffered reader
        bufferedReader.close();

        // Test if it can read the file and output
        System.out.println(file.exists());
        System.out.println(frqtable.size());

    }

    // Generate tree method
    public void genTree() {

        // While freq. table is greater than 1 than add the left and right side to the treenode
        while (frqtable.size() > 1) {

            // Variables for the left nodes and right nodes
            TreeNode left = (TreeNode) frqtable.get(1);
            TreeNode right = (TreeNode) frqtable.get(2);

            // Remove the items from list array based so the next items can be added
            frqtable.remove(1);
            frqtable.remove(1);

            // Variable initialised to represent the items from the frequency table
            HuffItem leftItem = ((HuffItem) left.getItem());
            HuffItem rightItem = ((HuffItem) right.getItem());

            // Sum the frequencies and represent as an asterisk
            HuffItem sumFreq = new HuffItem("*", leftItem.getFreq() + rightItem.getFreq());

            // initialise the parent node so the node to be added can detect it
            TreeNode parentNode = new TreeNode(sumFreq, left, right);

            // Adds items into the tree based on the last item on the list array which is the sum of the frequency which is how huffman tree algorithms build the tree
            frqtable.add(frqtable.size() + 1, parentNode);

            // Moves the frequency above the list and repeats the cycle for summing the frequencies
            frqtable.bubbleSort();

        }

        // Finally get the root node once the tree is completed which is equal to the final sum of the frequency table
        treeNode = (TreeNode) frqtable.get(1);
    }

    // Decode method
    public String decode(String code) {
        // Initialise the current node to the root node
        TreeNode curr = treeNode;

        // Declare a character variable to represent the left and right sides which are 0 and 1 respectively
        char c;

        // For loop to iterate through the tree
        for (int i = 0; i < code.length(); i++) {

            // Binary digit is a character
            c = code.charAt(i);

            // If its 0 get the left item else get the right which is 1
            if (c == '0') {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }
        // Initialise a string for the letter or symbol that the binary digits go to
        String symbol = ((HuffItem) curr.getItem()).getSym();

        // Return the symbol
        return symbol;
    }

    // Encode method, recursive call
    public void encode(TreeNode treeNds, String code, String symbol) {

        // The letter is initialised to the symbol
        String letter = ((HuffItem) treeNds.getItem()).getSym();

        // If the letter is equals to the symbol
        if (letter.equals(symbol)) {
            // Initialise the global variable in order to pass it back out due to the method being void and recursive
            encodeRes = code;
        }

        // If the left node or right node is not null
        if (treeNds.getLeft() != null) {
            // Get the left node and encode it to 0
            encode(treeNds.getLeft(), code + "0", symbol);
        }
        // Get the left node and encode it to 1
        if (treeNds.getRight() != null) {
            encode(treeNds.getRight(), code + "1", symbol);
        }
    }

    // Get method for the tree nodes to be used in the encode method on the gui
    public TreeNode getTreeNodes() {
        return treeNode;
    }

    // Get method to get the encode result and output to the text area
    public String getEncodeRes() {
        return encodeRes;
    }
}
