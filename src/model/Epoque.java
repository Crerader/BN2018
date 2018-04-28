package model;

import java.util.HashMap;

public abstract class Epoque {

    /**
     * Identifiant epoque medieval
     */
    public static final int MEDIEVAL = 0;
    /**
     * Identifiant epoque contemporaine
     */
    public static final int CONTEMPORAINE = 1;

    /**
     * Methode permettant la création des bateaux au début de la partie
     * @param joueur, joueur qui doit placer ces bateaux
     */
    public abstract void creerBateau(Joueur joueur);

    /**
     * @param epoque identifiant de l'époque
     * @return medieval ou contemporaine
     */
    public static Epoque getFactory(int epoque){
        switch (epoque){
            case MEDIEVAL:
                return new Medieval();
            case CONTEMPORAINE:
                return new Contemporaine();
            default:
                return new Medieval();

        }
    }

    /**
     * @return identifiant Epoque
     */
    public abstract int getType();


}
