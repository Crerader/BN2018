package model;

import model.contemporaine.Croiseur;
import model.contemporaine.PorteAvion;
import model.contemporaine.SousMarin;
import model.contemporaine.Torpilleur;

import java.awt.*;
import java.util.HashMap;

public class Contemporaine extends Epoque {
    @Override
    public void creerBateau(Joueur joueur) {
        Bateau porteAvion = new PorteAvion(Color.GRAY);
        Bateau croiseur = new Croiseur(Color.YELLOW);
        Bateau sousMarin1 = new SousMarin(Color.PINK);
        Bateau sousMarin2 = new SousMarin(Color.PINK);
        Bateau torpilleur = new Torpilleur(Color.CYAN);

        joueur.ajouterBateauSansPosi(porteAvion);
        joueur.ajouterBateauSansPosi(croiseur);
        joueur.ajouterBateauSansPosi(sousMarin1);
        joueur.ajouterBateauSansPosi(sousMarin2);
        joueur.ajouterBateauSansPosi(torpilleur);
    }

    @Override
    public int getType() {
        return Epoque.CONTEMPORAINE;
    }


}
