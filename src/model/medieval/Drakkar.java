package model.medieval;

import model.Bateau;

import java.awt.*;

public class Drakkar extends Bateau {

    public final static int DAMAGES = 4;
    public final static int HP = 16;
    public final static int PROJECTILE = 6;

    public Drakkar(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public Drakkar(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }


    public String toString() {
        return "Drakkar";
    }
}
