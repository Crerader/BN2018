package model;

import view.VueJeu;
import view.VueMenu;

import java.util.Observable;

public class Jeu extends Observable {

    private Partie partieEnCours;
    private VueMenu vueMenu;

    /**
     * Constructeur
     */
    public Jeu(){
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
    public void commencer(int epoque, int ia){
        VueJeu vueJeu = new VueJeu();
        this.partieEnCours = new Partie();
        this.partieEnCours.addObserver(vueJeu);
        this.partieEnCours.setHumain(new Humain());
        this.partieEnCours.setIa(new Ordinateur());
        this.choixEpoque(epoque);
        this.choixIA(ia);
        this.vueMenu.setVisible(false);
    }

    /**
     * Choix de l'époque des bateaux
     * @param epoque époque des bateaux, 0 : medievalle, 1 : contemporaine
     */
    private void choixEpoque(int epoque){
        this.partieEnCours.ajouterEpoque(epoque,false);
    }

    /**
     * Choix de l'attaque de l'IA
     * @param ia type d'attaque, 0 : aléatoire, 1 : en croix;
     */
    private void choixIA(int ia){
        this.partieEnCours.setChoixIA(ia);
    }

    /**
     * Charge une partie de jeu
     */
    public void charger(String chemin){
        this.partieEnCours = new Partie();
        this.partieEnCours.load(chemin);
    }

    /**
     * methode permettant de savoir si une partie
     * peut être reprise ou non.
     * @return
     */
    public boolean existePartie() {
        // T0D0 : analyse des sauvegardes si existe
        return true;
    }

    /**
     * @return vueMenu
     */
    public VueMenu getVueMenu(){
        return this.vueMenu;
    }
}
