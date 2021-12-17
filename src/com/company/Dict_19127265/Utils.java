package com.company.Dict_19127265;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
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

        dialog.setSize(300, 100);
        dialog.setVisible(true);
    }

    public HashMap<String, Vector<String>> readFile(JFrame frame, String path) {
        File f = new File(path);

        HashMap<String, Vector<String>> list = new HashMap<>();

        if (!f.isFile()) return list;

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
                        Vector<String> value = new Vector<>();
                        value.add(temp[1]);
                        list.put(temp[0], value);
                    }
                }
            } while (s != null);
        }

        return list;
    }
}
