package com.company.Dict_19127265;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * com.company.Dict_19127265
 * Created by Tai
 * Date 12/17/2021 - 9:41 PM
 * Description: ...
 */
public class HandleCard implements ActionListener {
    JPanel card;
    CardLayout cl;

    public HandleCard(JPanel card) {
        this.card = card;
        cl = (CardLayout) card.getLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "search-by-slang": {
                cl.show(card, "search-by-slang");
                break;
            }
            case "search-by-def": {
                cl.show(card, "search-by-def");
                break;
            }
            default:
                break;
        }
    }
}
