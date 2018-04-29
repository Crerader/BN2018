package model;

import view.VueJeu;
import view.VueMenu;

import java.io.File;
import java.util.ArrayList;
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
        jeu.miseAjour();
    }

    /**
     * Créer une partie de bataille navale
     */
    public void commencer(int epoque, int ia){
        VueJeu vueJeu = new VueJeu();
        this.partieEnCours = new Partie();
        this.partieEnCours.addObserver(vueJeu);
        this.partieEnCours.setHumain(new Humain());
        this.choixIA(ia);
        this.choixEpoque(epoque);
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
        VueJeu vueJeu = new VueJeu();
        this.partieEnCours.addObserver(vueJeu);
        this.partieEnCours.load(chemin);
        this.vueMenu.setVisible(false);
    }

    /**
     * methode permettant de savoir si une partie
     * peut être reprise ou non.
     * @return
     */
    public ArrayList<String> existePartie() {
        File directory = new File("save");
        String [] listefichiers;
        ArrayList<String> res = new ArrayList<>();
        int i;
        listefichiers = directory.list();
        for(i = 0; i < listefichiers.length; i++){
            if(listefichiers[i].endsWith(".xml")){
                res.add(listefichiers[i]);
            }
        }
        return res;
    }

    /**
     * @return vueMenu
     */
    public VueMenu getVueMenu(){
        return this.vueMenu;
    }

    /**
     * Actualise la vue du joueur humain
     */
    private void miseAjour() {
        this.setChanged();
        this.notifyObservers();
    }

}
