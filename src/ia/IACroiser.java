package ia;

import model.Bateau;
import model.Joueur;
import model.Ordinateur;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class IACroiser extends Ordinateur {


    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int EST = 3;
    public static final int WEST = 4;
    public static final int NEW = 0;


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
                System.out.println("On entre bien ici");
                boolean arret = false;
                while (!arret) {
                    int id = rand.nextInt(4) + 1;
                    if (!this.nbTitr.contains(id)) {
                        switch (id) {
                            case NORTH:
                                if (this.middle.getX() + 1 < 10) {
                                    attaque = new Point((int) this.middle.getX() + 1, (int) this.middle.getY());
                                    arret = true;
                                }
                                this.nbTitr.add(NORTH);
                                break;
                            case SOUTH:
                                if (this.middle.getX() - 1 >= 0) {
                                    attaque = new Point((int) this.middle.getX() - 1, (int) this.middle.getY());
                                    arret = true;
                                }
                                this.nbTitr.add(SOUTH);
                                break;
                            case EST:
                                if (this.middle.getY() + 1 < 10) {
                                    attaque = new Point((int) this.middle.getX() , (int) this.middle.getY() + 1);
                                    arret = true;
                                }
                                this.nbTitr.add(EST);
                                break;
                            case WEST:
                                if (this.middle.getY() - 1 >= 0) {
                                    attaque = new Point((int) this.middle.getX(), (int) this.middle.getY() - 1);
                                    arret = true;
                                }
                                this.nbTitr.add(WEST);
                                break;
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
        if(this.nbTitr.size() == 5){
            this.nbTitr = new ArrayList<>();
        }
        res = this.adversaire.estAttaque(attaque, b.getDegats());
        switch (res){
            case Joueur.RATE :
                this.addAttaqueRate(attaque);
                break;
            case Joueur.TOUCHE :
                this.addAttaqueTouche(attaque);
                this.nbTitr.add(6);
                break;
            default:
                break;
        }
        b.retirerProjectile();
        return res;
    }
}
