package view;

import controller.ControllerJeu;
import controller.ControllerMenu;
import model.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

public class VueJeu extends Vue {

    private final PanelPlacement placement = new PanelPlacement();
    private final JFrame frame = new JFrame("Bataille Navale : partie");

    /**
     * Constructeur prenant un JPanel et un EventListener comme controller
     */
    public VueJeu() {
        this.frame.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        this.frame.setLocationRelativeTo(null);
        this.controller = new ControllerJeu();
        placement.addActionListener((ActionListener)this.controller);
        this.panel = placement;

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
    }

    public static void main(String[] args) {
        VueJeu v = new VueJeu();
    }

}
