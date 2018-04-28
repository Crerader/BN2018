package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ordinateur extends Joueur{

    //String utilisé pour le jeu en croix afin de savoir quel a été le dernier mouvement
    //Cela permettra de savoir quel est la croix que l'on doit regarder
    private String lastPlay;
    private String styleDeJeu;

    public Ordinateur(){
        super();
    }

    public void setStyle(String s){
        this.styleDeJeu = s;
    }


    public void jouerUnCoup(){
        switch(styleDeJeu){
            case "aleatoire":
                this.jouerAleatoire();
                break;
            case "croix":
                this.jouerCroix();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Aucun style de jeu sélectionné");
                break;
        }
    }

    private void jouerAleatoire() {
        Random rand = new Random();
        int i = rand.nextInt(10);
        int j = rand.nextInt(10);
        if(attaque[i][j] ==false ){
            attaque[i][j] = true;
        }

    }

    private void jouerCroix() {

    }

    //TODO : A modifier afin de vérifier que l'on ne passe pas au dessus des autres bateaux déjà placés
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
                            posOk = true;
                        }
                        break;
                    //De meme pour chaque direction
                    case 1:
                        if(initY + b.getNbCase() <= 10)
                            posOk = true;
                        break;
                    case 2:
                        if(initX + b.getNbCase() <= 10)
                            posOk = true;
                        break;
                    case 3:
                        if(initX - b.getNbCase() >= 0)
                            posOk = true;
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
            i=0;
            posOk = false;
        }
    }



}
