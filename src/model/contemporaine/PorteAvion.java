package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class PorteAvion extends Bateau {

    public final static int DAMAGES = 24;
    public final static int HP = 96;

    public PorteAvion(String chemin) {
        super(chemin);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;
    }

    public PorteAvion(Color color) {
        super(color);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
        this.hp = HP;

    }


    public String toString() {
        return "Porte Avion";
    }
}
