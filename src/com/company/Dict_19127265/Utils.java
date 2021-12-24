package com.company.Dict_19127265;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;

/**
 * com.company.Dict_19127265
 * Created by Tai
 * Date 12/17/2021 - 5:07 PM
 * Description: ...
 */
public class Utils {
    public void showDialog(JFrame frame, String msg) {
        JDialog dialog = new JDialog(frame, "Notification");

        JLabel label = new JLabel(msg);
        label.setHorizontalAlignment(JLabel.CENTER);

        dialog.add(label);

        dialog.setSize(500, 200);
        dialog.setVisible(true);
    }

    public Object[][] convertToObjectArray(HashMap<String, String> list) {
        Object[][] result = new Object[list.size()][];
        int index = 0;
        for (Map.Entry<String, String> e : list.entrySet()) {
            result[index++] = new Object[]{e.getKey(), e.getValue()};
        }
        return result;
    }

    public Object[][] convertToObjectArray(LinkedList<String> list) {
        Object[][] result = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = new Object[]{list.get(i).split("`")[0], list.get(i).split("`")[1]};
        }
        return result;
    }

    public HashMap<String, String> readFile(JFrame frame, String path) {
        File f = new File(path);

        HashMap<String, String> list = new HashMap<>();

        if (!f.isFile()) {
            showDialog(frame, "File not found");
            return list;
        }

        //File is not empty
        if (f.length() != 0) {
            BufferedReader br = null;

            try {
                br = new BufferedReader(new FileReader(path));
            } catch (Exception e) {
                showDialog(frame, e.getMessage());
            }

            String s;
            do {
                s = null;

                try {
                    s = br != null ? br.readLine() : null;
                } catch (Exception e) {
                    showDialog(frame, e.getMessage());
                }

                if (s != null) {
                    String[] temp = s.split("`");
                    if (temp.length == 2) {
                        list.put(temp[0], temp[1]);
                    }
                }
            } while (s != null);
        }

        return list;
    }

    public void writeFile(JFrame frame, String path, HashMap<String, String> list) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
        } catch (Exception e) {
            showDialog(frame, e.getMessage());
            return;
        }

        for (Map.Entry<String, String> entry : list.entrySet()) {
            try {
                bw.write(entry.getKey() + "`" + entry.getValue() + System.lineSeparator());
            } catch (Exception e) {
                showDialog(frame, e.getMessage());
            }
        }

        try {
            bw.close();
        } catch (Exception e) {
            showDialog(frame, e.getMessage());
        }
    }

}
