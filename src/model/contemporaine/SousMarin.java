package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class SousMarin extends Bateau {

    public final static int DAMAGES = 24;
    public final static int HP = 72;
    public final static int PROJECTILE = 8;

    public SousMarin(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public SousMarin(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_3_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;

    }

    public String toString() {
        return "Sous-marin";
    }

}
