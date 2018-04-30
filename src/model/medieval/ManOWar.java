package model.medieval;

import model.Bateau;

import java.awt.*;

public class ManOWar extends Bateau {

    public final static int DAMAGES = 15;
    public final static int HP = 45;
    public final static int PROJECTILE = 8;

    public ManOWar(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public ManOWar(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }


    public String toString() {
        return "ManOWar";
    }
}
