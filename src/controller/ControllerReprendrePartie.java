package controller;

import model.Jeu;
import view.PanelCreationPartie;
import view.PanelMenu;
import view.PanelReprendrePartie;
import view.VueMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerReprendrePartie implements ActionListener {

    private Jeu jeu;
    private PanelReprendrePartie panel;

    public ControllerReprendrePartie(Jeu jeu, PanelReprendrePartie panel){
        this.jeu = jeu;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VueMenu vueMenu = jeu.getVueMenu();
        JButton btn = (JButton) e.getSource();
        switch(btn.getActionCommand()) {
            case PanelCreationPartie.VALIDER:
                String chemin = this.panel.getListeSelected();
                jeu.charger("save/" + chemin + ".xml");
                //TODO : A completer
                break;
            case PanelCreationPartie.ANNULER:
                vueMenu.setPanel(VueMenu.menu);
                break;
        }
    }
}
