package ia;

import model.Ordinateur;

import java.util.Random;

public class IAAleatoire extends Ordinateur {


    @Override
    public int getType() {
        return ALEATOIRE;
    }

    @Override
    public void jouerUnCoup() {
        Random rand = new Random();
        int i = rand.nextInt(10);
        int j = rand.nextInt(10);
        if(attaque[i][j] ==false ){
            attaque[i][j] = true;
        }
    }
}
