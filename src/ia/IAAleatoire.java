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
        Bateau b = this.getBateauRandom();
        boolean stop = false;
        while (!stop) {
            res = 0;
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            if (!existePoint(this.attaqueRate, x, y)) {
                if (!existePoint(this.attaqueTouche, x, y)) {
                    res = this.adversaire.estAttaque(new Point(x, y), b.getDegats());
                    switch (res) {
                        case Joueur.RATE:
                            this.addAttaqueRate(new Point(x, y));
                            break;
                        case Joueur.TOUCHE:
                            this.addAttaqueTouche(new Point(x, y));
                            break;
                        default:
                            break;
                    }
                    stop = true;
                }
            }
        }
        return res;


    }
}
