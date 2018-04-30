package model.medieval;

import model.Bateau;

import java.awt.*;

public class Drakkar extends Bateau {

    public final static int DAMAGES = 6;
    public final static int HP = 16;
    public final static int PROJECTILE = 25;

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
