package Cashcash;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Setup{

    JTextField text1,text2;
    JButton btn;

    public Setup() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver OK !");
        String url="jdbc:mysql://localhost:3306/cashcash";
        String user="root";
        String password="";
        Connection cnx= DriverManager.getConnection(url, user, password);
        System.out.println("Connexion à la base de donnée établie.");

        JFrame f = new JFrame("CashCashApp");
        text1 = new JTextField();
        text1.setBounds(20,50,280,30);
        //text2 = new JTextField();
        //text2.setBounds(20,90,280,30);
        //text2.setEditable(false);
        btn = new JButton("Rechercher");
        btn.setBounds(100,140,150,40);
        btn.addActionListener(new BoutonEmploye(text1, cnx));
        f.add(text1);
        //f.add(text2);
        f.add(btn);
        f.setSize(340,250);
        f.setLayout(null);
        f.setVisible(true);
    }
}
