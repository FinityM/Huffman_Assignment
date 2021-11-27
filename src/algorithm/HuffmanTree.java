package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree {
    TreeNode root;
    TreeNode newNodes;
    ListArrayBased frqtable = new ListArrayBased();
    String s;
    String[] starr;
    String encodeRes;

    public void readFreqTable() throws IOException {
        File file = new File("Textfiles/LetterCountAscending.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int i = 1;

        while ((s = bufferedReader.readLine()) != null) {
            starr = s.split("\t", 2);
            String letters = starr[0];
            int numbers = Integer.parseInt(starr[1]);
            HuffItem freqItems = new HuffItem(letters, numbers);
            newNodes = new TreeNode(freqItems);
            frqtable.add(i++, newNodes);
        }

        bufferedReader.close();
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

            HuffItem leftItem = ((HuffItem) left.getItem());
            HuffItem rightItem = ((HuffItem) right.getItem());

            HuffItem sumFreq = new HuffItem("*", leftItem.getFreq() + rightItem.getFreq());

            TreeNode parentNode = new TreeNode(sumFreq, left, right);
            frqtable.add(frqtable.size() + 1, parentNode);
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
