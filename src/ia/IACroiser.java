package ia;

import model.Bateau;
import model.Joueur;
import model.Ordinateur;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class IACroiser extends Ordinateur {


    public static final int EST = 1;
    public static final int SOUTH = 2;
    public static final int NORTH = -2;
    public static final int WEST = -1;
    public static final int NEW = 0;
    public static int directionAttaque = 0 ;
    public int coupPrecedent = 0;


    /**
     * Point de référence pour l'attaque croisé
     */
    private Point middle;

    /**
     * Utile pour savoir quand il faut changer de points de référence
     */
    private ArrayList<Integer> nbTitr;

    public IACroiser() {
        this.middle = null;
        this.nbTitr = new ArrayList<>();
    }


    @Override
    public int getType() {
        return CROISER;
    }

    @Override
    public int jouerUnCoup() {
        int res = 0;
        Random rand = new Random();
        boolean stop = false;
        Bateau b = this.getBateauRandom();
        Point attaque = null;
        int tmpDir = 0;
        while (!stop) {
            if (this.nbTitr.size() == NEW) {
                int x = rand.nextInt(10);
                int y = rand.nextInt(10);
                if (!existePoint(this.attaqueRate, x, y)) {
                    if (!existePoint(this.attaqueTouche, x, y)) {
                        attaque = new Point(x, y);
                        this.middle = new Point(x, y);
                        stop = true;
                    }
                }
            } else {
                boolean arret = false;
                while (!arret) {
                    if (directionAttaque == 0) {
                        int id = (rand.nextInt(4) + 1)-2
                                ;
                        HashMap<Boolean, Point> resMethodAttaque = this.attaqueMethod(id);
                        Set<Boolean> set = resMethodAttaque.keySet();
                        for(boolean bool : set) {
                            attaque = resMethodAttaque.get(bool);
                            arret = bool;
                        }
                        tmpDir = id;
                    }else{
                        HashMap<Boolean, Point> resMethodAttaque = this.attaqueMethod(directionAttaque);
                        Set<Boolean> set = resMethodAttaque.keySet();
                        for(boolean bool : set) {
                            attaque = resMethodAttaque.get(bool);
                            arret = bool;
                        }

                    }
                    if(attaque == null && this.nbTitr.size() == 5){
                        arret = true;
                        this.nbTitr = new ArrayList<>();
                    }else{
                        stop = true;
                    }
                }
            }
        }
        /*if(this.nbTitr.size() == 5){
            this.nbTitr = new ArrayList<>();
        }*/
        res = this.adversaire.estAttaque(attaque, b.getDegats());
        switch (res){
            case Joueur.RATE :
                this.addAttaqueRate(attaque);
                /*switch(coupPrecedent) {
                    case 0:
                    case Joueur.RATE:
                    case Joueur.COULE:
                        directionAttaque = 0;
                        break;
                    default:
                        directionAttaque = (directionAttaque * -1);
                }*/
                coupPrecedent = Joueur.RATE;
                break;
            case Joueur.TOUCHE :
                this.addAttaqueTouche(attaque);
                this.nbTitr.add(5); //On ajoute le centre à nbtitr, sinon on ne rentrera jamais dans le else
                if(directionAttaque == 0){
                    directionAttaque = tmpDir;
                }
                coupPrecedent = Joueur.TOUCHE;
                break;
            case Joueur.COULE:
                this.nbTitr = new ArrayList<>();
                coupPrecedent = Joueur.COULE;
            default:
                break;
        }
        b.retirerProjectile();
        return res;
    }

    private HashMap<Boolean, Point> attaqueMethod(int i){
        HashMap<Boolean, Point> retour = new HashMap<>();
        boolean arret =false;
        Point attaque = null;
        int add = this.nbTitr.size();
        switch (i) {
            case NORTH:
                if (this.middle.getY() + 1 < 10) {
                    attaque = new Point((int) this.middle.getX(), (int) this.middle.getY()-add);
                    arret = true;
                }
                break;
            case SOUTH:
                if (this.middle.getY() - 1 >= 0) {
                    attaque = new Point((int) this.middle.getX(), (int) this.middle.getY()+add);
                    arret = true;
                }
                break;
            case EST:
                if (this.middle.getX() + 1 < 10) {
                    attaque = new Point((int) this.middle.getX() +add, (int) this.middle.getY());
                    arret = true;
                }
                break;
            case WEST:
                if (this.middle.getX() - 1 >= 0) {
                    attaque = new Point((int) this.middle.getX() -add, (int) this.middle.getY());
                    arret = true;
                }
                break;
        }
        retour.put(arret,attaque);
        return retour;
    }
}
