package Cashcash;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class BoutonClient implements ActionListener {

    JTextField textField;
    Connection cnx;

    public BoutonClient(JTextField textField, Connection cnx) {
        this.textField = textField;
        this.cnx = cnx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String num_client = textField.getText();
            Statement stmt = cnx.createStatement();
            //Requête SQL qui sélectionne le client en fonction du numéro de client saisi.
            String sql_client = "SELECT * FROM cashcash.client WHERE numClient = " + num_client;
            ResultSet res = stmt.executeQuery(sql_client);
            ResultSetMetaData resmtdt = res.getMetaData();
            // int columns_count = resmtdt.getColumnCount();
            //res.next();

            JFrame frame_client = new JFrame("Données du client"); // Création d'une nouvelle fenêtre pour stoker les étiquettes
            String donneesClient = "<html>"; // Pour les retours à la ligne


            while(res.next()){
                // Récupération par nom de colonne dans la Base de donnée
                String adresseMail = res.getString("adresseMail");
                String telClient = res.getString("telClient");
                String numClient = res.getString("numClient");
                String codeAPE = res.getString("codeAPE");
                String SIREN = res.getString("SIREN");
                String adresse = res.getString("adresse");
                String faxClient = res.getString("faxClient");
                String raisonSociale = res.getString("raisonSociale");
                String distanceKilometre = res.getString("distanceKilometre");
                String dureeDeplacement = res.getString("dureeDeplacement");
                String numAgence = res.getString("numAgence");

                donneesClient
                        += "<u>Client <b>N°" + numClient +"</b></u>" // Affichage du numéro de client
                        + "<br/><br/><hr>" // <br> : retour à la ligne --- <hr> : séparation par un trait
                        + "Code APE :    " + codeAPE // Affichage du code APE
                        + "<br/><hr>" // ...
                        + "Code SIREN :    " + SIREN // Affichage du code SIREN
                        + "<br/><hr>" // ...
                        + "Numéro de téléphone : " + telClient // Affichage du numéro de téléphone
                        + "<br/><hr>" // ...
                        + "Domicile : " + adresse // Affichage de l'adresse du domicile
                        + "<br/><hr>" // ...
                        + "Adresse e-mail : " + adresseMail // Affichage de l'adresse mail
                        + "<br/><hr>" // ...
                        + "Numéro de FAX : " + faxClient // Affichage du numéro de fax du client
                        + "<br/><hr>" // ...
                        + "Raison sociale : " + raisonSociale // Affichage de la raison sociale
                        + "<br/><hr>" // ...
                        + "Distance en kilomètre(s) : " + distanceKilometre // Affichage de la distance en kilomètres
                        + "<br/><hr>" // ...
                        + "Durée du déplacement : " + dureeDeplacement // Affichage de la durée du déplacement
                        + "<br/><hr>" // ...
                        + "Numéro d'agence : " + numAgence // Affichage du numéro d'agence
                        + "<hr>"; // ...
            }
            donneesClient += "</html>";
            JLabel label = new JLabel(donneesClient);

            frame_client.add(label);
            frame_client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame_client.setSize(370, 490);
            frame_client.setVisible(true);

            cnx.close(); // Fermeture de la connexion

            // Automatisation de l'écriture dans la console en cas d'ajout ou de suppression de données
//             for(int i = 1; i <= columns_count; i++) {
//                 String temp = res.getString(i);
//                 String col_name = resmtdt.getColumnName(i);
//                System.out.println(col_name + " : " + temp);
//             }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
