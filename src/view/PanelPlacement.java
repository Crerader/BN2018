package view;

import controller.ControllerGrille;
import controller.ControllerPlacement;
import model.Bateau;
import model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Panel affichant une grille de jeu ainsi
 * qu'un bouton par type de bateau
 */
public class PanelPlacement extends JPanel {

    protected JPanel bateauxPanel = new JPanel();
    protected JPanel grillePanel = new PanelGrille();
    protected JButton cinqBateau = new JButton("cinq");
    protected JButton quatreBateau = new JButton("quatre");
    protected JButton troisBateau = new JButton("trois");
    protected JButton deuxBateau = new JButton("deux");

    public final static String BOUTON_BATEAU_2_CASES = "2";
    public final static String BOUTON_BATEAU_3_CASES = "3";
    public final static String BOUTON_BATEAU_4_CASES = "4";
    public final static String BOUTON_BATEAU_5_CASES = "5";

    public PanelPlacement() {
        // on assigne des identifiants aux boutons
        this.deuxBateau.setActionCommand(BOUTON_BATEAU_2_CASES);
        this.troisBateau.setActionCommand(BOUTON_BATEAU_3_CASES);
        this.quatreBateau.setActionCommand(BOUTON_BATEAU_4_CASES);
        this.cinqBateau.setActionCommand(BOUTON_BATEAU_5_CASES);

        this.setPreferredSize(new Dimension(Vue.WIDTH-20, Vue.HEIGHT-200));

        JPanel body = new JPanel();
        body.setPreferredSize(new Dimension( Vue.WIDTH, Vue.HEIGHT));
        body.setLayout(new BorderLayout());
        this.bateauxPanel.setPreferredSize(new Dimension(Vue.WIDTH /3,Vue.HEIGHT));
        this.grillePanel.setPreferredSize(new Dimension(Vue.WIDTH * (2/3), Vue.HEIGHT));

        // bateau panel set
        this.bateauxPanel.setLayout(new GridLayout(6,3, 0, 5));
        //this.bateauxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(deuxBateau);
        this.bateauxPanel.add(new JPanel());

        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(troisBateau);
        this.bateauxPanel.add(new JPanel());

        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(quatreBateau);
        this.bateauxPanel.add(new JPanel());

        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(cinqBateau);
        this.bateauxPanel.add(new JPanel());

        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(new JPanel());
        this.bateauxPanel.add(new JPanel());
        body.add(this.grillePanel);
        body.add(this.bateauxPanel, BorderLayout.LINE_END);
        this.add(body);
    }

    /**
     * ajoute un controller pour gérer les évenements
     * sur les differents boutons de l'interface
     * @param controller
     */
    public void addMouseListener(MouseListener controller) {
        this.deuxBateau.addMouseListener(controller);
        this.troisBateau.addMouseListener(controller);
        this.quatreBateau.addMouseListener(controller);
        this.cinqBateau.addMouseListener(controller);
        this.grillePanel.addMouseListener(controller);
        ((ControllerPlacement)controller).setGrille(((PanelGrille)this.grillePanel).getGrille());
    }

    /**
     * Permet de changer l'affichage des boutons
     * @param enabled
     *      statut (activé ou non)
     * @param nbCasesBateau
     *      bouton choisi
     */
    public void setBoutonEnabled(boolean enabled, int nbCasesBateau) {
        switch (nbCasesBateau) {
            case 2:
                this.deuxBateau.setEnabled(enabled);
                break;
            case 3:
                this.troisBateau.setEnabled(enabled);
                break;
            case 4:
                this.quatreBateau.setEnabled(enabled);
                break;
            case 5:
                this.cinqBateau.setEnabled(enabled);
                break;
        }
    }

    /**
     * Permet de modifier l'époque
     * @param listeBateau
     */
    public void setBateauEpoque(ArrayList<Bateau> listeBateau) {
        for(Bateau b : listeBateau) {
            int taille = b.getNbCase();
            switch (taille) {
                case 2:
                    this.deuxBateau.setText(b.toString());
                    this.deuxBateau.setBackground(b.getColor());
                    break;
                case 3:
                    this.troisBateau.setText(b.toString());
                    this.troisBateau.setBackground(b.getColor());
                    break;
                case 4:
                    this.quatreBateau.setText(b.toString());
                    this.quatreBateau.setBackground(b.getColor());
                    break;
                case 5:
                    this.cinqBateau.setText(b.toString());
                    this.cinqBateau.setBackground(b.getColor());
                    break;
            }
        }
        this.repaint();
    }

    public void afficherBateaux(ArrayList<Bateau> listeBateaux) {
        for(Bateau b : listeBateaux) {
            ((PanelGrille)this.grillePanel).afficherBateau(b);
        }
    }
}
