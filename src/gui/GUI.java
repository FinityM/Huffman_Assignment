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

        panel1.setBackground(Color.GRAY);
        panel2.setBackground(Color.BLACK);
        panel3.setBackground(Color.BLUE);

        panel1.add(textArea1);
        panel2.add(encode);
        panel2.add(decode);
        panel3.add(textArea2);

        gui.add(panel1);
        gui.add(panel2);
        gui.add(panel3);

        gui.setLocation(600, 100);
        gui.setSize(500, 500);
        gui.setVisible(true);
        gui.setLayout(new GridLayout(0, 1));
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encode) {
            System.out.println("Encode was pressed");
            message = textArea1.getText();
            // Insert function for validation

            // Encoding Function
            tree.encode(tree.getRoot(), "", (String) message);
            textArea2.setText(tree.getEncodeRes());

            System.out.println(tree.getEncodeRes());

        } else if (e.getSource() == decode) {
            System.out.println("Decode was pressed");
            message = textArea1.getText();
            // Insert function for validation

            // Decoding function
            textArea2.setText(tree.decode((String) message));
            System.out.println(message);

        }

    }
}
