package view;

import controller.ControllerJeu;
import controller.ControllerMenu;
import model.Bateau;
import model.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;

public class VueJeu extends Vue {

    public final static int WIDTH = Vue.WIDTH + 200;
    public final static int HEIGHT = Vue.HEIGHT + 200;

    private final PanelPlacement placement = new PanelPlacement();
    private final PanelJeu jeu = new PanelJeu();
    private final JFrame frame = new JFrame("Bataille Navale : partie");
    private boolean inGame, inPlacement;
    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueJeu() {

        this.frame.setLocationRelativeTo(null);
        this.controller = new ControllerJeu();
        placement.addActionListener((ActionListener)this.controller);
        this.panel = placement;
        this.inGame = false;
        this.inPlacement = false;
        this.setPanel(this.panel);
        this.frame.setPreferredSize(new Dimension(VueJeu.WIDTH-50, VueJeu.HEIGHT-150));
    }

    public void setPanel(JPanel panel) {
        this.frame.setPreferredSize(new Dimension(VueJeu.WIDTH, VueJeu.HEIGHT));
        this.frame.setContentPane(panel);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        Partie p = (Partie)o;
        boolean started = p.isStarted();
        if(started && !inGame) {
            // lancement de la partie
            inGame = true;
            this.setPanel(this.jeu);
        } else if (!started && inPlacement) {
            // actualisation de l'interface en fonction des placements de bateaux
            HashMap<Integer, Integer> listeBateauHumain = p.getListeBateauxBySize(false);
            if(listeBateauHumain.get(2) == Partie.NB_BATEAU_2) {
                this.placement.setBoutonEnabled(false, 2);
            } else if (listeBateauHumain.get(3) == Partie.NB_BATEAU_3) {
                this.placement.setBoutonEnabled(false, 3);
            } else if (listeBateauHumain.get(4) == Partie.NB_BATEAU_4) {
                this.placement.setBoutonEnabled(false, 4);
            } else if (listeBateauHumain.get(5) == Partie.NB_BATEAU_5) {
                this.placement.setBoutonEnabled(false, 5);
            }
        } else if(!inPlacement) {
            // actualisation de l'interface en fonction de l'époque choisie
            this.inPlacement = true;
            this.placement.setBateauEpoque(p.getListeBateaux(false));
        }
        this.frame.pack();
    }

    public static void main(String[] args) {
        VueJeu v = new VueJeu();
    }

}
