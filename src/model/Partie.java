package model;

import dao.AbstractDAOFactory;
import view.Vue;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Partie extends Observable {

    public final static int JOUEUR_HUMAIN = 0 ;
    public final static int JOUEUR_IA = 1;

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
     * Dernier message d'action à afficher dans la console
     * de la partie
     */
    private String lastMessage;

    /**
     * joueur vainqueur de la partie
     */
    private Joueur vainqueur;

    /**
     * Partie entièrement finie
     */
    private boolean ended;

    /**
     * Constructeur vide
     */
    public Partie() {
        this.joueurCourant = JOUEUR_HUMAIN;
        this.epoque = null;
        this.humain = null;
        this.ia = null;
        this.vueJeu = null;
        this.started = false;
        this.ready = false;
        this.ended = false;
        this.lastMessage = "";
        this.vainqueur = null;
        this.dao = AbstractDAOFactory.getAbstractDAOFactory(AbstractDAOFactory.XML);
    }

    /**
     * getter setter
     * etat final de la partie
     *
     * @return
     *          partie finie ou non
     */
    public boolean isEnded() {
        return this.ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    /**
     * getter attribut vainqueur de la partie
     * @return
     */
    public Joueur getVainqueur() {
        return this.vainqueur;
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
    public void changerJoueur() throws InterruptedException {
        if (this.joueurCourant == JOUEUR_HUMAIN) {
            this.joueurCourant = JOUEUR_IA ;
            this.log("C'est au tour de l'IA de jouer.");
            attaquer(null, null);
        } else {
            this.joueurCourant = JOUEUR_HUMAIN;
            this.log("C'est à votre tour de jouer.");
        }
        miseAjour();
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
    public void miseAjour() {
        this.setChanged();
        this.notifyObservers();
        this.lastMessage = "";
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
                    this.humain.setAdversaire(this.ia);
                    this.ia.setAdversaire(this.humain);
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
            this.log("Nouvelle partie lancée...\nC'est à votre tour de jouer.");
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
     * @param chemin fichier de sauvegarde à charger
     */
    public void load(String chemin) {
        this.dao.getPartieDAO().load(chemin, this);
    }

    /**
     * Sauvegarde une partie en cours
     * @param chemin nouveau fichier de sauvegarder creer
     */
    public void save(String chemin){
        this.dao.getPartieDAO().save(chemin,this);
    }

    /**
     * Choix du type d'attaque de l'ia
     *
     * @param ia type d'attaque
     */
    public void setChoixIA(int ia) {
        this.setIa(Ordinateur.getIA(ia));
    }

    /**
     * methode d'attaque
     * @param p
     *      case à attaquer
     * @param b
     *       bateau attaquant
     * @return
     *      attaque validee ou non
     */
    public int attaquer(Point p, Bateau b) throws InterruptedException {
        //System.out.println("attaque bateau : " + b.getDegats());
        int res;
        if(getJoueurCourant() == JOUEUR_HUMAIN) {
            res = this.humain.attaque(p, b);
            switch(res) {
                case Joueur.RATE:
                    this.log("Oups! Le tir n'a atteint aucune cible.");
                    break;
                case Joueur.COULE:
                    this.log("Bingo! Vous avez coulé un navire adverse.");
                    break;
                case Joueur.TOUCHE:
                    this.log("Yes! Votre tir a atteint une cible.");
                    break;
            }
        } else {
            res = ((Ordinateur)this.ia).jouerUnCoup();
            switch(res) {
                case Joueur.RATE:
                    this.log("Yes! L'IA vous a raté !");
                    break;
                case Joueur.COULE:
                    this.log("Nooooon, l'IA a coulé votre bateau ! :(");
                    break;
                case Joueur.TOUCHE:
                    this.log("Eh zut! L'IA a touché votre bateau. ");
                    break;
            }
        }
        if(this.ia.isDestructed() || (this.ia.getProjectileRestant() <= 0 && this.getHumain().getProjectileRestant() > 0)) {
            this.vainqueur = this.humain;
        } else if (this.humain.isDestructed() || (this.getHumain().getProjectileRestant() <= 0 && this.getIa().getProjectileRestant() > 0)) {
            this.vainqueur = this.ia;
        }else if(this.humain.getProjectileRestant() == 0 && this.getIa().getProjectileRestant() == 0){
            int bateauHumainEnVie = 0;
            for(int i = 0 ; i < this.humain.getTailleBateaux() ; i++){
                if(this.humain.getBateau(i).getHp() > 0){
                    bateauHumainEnVie++;
                }
            }
            int bateauIAEnVie = 0;
            for(int i = 0 ; i < this.ia.getTailleBateaux() ; i++){
                if(this.ia.getBateau(i).getHp() > 0){
                    bateauIAEnVie++;
                }
            }
            if(bateauHumainEnVie > bateauIAEnVie){
                this.vainqueur = this.humain;
            }else if(bateauIAEnVie > bateauHumainEnVie){
                this.vainqueur = this.ia;
            }else{
                this.vainqueur = this.humain;
            }
        }
        miseAjour();
        Thread.sleep(300);
        if(this.vainqueur == null) {
            changerJoueur();
        }
        return res;
    }


    /**
     * retourne le dernier message de log
     * @return
     *      dernier message log
     */
    public String getLastMessage() {
        return this.lastMessage;
    }

    /**
     * Imprime un nouveau message
     * @param message
     *          message à afficher
     */
    public void log(String message) {
        if(this.lastMessage == "") {
            this.lastMessage = message;
        } else if (this.lastMessage == message) {
            this.lastMessage = message;
        } else {
            //this.lastMessage += "\n" + message;
        }
        miseAjour();
        this.lastMessage = "";
    }

}
