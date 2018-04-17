package view;

import javax.swing.*;
import java.awt.*;

/**
 * Représente l'affichage de la grille
 * de jeu
 */
public class PanelGrille extends JPanel {

    private JButton[][] cases;

    public PanelGrille() {
        super();
        final int ASCII = 65;
        this.cases = new JButton[10][10];
        this.setLayout(new GridLayout(10,10));
        for(int i = 0; i < this.cases.length;i++) {
            char currentLine = (char)(ASCII + i);
            for(int j = 0; j < this.cases.length; j++) {
                this.cases[i][j] = new JButton("" + currentLine + j);
                this.cases[i][j].setPreferredSize(new Dimension(20,20));
                this.add(this.cases[i][j]);
                System.out.println("add case");
            }
        }
    }

}
