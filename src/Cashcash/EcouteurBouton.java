package Cashcash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.sql.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EcouteurBouton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Générer un fichier XML")){


            try {

                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver OK !");
                String url="jdbc:mysql://localhost:3306/cashcash";
                String user="root";
                String password="";
                Connection cnx= DriverManager.getConnection(url, user, password);
                System.out.println("Connexion à la base de donnée établie.");



                Statement stmt = cnx.createStatement();
                String sql = "SELECT codeAPE, SIREN, telClient, adresse, adresseMail, faxClient, raisonSociale FROM cashcash.client";
                ResultSet res = stmt.executeQuery(sql);

                //étape 5: extraire les données
                while(res.next()){
                    //Récupérer par nom de colonne
                    String codeAPE = res.getString("codeAPE");
                    String SIREN = res.getString("SIREN");
                    String telClient = res.getString("telClient");
                    String adresse = res.getString("adresse");
                    String adresseMail = res.getString("adresseMail");
                    String faxClient = res.getString("faxClient");
                    String raisonSociale = res.getString("raisonSociale");

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    // Ajout de l'élément principal
                    Document doc = docBuilder.newDocument();
                    Element rootElement = doc.createElement("Cashcash");
                    doc.appendChild(rootElement);

                    // staff elements
                    Element staff = doc.createElement("Clients");
                    rootElement.appendChild(staff);

                    // Ajout de l'élément Prénom
                    Element codeape = doc.createElement("CodeAPE");
                    codeape.appendChild(doc.createTextNode(codeAPE));
                    staff.appendChild(codeape);

                    // Ajout de l'élément Nom de famille
                    Element siren = doc.createElement("SIREN");
                    siren.appendChild(doc.createTextNode(SIREN));
                    staff.appendChild(siren);

                    // Ajout de l'élément Nom de famille
                    Element tel = doc.createElement("Téléphone");
                    tel.appendChild(doc.createTextNode(telClient));
                    staff.appendChild(tel);

                    // Ajout de l'élément Nom de famille
                    Element addr = doc.createElement("Adresse");
                    addr.appendChild(doc.createTextNode(adresse));
                    staff.appendChild(addr);

                    // Ajout de l'élément Nom de famille
                    Element addrmail = doc.createElement("Mail");
                    addrmail.appendChild(doc.createTextNode(adresse));
                    staff.appendChild(addrmail);

                    // Ajout de l'élément Nom de famille
                    Element fax = doc.createElement("Fax");
                    fax.appendChild(doc.createTextNode(faxClient));
                    staff.appendChild(fax);

                    // Ajout de l'élément Nom de famille
                    Element raisons = doc.createElement("RaisonSociale");
                    raisons.appendChild(doc.createTextNode(raisonSociale));
                    staff.appendChild(raisons);

                    // Ajouter du contenu dans le document XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File("Fichier_de_"+ codeAPE +".xml"));

                    transformer.transform(source, result);



                }
                cnx.close();

                System.out.println("Les fichiers ont été générés avec succès !");

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

        else if (e.getActionCommand().equals("Deuxième bouton")) {
            System.out.println("Thé eau sale eins");
        }


    }
}
