package controller;

import model.Jeu;
import view.PanelCreationPartie;
import view.VueJeu;
import view.VueMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenuBar implements ActionListener {

    private Jeu jeu;

    public ControllerMenuBar(Jeu jeu){
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VueMenu vueMenu = jeu.getVueMenu();
        VueJeu vueJeu = jeu.getVueJeu();
        JMenuItem btn = (JMenuItem) e.getSource();
        switch(btn.getActionCommand()){
            case VueJeu.SAUVEGARDER:
                jeu.save();
                vueJeu.exit();
                jeu.activeVueMenu();
                break;
            case VueJeu.QUITTER:
                vueJeu.exit();
                jeu.activeVueMenu();
                break;
            case VueJeu.NOUVEAU:
                vueJeu.exit();
                jeu.activeVueMenu();
                vueMenu.setPanel(VueMenu.creationPartie);
                break;
        }
    }
}
