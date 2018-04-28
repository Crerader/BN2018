package dao;

import model.Humain;
import model.Joueur;
import model.Ordinateur;
import model.Partie;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

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
            Joueur ordinateur = new Ordinateur();
            partie.setIa(ordinateur);
            NodeList iaAttrs = ia.getChildNodes();
            Node type = iaAttrs.item(1);
            int choixIA = Integer.parseInt(type.getTextContent());
            ((Ordinateur) ordinateur).setStyle(choixIA);
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
            //System.out.println(partie.getHumain().getBateau(0).getColor().toString());


            //charge les bateaux du joueur Humain
            int nbNodeBateaux = humainBateaux.getLength();
            for(int i = 0 ; i < nbNodeBateaux ; i++){
                Node nodeHumainBateau = humainBateaux.item(i);
                if(nodeHumainBateau.getNodeName().equals("bateau")){
                    NodeList nodeList = nodeHumainBateau.getChildNodes();
                    int nbNodeList = nodeList.getLength();
                    for(int  j = 0 ; j < nbNodeList ; j++){
                        System.out.println(" j : " + j + " : " + nodeList.item(j).getNodeName());
                    }
                }
                //System.out.println(" i : " + i + " : " + humainBateaux.item(i).getNodeName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Partie partie) {

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
}
