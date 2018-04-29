package model;

import dao.AbstractDAOFactory;
import view.Vue;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Partie extends Observable {

    /**
     * constantes pour le nombre de bateau
     * dans une catégorie donnée (2, 3, .., cases données)
     */
    public final static int NB_BATEAU_2 = 1;
    public final static int NB_BATEAU_3 = 2;
    public final static int NB_BATEAU_4 = 1;
    public final static int NB_BATEAU_5 = 1;

    /**
     * Partie lancee ou non
     */
    private boolean started;

    /**
     * Partie prête à être lancée ou non
     */
    private boolean ready;

    /**
     * Joueur qui doit jouer
     * 0 : humain
     * 1 : ordinateur
     */
    private int joueurCourant;

    /**
     * Époque de la bataille navale
     */
    private Epoque epoque;

    /**
     * Joueur humain
     */
    private Joueur humain;

    /**
     * Ordinateur
     */
    private Joueur ia;

    /**
     * DAO pour la sauvegarde et le chargement d'une partie
     */
    private AbstractDAOFactory dao;

    /**
     *
     */
    private Vue vueJeu;

    /**
     * Constructeur vide
     */
    public Partie() {
        this.joueurCourant = 0;
        this.epoque = null;
        this.humain = null;
        this.ia = null;
        this.vueJeu = null;
        this.started = false;
        this.ready = false;
        this.dao = AbstractDAOFactory.getAbstractDAOFactory(AbstractDAOFactory.XML);
    }

    /**
     * @return joueur courant
     */
    public int getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Change de joueur
     */
    public void changerJoueur() {
        if (this.joueurCourant == 0) {
            this.joueurCourant = 1;
        } else {
            this.joueurCourant = 0;
        }
    }

    /**
     * @return époque medieval/contemporaine
     */
    public Epoque getEpoque() {
        return epoque;
    }

    /**
     * Set une époque via une factory
     *
     * @param epoque 0 : medieval, 1 : contemporaine
     */
    public void ajouterEpoque(int epoque, boolean sauvegarde) {
        if (epoque == 0) {
            this.epoque = Epoque.getFactory(Epoque.MEDIEVAL);
        } else {
            this.epoque = Epoque.getFactory(Epoque.CONTEMPORAINE);
        }
        this.epoque.creerBateau(humain);
        this.epoque.creerBateau(ia);
        if (!sauvegarde) {
           Ordinateur tmp = (Ordinateur) this.getIa();
           tmp.placerBateau();
        }
        this.miseAjour();
    }

    /**
     * @return le joueur humain
     */
    public Joueur getHumain() {
        return humain;
    }

    /**
     * Set le joueur humain
     *
     * @param humain
     */
    public void setHumain(Joueur humain) {
        this.humain = humain;
    }

    /**
     * @return le joueur IA
     */
    public Joueur getIa() {
        return ia;
    }

    /**
     * Set le joueur ordinateur
     *
     * @param ia
     */
    public void setIa(Joueur ia) {
        this.ia = ia;
    }

    /**
     * Actualise la vue du joueur humain
     */
    private void miseAjour() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Methode qui ajoute un bateau a un joueur en vérifiant si le bateau peut être placé
     *
     * @param bateau    qui doit être placé
     * @param joueur    qui place le bateau
     * @param positions différentes cases occupé par le bateau
     * @return true si placement reussi, false sinon
     */
    public boolean placerBateau(Bateau bateau, Joueur joueur, ArrayList<Point> positions) {
        boolean res = true;
        for (Point p : positions) {
            if (!joueur.getPlaceDispo((int) p.getX(), (int) p.getY())) {
                res = false;
                break;
            }
        }
        if (res) {
            joueur.ajouterBateauPositions(bateau, positions);
        }
        miseAjour();
        return res;
    }

    /**
     * permet de savoir si la partie est
     * prête à être lancée ou non.
     *
     * @return statut prêt de la partie
     */
    public boolean isReady() {
        boolean ready = false;
        if(this.ready == false) {
            if(this.humain != null && this.ia != null) {
                if(this.humain.isReady() && this.ia.isReady()) {
                    this.ready = true;
                    miseAjour();
                    return true;
                }
            }
        } else {
            return this.ready;
        }
        return ready;
    }

    /**
     * permet de lancer la partie
     */
    public void start() {
        if (!started) {
            started = true;
            miseAjour();
        }
    }

    /**
     * permet de savoir si la partie
     * est lancee ou non
     *
     * @return statut lance de la partie
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * récupère la liste des bateau d'un des deux joueurs
     *
     * @param ia booléen pour savoir si on veut la liste de l'ia ou non
     * @return liste bateau du joueur demandé
     */
    public HashMap<Integer, Integer> getListeBateauxBySize(boolean ia) {
        if (ia) {
            return this.ia.getListeBateauxBySize();
        } else {
            return this.humain.getListeBateauxBySize();
        }
    }

    /**
     * récupère la liste des bateau d'un des deux joueurs
     *
     * @param ia booléen pour savoir si on veut la liste de l'ia ou non
     * @return liste bateau du joueur demandé
     */
    public ArrayList<Bateau> getListeBateaux(boolean ia) {
        if (ia) {
            return this.ia.getListeBateaux();
        } else {
            return this.humain.getListeBateaux();
        }
    }

    /**
     * Charge une partie
     *
     * @param chemin fichier de sauvegarde à charger
     */
    public void load(String chemin) {
        this.dao.getPartieDAO().load(chemin, this);
    }

    /**
     * Choix du type d'attaque de l'ia
     *
     * @param ia type d'attaque
     */
    public void setChoixIA(int ia) {
        this.setIa(Ordinateur.getIA(ia));
    }

}
