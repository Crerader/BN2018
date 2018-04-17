package controller;

import model.Partie;
import view.PanelPlacement;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerPlacement implements MouseListener {

    private Partie partie;

    public ControllerPlacement(Partie partie) {
        this.partie = partie;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        switch(btn.getActionCommand()) {
            case PanelPlacement.BOUTON_BATEAU_2_CASES:
                //T0D0 : verifier si le bateau du joueur possède une position ou non, s'il n'en as pas on peut le placer
                break;
            case PanelPlacement.BOUTON_BATEAU_3_CASES:
                break;
            case PanelPlacement.BOUTON_BATEAU_4_CASES:
                break;
            case PanelPlacement.BOUTON_BATEAU_5_CASES:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
