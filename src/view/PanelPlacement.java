package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelPlacement extends JPanel {

    protected JPanel bateauxPanel = new JPanel();
    protected JPanel grillePanel = new PanelGrille();
    protected JButton cinqBateau = new JButton("cinq");
    protected JButton quatreBateau = new JButton("quatre");
    protected JButton troisBateau = new JButton("trois");
    protected JButton deuxBateau = new JButton("deux");

    public PanelPlacement() {
        this.setPreferredSize(new Dimension(Vue.WIDTH-20, Vue.HEIGHT-100));

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

    public void addActionListener(ActionListener controller) {

    }
}
