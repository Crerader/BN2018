package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class SousMarin extends Bateau {

    public final static int DAMAGES = 6;

    public SousMarin(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
    }

    public SousMarin(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;

    }

    public String toString() {
        return "Sous-marin";
    }

}
