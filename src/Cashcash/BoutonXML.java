package Cashcash;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class BoutonXML implements ActionListener {

    JTextField textField;
    Connection cnx;

    public BoutonXML(JTextField textField, Connection cnx) {
        this.textField = textField;
        this.cnx = cnx;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cashcash"; // Connexion à la base de données
            String user = "root"; // Nom d'utilisateur
            String password = ""; // Mot de passe
            Connection cnx = DriverManager.getConnection(url, user, password);


            String num_client = textField.getText(); // Stockage du numéro de client saisi (dans la variable num_client)
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM cashcash.client WHERE numClient = " + num_client; // Séléction du client ayant le n° de client saisi auparavant
            ResultSet res = stmt.executeQuery(sql); // Exécution de la requête

            //Créer un nouveau frame pour stocker l'étiquette
            // JFrame frame = new JFrame("Liste des adresses mail");
            // String adresseMailConcat = "<html>"; // Pour les retours à la ligne

            //étape 5: extraire les données
            while (res.next()) {
                //Récupérer par nom de colonne
                String numClient = res.getString("numClient");
                String codeAPE = res.getString("codeAPE");
                String SIREN = res.getString("SIREN");
                String telClient = res.getString("telClient");
                String adresse = res.getString("adresse");
                String adresseMail = res.getString("adresseMail");
                String faxClient = res.getString("faxClient");
                String raisonSociale = res.getString("raisonSociale");
                String distanceKilometre = res.getString("distanceKilometre");
                String dureeDeplacement = res.getString("dureeDeplacement");
                String numAgence = res.getString("numAgence");

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // Ajout de l'élément principal
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("ClientCashCash");
                doc.appendChild(rootElement);

                // Element principal
                Element client = doc.createElement("client");
                rootElement.appendChild(client);

                // Section : Numéro de client
                Element numeroClient = doc.createElement("numero");
                numeroClient.appendChild(doc.createTextNode(numClient) );
                client.appendChild(numeroClient);

                // Section : Code APE
                Element codeape = doc.createElement("codeAPE");
                codeape.appendChild(doc.createTextNode(codeAPE) );
                client.appendChild(codeape);

                // Section : Code SIREN
                Element codesiren = doc.createElement("siren");
                codesiren.appendChild(doc.createTextNode(SIREN) );
                client.appendChild(codesiren);

                // Section : Téléphone client
                Element numerotel = doc.createElement("telephone");
                numerotel.appendChild(doc.createTextNode(telClient) );
                client.appendChild(numerotel);

                // Section : Adresse
                Element adressedom = doc.createElement("adresse");
                adressedom.appendChild(doc.createTextNode(adresse) );
                client.appendChild(adressedom);

                // Section : Adresse e-mail
                Element adressemail = doc.createElement("mail");
                adressemail.appendChild(doc.createTextNode(adresseMail) );
                client.appendChild(adressemail);

                // Section : Numéro de fax
                Element numerofax = doc.createElement("fax");
                numerofax.appendChild(doc.createTextNode(faxClient) );
                client.appendChild(numerofax);

                // Section : Raison sociale
                Element raisons = doc.createElement("raisonSociale");
                raisons.appendChild(doc.createTextNode(raisonSociale) );
                client.appendChild(raisons);

                // Section : Distance en kilomètres
                Element distance = doc.createElement("distanceKilometres");
                distance.appendChild(doc.createTextNode(distanceKilometre) );
                client.appendChild(distance);

                // Section : Durée du déplacement
                Element duree = doc.createElement("dureeDeplacement");
                duree.appendChild(doc.createTextNode(dureeDeplacement) );
                client.appendChild(duree);

                // Section : Numéro d'agence
                Element numeroagence = doc.createElement("agence");
                numeroagence.appendChild(doc.createTextNode(numAgence) );
                client.appendChild(numeroagence);



                // Ajouter du contenu dans le document XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("./FichiersXML/Fichier_client_n°" + num_client + ".xml"));

                transformer.transform(source, result);

            }
            cnx.close(); // Fermeture de la connexion

            System.out.println("Le fichier XML a été généré avec succès !");

            // Codes d'erreurs
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

}
