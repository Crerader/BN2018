package model.medieval;

import model.Bateau;

import java.awt.*;

public class Drakkar extends Bateau {


    public Drakkar(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_2_CASES;
    }

    public Drakkar(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_2_CASES;
    }

    @Override
    public void prendreDegat() {

    }
}
