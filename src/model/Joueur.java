package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Joueur {

    /**
     * Nombre de bateaux par joueurs
     */
    public final static int NB_BATEAU = 5;

    /**
     * Plateau des cases attaquées par le joueur
     */
    protected boolean[][] attaque;
    /**
     * Joueur adverse
     */
    protected Joueur adversaire;
    /**
     * Liste des bateaux disponibles ou coulés du joueur
     */
    protected ArrayList<Bateau> bateaux;

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
    public void ajouterBateauPositions(Bateau bateau, ArrayList<Point> positions){
        bateau.setPositions(positions);
        this.bateaux.add(bateau);
    }

    /**
     * Ajoute un bateau sans position à un joueur
     * Utile lors du placement des bateaux, recupere la liste des bateaux disponibles
     * @param bateau
     */
    public void ajouterBateauSansPosi(Bateau bateau){
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
     * méthode qui récupère le premier bateau de nombre de case donné
     * qui n'est pas encore positionné sur la grille
     * @param nbCase
     *          nombre de case du bateau souhaité
     * @return
     *          premier bateau disponible
     */
    public Bateau getBateauNoPosition(int nbCase) {
        for(Bateau b : this.bateaux) {
            if(b.getTaillePosition() == 0 && b.getNbCase() == nbCase) {
                return b;
            }
        }
        return null;
    }

    /**
     * @return la taille de la liste de bateaux
     */
    public int getTailleBateaux(){
        return this.bateaux.size();
    }

    /**
     * recupere la liste courante des bateaux du joueur classée
     * par nombre de case de bateau
     */
    public HashMap<Integer, Integer> getListeBateauxBySize() {
        HashMap<Integer, Integer> liste = new HashMap<Integer, Integer>();
        for(Bateau b : this.bateaux) {
            int taille = b.getNbCase();
            if(liste.get(taille) != null) {
                liste.put(taille, liste.get(taille)+1);
            } else {
                liste.put(taille, 1);
            }
        }
        return liste;
    }

    /**
     * recupere la liste courante des bateaux du joueur classée
     * par nombre de case de bateau
     */
    public ArrayList<Bateau> getListeBateaux() {
        return this.bateaux;
    }
}
