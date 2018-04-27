package model;

import javax.swing.*;
import java.util.Random;

public class Ordinateur extends Joueur{

    //String utilisé pour le jeu en croix afin de savoir quel a été le dernier mouvement
    //Cela permettra de savoir quel est la croix que l'on doit regarder
    private String lastPlay;
    private String styleDeJeu;

    public Ordinateur(){
        super();
    }

    public void setStyle(String s){
        this.styleDeJeu = s;
    }


    public void jouerUnCoup(){
        switch(styleDeJeu){
            case "aleatoire":
                this.jouerAleatoire();
                break;
            case "croix":
                this.jouerCroix();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Aucun style de jeu sélectionné");
                break;
        }
    }

    private void jouerAleatoire() {
        Random rand = new Random();
        int i = rand.nextInt(10);
        int j = rand.nextInt(10);
        if(attaque[i][j] ==false ){
            attaque[i][j] = true;
        }

    }

    private void jouerCroix() {

    }


}
