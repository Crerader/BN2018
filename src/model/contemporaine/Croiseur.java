package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class Croiseur extends Bateau {

    public final static int DAMAGES = 10;

    public Croiseur(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
    }

    public Croiseur(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
    }

    public String toString() {
        return "Croiseur";
    }
}
