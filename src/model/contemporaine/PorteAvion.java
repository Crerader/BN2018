package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class PorteAvion extends Bateau {

    public final static int DAMAGES = 8;

    public PorteAvion(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;
    }

    public PorteAvion(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_5_CASES;
        this.degats = DAMAGES;

    }


    public String toString() {
        return "Porte Avion";
    }
}
