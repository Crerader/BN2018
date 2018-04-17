package model;

import dao.AbstractDAOFactory;
import view.Vue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Partie extends Observable {

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
     * @return époque medieval/contemporaine
     */
    public Epoque getEpoque() {
        return epoque;
    }

    /**
     * Set une époque via une factory
     * @param epoque 0 : medieval, 1 : contemporaine
     */
    public void ajouterEpoque(int epoque) {
        if(epoque == 0){
            this.epoque = Epoque.getFactory(Epoque.MEDIEVAL);
        }else{
            this.epoque = Epoque.getFactory(Epoque.CONTEMPORAINE);
        }
        this.epoque.creerBateau(humain);
        //Methode placer bateau pour IA

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

    /**
     * Methode qui ajoute un bateau a un joueur en vérifiant si le bateau peut être placé
     * @param bateau qui doit être placé
     * @param joueur qui place le bateau
     * @param positions différentes cases occupé par le bateau
     * @return true si placement reussi, false sinon
     */
    public boolean placerBateau(Bateau bateau, Joueur joueur, ArrayList<Point> positions){
        boolean res = true;
        for (Point p: positions){
            for (int i = 0 ; i < joueur.getTailleBateaux() ; i++){
                Bateau tmpBateau = joueur.getBateau(i);
                for (int j = 0; j < tmpBateau.getTaillePosition(); j++){
                    Point tmpPoint = tmpBateau.getPostion(j);
                    if(tmpPoint.getX() == p.getX() && tmpPoint.getY() == p.getY()){
                        res = false;
                        break;
                    }
                }
            }
        }
        if(res){
            joueur.ajouterBateau(bateau,positions);
        }
        return res;
    }

    
}
