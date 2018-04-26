package controller;

import model.Bateau;
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
        Bateau b;
        switch(btn.getActionCommand()) {
            case PanelPlacement.BOUTON_BATEAU_2_CASES:
                b = partie.getHumain().getBateauNoPosition(2);
                if(b != null) {
                    // lancement detection click placement
                    System.out.println("choisir une case et une orientation");
                }
                break;
            case PanelPlacement.BOUTON_BATEAU_3_CASES:
                b = partie.getHumain().getBateauNoPosition(3);
                if(b != null) {

                }
                break;
            case PanelPlacement.BOUTON_BATEAU_4_CASES:
                b = partie.getHumain().getBateauNoPosition(4);
                if(b != null) {

                }
                break;
            case PanelPlacement.BOUTON_BATEAU_5_CASES:
                b = partie.getHumain().getBateauNoPosition(5);
                if(b != null) {

                }
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
