package Cashcash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
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

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Ajout de l'élément principal
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Cashcash");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Membres");
            rootElement.appendChild(staff);

            // Ajout de l'élément STAFF
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            // Ajout de l'élément Prénom
            Element firstname = doc.createElement("Prénom");
            firstname.appendChild(doc.createTextNode("Eric"));
            staff.appendChild(firstname);

            // Ajout de l'élément Nom de famille
            Element lastname = doc.createElement("Nom");
            lastname.appendChild(doc.createTextNode("Maes"));
            staff.appendChild(lastname);

            // Ajouter du contenu dans le document XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("monFichier.xml"));

            transformer.transform(source, result);

            System.out.println("Le fichier à été généré avec succès !");

            // Codes d'erreurs
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        }
    }
}
