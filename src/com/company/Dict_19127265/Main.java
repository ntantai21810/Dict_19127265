package com.company.Dict_19127265;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

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
    JTextField editSlangInput, editDefInput;
    JTextField deleteSlangInput;

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
        JButton editWordBtn = new JButton("Edit word");
        JButton deleteWordBtn = new JButton("Delete word");
        JButton resetWordBtn = new JButton("Reset default slang word");
        JButton randomSlangWord = new JButton("Random a slang word");
        JButton quizBySlang = new JButton("Quiz by slang");
        JButton quizByWord = new JButton("Quiz by word");
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        searchBySlangBtn.setActionCommand("search-by-slang");
        searchByDefBtn.setActionCommand("search-by-def");
        showHistoryBtn.setActionCommand("show-history");
        addNewWordBtn.setActionCommand("add-word");
        editWordBtn.setActionCommand("edit-word");
        deleteWordBtn.setActionCommand("delete-word");
        resetWordBtn.setActionCommand("reset-word");
        randomSlangWord.setActionCommand("random-slang");
        quizBySlang.setActionCommand("quiz-by-slang");
        quizByWord.setActionCommand("quiz-by-word");
        searchBySlangBtn.addActionListener(hc);
        searchByDefBtn.addActionListener(hc);
        showHistoryBtn.addActionListener(hc);
        addNewWordBtn.addActionListener(hc);
        editWordBtn.addActionListener(hc);
        deleteWordBtn.addActionListener(hc);
        resetWordBtn.addActionListener(this);
        randomSlangWord.addActionListener(this);
        quizBySlang.addActionListener(this);
        quizByWord.addActionListener(this);
        left.add(searchBySlangBtn);
        left.add(searchByDefBtn);
        left.add(showHistoryBtn);
        left.add(addNewWordBtn);
        left.add(editWordBtn);
        left.add(deleteWordBtn);
        left.add(resetWordBtn);
        left.add(randomSlangWord);
        left.add(quizBySlang);
        left.add(quizByWord);

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

        JPanel editWordCard = new JPanel();
        JPanel editWordContainer = new JPanel();
        editWordCard.setLayout(new BorderLayout());
        editWordContainer.setLayout(new GridBagLayout());
        JLabel editSlangLabel = new JLabel("Enter slang");
        editSlangInput = new JTextField(20);
        JLabel editDefLabel = new JLabel("Enter definition");
        editDefInput = new JTextField(20);
        JButton editWordSubmitBtn = new JButton("Edit");
        editWordSubmitBtn.setActionCommand("edit-word-submit");
        editWordSubmitBtn.addActionListener(this);
        c.gridx =0;
        c.gridy = 0;
        editWordContainer.add(editSlangLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        editWordContainer.add(editSlangInput, c);
        c.gridx = 0;
        c.gridy = 1;
        editWordContainer.add(editDefLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        editWordContainer.add(editDefInput, c);
        c.gridx = 0;
        c.gridy = 2;
        editWordContainer.add(editWordSubmitBtn, c);
        editWordCard.add(editWordContainer, BorderLayout.PAGE_START);
        right.add(editWordCard, "edit-word");

        JPanel deleteWordCard = new JPanel();
        JPanel deleteWordContainer = new JPanel();
        deleteWordCard.setLayout(new BorderLayout());
        deleteWordContainer.setLayout(new GridBagLayout());
        JLabel deleteSlangLabel = new JLabel("Enter slang");
        deleteSlangInput = new JTextField(20);
        JButton deleteWordSubmitBtn = new JButton("Delete");
        deleteWordSubmitBtn.setActionCommand("delete-word-submit");
        deleteWordSubmitBtn.addActionListener(this);
        c.gridx =0;
        c.gridy = 0;
        deleteWordContainer.add(deleteSlangLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        deleteWordContainer.add(deleteSlangInput, c);
        c.gridx = 0;
        c.gridy = 2;
        deleteWordContainer.add(deleteWordSubmitBtn, c);
        deleteWordCard.add(deleteWordContainer, BorderLayout.PAGE_START);
        right.add(deleteWordCard, "delete-word");

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
                if (list.get(slang) != null) {
                    int choice = JOptionPane.showConfirmDialog(frame, "Word already exist. Yes to overwrite. No to duplicate");
                    if (choice == 0) {
                        list.put(slang, value);
                        utils.showDialog(frame, "Add success");
                    }
                    else if (choice == 1) {
                        list.put(slang, list.get(slang) + " | " + value);
                        utils.showDialog(frame, "Add success");
                    }
                }
                else {
                    list.put(slang, value);
                    utils.showDialog(frame, "Add success");
                }
                break;
            }
            case "edit-word-submit": {
                String slangEdit = editSlangInput.getText();
                String defEdit = editDefInput.getText();

                if (list.get(slangEdit) == null) {
                    utils.showDialog(frame, "Slang not found");
                }
                else {
                    list.put(slangEdit, defEdit);
                    utils.showDialog(frame, "Edit success");
                }
                break;
            }
            case "delete-word-submit": {
                String slang = deleteSlangInput.getText();

                if (list.get(slang) == null) {
                    utils.showDialog(frame, "Slang not found");
                }
                else {
                    int input = JOptionPane.showConfirmDialog(frame, "Do you want to delete?");
                    //Yes 0 No 1 Cancel 2 Exit -1
                    if (input == 0) {
                        list.remove(slang);
                        utils.showDialog(frame, "Delete success");
                    }
                }
                break;
            }
            case "reset-word": {
                list = utils.readFile(frame, "slang.txt");
                utils.showDialog(frame, "Reset success");
                break;
            }
            case "random-slang": {
                List<String> keysList = new ArrayList<>(list.keySet());
                int randomIndex = new Random().nextInt(keysList.size());
                String message = keysList.get(randomIndex) + "   ===>   " + list.get(keysList.get(randomIndex));
                utils.showDialog(frame, message);
                break;
            }
            case "quiz-by-slang": {
                List<String> keysList = new ArrayList<>(list.keySet());
                Vector<Integer> ans = new Vector<>();
                int idx1 = new Random().nextInt(keysList.size());
                int idx2 = new Random().nextInt(keysList.size());
                int idx3 = new Random().nextInt(keysList.size());
                int idx4 = new Random().nextInt(keysList.size());
                ans.add(idx1);
                ans.add(idx2);
                ans.add(idx3);
                ans.add(idx4);
                int idxAns = new Random().nextInt(4);
                JRadioButton ans1 = new JRadioButton(list.get(keysList.get(idx1)));
                JRadioButton ans2 = new JRadioButton(list.get(keysList.get(idx2)));
                JRadioButton ans3 = new JRadioButton(list.get(keysList.get(idx3)));
                JRadioButton ans4 = new JRadioButton(list.get(keysList.get(idx4)));
                JDialog dialog = new JDialog(frame, "Quiz by slang");
                JLabel label = new JLabel(keysList.get(ans.get(idxAns)));
                JPanel container = new JPanel();
                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                container.add(label);
                container.add(ans1);
                container.add(ans2);
                container.add(ans3);
                container.add(ans4);
                dialog.add(container);

                ans1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx1 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });
                ans2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx2 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });
                ans3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx3 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });
                ans4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx4 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });

                dialog.setSize(500, 300);
                dialog.setVisible(true);
                break;
            }
            case "quiz-by-word": {
                List<String> keysList = new ArrayList<>(list.keySet());
                Vector<Integer> ans = new Vector<>();
                int idx1 = new Random().nextInt(keysList.size());
                int idx2 = new Random().nextInt(keysList.size());
                int idx3 = new Random().nextInt(keysList.size());
                int idx4 = new Random().nextInt(keysList.size());
                ans.add(idx1);
                ans.add(idx2);
                ans.add(idx3);
                ans.add(idx4);
                int idxAns = new Random().nextInt(4);
                JRadioButton ans1 = new JRadioButton(keysList.get(idx1));
                JRadioButton ans2 = new JRadioButton(keysList.get(idx2));
                JRadioButton ans3 = new JRadioButton(keysList.get(idx3));
                JRadioButton ans4 = new JRadioButton(keysList.get(idx4));
                JDialog dialog = new JDialog(frame, "Quiz by Word");
                JLabel label = new JLabel(list.get(keysList.get(ans.get(idxAns))));
                JPanel container = new JPanel();
                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                container.add(label);
                container.add(ans1);
                container.add(ans2);
                container.add(ans3);
                container.add(ans4);
                dialog.add(container);

                ans1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx1 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });
                ans2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx2 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });
                ans3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx3 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });
                ans4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        if (idx4 == ans.get(idxAns)) {
                            utils.showDialog(frame, "Correct");
                        }
                        else {
                            utils.showDialog(frame, "Incorrect");
                        }
                    }
                });

                dialog.setSize(500, 300);
                dialog.setVisible(true);
                break;
            }
        }
    }
}
