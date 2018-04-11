package view;

import controller.ControllerMenu;
import model.Jeu;
import model.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

public class VueMenu extends Vue {

    public static final PanelMenu menu = new PanelMenu();
    public static final PanelCreationPartie creationPartie = new PanelCreationPartie();
    private final JFrame frame = new JFrame("Bataille Navale : menu");

    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueMenu(Jeu jeu) {
        this.frame.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        this.frame.setLocationRelativeTo(null);
        this.controller = new ControllerMenu(jeu);
        menu.addActionListener((ActionListener)this.controller);
        this.panel = menu;

        this.setPanel(this.panel);
    }

    public void setPanel(JPanel panel) {
        this.frame.setContentPane(panel);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        Partie partie = (Partie)o;
        if (partie.existePartie()) {
            this.menu.setReprendreEnabled(true);
        }
    }
}

