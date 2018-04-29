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
    public void jouerUnCoup() {
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
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            if(!existePoint(this.attaqueRate,x,y)){
                if(!existePoint(this.attaqueTouche,x,y)){
                    Bateau bateauAdvers = this.adversaire.getBateauPosition(new Point(x,y));
                    if(bateauAdvers != null && bateauAdvers.getHp() > 0){
                        bateauAdvers.prendreDegat(b.getDegats());
                        this.addAttaqueTouche(new Point(x,y));
                        stop = true;
                    }else{
                        this.addAttaqueRate(new Point(x,y));
                        stop = true;
                    }
                }
            }
        }


    }
}
