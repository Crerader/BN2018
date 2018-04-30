package model.medieval;

import model.Bateau;
import view.PanelPlacement;

import java.awt.*;

public class Galion extends Bateau {

    public final static int DAMAGES = 10;
    public final static int HP = 40;
    public final static int PROJECTILE = 6;

    public Galion(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public Galion(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }


    public String toString() {
        return "Galion";
    }
}
