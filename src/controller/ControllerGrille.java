package controller;

import model.Jeu;
import model.Partie;
import view.PanelCreationPartie;
import view.PanelMenu;
import view.VueMenu;

import javax.swing.*;
import java.awt.event.*;

public class ControllerGrille implements MouseListener {

    private Jeu jeu;
    private Partie partieEnCours;

    public ControllerGrille(Jeu jeu){
        this.jeu = jeu;
    }

    public ControllerGrille(Partie partieEnCours){
        this.partieEnCours = partieEnCours;
    }

    public ControllerGrille(){}


    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        System.out.println("Click : " + btn.getActionCommand());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        System.out.println("Entered : " + btn.getActionCommand());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

