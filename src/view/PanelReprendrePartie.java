package view;

import controller.ControllerReprendrePartie;
import model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelReprendrePartie extends JPanel {

    private JComboBox listePartie;
    private JButton valider;
    private JButton annuler;

    public PanelReprendrePartie(ArrayList<String> parties){
        super();
        this.listePartie = new JComboBox();
        this.valider = new JButton(PanelCreationPartie.VALIDER);
        this.annuler = new JButton(PanelCreationPartie.ANNULER);
        for(String s : parties){
            this.listePartie.addItem(s.replaceFirst(".xml",""));
        }

        this.setPreferredSize(new Dimension(Vue.WIDTH, Vue.HEIGHT));
        this.valider.setPreferredSize(new Dimension(PanelMenu.BUTTON_WIDTH, PanelMenu.BUTTON_HEIGHT));
        this.annuler.setPreferredSize(new Dimension(PanelMenu.BUTTON_WIDTH, PanelMenu.BUTTON_HEIGHT));
        this.listePartie.setPreferredSize(new Dimension(PanelMenu.BUTTON_WIDTH, PanelMenu.BUTTON_HEIGHT));

        Box box = Box.createVerticalBox();
        box.setPreferredSize(new Dimension(800,200));

        this.setLayout(new GridLayout(5,3, 20, 20));

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.listePartie);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.valider).setLocation(2,1);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.annuler).setLocation(3,1);


    }

    public String getListeSelected(){
        return (String) this.listePartie.getSelectedItem();
    }

    public void addActionListener(Jeu jeu){
        this.valider.addActionListener(new ControllerReprendrePartie(jeu,this));
        this.annuler.addActionListener(new ControllerReprendrePartie(jeu,this));
    }
}
