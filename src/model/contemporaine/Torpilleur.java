package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class Torpilleur extends Bateau {

    public final static int DAMAGES = 12;
    public final static int HP = 48;

    public Torpilleur(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }

    public Torpilleur(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
        this.hp = HP;

    }

    public String toString() {
        return "Torpilleur";
    }
}
