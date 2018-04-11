package controller;

import model.Jeu;
import view.VueMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu implements ActionListener {

    private Jeu jeu;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        switch(btn.getActionCommand()) {
            case VueMenu.REPRENDRE_COMMANDE:
                break;
            case VueMenu.COMMENCER_COMMANDE:
                break;
            case VueMenu.QUITTER_COMMANDE:
                System.exit(0);
                break;
        }
    }
}
