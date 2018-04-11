package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelMenu extends JPanel {

    public final static int BUTTON_WIDTH = 300;
    public final static int BUTTON_HEIGHT = 20;
    public final static String REPRENDRE_COMMANDE = "reprendre";
    public final static String COMMENCER_COMMANDE = "commencer";
    public final static String QUITTER_COMMANDE = "quitter";

    private JButton reprendreButton = new JButton("Reprendre une partie");
    private JButton commencerButton = new JButton("Commencer une partie");
    private JButton quitterButton = new JButton("Quitter");
    
    public PanelMenu() {
        this.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        this.reprendreButton.setPreferredSize(new Dimension(PanelMenu.BUTTON_WIDTH, PanelMenu.BUTTON_HEIGHT));
        this.commencerButton.setPreferredSize(new Dimension(PanelMenu.BUTTON_WIDTH, PanelMenu.BUTTON_HEIGHT));
        this.quitterButton.setPreferredSize(new Dimension(PanelMenu.BUTTON_WIDTH, PanelMenu.BUTTON_HEIGHT));
        this.reprendreButton.setActionCommand(PanelMenu.REPRENDRE_COMMANDE);
        this.commencerButton.setActionCommand(PanelMenu.COMMENCER_COMMANDE);
        this.quitterButton.setActionCommand(PanelMenu.QUITTER_COMMANDE);

        Box box = Box.createVerticalBox();
        box.setPreferredSize(new Dimension(800,200));


        this.setLayout(new GridLayout(5,3, 20, 20));
        this.reprendreButton.setEnabled(false);

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(reprendreButton);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(commencerButton).setLocation(2,1);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(quitterButton).setLocation(3,1);
    }

    public void addActionListener(ActionListener al) {
        reprendreButton.addActionListener((ActionListener) al);
        commencerButton.addActionListener((ActionListener) al);
        quitterButton.addActionListener((ActionListener) al);
    }

    public void setReprendreEnabled(boolean enabled) {
        this.reprendreButton.setEnabled(enabled);
    }
}
