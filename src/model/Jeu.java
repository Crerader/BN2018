package model;

import view.VueMenu;

import java.util.Observable;

public class Jeu extends Observable {

    private Partie partie;
    private VueMenu vueMenu;

    /**
     * Constructeur
     */
    public Jeu(){
        this.partie = new Partie();
        this.vueMenu = new VueMenu(this);
        this.addObserver(this.vueMenu);
    }

    /**
     * Lancement du jeu
     * @param args
     */
    public static void main(String[]args){
        Jeu jeu = new Jeu();

    }

    /**
     * Créer une partie de bataille navale
     */
    public void commencer(){


    }

    /**
     * Choix de l'époque des bateaux
     * @param epoque époque des bateaux, 0 : medievalle, 1 : contemporaine
     */
    public void choixEpoque(int epoque){
        this.partie.ajouterEpoque(epoque);
    }

    /**
     * Charge une partie de jeu
     */
    public void charger(){

    }

    /**
     * @return vueMenu
     */
    public VueMenu getVueMenu(){
        return this.vueMenu;
    }
}
