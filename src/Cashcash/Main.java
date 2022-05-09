package Cashcash;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        //ConnexionBDD cnx = new ConnexionBDD();
        try {
            new Setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
