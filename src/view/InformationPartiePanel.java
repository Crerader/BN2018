package view;

import model.Bateau;
import model.Joueur;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class InformationPartiePanel extends JPanel {


    protected JPanel bateauxPanel = new JPanel();
    protected JButton cinqBateau = new JButton("cinq");
    protected JButton quatreBateau = new JButton("quatre");
    protected JButton troisBateau = new JButton("trois");
    protected JButton deuxBateau = new JButton("deux");
    protected JTextPane log = new JTextPane();
    protected JScrollPane jsp = new JScrollPane(log);

    public final static String BOUTON_BATEAU_2_CASES = "2";
    public final static String BOUTON_BATEAU_3_CASES = "3";
    public final static String BOUTON_BATEAU_4_CASES = "4";
    public final static String BOUTON_BATEAU_5_CASES = "5";

    public InformationPartiePanel() {
        this.setPreferredSize(new Dimension(200, Vue.HEIGHT));
        //configuration JTextPane
        log.setEditable(false);
        log.setPreferredSize(new Dimension(200, Vue.HEIGHT));
        // on assigne des identifiants aux boutons
        this.deuxBateau.setActionCommand(BOUTON_BATEAU_2_CASES);
        this.troisBateau.setActionCommand(BOUTON_BATEAU_3_CASES);
        this.quatreBateau.setActionCommand(BOUTON_BATEAU_4_CASES);
        this.cinqBateau.setActionCommand(BOUTON_BATEAU_5_CASES);

        //this.setPreferredSize(new Dimension(100, 300));
        this.setLayout(new GridLayout(0, 1, 0, 5));
//
//        // bateau panel set
//        this.bateauxPanel.setLayout(new GridLayout(0,1, 0, 5));
//        System.out.println(this.getWidth());
        //this.setBorder(BorderFactory.createLineBorder(Color.red));
//        this.bateauxPanel.setPreferredSize(new Dimension(200, Vue.HEIGHT/2));
//        this.bateauxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
//        this.bateauxPanel.add(deuxBateau);
//        this.bateauxPanel.add(troisBateau);
//        this.bateauxPanel.add(quatreBateau);
//        this.bateauxPanel.add(cinqBateau);
//        this.add(bateauxPanel, BorderLayout.LINE_START);
        this.add(deuxBateau);
        this.add(troisBateau);
        this.add(quatreBateau);
        this.add(cinqBateau);
        this.add(jsp);
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        InformationPartiePanel m = new InformationPartiePanel();
        jf.setPreferredSize(new Dimension(VueJeu.WIDTH, VueJeu.HEIGHT));
        jf.setContentPane(m);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }

    public void addLogLine(String txt, Color color) {
        StyledDocument doc = log.getStyledDocument();
        Style style = log.addStyle("rnd", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), txt + "\n", style);
            log.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {

        }
    }

    public void addMouseListener(MouseListener c) {
        this.deuxBateau.addMouseListener(c);
        this.troisBateau.addMouseListener(c);
        this.quatreBateau.addMouseListener(c);
        this.cinqBateau.addMouseListener(c);
    }

    /**
     * Permet de modifier l'époque
     *
     * @param listeBateau
     */
    public void setBateauEpoque(ArrayList<Bateau> listeBateau) {
        for (Bateau b : listeBateau) {
            int taille = b.getNbCase();
            switch (taille) {
                case 2:
                    this.deuxBateau.setText(b.toString());
                    this.deuxBateau.setBackground(b.getColor());
                    this.deuxBateau.setOpaque(true);
                    break;
                case 3:
                    this.troisBateau.setText(b.toString());
                    this.troisBateau.setBackground(b.getColor());
                    this.troisBateau.setOpaque(true);
                    break;
                case 4:
                    this.quatreBateau.setText(b.toString());
                    this.quatreBateau.setBackground(b.getColor());
                    this.quatreBateau.setOpaque(true);
                    break;
                case 5:
                    this.cinqBateau.setText(b.toString());
                    this.cinqBateau.setBackground(b.getColor());
                    this.cinqBateau.setOpaque(true);
                    break;
            }
        }
        this.repaint();
    }

    public void bloquerBoutonBateauMort(int taille) {
        switch (taille) {
            case Bateau.TAILLE_2_CASES:
                this.deuxBateau.setEnabled(false);
                break;
            case Bateau.TAILLE_3_CASES:
                this.troisBateau.setEnabled(false);
                break;
            case Bateau.TAILLE_4_CASES:
                this.quatreBateau.setEnabled(false);
                break;
            case Bateau.TAILLE_5_CASES:
                this.cinqBateau.setEnabled(false);
                break;
        }
        this.repaint();
    }

}
