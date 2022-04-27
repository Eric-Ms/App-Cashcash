package Cashcash;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        Connection cnx = connecterDB();

        MaFenetre fen = new MaFenetre();
        fen.setVisible(true);
    }

    public static Connection connecterDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver OK !");
            String url="jdbc:mysql://localhost:3306/cashcash";
            String user="";
            String password="";
            Connection cnx=DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de donnée établie.");
            return cnx;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
