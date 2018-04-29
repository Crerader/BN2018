package ia;

import model.Bateau;
import model.Joueur;
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
        stop = false;
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
