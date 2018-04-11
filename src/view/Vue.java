package view;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;
import java.util.Observer;

/**
 * Classe abstraite Vue, permet l'implementation d'une fenêtre
 * avec ses actions utilisateurs propres.
 */
public abstract class Vue implements Observer {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    /**
     * fenêtre à afficher
     */
    protected JPanel panel;

    /**
     * Controller associée à la vue
     */
    protected EventListener controller;

    /**
     * Constructeur
     */
    public Vue() {}


}
