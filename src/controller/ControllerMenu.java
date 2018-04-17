package controller;

import model.Jeu;
import view.PanelCreationPartie;
import view.PanelMenu;
import view.VueMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu implements ActionListener {

    private Jeu jeu;

    public ControllerMenu(Jeu jeu){
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VueMenu vueMenu = jeu.getVueMenu();
        JButton btn = (JButton) e.getSource();
        switch(btn.getActionCommand()) {
            case PanelMenu.REPRENDRE_COMMANDE:
                break;
            case PanelMenu.COMMENCER_COMMANDE:
                vueMenu.setPanel(VueMenu.creationPartie);
                break;
            case PanelMenu.QUITTER_COMMANDE:
                System.exit(0);
                break;
            case PanelCreationPartie.ANNULER:
                vueMenu.setPanel(VueMenu.menu);
                break;
            case PanelCreationPartie.VALIDER:
                break;
        }
    }
}
