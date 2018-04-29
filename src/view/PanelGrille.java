package view;

import controller.ControllerGrille;
import model.Bateau;
import model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Représente l'affichage de la grille
 * de jeu
 */
public class PanelGrille extends JPanel {

    private ArrayList<Point> casesRatees;
    private ArrayList<Point> casesTouchees;

    private JButton[][] cases;
    public final static int ASCII = 65;

    public PanelGrille() {
        super();
        this.casesRatees = new ArrayList<Point>();
        this.casesTouchees = new ArrayList<Point>();

        this.cases = new JButton[10][10];
        this.setLayout(new GridLayout(10,10));
        for(int i = 0; i < this.cases.length;i++) {
            char currentLine = (char)(ASCII + i);
            for(int j = 0; j < this.cases.length; j++) {
                String id = "" + currentLine + j;
                this.cases[i][j] = new JButton(id);
                this.cases[i][j].setActionCommand(id);
                this.cases[i][j].setPreferredSize(new Dimension(20,20));
                this.add(this.cases[i][j]);
            }
        }
    }

    public JButton getCase(int i, int j) {
        return this.cases[i][j];
    }

    public JButton[][] getGrille(){
        return this.cases;
    }

    public void addMouseListener(MouseListener listener) {
        for(int i = 0 ; i < this.cases.length; i++) {
            for(int j = 0; j < this.cases[i].length; j++) {
                this.cases[i][j].addMouseListener(listener);
            }
        }
    }

    public void afficherBateau(Bateau b) {
        for(Point p : b.getPositions()) {
            this.cases[(int)p.getY()][(int)p.getX()].setBackground(b.getColor());
            this.cases[(int)p.getY()][(int)p.getX()].setOpaque(true);
        }
    }

    public void afficherAttaqueTouche(Point pxy) {
        this.cases[(int)pxy.getY()][(int)pxy.getX()].setBackground(Color.RED);
        this.cases[(int)pxy.getY()][(int)pxy.getX()].setOpaque(true);
    }

    public void afficherAttaqueRate(Point pxy) {
        this.cases[(int)pxy.getY()][(int)pxy.getX()].setBackground(Color.GREEN);
        this.cases[(int)pxy.getY()][(int)pxy.getX()].setOpaque(true);
    }


    public void afficherBateauToucher(Point p) {
        this.cases[(int)p.getY()][(int)p.getX()].setBackground(Color.BLACK);
        this.cases[(int)p.getY()][(int)p.getX()].setOpaque(true);
    }
}
