package model.medieval;

import model.Bateau;

import java.awt.*;

public class Drakkar extends Bateau {

    public final static int DAMAGES = 4;
    public final static int HP = 16;

    public Drakkar(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }

    public Drakkar(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_2_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }


    public String toString() {
        return "Drakkar";
    }
}
