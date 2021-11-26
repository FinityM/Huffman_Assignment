package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree {
    TreeNode root;
    ListArrayBased frqtable = new ListArrayBased();
    File file;
    BufferedReader reader;
    String s;
    String[] starr;
    String encodeRes;

    public void readFreqTable() throws IOException {
        int i = 1;
        file = new File("Textfiles/LetterCountAscending.txt");
        reader = new BufferedReader(new FileReader(file));

        while ((s = reader.readLine()) != null) {
            starr = s.split("\t", 2);
            frqtable.add(i++, new TreeNode(new HuffItem(starr[0], Integer.parseInt(starr[1]))));
        }

        reader.close();
        // Test if it can read the file and output
        System.out.println(file.exists());
        System.out.println(frqtable.size());

    }


    public void genTree() {

        while (frqtable.size() > 1) {
            TreeNode left = (TreeNode) frqtable.get(1);
            TreeNode right = (TreeNode) frqtable.get(2);

            frqtable.remove(1);
            frqtable.remove(1);

            TreeNode parent = new TreeNode(new HuffItem("*", ((HuffItem) left.getItem()).getFreq() + ((HuffItem) right.getItem()).getFreq()), left, right);
            frqtable.add(frqtable.size() + 1, parent);
            frqtable.bubbleSort();

        }

        root = (TreeNode) frqtable.get(1);

    }

    public String decode(String code) {
        TreeNode curr = root;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            if (c == '0') {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }
        return ((HuffItem) curr.getItem()).getSym();
    }

    public void encode(TreeNode rootNode, String code, String symbol) {

        if (((HuffItem) rootNode.getItem()).getSym().equals(symbol)) {
            encodeRes = code;
        }

        if (rootNode.getLeft() != null) {
            encode(rootNode.getLeft(), code + "0", symbol);
        }
        if (rootNode.getRight() != null) {
            encode(rootNode.getRight(), code + "1", symbol);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public String getEncodeRes() {
        return encodeRes;
    }
}
