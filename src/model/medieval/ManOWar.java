package model.medieval;

import model.Bateau;

import java.awt.*;

public class ManOWar extends Bateau {

    public final static int DAMAGES = 10;

    public ManOWar(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
    }

    public ManOWar(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
    }


    public String toString() {
        return "ManOWar";
    }
}
