package model.medieval;

import model.Bateau;

import java.awt.*;

public class ManOWar extends Bateau {

    public final static int DAMAGES = 10;
    public final static int HP = 40;

    public ManOWar(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }

    public ManOWar(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }


    public String toString() {
        return "ManOWar";
    }
}
