package controller;

import javafx.scene.layout.Pane;
import model.Bateau;
import model.Partie;
import view.PanelGrille;
import view.PanelPlacement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ControllerPlacement implements MouseListener {

    private Partie partie;

    public JButton caseSelected;
    public Bateau bateauSelected;
    public int caseSelectedX, caseSelectedY;


    private ArrayList<JButton> prevCases;
    private ArrayList<JButton> occupiedCases;
    private JButton[][] cases;

    public ControllerPlacement(Partie partie) {

        this.partie = partie;
        this.bateauSelected = null;
        this.caseSelected = null;
        this.cases = null;
        this.prevCases = new ArrayList<JButton>();
        this.occupiedCases = new ArrayList<JButton>();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        Bateau b;
        switch (btn.getActionCommand()) {
            case PanelPlacement.BOUTON_BATEAU_2_CASES:
                if (this.caseSelected == null) {
                    b = partie.getHumain().getBateauNoPosition(2);
                    if (b != null) {
                        // lancement detection click placement
                        this.bateauSelected = b;
                    }
                }else {
                    cancelSelection();
                }
                break;
            case PanelPlacement.BOUTON_BATEAU_3_CASES:
                if (this.caseSelected == null) {
                    b = partie.getHumain().getBateauNoPosition(3);
                    if (b != null) {
                        this.bateauSelected = b;
                    }
                }else {
                    cancelSelection();
                }
                break;
            case PanelPlacement.BOUTON_BATEAU_4_CASES:
                if (this.caseSelected == null) {
                    b = partie.getHumain().getBateauNoPosition(4);
                    if (b != null) {
                        this.bateauSelected = b;
                    }
                } else {
                    cancelSelection();
                }
                break;
            case PanelPlacement.BOUTON_BATEAU_5_CASES:
                if (this.caseSelected == null) {
                    b = partie.getHumain().getBateauNoPosition(5);
                    if (b != null) {
                        this.bateauSelected = b;
                    }
                }else {
                    cancelSelection();
                }
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
                            btn.setBackground(Color.RED);
                            this.caseSelected = btn;
                        } else {
                            // cases du bateau selectionnees
                            ArrayList<Point> positions = new ArrayList<Point>();
                            for (JButton j : this.prevCases) {
                                Point p = getPointFromActionCommand(j.getActionCommand());
                                positions.add(p);
                                j.setBackground(this.bateauSelected.getColor());
                                occupiedCases.add(j);
                            }
                            this.caseSelected.setBackground(this.bateauSelected.getColor());
                            occupiedCases.add(this.caseSelected);
                            this.partie.placerBateau(this.bateauSelected, this.partie.getHumain(), positions);
                            this.bateauSelected = null;
                            this.caseSelected = null;
                            this.prevCases.clear();
                        }
                    }
                } else {
                    cancelSelection();
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
        public void mouseEntered (MouseEvent e){
            JButton btn = (JButton) e.getSource();
            switch (btn.getActionCommand()) {
                case PanelPlacement.BOUTON_BATEAU_2_CASES:
                    break;
                case PanelPlacement.BOUTON_BATEAU_3_CASES:
                    break;
                case PanelPlacement.BOUTON_BATEAU_4_CASES:
                    break;
                case PanelPlacement.BOUTON_BATEAU_5_CASES:
                    break;
                default:

                    if (this.caseSelected != null) {
                        Point pos = getPointFromActionCommand(btn.getActionCommand());
                        JButton entered;
                        switch (getDirectionFromPosition(this.caseSelectedX, this.caseSelectedY, (int) pos.getX(), (int) pos.getY())) {
                            case "RIGHT":
                                if (this.caseSelectedX + this.bateauSelected.getNbCase()-1 < 10) {
                                    refreshGrille();
                                    for (int i = 1; i < this.bateauSelected.getNbCase(); i++) {
                                        entered = this.cases[this.caseSelectedY][this.caseSelectedX + i];
                                        this.prevCases.add(entered);
                                        entered.setBackground(Color.RED);
                                    }
                                }
                                break;
                            case "LEFT":
                                if (this.caseSelectedX - this.bateauSelected.getNbCase()+1 >= 0) {
                                    refreshGrille();
                                    for (int i = 1; i < this.bateauSelected.getNbCase(); i++) {
                                        entered = this.cases[this.caseSelectedY][this.caseSelectedX - i];
                                        this.prevCases.add(entered);
                                        entered.setBackground(Color.RED);
                                    }
                                }
                                break;
                            case "DOWN":
                                if (this.caseSelectedY + this.bateauSelected.getNbCase()-1 < 10) {
                                    refreshGrille();
                                    for (int i = 1; i < this.bateauSelected.getNbCase(); i++) {
                                        entered = this.cases[this.caseSelectedY + i][this.caseSelectedX];
                                        this.prevCases.add(entered);
                                        entered.setBackground(Color.RED);
                                    }
                                }
                                break;
                            case "UP":
                                if (this.caseSelectedY - this.bateauSelected.getNbCase()+1 >= 0) {
                                    refreshGrille();
                                    for (int i = 1; i < this.bateauSelected.getNbCase(); i++) {
                                        entered = this.cases[this.caseSelectedY - i][this.caseSelectedX];
                                        this.prevCases.add(entered);
                                        entered.setBackground(Color.RED);
                                    }
                                }
                                break;
                        }
                    }
            }
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

    private String getDirectionFromPosition(int originX, int originY, int x, int y) {
        String res = "";
        if (x > originX && y == originY) {
            res = "RIGHT";
        } else if (x < originX && y == originY) {
            res = "LEFT";
        } else if (x == originX && y > originY) {
            res = "DOWN";
        } else if (x == originX && y < originY) {
            res = "UP";
        }
        return res;
    }

    private void refreshGrille() {
        if (this.prevCases != null) {
            for (JButton j : this.prevCases) {
                j.setBackground(new JButton().getBackground());
            }
            this.prevCases.clear();
        }
    }

    private void cancelSelection() {
        if (this.caseSelected != null) {
            this.caseSelected.setBackground(new JButton().getBackground());
            this.caseSelected = null;
        }
        this.refreshGrille();
    }


}