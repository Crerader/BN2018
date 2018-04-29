package model.contemporaine;

import model.Bateau;

import java.awt.*;

public class PorteAvion extends Bateau {

    public PorteAvion(int hp, String chemin) {
        super(hp, chemin);
        this.nbCase = Bateau.TAILLE_5_CASES;
    }

    public PorteAvion(int hp, Color color) {
        super(hp, color);
        this.nbCase = Bateau.TAILLE_5_CASES;

    }


    public String toString() {
        return "Porte Avion";
    }
}
