package Cashcash;

import java.sql.*;
import com.mysql.jdbc.Driver;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver OK !");
            String url="jdbc:mysql://localhost:3306/cashcash";
            String user="root";
            String password="";
            Connection cnx= DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de donnée établie.");

            Statement stmt = cnx.createStatement();
            String sql = "SELECT numClient, adresseMail, SIREN, telClient FROM cashcash.client";
            ResultSet res = stmt.executeQuery(sql);

            //étape 5: extraire les données
            while(res.next()){
                //Récupérer par nom de colonne
                int numClient = res.getInt("numClient");
                String adresseMail = res.getString("adresseMail");
                String SIREN = res.getString("SIREN");
                String telClient = res.getString("telClient");
                //Afficher les valeurs
                System.out.print("Num: " + numClient);
                System.out.print("\nMail: " + adresseMail);
                System.out.print("\nNuméro de téléphone: " + telClient);
                System.out.print("\nSIREN: " + SIREN);
                System.out.print("\n====================\n");
            }
            cnx.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        MaFenetre fen = new MaFenetre();
        fen.setVisible(true);
    }
}
