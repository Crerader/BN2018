package view;

import controller.ControllerJeu;
import controller.ControllerMenu;
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
    private boolean inGame;
    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueJeu() {
        this.frame.setPreferredSize(new Dimension(VueJeu.WIDTH, VueJeu.HEIGHT));
        this.frame.setLocationRelativeTo(null);
        this.controller = new ControllerJeu();
        placement.addActionListener((ActionListener)this.controller);
        this.panel = placement;
        this.inGame = false;
        this.setPanel(this.panel);
    }

    public void setPanel(JPanel panel) {
        this.frame.setContentPane(panel);
        this.frame.setResizable(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        Partie p = (Partie)o;
        boolean started = p.isStarted();

        if(started && !inGame) {
            inGame = true;
            this.setPanel(this.jeu);
        } else if (!started) {
            HashMap<Integer, Integer> listeBateauHumain = p.getListeBateaux(false);
            if(listeBateauHumain.get(2) == Partie.NB_BATEAU_2) {
                this.placement.setBoutonEnabled(false, 2);
            } else if (listeBateauHumain.get(3) == Partie.NB_BATEAU_3) {
                this.placement.setBoutonEnabled(false, 3);
            } else if (listeBateauHumain.get(4) == Partie.NB_BATEAU_4) {
                this.placement.setBoutonEnabled(false, 4);
            } else if (listeBateauHumain.get(5) == Partie.NB_BATEAU_5) {
                this.placement.setBoutonEnabled(false, 5);
            }
        }
    }

    public static void main(String[] args) {
        VueJeu v = new VueJeu();
    }

}
