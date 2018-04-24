package model;

import java.awt.*;
import java.util.ArrayList;

public abstract class Joueur {

    /**
     * Nombre de bateaux par joueurs
     */
    public final static int NB_BATEAU = 5;

    /**
     * Plateau des cases attaquées par le joueur
     */
    private boolean[][] attaque;
    /**
     * Joueur adverse
     */
    private Joueur adversaire;
    /**
     * Liste des bateaux disponibles ou coulés du joueur
     */
    private ArrayList<Bateau> bateaux;

    /**
     * Constructeur vide
     */
    public Joueur(){
        this.adversaire = null;
        this.bateaux = new ArrayList<Bateau>();
        this.attaque = new boolean[10][10];
    }

    /**
     * Ajout de bateau lors de la création de partie
     * @param bateau qui a été placé
     * @param positions différentes cases occupé par le bateau
     */
    public void ajouterBateau(Bateau bateau, ArrayList<Point> positions){
        bateau.setPositions(positions);
        this.bateaux.add(bateau);
    }

    /**
     * @param i position du bateau dans la liste
     * @return un bateau
     */
    public Bateau getBateau(int i) {
        return this.bateaux.get(i);
    }

    /**
     * @return la taille de la liste de bateaux
     */
    public int getTailleBateaux(){
        return this.bateaux.size();
    }
}
