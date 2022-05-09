package Cashcash;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Setup{

    JTextField text1,text2;
    JButton btn, btn2;

    public Setup() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver opérationnel !");
        String url="jdbc:mysql://localhost:3306/cashcash";
        String user="root";
        String password="";
        Connection cnx= DriverManager.getConnection(url, user, password);
        System.out.println("Connexion à la base de donnée établie.");

        JFrame f = new JFrame("CashCashApp");
        text1 = new JTextField();
        text1.setBounds(20,50,310,30);
        //text2 = new JTextField();
        //text2.setBounds(20,90,280,30);
        //text2.setEditable(false);
        btn = new JButton("Rechercher");
        btn.setBounds(100,140,160,40);
        btn.addActionListener(new BoutonClient(text1, cnx));
        btn2 = new JButton("Générer XML");
        btn2.setBounds(100,100,160,40);
        btn2.addActionListener(new BoutonXML(text1, cnx));
        f.add(text1);
        //f.add(text2);
        f.add(btn);
        f.add(btn2);
        f.setSize(370,490);
        f.setLayout(null);
        f.setVisible(true);
    }
}
