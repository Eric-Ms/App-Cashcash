package Cashcash;

import java.sql.*;
import com.mysql.jdbc.Driver;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        ConnexionBDD cnx = new ConnexionBDD();

        MaFenetre fen = new MaFenetre();
        fen.setVisible(true);
    }
}
