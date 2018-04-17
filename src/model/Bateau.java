package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Bateau {

    private int hp;
    private ArrayList<Point> positions;
    private BufferedImage image;

    public Bateau(int hp,String chemin){
        this.hp = hp;
        this.positions = new ArrayList<>();
        try {
             image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
