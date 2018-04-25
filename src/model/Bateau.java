package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Bateau {

    public static final int TAILLE_5_CASES = 5;
    public static final int TAILLE_4_CASES = 4;
    public static final int TAILLE_3_CASES = 3;
    public static final int TAILLE_2_CASES = 2;

    public static final int VIE_5_CASES = 30;
    public static final int VIE_4_CASES = 20;
    public static final int VIE_3_CASES = 15;
    public static final int VIE_2_CASES = 10;



    protected int hp;
    protected ArrayList<Point> positions;
    protected BufferedImage image;
    protected int nbCase;

    //Temporaire
    protected Color color;


    public Bateau(int hp,String chemin){
        this.hp = hp;
        this.positions = new ArrayList<>();
        try {
             image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //En attendant d'avoir les images, on utilise des couleurs différentes pour les bateaux
    public Bateau(int hp, Color color){
        this.hp = hp;
        this.positions = new ArrayList<>();
        this.color = color;
    }

    public void setPositions(ArrayList<Point> positions){
        this.positions = (ArrayList<Point>) positions.clone();
    }

    /**
     * @param i un élément de la liste positions
     * @return un point
     */
    public Point getPostion(int i) {
        return this.positions.get(i);
    }

    /**
     * @return la taille de la liste des cases (représenter par des points)
     */
    public int getTaillePosition(){
        return this.positions.size();
    }

    /**
     * @return le nombre de points de vie d'un bateau
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * @return la taille d'un bateau (correspond au nombre de cases)
     */
    public int getNbCase() {
        return this.nbCase;
    }

    /**
     * Temporaire
     * @return une couleur pour representer le bateau
     */
    public Color getColor() {
        return this.color;
    }

    public abstract void prendreDegat();
}
