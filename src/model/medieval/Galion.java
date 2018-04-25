package model.medieval;

import model.Bateau;
import view.PanelPlacement;

import java.awt.*;

public class Galion extends Bateau {

    public Galion(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_4_CASES;
    }

    public Galion(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_4_CASES;
    }

    @Override
    public void prendreDegat() {

    }

    public String toString() {
        return "Galion";
    }
}
