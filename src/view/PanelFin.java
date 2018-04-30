package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * JPanel affichant la fin de la partie :
 *      - petit message de perte ou de victoire
 *      - un JButton de retour menu
 */
public class PanelFin extends JPanel {

    public final static String RETOUR_MENU = "RETOURMENU";
    private JButton retourMenu = new JButton("Retourner au menu");
    private boolean win;
    private JLabel label;

    public PanelFin(boolean victoire){
        super();
        this.retourMenu.setActionCommand(RETOUR_MENU);
        this.win = victoire;
        if(this.win) {
            this.label = new JLabel("Vous avez gagné !");
        } else {
            this.label = new JLabel("Vous avez perdu ! :(");
        }

        label.setFont(new Font("Serif", Font.PLAIN, 64));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.setPreferredSize(new Dimension(100, Vue.HEIGHT));

        this.setLayout(new GridLayout(5,1, 5, 5));


        this.add(new JPanel());
        this.add(this.label);
        JPanel line = new JPanel();
        line.setLayout(new GridLayout(0, 3, 5, 5));
        line.add(new JPanel());
        line.add(this.retourMenu);
        line.add(new JPanel());
        this.add(line);
        this.add(new JPanel());

    }


    public void addMouseListener(MouseListener c){
        this.retourMenu.addMouseListener(c);
    }

}
