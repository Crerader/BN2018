package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class Torpilleur extends Bateau {

    public Torpilleur(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_2_CASES;
    }

    public Torpilleur(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_2_CASES;
    }


    public String toString() {
        return "Torpilleur";
    }
}
