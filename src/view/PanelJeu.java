package view;

import model.Bateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelJeu extends JPanel {



    protected JPanel grillePanelIA = new PanelGrille();
    protected JPanel grillePanelHumain = new PanelGrille();
    protected JPanel informationPanel = new InformationPartiePanel();



    public PanelJeu() {
        // on assigne des identifiants aux boutons

        this.setPreferredSize(new Dimension(VueJeu.WIDTH, VueJeu.HEIGHT-50));
        JPanel body = new JPanel();
        body.setPreferredSize(new Dimension(VueJeu.WIDTH+100, VueJeu.HEIGHT-50));
        body.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //side.setPreferredSize(new Dimension(Vue.WIDTH / 3, Vue.HEIGHT));
        // T0D0: changer le bouton par un panel informatif

        GridBagConstraints horizontalFill = new GridBagConstraints();
        horizontalFill.anchor = GridBagConstraints.WEST;
        horizontalFill.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(20,20,20,20);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 16;
        gbc.gridwidth = 16;
        gbc.ipady = 0;
        gbc.ipadx = 0;
        body.add(this.grillePanelIA, gbc);
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridheight = 16;
        gbc.gridwidth = 16;
        body.add(this.grillePanelHumain, gbc);
        gbc.gridx = 16;
        gbc.gridy = 0;
        gbc.gridheight = 32;
        gbc.gridwidth = 48;
        body.add(this.informationPanel, gbc);

        this.add(body, BorderLayout.LINE_START);

    }

    public void addMouseListener(MouseListener controller) {
        this.grillePanelIA.addMouseListener(controller);
        this.informationPanel.addMouseListener(controller);
    }

    public void afficherBateaux(ArrayList<Bateau> listeBateaux) {
        for(Bateau b : listeBateaux) {
            ((PanelGrille)this.grillePanelHumain).afficherBateau(b);
        }
    }

    public void addLogLine(String txt, Color color) {
        ((InformationPartiePanel)this.informationPanel).addLogLine(txt, color);
    }

    public void afficherAttaquesTouchees(ArrayList<Point> attaques) {
        for(Point p : attaques) {
            ((PanelGrille)this.grillePanelIA).afficherAttaqueTouche(p);
        }
    }

    public void afficherAttaquesRatees(ArrayList<Point> attaques) {
        for(Point p : attaques) {
            ((PanelGrille)this.grillePanelIA).afficherAttaqueRate(p);
        }
    }

    public void afficherBateauxToucher(ArrayList<Bateau> bateaux, ArrayList<Point> toucherIA) {
        for(Bateau b : bateaux){
            for(int i = 0 ; i < b.getTaillePosition() ; i++){
                Point p = b.getPostion(i);
                for(Point tmp : toucherIA){
                    if(p == tmp){
                        ((PanelGrille)this.grillePanelHumain).afficherBateauToucher(p);
                    }
                }
            }
        }
    }
}
