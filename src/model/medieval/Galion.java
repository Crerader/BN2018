package model.medieval;

import model.Bateau;
import view.PanelPlacement;

import java.awt.*;

public class Galion extends Bateau {

    public final static int DAMAGES = 8;

    public Galion(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
    }

    public Galion(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_4_CASES;
        this.degats = DAMAGES;
    }


    public String toString() {
        return "Galion";
    }
}
