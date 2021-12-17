package com.company.Dict_19127265;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Utils utils = new Utils();
        JFrame frame = new JFrame("Dict");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        utils.readFile(frame, "slang.txt");
    }
}
