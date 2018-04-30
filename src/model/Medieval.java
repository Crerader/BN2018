package model;

import model.medieval.Caravelle;
import model.medieval.Drakkar;
import model.medieval.Galion;
import model.medieval.ManOWar;

import java.awt.*;
import java.util.HashMap;

public class Medieval extends Epoque {

    @Override
    public void creerBateau(Joueur joueur) {
        Bateau manowar = new ManOWar(Color.RED);
        Bateau galion = new Galion(Color.BLUE);
        Bateau drakkar = new Drakkar(Color.ORANGE);
        Bateau caravelle1 = new Caravelle(Color.GREEN);
        Bateau caravelle2 = new Caravelle(Color.GREEN);

        joueur.ajouterBateauSansPosi(manowar);
        joueur.ajouterBateauSansPosi(galion);
        joueur.ajouterBateauSansPosi(drakkar);
        joueur.ajouterBateauSansPosi(caravelle1);
        joueur.ajouterBateauSansPosi(caravelle2);
    }

    @Override
    public int getType() {
        return Epoque.MEDIEVAL;
    }

}
