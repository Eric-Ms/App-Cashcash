package Cashcash;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Setup{

    JTextField saisieNum;
    JButton btnSearch, btnXML, btnSend;

    public Setup() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver opérationnel !");
        String url="jdbc:mysql://localhost:3306/cashcash";
        String user="admin";
        String password="password";
        Connection cnx= DriverManager.getConnection(url, user, password);
        System.out.println("Connexion à la base de donnée établie.");

        JFrame f = new JFrame("CashCashApp");
        saisieNum = new JTextField("Saisissez un numéro de client");
        saisieNum.setBounds(20,50,310,30);
        btnSearch = new JButton("Rechercher");
        btnSearch.setBounds(100,140,160,40);
        btnSearch.addActionListener(new BoutonClient(saisieNum, cnx));
        btnXML = new JButton("Générer XML");
        btnXML.setBounds(100,190,160,40);
        btnXML.addActionListener(new BoutonXML(saisieNum, cnx));
        btnSend = new JButton("Envoyer un courier");
        btnSend.setBounds(100,240,160,40);
        btnSend.addActionListener(new BoutonSend(saisieNum, cnx));
        f.add(saisieNum);
        //f.add(text2);
        f.add(btnSearch);
        f.add(btnXML);
        f.add(btnSend);
        f.setSize(370,490);
        f.setLayout(null);
        f.setVisible(true);
    }
}
