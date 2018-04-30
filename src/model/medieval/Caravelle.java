package model.medieval;

import model.Bateau;

import java.awt.*;

public class Caravelle extends Bateau {

    public final static int DAMAGES = 6;
    public final static int HP = 24;

    public Caravelle(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }

    public Caravelle( Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }



    public String toString() {
        return "Caravelle";
    }
}
