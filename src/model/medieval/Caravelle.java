package model.medieval;

import model.Bateau;

import java.awt.*;

public class Caravelle extends Bateau {


    public Caravelle(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_3_CASES;
    }

    public Caravelle(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_3_CASES;
    }

    @Override
    public void prendreDegat() {

    }

    public String toString() {
        return "Caravelle";
    }
}
