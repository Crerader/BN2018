package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class Croiseur extends Bateau {

    public final static int DAMAGES = 14;
    public final static int HP = 96;
    public final static int PROJECTILE = 4;

    public Croiseur(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public Croiseur(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public String toString() {
        return "Croiseur";
    }
}
