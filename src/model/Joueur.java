package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Joueur {

    /**
     * statuts d'une attaque
     */
    public final static int TOUCHE = 1;
    public final static int RATE = 2;
    public final static int COULE = 3;

    /**
     * Nombre de bateaux par joueurs
     */
    public final static int NB_BATEAU = 5;

    /**
     * Plateau des cases attaquées par le joueur
     */
    protected boolean[][] attaque;

    /**
     * Plateau des cases attaquées par le joueur
     */
    protected ArrayList<Point> attaqueRate;

    /**
     * Plateau des cases attaquées par le joueur
     */
    protected ArrayList<Point> attaqueTouche;
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
        this.attaqueRate = new ArrayList<Point>();
        this.attaqueTouche = new ArrayList<Point>();
        this.attaque = new boolean[10][10];
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                this.attaque[i][j] = false;
            }
        }
    }

    /**
     * permet de set un adversaire à ce joueur
     * @param adv
     *      autre Joueur
     */
    public void setAdversaire(Joueur adv) {
        this.adversaire = adv;
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
     * méthode qui récupère le premier bateau de nombre de case donné
     * qui n'est pas encore positionné sur la grille
     * @param nbCase
     *          nombre de case du bateau souhaité
     * @return
     *          premier bateau disponible
     */
    public Bateau getBateauByNbCaseNoPosition(int nbCase) {
        for(Bateau b : this.bateaux) {
            if(b.getNbCase() == nbCase) {
                return b;
            }
        }
        return null;
    }

    /**
     * methode permettant de recuperer un bateau
     * à une position Point (x, y) donnée
     *
     * @param p
     *      position (Point(x, y)) donnée
     * @return
     *      bateau si existe ou null sinon
     */
    public Bateau getBateauPosition(Point p) {
        Bateau res = null;
        for(Bateau b : this.bateaux) {
            for(Point pos : b.getPositions()) {
                if(pos == p) {
                    res = b;
                }
            }
        }
        return res;
    }

    /**
     * @return la taille du plateau
     */
    public int getTaillePlateau(){
        return this.attaque.length;
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
     * Ajoute la position touche
     */
    public void setAttaqueTouche(Point pxy){
        this.attaqueTouche.add(pxy);
    }

    /**
     * Ajoute la position rate
     */
    public void setAttaqueRatee(Point pxy){
        this.attaqueRate.add(pxy);
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
     * Change la valeur de la case du tableau attaque à true
     * @param x
     * @param y
     */
    public void setAttaque(int x, int y){
        this.attaque[x][y] = true;
    }

    /**
     * retourne la liste des cases visées
     * mais ratées
     * @return
     */
    public ArrayList<Point> getAttaqueRate() {
        return this.attaqueRate;
    }

    /**
     * retourne la liste des cases visées et
     * touchées
     * @return
     */
    public ArrayList<Point> getAttaqueTouche() {
        return attaqueTouche;
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

    /**
     * methode d'attaque du joueur vers son adversaire
     * @param position
     *          position à attaquer
     * @param bateauAttaquant
     *          bateau attaquant
     * @return
     *          attaque reussie ou non
     */
    public int attaque(Point position, Bateau bateauAttaquant) {
        int res =  this.adversaire.estAttaque(position, bateauAttaquant.getDegats());
        switch(res) {
            case Joueur.TOUCHE | Joueur.COULE:
                this.setAttaqueTouche(position);
                break;
            case Joueur.RATE :
                this.setAttaqueRatee(position);
                break;
        }
        return res;
    }

    /**
     * methode pour recevoir une attaque
     *
     * @param position
     *          position ou le joueur est attaqué
     * @param degats
     *          nombre de dégats recus
     * @return
     *          attaque reussie ou non
     */
    public int estAttaque(Point position, int degats) {
        int touche = Joueur.RATE;
        Bateau bateauAttaque = this.getBateauPosition(position);
        if(bateauAttaque != null) {
            if(bateauAttaque.prendreDegat(degats)) {
                touche = Joueur.COULE;
            } else {
                touche = Joueur.TOUCHE;
            }
        }
        return touche;
    }
}
