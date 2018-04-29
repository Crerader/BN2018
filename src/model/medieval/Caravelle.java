package model.medieval;

import model.Bateau;

import java.awt.*;

public class Caravelle extends Bateau {

    public final static int DAMAGES = 6;

    public Caravelle(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
    }

    public Caravelle(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
    }



    public String toString() {
        return "Caravelle";
    }
}
