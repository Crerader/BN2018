package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class SousMarin extends Bateau {

    public SousMarin(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_3_CASES;
    }

    public SousMarin(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_3_CASES;
    }

    public String toString() {
        return "Sous-marin";
    }

}
