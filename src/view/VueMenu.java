package view;

import controller.ControllerMenu;
import model.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

public class VueMenu extends Vue {

    public final static int BUTTON_WIDTH = 300;
    public final static int BUTTON_HEIGHT = 20;
    public final static String REPRENDRE_COMMANDE = "reprendre";
    public final static String COMMENCER_COMMANDE = "commencer";
    public final static String QUITTER_COMMANDE = "quitter";

    private JButton reprendreButton = new JButton("Reprendre une partie");
    private JButton commencerButton = new JButton("Commencer une partie");
    private JButton quitterButton = new JButton("Quitter");


    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueMenu() {
        JFrame f = new JFrame("Bataille Navale : menu");
        f.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        f.setLocationRelativeTo(null);

        this.controller = new ControllerMenu();
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        this.reprendreButton.setPreferredSize(new Dimension(VueMenu.BUTTON_WIDTH, VueMenu.BUTTON_HEIGHT));
        this.commencerButton.setPreferredSize(new Dimension(VueMenu.BUTTON_WIDTH, VueMenu.BUTTON_HEIGHT));
        this.quitterButton.setPreferredSize(new Dimension(VueMenu.BUTTON_WIDTH, VueMenu.BUTTON_HEIGHT));
        this.reprendreButton.setActionCommand(VueMenu.REPRENDRE_COMMANDE);
        this.commencerButton.setActionCommand(VueMenu.COMMENCER_COMMANDE);
        this.quitterButton.setActionCommand(VueMenu.QUITTER_COMMANDE);

        Box box = Box.createVerticalBox();
        box.setPreferredSize(new Dimension(800,200));


        this.panel.setLayout(new GridLayout(5,3, 20, 20));
        this.reprendreButton.setEnabled(false);
        reprendreButton.addActionListener((ActionListener) this.controller);
        commencerButton.addActionListener((ActionListener) this.controller);
        quitterButton.addActionListener((ActionListener) this.controller);

        this.panel.add(new JPanel());
        this.panel.add(new JPanel());
        this.panel.add(new JPanel());
        this.panel.add(new JPanel());
        this.panel.add(reprendreButton);
        this.panel.add(new JPanel());
        this.panel.add(new JPanel());
        this.panel.add(commencerButton).setLocation(2,1);
        this.panel.add(new JPanel());
        this.panel.add(new JPanel());
        this.panel.add(quitterButton).setLocation(3,1);

        f.setContentPane(this.panel);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        Partie partie = (Partie)o;
        if (partie.existePartie()) {
            this.reprendreButton.setEnabled(true);
        }
    }
}

