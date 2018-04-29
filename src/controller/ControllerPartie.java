package controller;

import model.Bateau;
import model.Partie;
import view.PanelGrille;
import view.PanelPlacement;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ControllerPartie implements MouseListener {

    private Partie partie;

    public JButton caseSelected;
    public Bateau bateauSelected;
    public int caseSelectedX, caseSelectedY;

    private JButton[][] cases;

    public ControllerPartie(Partie partie) {
        this.partie = partie;
        this.bateauSelected = null;
        this.caseSelected = null;
        this.cases = null;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        Bateau b;
        switch (btn.getActionCommand()) {
            case PanelPlacement.BOUTON_BATEAU_2_CASES:
                b = partie.getHumain().getBateauByNbCaseNoPosition(2);
                this.bateauSelected = b;
                break;
            case PanelPlacement.BOUTON_BATEAU_3_CASES:
                b = partie.getHumain().getBateauByNbCaseNoPosition(3);
                this.bateauSelected = b;
                break;
            case PanelPlacement.BOUTON_BATEAU_4_CASES:
                b = partie.getHumain().getBateauByNbCaseNoPosition(4);
                this.bateauSelected = b;
                break;
            case PanelPlacement.BOUTON_BATEAU_5_CASES:
                b = partie.getHumain().getBateauByNbCaseNoPosition(5);
                this.bateauSelected = b;

                break;
            default:
                // selection d'une case de la grille
                if (SwingUtilities.isLeftMouseButton(e)) {
                    String pos = btn.getActionCommand();
                    if (this.bateauSelected != null) {
                        Point posXY = getPointFromActionCommand(pos);
                        if (this.caseSelected == null) {
                            this.caseSelectedX = (int) posXY.getX();
                            this.caseSelectedY = (int) posXY.getY();
                            btn.setBorder(new LineBorder(Color.GREEN));
                            this.caseSelected = btn;
                            partie.log("Vous attaquez en " + pos + ".");
                            try {
                                this.partie.attaquer(posXY, this.bateauSelected);
                                this.caseSelected = null;
                            } catch (Exception e1) {

                            }
                        }
//                        } else {
//                            // cases du bateau selectionnees
//                            ArrayList<Point> positions = new ArrayList<Point>();
//
//                            this.bateauSelected = null;
//                            this.caseSelected = null;
//                        }
                    }
                }
        }
    }

    @Override
    public void mousePressed (MouseEvent e){

    }

    @Override
    public void mouseReleased (MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }


    @Override
    public void mouseExited (MouseEvent e){

    }

    public void setGrille(JButton[][] cases) {
        this.cases = cases;
    }

    private Point getPointFromActionCommand(String actionCommand) {
        Point res = new Point();
        res.y = (int) (actionCommand.charAt(0)) - PanelGrille.ASCII;
        res.x = Character.getNumericValue(actionCommand.charAt(1));
        return res;
    }


    private void refreshGrille() {
//        if (this.prevCases != null) {
//            for (JButton j : this.prevCases) {
//                j.setBackground(new JButton().getBackground());
//            }
//            this.prevCases.clear();
//        }
    }

    private void cancelSelection() {
        if (this.caseSelected != null) {
            this.caseSelected.setBackground(new JButton().getBackground());
            this.caseSelected = null;
        }
        this.refreshGrille();
    }


}
