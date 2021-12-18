package com.company.Dict_19127265;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main implements ActionListener {
    public Utils utils = new Utils();
    public JFrame frame = new JFrame("Dict");
    String[] columns = new String[]{"Slang", "Definition"};
    public HashMap<String, String> filter = new HashMap<>();
    public HashMap<String, String> filterDef = new HashMap<>();
    public HashMap<String, String> list = utils.readFile(frame, "slang.txt");
    public LinkedList<String> historyList = new LinkedList<>();
    JTable searchBySlangTable, searchByDefTable, historyTable;
    JTextField searchBySlangInput, searchByDefInput;

    public Main() {
        JPanel right = new JPanel();
        right.setLayout(new CardLayout());
        HandleCard hc = new HandleCard(right);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        JPanel left = new JPanel();
        JButton searchBySlangBtn = new JButton("Search by slang");
        JButton searchByDefBtn = new JButton("Search by definition");
        JButton showHistoryBtn = new JButton("Show history");
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        searchBySlangBtn.setActionCommand("search-by-slang");
        searchByDefBtn.setActionCommand("search-by-def");
        showHistoryBtn.setActionCommand("show-history");
        searchBySlangBtn.addActionListener(hc);
        searchByDefBtn.addActionListener(hc);
        showHistoryBtn.addActionListener(hc);
        left.add(searchBySlangBtn);
        left.add(searchByDefBtn);
        left.add(showHistoryBtn);

        JPanel searchBySlangCard = new JPanel();
        JPanel searchBySlangHeading = new JPanel();
        JLabel searchBySlagLabel = new JLabel("Enter slang");
        searchBySlangInput = new JTextField(20);
        searchBySlangTable = new JTable();
        JButton searchSlang = new JButton("Search");
        searchSlang.setActionCommand("search-slang");
        searchSlang.addActionListener(this);
        searchBySlangTable.setModel(new DefaultTableModel(utils.convertToObjectArray(filter), columns));
        searchBySlangCard.setLayout(new BoxLayout(searchBySlangCard, BoxLayout.Y_AXIS));
        searchBySlangHeading.add(searchBySlagLabel);
        searchBySlangHeading.add(searchBySlangInput);
        searchBySlangCard.add(searchBySlangHeading);
        searchBySlangCard.add(searchSlang);
        searchBySlangCard.add(new JScrollPane(searchBySlangTable));
        right.add(searchBySlangCard, "search-by-slang");

        JPanel showHistoryCard = new JPanel();
        showHistoryCard.setLayout(new BoxLayout(showHistoryCard, BoxLayout.Y_AXIS));
        JLabel showHistoryLabel = new JLabel("History");
        JPanel showHistoryLabelContainer = new JPanel();
        historyTable = new JTable();
        historyTable.setModel(new DefaultTableModel(utils.convertToObjectArray(historyList), columns));
        showHistoryLabelContainer.add(showHistoryLabel);
        showHistoryCard.add(showHistoryLabelContainer);
        showHistoryCard.add(new JScrollPane(historyTable));
        right.add(showHistoryCard, "show-history");


        JPanel searchByDefCard = new JPanel();
        JPanel searchByDefHeading = new JPanel();
        JLabel searchByDefLabel = new JLabel("Enter definition");
        searchByDefInput = new JTextField(20);
        searchByDefTable = new JTable();
        JButton searchDef = new JButton("Search");
        searchDef.setActionCommand("search-def");
        searchDef.addActionListener(this);
        searchByDefTable.setModel(new DefaultTableModel(utils.convertToObjectArray(filterDef), columns));
        searchByDefCard.setLayout(new BoxLayout(searchByDefCard, BoxLayout.Y_AXIS));
        searchByDefHeading.add(searchByDefLabel);
        searchByDefHeading.add(searchByDefInput);
        searchByDefCard.add(searchByDefHeading);
        searchByDefCard.add(searchDef);
        searchByDefCard.add(new JScrollPane(searchByDefTable));
        right.add(searchByDefCard, "search-by-def");

        container.add(left, BorderLayout.LINE_START);
        container.add(right, BorderLayout.CENTER);

        frame.add(container);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "search-slang": {
                String searchString = searchBySlangInput.getText();
                String value = list.get(searchString);
                filter.clear();
                filter.put(searchString, value);
                historyList.addFirst(searchString + "`" + value);
                searchBySlangTable.setModel(new DefaultTableModel(utils.convertToObjectArray(filter), columns));
                historyTable.setModel(new DefaultTableModel(utils.convertToObjectArray(historyList), columns));
                break;
            }
            case "search-def": {
                String searchString = searchByDefInput.getText();
                filterDef.clear();
                for (Map.Entry<String, String> entry : list.entrySet()) {
                    if (entry.getValue().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT))) {
                        filterDef.put(entry.getKey(), entry.getValue());
                        historyList.addFirst(entry.getKey() + "`" + entry.getValue());
                    }
                }
                historyTable.setModel(new DefaultTableModel(utils.convertToObjectArray(historyList), columns));
                searchByDefTable.setModel(new DefaultTableModel(utils.convertToObjectArray(filterDef), columns));
                break;
            }
        }
    }
}
