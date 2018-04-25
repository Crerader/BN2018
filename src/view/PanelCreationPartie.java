package view;

import controller.ControllerMenu;
import model.Epoque;
import model.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelCreationPartie extends JPanel {

    public static final String VALIDER = "valider";
    public static final String ANNULER = "annuler";


    private JRadioButton medieval;
    private JRadioButton contemporaine;
    private JRadioButton aleatoire;
    private JRadioButton croix;

    private JPanel choixEpoque;
    private JPanel choixIA;
    private JButton valider;
    private JButton annuler;


    public PanelCreationPartie(Jeu jeu) {
        super();
        this.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));

        this.choixEpoque = new JPanel();
        ButtonGroup b1 = new ButtonGroup();
        this.choixIA = new JPanel();
        ButtonGroup b2 = new ButtonGroup();

        this.valider = new JButton(VALIDER);
        this.annuler = new JButton(ANNULER);
        this.valider.setActionCommand(VALIDER);
        this.annuler.setActionCommand(ANNULER);
        JPanel boutons = new JPanel();
        boutons.add(this.valider);
        boutons.add(this.annuler);

        medieval = new JRadioButton("Médiéval");
        contemporaine = new JRadioButton("Contemporaine");
        b1.add(medieval);
        b1.add(contemporaine);
        medieval.setSelected(true);
        BufferedImage medievalIcon = null;
        BufferedImage contemporaineIcon = null;
        try {
            medievalIcon = ImageIO.read(new File("img/BateauPirate1.png"));
            contemporaineIcon = ImageIO.read(new File("img/Croiseur1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        JPanel medievalPanel = new JPanel();
        medievalPanel.setLayout(new BorderLayout());
        medievalPanel.add(new JLabel(new ImageIcon(medievalIcon)));
        medievalPanel.add(medieval, BorderLayout.SOUTH);


        JPanel contemporainelPanel = new JPanel();
        contemporainelPanel.setLayout(new BorderLayout());
        contemporainelPanel.add(new JLabel(new ImageIcon(contemporaineIcon)));
        contemporainelPanel.add(contemporaine, BorderLayout.SOUTH);


        aleatoire = new JRadioButton("aléatoire");
        croix = new JRadioButton("en croix");
        b2.add(aleatoire);
        b2.add(croix);
        aleatoire.setSelected(true);

        this.choixEpoque.add(medievalPanel);
        this.choixEpoque.add(contemporainelPanel);

        this.choixIA.add(aleatoire);
        this.choixIA.add(croix);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel titre = new JLabel("Créer votre partie", SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(60, 0, 0, 0);
        this.add(titre, c);
        c.gridy = 1;
        this.add(choixEpoque, c);
        c.gridy = 2;
        this.add(choixIA, c);
        c.gridy = 3;
        this.add(boutons, c);

        this.valider.addActionListener(new ControllerMenu(jeu, this));
        this.annuler.addActionListener(new ControllerMenu(jeu, this));

    }

    public int getChoixEpoque() {
        if (this.medieval.isSelected()) {
            return Epoque.MEDIEVAL;
        } else {
            return Epoque.CONTEMPORAINE;
        }
    }

    public int getChoixIA() {
        //A changer
        if (this.aleatoire.isSelected()) {
            return 0;
        } else {
            return 1;
        }
    }
}
