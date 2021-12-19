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
    JTextField inputSlangInput, inputDefInput;

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
        JButton addNewWordBtn = new JButton("Add new word");
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        searchBySlangBtn.setActionCommand("search-by-slang");
        searchByDefBtn.setActionCommand("search-by-def");
        showHistoryBtn.setActionCommand("show-history");
        addNewWordBtn.setActionCommand("add-word");
        searchBySlangBtn.addActionListener(hc);
        searchByDefBtn.addActionListener(hc);
        showHistoryBtn.addActionListener(hc);
        addNewWordBtn.addActionListener(hc);
        left.add(searchBySlangBtn);
        left.add(searchByDefBtn);
        left.add(showHistoryBtn);
        left.add(addNewWordBtn);

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

        JPanel addNewWordCard = new JPanel();
        JPanel addNewWordCardContainer = new JPanel();
        addNewWordCard.setLayout(new BorderLayout());
        addNewWordCardContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel inputSlangLabel = new JLabel("Enter slang");
        inputSlangInput = new JTextField(20);
        JLabel inputDefLabel = new JLabel("Enter definition");
        inputDefInput = new JTextField(20);
        JButton addNewWordSubmitBtn = new JButton("Add");
        addNewWordSubmitBtn.setActionCommand("add-word-submit");
        addNewWordSubmitBtn.addActionListener(this);
        c.gridx =0;
        c.gridy = 0;
        addNewWordCardContainer.add(inputSlangLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        addNewWordCardContainer.add(inputSlangInput, c);
        c.gridx = 0;
        c.gridy = 1;
        addNewWordCardContainer.add(inputDefLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        addNewWordCardContainer.add(inputDefInput, c);
        c.gridx = 0;
        c.gridy = 2;
        addNewWordCardContainer.add(addNewWordSubmitBtn, c);
        addNewWordCard.add(addNewWordCardContainer, BorderLayout.PAGE_START);
        right.add(addNewWordCard, "add-word");

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
                    }
                }
                searchByDefTable.setModel(new DefaultTableModel(utils.convertToObjectArray(filterDef), columns));
                break;
            }
            case "add-word-submit": {
                String slang = inputSlangInput.getText();
                String value = inputDefInput.getText();

                //Yes 0 No 1 Cancel 2 x -1
                if (!Objects.equals(list.get(slang), "null")) {
                    int choice = JOptionPane.showConfirmDialog(frame, "Word already exist. Yes to overwrite. No to duplicate");
                    if (choice == 0) {
                        list.put(slang, value);
                    }
                    else if (choice == 1) {
                        list.put(slang, list.get(slang) + " | " + value);
                    }
                }
                else {
                    list.put(slang, value);
                }
            }
        }
    }
}
