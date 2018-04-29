package view;

import controller.*;
import model.Bateau;
import model.Jeu;
import model.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class VueJeu extends Vue {

    public final static int WIDTH = Vue.WIDTH + 200;
    public final static int HEIGHT = Vue.HEIGHT + 200;
    public static final String NOUVEAU = "Nouveau";
    public static final String SAUVEGARDER = "Sauvegarder";
    public static final String QUITTER = "Quitter";

    private final PanelPlacement placement = new PanelPlacement();
    private final PanelJeu jeu = new PanelJeu();
    private final JFrame frame = new JFrame("Bataille Navale : partie");
    private boolean inGame, inPlacement;

    protected JMenuBar menuBar = new JMenuBar();
    protected JMenuItem menu = new JMenuItem(NOUVEAU);
    protected JMenuItem menu_2 = new JMenuItem(SAUVEGARDER);
    protected JMenuItem menu_3 = new JMenuItem(QUITTER);

    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueJeu(Jeu j) {
        this.frame.setLocationRelativeTo(null);
        //this.controller = new ControllerJeu();
        //placement.addActionListener((ActionListener)this.controller);
        this.panel = placement;
        this.inGame = false;
        this.inPlacement = false;
        this.setPanel(this.panel);
        this.frame.setPreferredSize(new Dimension(VueJeu.WIDTH-50, VueJeu.HEIGHT-150));
        this.menuBar.add(menu);
        this.menuBar.add(menu_2);
        this.menuBar.add(menu_3);
        this.menu.addActionListener(new ControllerMenuBar(j));
        this.menu_2.addActionListener(new ControllerMenuBar(j));
        this.menu_3.addActionListener(new ControllerMenuBar(j));
    }

    public void setPanel(JPanel panel) {
        this.frame.setPreferredSize(new Dimension(VueJeu.WIDTH+100, VueJeu.HEIGHT));
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
        if(p.getVainqueur() != null) {
            this.jeu.addLogLine("Le vainqueur est l'" + p.getVainqueur() + " !", Color.GREEN);
        } else if(started && !inGame) {
            // lancement de la partie
            inGame = true;
            inPlacement = false;
            this.jeu.addMouseListener(new ControllerPartie(p));
            this.setPanel(this.jeu);
            this.afficherBateaux(p.getListeBateaux(false));
            this.jeu.setBateauEpoque(p.getListeBateaux(false));
            this.frame.setJMenuBar(this.menuBar);
            p.miseAjour();
        } else if (!started && inPlacement) {
            this.frame.setJMenuBar(null);
            // actualisation de l'interface en fonction des placements de bateaux
            HashMap<Integer, Integer> listeBateauHumain = p.getListeBateauxBySize(false);
            if(listeBateauHumain.get(2) == Partie.NB_BATEAU_2) {
                this.placement.setBoutonEnabled(false, 2);
            }
            if (listeBateauHumain.get(3) == Partie.NB_BATEAU_3) {
                this.placement.setBoutonEnabled(false, 3);
            }
            if (listeBateauHumain.get(4) == Partie.NB_BATEAU_4) {
                this.placement.setBoutonEnabled(false, 4);
            }
            if (listeBateauHumain.get(5) == Partie.NB_BATEAU_5) {
                this.placement.setBoutonEnabled(false, 5);
            }
            if(p.isReady()) {
                this.placement.setJouerVisible();
            }
        } else if(!inPlacement && !inGame) {
            // actualisation de l'interface en fonction de l'époque choisie
            this.inPlacement = true;
            this.placement.addMouseListener(new ControllerPlacement(p));
            this.placement.setBateauEpoque(p.getListeBateaux(false));
        } else if(inGame) {
            this.jeu.addLogLine(p.getLastMessage(), Color.red);
            this.jeu.afficherAttaquesRatees(p.getHumain().getAttaqueRate());
            this.jeu.afficherAttaquesTouchees(p.getHumain().getAttaqueTouche());
        }
        this.afficherBateaux(p.getListeBateaux(false));
        this.frame.pack();
    }

    public void afficherBateaux(ArrayList<Bateau> bateaux) {
        if(inPlacement) {
            this.placement.afficherBateaux(bateaux);
        } else if (inGame) {
            this.jeu.afficherBateaux(bateaux);
        }
    }

    public void afficherAttaque(ArrayList<Point> attaquesRate, ArrayList<Point> attaquesTouchee) {
        if (inGame) {
            this.jeu.afficherAttaquesRatees(attaquesRate);
            this.jeu.afficherAttaquesTouchees(attaquesTouchee);
        }
    }


    public void exit(){
        this.frame.setVisible(false);
    }


}
