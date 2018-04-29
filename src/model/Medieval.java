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
        Bateau manowar = new ManOWar(Bateau.VIE_5_CASES,Color.RED);
        Bateau galion = new Galion(Bateau.VIE_4_CASES,Color.BLUE);
        Bateau drakkar = new Drakkar(Bateau.VIE_3_CASES,Color.ORANGE);
        Bateau caravelle1 = new Caravelle(Bateau.VIE_2_CASES,Color.GREEN);
        Bateau caravelle2 = new Caravelle(Bateau.VIE_2_CASES,Color.GREEN);

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
