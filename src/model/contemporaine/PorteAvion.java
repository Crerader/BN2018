package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class PorteAvion extends Bateau {

    public final static int DAMAGES = 30;
    public final static int HP = 120;
    public final static int PROJECTILE = 3;

    public PorteAvion(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;
    }

    public PorteAvion(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
        this.projectile = PROJECTILE;

    }


    public String toString() {
        return "Porte Avion";
    }
}
