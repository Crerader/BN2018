package view;

import javax.swing.*;
import java.awt.*;

public class PanelCreationPartie extends JPanel {

    private JPanel choixEpoque;
    private JPanel choixIA;
    private JButton valider;
    private JButton annuler;


    public PanelCreationPartie(){
        super();
        this.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));

        this.choixEpoque = new JPanel();
        this.choixIA = new JPanel();

        this.valider = new JButton("Valider");
        this.annuler = new JButton("Annuler");

        JCheckBox medieval = new JCheckBox("Médiéval");
        JCheckBox contemporaine = new JCheckBox("Contemporaine");

        JCheckBox aleatoire = new JCheckBox("aléatoire");
        JCheckBox croix = new JCheckBox("en croix");

        this.choixEpoque.add(medieval);
        this.choixEpoque.add(contemporaine);

        this.choixIA.add(aleatoire);
        this.choixIA.add(croix);

        this.add(choixEpoque);
        this.add(choixIA);
        this.add(valider);
        this.add(annuler);
    }
}
