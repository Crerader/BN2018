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

    private JButton reprendreButton = new JButton("Reprendre une partie");
    private JButton commencerButton = new JButton("Commencer une partie");
    private JButton quitterButton = new JButton("Quitter");


    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueMenu() {
        JFrame f = new JFrame("Bataille Navale : menu");
        f.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        this.controller = new ControllerMenu();
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        this.panel.add(reprendreButton, BorderLayout.CENTER);
        this.panel.add(commencerButton, BorderLayout.CENTER);
        this.panel.add(quitterButton, BorderLayout.CENTER);

        this.reprendreButton.setEnabled(false);
        reprendreButton.addActionListener((ActionListener) this.controller);
        commencerButton.addActionListener((ActionListener) this.controller);
        quitterButton.addActionListener((ActionListener) this.controller);

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

