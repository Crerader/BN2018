package ia;

import model.Ordinateur;

public class IACroiser extends Ordinateur {

    //String utilisé pour le jeu en croix afin de savoir quel a été le dernier mouvement
    //Cela permettra de savoir quel est la croix que l'on doit regarder
    private String lastPlay;


    @Override
    public int getType() {
        return CROISER;
    }

    @Override
    public int jouerUnCoup() {
        return 0;
    }
}
