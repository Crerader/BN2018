package view;

import controller.ControllerGrille;
import model.Jeu;

import javax.swing.*;
import java.awt.*;

/**
 * Représente l'affichage de la grille
 * de jeu
 */
public class PanelGrille extends JPanel {

    private JButton[][] cases;
    private final static int ASCII = 65;

    public PanelGrille() {
        super();

        this.cases = new JButton[10][10];
        this.setLayout(new GridLayout(10,10));
        for(int i = 0; i < this.cases.length;i++) {
            char currentLine = (char)(ASCII + i);
            for(int j = 0; j < this.cases.length; j++) {
                String id = "" + currentLine + j;
                ControllerGrille cg = new ControllerGrille();
                this.cases[i][j] = new JButton(id);
                this.cases[i][j].addMouseListener(cg);
                this.cases[i][j].setActionCommand(id);
                this.cases[i][j].setPreferredSize(new Dimension(20,20));
                this.add(this.cases[i][j]);
            }
        }
    }

    public JButton getGrille(int i, int j) {
        return this.cases[i][j];
    }

}
