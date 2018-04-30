package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class Croiseur extends Bateau {

    public final static int DAMAGES = 30;
    public final static int HP = 120;

    public Croiseur(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }

    public Croiseur(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }

    public String toString() {
        return "Croiseur";
    }
}
