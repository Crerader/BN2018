package model.medieval;

import model.Bateau;

import java.awt.*;

public class Caravelle extends Bateau {

    public final static int DAMAGES = 8;
    public final static int HP = 24;
    public final static int PROJECTILE = 20;

    public Caravelle(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public Caravelle( Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }



    public String toString() {
        return "Caravelle";
    }
}
