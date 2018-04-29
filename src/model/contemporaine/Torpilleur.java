package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class Torpilleur extends Bateau {

    public final static int DAMAGES = 4;

    public Torpilleur(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
    }

    public Torpilleur(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;

    }

    public String toString() {
        return "Torpilleur";
    }
}
