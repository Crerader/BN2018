package model;

import dao.AbstractDAOFactory;
import view.Vue;

public class Partie extends Observable{

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
    public Partie(){
        this.joueurCourant = 0;
        this.epoque = null;
        this.humain = null;
        this.ia = null;
        this.vueJeu = null;
        //this.dao = AbstractDAOFactory.getAbstractDAOFactory(AbstractDAOFactory.XML);
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
        if(this.joueurCourant == 0){
            this.joueurCourant = 1;
        }else{
            this.joueurCourant = 0;
        }
    }

    /**
     *
     * @return époque medievalle/contemporaine
     */
    public Epoque getEpoque() {
        return epoque;
    }

    /**
     * Set une époque via une factory
     * @param epoque 0 : medievalle, 1 : contemporaine
     */
    public void ajouterEpoque(int epoque) {
        if(epoque == 0){
            //this.epoque = Epoque.getFactory(Epoque.MEDIEVALLE);
        }else{
            //this.epoque = Epoque.getFactory(Epoque.CONTEMPORAINE);
        }

    }

    /**
     * @return le joueur humain
     */
    public Joueur getHumain() {
        return humain;
    }

    /**
     * Set le joueur humain
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
     * @param ia
     */
    public void setIa(Joueur ia) {
        this.ia = ia;
    }

    /**
     * Actualise la vue du joueur humain
     */
    private void miseAjour(){
        this.setChanged();
        this.notifyObservers();
    }

    
}
