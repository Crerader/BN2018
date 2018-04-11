package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelCreationPartie extends JPanel {

    public static final String VALIDER = "valider";
    public static final String ANNULER = "annuler";


    private JPanel choixEpoque;
    private JPanel choixIA;
    private JButton valider;
    private JButton annuler;


    public PanelCreationPartie(){
        super();
        this.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));

        JLabel titre = new JLabel("Créer votre partie");
        this.add(titre);

        this.choixEpoque = new JPanel();
        ButtonGroup b1 = new ButtonGroup();
        this.choixIA = new JPanel();
        ButtonGroup b2 = new ButtonGroup();

        this.valider = new JButton(VALIDER);
        this.annuler = new JButton(ANNULER);
        this.valider.setActionCommand(VALIDER);
        this.annuler.setActionCommand(ANNULER);

        JRadioButton medieval = new JRadioButton("Médiéval");
        JRadioButton contemporaine = new JRadioButton("Contemporaine");
        medieval.isSelected();
        b1.add(medieval);
        b1.add(contemporaine);
        BufferedImage medievalIcon = null;
        BufferedImage contemporaineIcon = null;
        try {
            medievalIcon = ImageIO.read(new File("img/BateauPirate1.png"));
            contemporaineIcon = ImageIO.read(new File("img/Croiseur1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel medievalPanel = new JPanel();
        medievalPanel.add(new JLabel(new ImageIcon(medievalIcon)));
        medievalPanel.add(medieval);
        medievalPanel.setLayout(new GridLayout(2,1));


        JPanel contemporainelPanel = new JPanel();
        contemporainelPanel.add(new JLabel(new ImageIcon(contemporaineIcon)));
        contemporainelPanel.add(contemporaine);
        contemporainelPanel.setLayout(new GridLayout(2,1));



        JRadioButton aleatoire = new JRadioButton("aléatoire");
        JRadioButton croix = new JRadioButton("en croix");
        aleatoire.isSelected();
        b2.add(aleatoire);
        b2.add(croix);

        this.choixEpoque.add(medievalPanel);
        this.choixEpoque.add(contemporainelPanel);

        this.choixIA.add(aleatoire);
        this.choixIA.add(croix);

        this.add(choixEpoque);
        this.add(choixIA);
        this.add(valider);
        this.add(annuler);
    }
}
