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
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                this.attaque[i][j] = false;
            }
        }
    }

    /**
     * Changement position bateau donné
     * @param bateau qui a été placé
     * @param positions différentes cases occupé par le bateau
     */
    public void ajouterBateauPositions(Bateau bateau, ArrayList<Point> positions){
        bateau.setPositions(positions);
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
            liste.put(b.getNbCase(), 0);
        }
        int i = 0;
        for(Bateau b : this.bateaux) {
            int taille = b.getNbCase();
            if(b.getTaillePosition() > 0) {
                liste.put(taille, liste.get(taille)+1);
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

    /**
     * Change la valeur de la case du tableau attaque à true
     * @param x
     * @param y
     */
    public void setAttaque(int x, int y){
        this.attaque[x][y] = true;
    }

    /**
     * @return la taille du plateau
     */
    public int getTaillePlateau(){
        return this.attaque.length;
    }

    /**
     * @param x
     * @param y
     * @return true si le joueur a attaqué cette position, false sinon
     */
    public boolean getAttaque(int x, int y) {
        return this.attaque[x][y];
    }

    /**
     * methode qui retourne true si le joueur est prêt à jouer
     * == que tous ses bateaux sont placés
     *
     * @return
     *      true si joueur prêt
     */
    public boolean isReady() {
        boolean pret = true;
        if (this.getTailleBateaux() == Joueur.NB_BATEAU) {
            for (Bateau b : this.getListeBateaux()) {
                if (b.getTaillePosition() < b.getNbCase()) {
                    pret = false;
                    break;
                }
            }
        } else pret = false;
        return pret;
    }

    /**
     * @param x coord x
     * @param y coord y
     * @return true si un bateau n'est pas placé au coordonnée donnée en paramètre, false sinon
     */
    public boolean getPlaceDispo(int x, int y){
        boolean res = true;
        for(int i = 0; i < this.getTailleBateaux() ; i++){
            Bateau tmp = this.getBateau(i);
            for(int j = 0 ; j < tmp.getTaillePosition() ; j++){
                Point p = tmp.getPostion(j);
                if(p.getX() == x && p.getY() == y){
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
}
