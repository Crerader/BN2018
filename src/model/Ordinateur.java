package model;

import ia.IAAleatoire;
import ia.IACroiser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public  abstract class Ordinateur extends Joueur{

    public static final int ALEATOIRE = 0;
    public static final int CROISER = 1;



    public Ordinateur(){
        super();
    }

    public abstract int getType();

    public abstract int jouerUnCoup();

    /**
     * Crée l'IA en fonction de la stratégie d'attaque choisie
     * @param id
     * @return un type d'IA
     */
    public static Ordinateur getIA(int id){
        switch (id){
            case ALEATOIRE :
                return new IAAleatoire();
            case CROISER :
                return new IACroiser();
            default:
                return new IAAleatoire();
        }
    }


    /**
     * Placer les beataux de l'IA de façon aléatoire
     */
    public void placerBateau(){
        Random rand = new Random();
        boolean posOk = false;
        int initX=0, initY = 0 , dir = 0;
        //Pour chaque bateau
        for(Bateau b : bateaux){
            //Tant qu'on a pas des valeur OK pour les positions de ce bateau
            while(!posOk) {
                //On récupère un x et un y dans la taille du tableau
                initX = rand.nextInt(10);
                initY = rand.nextInt(10);
                //Direction du bateau = 0:Nord ; 1 : Sud ; 2:Est ; 3:Ouest
                dir = rand.nextInt(4);

                //On regarde si les coordonnées sont ok
                switch (dir) {
                    //Dans le cas ou la direction est vers le nord : on regarde si la position initiale crée - la taille du bateau ne sort pas du tableau
                    case 0:
                        if (initY - b.getNbCase() >= 0) {
                            posOk = this.positionsDispo(dir,b.getNbCase(),initX,initY);
                        }
                        break;
                    //De meme pour chaque direction
                    case 1:
                        if(initY + b.getNbCase() <= 10)
                            posOk = this.positionsDispo(dir,b.getNbCase(),initX,initY);
                        break;
                    case 2:
                        if(initX + b.getNbCase() <= 10)
                            posOk = this.positionsDispo(dir,b.getNbCase(),initX,initY);
                        break;
                    case 3:
                        if(initX - b.getNbCase() >= 0)
                            posOk = this.positionsDispo(dir,b.getNbCase(),initX,initY);
                        break;
                }
            }

            //On place les coordonnées du bateau
            int i = 0;
            ArrayList<Point> pos = new ArrayList<Point>();
            switch(dir){
                case 0:
                    while(pos.size() < b.getNbCase()){
                        pos.add(new Point(initX,initY-i));
                        i++;
                    }
                    break;
                case 1:
                    while(pos.size() < b.getNbCase()){
                        pos.add(new Point(initX,initY+i));
                        i++;
                    }
                    break;
                case 2:
                    while(pos.size() < b.getNbCase()){
                        pos.add(new Point(initX+i,initY));
                        i++;
                    }
                    break;
                case 3:
                    while(pos.size() < b.getNbCase()){
                        pos.add(new Point(initX-i,initY));
                        i++;
                    }
                    break;
            }
            b.setPositions(pos);
            posOk = false;
            //System.out.println(pos);
        }
    }

    /**
     * Vérifie si on peut placer un bateau en partant d'un point de départ et en suivant une direction
     * @param dir direction
     * @param nbCaseBateau taille du bateau
     * @param initX coord x
     * @param initY coord y
     * @return true si le bateau peut être placé, false sinon
     */
    private boolean positionsDispo(int dir, int nbCaseBateau, int initX, int initY){
        boolean res = true;
        int i = 0;
        ArrayList<Point> pos = new ArrayList<Point>();
        switch(dir){
            case 0:
                while(pos.size() < nbCaseBateau){
                    if(this.getPlaceDispo(initX,initY-i)){
                        pos.add(new Point(initX,initY-i));
                        i++;
                    }else{
                        res = false;
                        break;
                    }
                }
                break;
            case 1:
                while(pos.size() < nbCaseBateau){
                    if(this.getPlaceDispo(initX,initY+i)){
                        pos.add(new Point(initX,initY+i));
                        i++;
                    }else{
                        res = false;
                        break;
                    }
                }
                break;
            case 2:
                while(pos.size() < nbCaseBateau){
                    if(this.getPlaceDispo(initX+i,initY)){
                        pos.add(new Point(initX+i,initY));
                        i++;
                    }else{
                        res = false;
                        break;
                    }
                }
                break;
            case 3:
                while(pos.size() < nbCaseBateau){
                    if(this.getPlaceDispo(initX-i,initY)){
                        pos.add(new Point(initX-i,initY));
                        i++;
                    }else{
                        res = false;
                        break;
                    }
                }
                break;
        }
        return res;
    }

    /**
     * @return un bateau aléatoire de l'IA qui est encore en vie
     */
    protected Bateau getBateauRandom(){
        Random rand = new Random();
        boolean stop = false;
        int bateau = 0;
        Bateau b = null;
        while (!stop){
            bateau = rand.nextInt(5);
            b = this.getBateau(bateau);
            if(b.getHp() > 0 ){
                stop = true;
            }
        }
        return b;
    }


    public String toString() {
        return "Ordinateur";
    }


}
