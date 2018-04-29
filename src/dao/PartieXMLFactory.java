package dao;

import model.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PartieXMLFactory implements PartieDAO {

    public volatile static PartieXMLFactory instance = null;

    private DocumentBuilderFactory factory;
    private StringBuilder enteteXML;

    private PartieXMLFactory(){
        this.factory = DocumentBuilderFactory.newInstance();
        this.enteteXML = new StringBuilder();
        this.enteteXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
        this.enteteXML.append("<!DOCTYPE Bataille [" + "\n");
        this.enteteXML.append("<!ELEMENT Bataille (Humain, IA, Epoque, Courant)>" + "\n");
        this.enteteXML.append("<!ELEMENT Humain (attaques, bateaux)>" + "\n");
        this.enteteXML.append("<!ELEMENT attaques (attaque*)>" + "\n");
        this.enteteXML.append("<!ELEMENT attaque (x, y)>" + "\n");
        this.enteteXML.append("<!ELEMENT x (#PCDATA)>" + "\n");
        this.enteteXML.append("<!ELEMENT y (#PCDATA)>" + "\n");
        this.enteteXML.append("<!ELEMENT bateaux (bateau*)>" + "\n");
        this.enteteXML.append("<!ELEMENT bateau (type, hp, Positions)>" + "\n");
        this.enteteXML.append("<!ELEMENT type (#PCDATA)>" + "\n");
        this.enteteXML.append("<!ELEMENT hp (#PCDATA)>" + "\n");
        this.enteteXML.append("<!ELEMENT Positions (Position+)>" + "\n");
        this.enteteXML.append("<!ELEMENT Position (x, y)>" + "\n");
        this.enteteXML.append("<!ELEMENT IA (type, attaques, bateaux)>" + "\n");
        this.enteteXML.append("<!ELEMENT Epoque (#PCDATA)>" + "\n");
        this.enteteXML.append("<!ELEMENT Courant (#PCDATA)>" + "\n");
        this.enteteXML.append("] >" + "\n");
    }

    public static PartieXMLFactory getInstance(){
        if(instance == null){
            synchronized (PartieXMLFactory.class){
                if(instance == null){
                    instance = new PartieXMLFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public void load(String chemin, Partie partie) {
        try {
            DocumentBuilder builder = this.factory.newDocumentBuilder();
            File fileXML = new File(chemin);
            Document xml = builder.parse(fileXML);
            Element root = xml.getDocumentElement();

            //Recupere attributs humain
            NodeList nodes = root.getElementsByTagName("Humain");
            Node humain = nodes.item(0);
            Joueur humain1 = new Humain();
            partie.setHumain(humain1);
            NodeList humainAttrs = humain.getChildNodes();
            NodeList humainAttaques = humainAttrs.item(1).getChildNodes();
            NodeList humainBateaux = humainAttrs.item(3).getChildNodes();

            //Recupere attributs IA
            nodes = root.getElementsByTagName("IA");
            Node ia = nodes.item(0);
            NodeList iaAttrs = ia.getChildNodes();
            Node type = iaAttrs.item(1);
            int choixIA = Integer.parseInt(type.getTextContent());
            Joueur ordinateur = Ordinateur.getIA(choixIA);
            partie.setIa(ordinateur);
            NodeList iaAttaques = iaAttrs.item(3).getChildNodes();
            NodeList iaBateaux = iaAttrs.item(5).getChildNodes();

            //Recupere époque
            nodes = root.getElementsByTagName("Epoque");
            Node epoque = nodes.item(0);
            int partieEpoque = Integer.parseInt(epoque.getTextContent());

            //Recupere joueur courant
            nodes = root.getElementsByTagName("Courant");
            Node courant = nodes.item(0);
            int joueurCourant = Integer.parseInt(courant.getTextContent());
            if(partie.getJoueurCourant() != joueurCourant){
                partie.changerJoueur();
            }

            //charge les cases attaqués par le joueur Humain
            this.addAttaque(humainAttaques,humain1);
            //charge les cases attaques par le joueur IA
            this.addAttaque(iaAttaques,ordinateur);

            //Création des bateaux en fonction de l'époque
            partie.ajouterEpoque(partieEpoque,true);

            //charge les bateaux du joueur Humain
            this.addBoat(humainBateaux,humain1);

            //charge les bateaux de l'ordinateur
            this.addBoat(iaBateaux,ordinateur);

            partie.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String chemin, Partie partie) {
        try {
            BufferedWriter save = new BufferedWriter(new FileWriter(chemin));
            save.write(this.enteteXML.toString());
            save.write("<Bataille>" + "\n");
            save.write("<Humain>" + "\n");
            save.write("<attaques>" + "\n");
            save.write(this.attaques(partie.getHumain()));
            save.write("</attaques>" + "\n");
            save.write("<bateaux>" + "\n");
            save.write(this.bateaux(partie.getHumain()));
            save.write("</bateaux>" + "\n");
            save.write("</Humain>" + "\n");
            save.write("<IA>" + "\n");
            save.write("<type>" + ((Ordinateur) partie.getIa()).getType() + "</type>" + "\n");
            save.write("<attaques>" + "\n");
            save.write(this.attaques(partie.getIa()));
            save.write("</attaques>" + "\n");
            save.write("<bateaux>" + "\n");
            save.write(this.bateaux(partie.getIa()));
            save.write("</bateaux>" + "\n");
            save.write("</IA>" + "\n");
            save.write("<Epoque>" + partie.getEpoque().getType() + "</Epoque>" + "\n");
            save.write("<Courant>" + partie.getJoueurCourant() + "</Courant>" + "\n");
            save.write("</Bataille>" + "\n");
            save.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Ajoute les attaques sauvegarder au joueur
     * @param root sauvegarde des attaques
     * @param j joueur
     */
    private void addAttaque(NodeList root, Joueur j){
        int nbNodeRootAttaques = root.getLength();
        for(int i = 0; i < nbNodeRootAttaques; i++) {
            Node nodeRootAttaques = root.item(i);
            if(nodeRootAttaques.getNodeName().equals("attaque")){
                NodeList coor = nodeRootAttaques.getChildNodes();
                Node x = coor.item(1);
                Node y = coor.item(3);
                j.setAttaque(Integer.parseInt(x.getTextContent()), Integer.parseInt(y.getTextContent()));
            }
        }
    }

    /**
     * Ajout les bateaux contenu dans la sauvegarde au joueur
     * @param root sauvegarde des bateaux
     * @param joueur joueur
     */
    private void addBoat(NodeList root, Joueur joueur){
        int nbNodeBateaux = root.getLength();
        for(int i = 0 ; i < nbNodeBateaux ; i++){
            Node nodeRotBateau = root.item(i);
            if(nodeRotBateau.getNodeName().equals("bateau")){
                NodeList nodeList = nodeRotBateau.getChildNodes();
                Node typeBateau = nodeList.item(1);
                //Recupere le type de bateau
                String typeB = typeBateau.getTextContent();
                Node hpBateau = nodeList.item(3);
                //Recupere son nombre de points de vie
                int hpB = Integer.parseInt(hpBateau.getTextContent());
                //Recupere les différentes positions dans le tableau des cases
                NodeList positions = nodeList.item(5).getChildNodes();
                ArrayList<Point> pos = new ArrayList<>();

                int nbNodeList = positions.getLength();
                for(int j = 0 ; j < nbNodeList ; j++){
                    if(positions.item(j).getNodeName().equals("Position")){
                        NodeList p = positions.item(j).getChildNodes();
                        Node x = p.item(1);
                        Node y = p.item(3);
                        pos.add(new Point(Integer.parseInt(x.getTextContent()), Integer.parseInt(y.getTextContent())));
                    }
                }
                for(int j = 0 ; j < joueur.getTailleBateaux() ; j++){
                    Bateau tmp = joueur.getBateau(j);
                    if(tmp.toString().equals(typeB)){
                        if(tmp.getTaillePosition() == 0){
                            tmp.setHp(hpB);
                            tmp.setPositions(pos);
                        }
                    }
                }
            }

        }
    }

    /**
     * @param joueur joueur
     * @return un string format XML comprenant les positions où le joueur a attaqué
     */
    private String attaques(Joueur joueur){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < joueur.getTaillePlateau() ; i++){
            for(int j = 0 ; j < joueur.getTaillePlateau() ; j++){
                if(joueur.getAttaque(i,j)){
                    res.append("<attaque>" + "\n");
                    res.append("<x>" + i + "</x>" +  "\n");
                    res.append("<y>" + j + "</y>" + "\n");
                    res.append("</attaque>" + "\n");
                }
            }
        }
        return res.toString();
    }

    /**
     * @param joueur joueur
     * @return un string format XML comprenant les informations des bateaux qu'a un joueur
     */
    private String bateaux(Joueur joueur){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < joueur.getTailleBateaux() ; i++) {
            Bateau tmp = joueur.getBateau(i);
            res.append("<bateau>" + "\n");
            res.append("<type>" + tmp.toString() + "</type>" + "\n");
            res.append("<hp>" + tmp.getHp() + "</hp>" + "\n");
            res.append("<Positions>" + "\n");
            for (int j = 0; j < tmp.getTaillePosition(); j++) {
                res.append("<Position>" + "\n");
                res.append("<x>" + (int)tmp.getPostion(j).getX() + "</x>" +  "\n");
                res.append("<y>" + (int)tmp.getPostion(j).getY() + "</y>" + "\n");
                res.append("</Position>" + "\n");
            }
            res.append("</Positions>" + "\n");
            res.append("</bateau>" + "\n");

        }
        return res.toString();
    }
}
