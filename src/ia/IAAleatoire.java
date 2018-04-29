package ia;

import model.Bateau;
import model.Ordinateur;

import java.awt.*;
import java.util.Random;

public class IAAleatoire extends Ordinateur {


    @Override
    public int getType() {
        return ALEATOIRE;
    }

    @Override
    public int jouerUnCoup() {
        int res = 0;
        Random rand = new Random();
        Bateau b = this.getBateauRandom();
        boolean stop = false;
        while(!stop){
            res = 0;
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            if(!existePoint(this.attaqueRate,x,y)){
                if(!existePoint(this.attaqueTouche,x,y)){
                    res = this.estAttaque(new Point(x,y),b.getDegats());
                    stop = true;
                }
            }
        }
        return  res;


    }
}
