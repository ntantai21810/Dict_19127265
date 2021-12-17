package com.company.Dict_19127265;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();
        JPanel right = new JPanel();
        String[] columns = new String[]{"Slang", "Definition"};
        right.setLayout(new CardLayout());
        HandleCard hc = new HandleCard(right);
        JFrame frame = new JFrame("Dict");
        HashMap<String, String> list = utils.readFile(frame, "slang.txt");

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        JPanel left = new JPanel();
        JButton searchBySlangBtn = new JButton("Search by slang");
        JButton searchByDefBtn = new JButton("Search by definition");
        JButton showHistoryBtn = new JButton("Show history");
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        searchBySlangBtn.setActionCommand("search-by-slang");
        searchByDefBtn.setActionCommand("search-by-def");
        searchBySlangBtn.addActionListener(hc);
        searchByDefBtn.addActionListener(hc);
        left.add(searchBySlangBtn);
        left.add(searchByDefBtn);
        left.add(showHistoryBtn);

        JPanel searchBySlangCard = new JPanel();
        JPanel searchBySlangHeading = new JPanel();
        JLabel searchBySlagLabel = new JLabel("Enter slang");
        JTextField searchBySlangInput = new JTextField(20);
        JTable searchBySlangTable = new JTable();
        searchBySlangTable.setModel(new DefaultTableModel(utils.convertToObjectArray(list), columns));
        searchBySlangCard.setLayout(new BoxLayout(searchBySlangCard, BoxLayout.Y_AXIS));
        searchBySlangHeading.add(searchBySlagLabel);
        searchBySlangHeading.add(searchBySlangInput);
        searchBySlangCard.add(searchBySlangHeading);
        searchBySlangCard.add(new JScrollPane(searchBySlangTable));
        right.add(searchBySlangCard, "search-by-slang");

        JPanel searchByDefCard = new JPanel();
        JPanel searchByDefHeading = new JPanel();
        JLabel searchByDefLabel = new JLabel("Enter definition");
        JTextField searchByDefInput = new JTextField(20);
        JTable searchByDefTable = new JTable();
        searchByDefTable.setModel(new DefaultTableModel(utils.convertToObjectArray(list), columns));
        searchByDefCard.setLayout(new BoxLayout(searchByDefCard, BoxLayout.Y_AXIS));
        searchByDefHeading.add(searchByDefLabel);
        searchByDefHeading.add(searchByDefInput);
        searchByDefCard.add(searchByDefHeading);
        searchByDefCard.add(new JScrollPane(searchByDefTable));
        right.add(searchByDefCard, "search-by-def");

        container.add(left, BorderLayout.LINE_START);
        container.add(right, BorderLayout.CENTER);

        frame.add(container);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
    }
}
