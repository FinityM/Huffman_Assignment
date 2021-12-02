package gui;

import algorithm.HuffmanTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI implements ActionListener {
    HuffmanTree tree = new HuffmanTree();
    JFrame gui;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JTextArea textArea1;
    JTextArea textArea2;
    JButton encode;
    JButton decode;
    Object message;
    String specialCharsRegex = "[\\\\!\"#*$%&()+,./:;<=>?@\\[\\]^_{|}~]+";

    public GUI() throws IOException {
        tree.readFreqTable();
        tree.genTree();
        gui = new JFrame();
        gui.getContentPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        textArea1 = new JTextArea();
        textArea2 = new JTextArea();
        textArea2.setEditable(false);

        encode = new JButton("Encode");
        decode = new JButton("Decode");

        encode.addActionListener(this);
        decode.addActionListener(this);

        textArea1.setPreferredSize(new Dimension(300, 200));
        textArea2.setPreferredSize(new Dimension(300, 200));

        panel1.setBackground(Color.decode("#F5D10D"));
        panel2.setBackground(Color.decode("#181818"));
        panel3.setBackground(Color.decode("#F5D10D"));

        panel1.add(textArea1);
        panel2.add(encode);
        panel2.add(decode);
        panel3.add(textArea2);

        gui.add(panel1);
        gui.add(panel2);
        gui.add(panel3);

        gui.setTitle("Huffman Tree Encoder and Decoder");
        gui.setLocation(600, 100);
        gui.setSize(500, 500);
        gui.setVisible(true);
        gui.setLayout(new GridLayout(0, 1));
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean encodeValid(char letter) {
        if (Character.isDigit(letter)) {
            textArea2.setText("You cannot enter numbers");
            System.out.println("You cannot enter numbers");
            return true;
        } else if (Character.isLowerCase(letter)) {
            textArea2.setText("You cannot enter lowercase letters");
            System.out.println("You cannot enter lowercase letters");
            return true;
        } else if (!Character.toString(letter).matches("[A-Z]")) {
            textArea2.setText("You can only enter capital letters");
            System.out.println("You can only enter capital letters");
            return true;
        } else {
            return false;
        }
    }

    public boolean decodeValid(String bindigits) {

        if (bindigits.matches("[a-zA-Z]+")) {
            textArea2.setText("You cannot decode letters");
            System.out.println("You cannot decode letters");
            return true;
        } else if (bindigits.matches("[2-9]")) {
            textArea2.setText("You can only enter 1 or 0");
            System.out.println("You can only enter 1 or 0");
            return true;
        } else if (bindigits.matches(specialCharsRegex)) {
            textArea2.setText("You cannot enter special characters");
            System.out.println("You cannot enter special characters");
            return true;

        } else if (bindigits.equals("")) {
            textArea2.setText("You did not type anything in the textbox");
            System.out.println("You did not type anything in the textbox");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encode) {
            System.out.println("Encode was pressed");
            message = textArea1.getText();

            // Insert function for validation and encoding
            // Exception catch for if the encode is pressed and no data is entered
            try {
                char validateChar = ((String) message).charAt(0);

                if (!encodeValid(validateChar)) {
                    tree.encode(tree.getTreeNodes(), "", (String) message);
                    textArea2.setText(tree.getEncodeRes());
                }

            } catch (Exception emptyE) {
                textArea2.setText("You did not enter anything on the textbox");
                System.out.println("You did not enter anything on the textbox");
            }

        } else if (e.getSource() == decode) {
            System.out.println("Decode was pressed");
            message = textArea1.getText();

            String binDigits = (String) message;

            String decodeDigits = tree.decode(binDigits);

            try {
                if (!decodeValid(binDigits) && !decodeDigits.equals("*")) {
                    textArea2.setText(tree.decode(binDigits));
                    System.out.println(tree.decode(binDigits));
                } else if (decodeDigits.equals("*") && !binDigits.equals("")) {
                    textArea2.setText("Not enough binary digits");
                    System.out.println("Not enough binary digits");
                } else if (binDigits.equals("")) {
                    textArea2.setText("You did not type anything in the textbox");
                    System.out.println("You did not type anything in the textbox");
                }
            } catch (Exception nullE) {
                textArea2.setText("Invalid input");
                System.out.println("Invalid input");

            }


        }

    }
}
