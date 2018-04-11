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

    private final PanelMenu menu = new PanelMenu();

    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueMenu() {
        JFrame f = new JFrame("Bataille Navale : menu");
        f.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        f.setLocationRelativeTo(null);
        this.controller = new ControllerMenu();
        menu.addActionListener((ActionListener)this.controller);
        this.panel = menu;

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
            this.menu.setReprendreEnabled(true);
        }
    }
}

