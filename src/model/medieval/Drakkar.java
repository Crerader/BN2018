package model.medieval;

import model.Bateau;

import java.awt.*;

public class Drakkar extends Bateau {

    public final static int DAMAGES = 4;

    public Drakkar(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
    }

    public Drakkar(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
    }


    public String toString() {
        return "Drakkar";
    }
}
