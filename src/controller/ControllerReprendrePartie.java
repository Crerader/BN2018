package controller;

import model.Jeu;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String nom = this.panel.getListeSelected();
                jeu.charger(Jeu.cheminSauvegarde + "/" + nom + ".xml");
                break;
            case PanelCreationPartie.ANNULER:
                jeu.activeVueMenu();
                break;
        }
    }
}
