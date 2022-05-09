package Cashcash;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BoutonClient implements ActionListener {

    private JTextField textField;
    private Connection cnx;

    public BoutonClient(JTextField textField, Connection cnx) {
        this.textField = textField;
        this.cnx = cnx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String num_client = textField.getText();
            Statement stmt = cnx.createStatement();
            //Requête SQL qui sélectionne le client en fonction du numéro de client saisi.
            String sql_client = "SELECT * FROM cashcash.client WHERE numClient = " + num_client;
            ResultSet res = stmt.executeQuery(sql_client);
            ResultSetMetaData resmtdt = res.getMetaData();
            int columns_count = resmtdt.getColumnCount();
            res.next();

            for(int i = 1; i <= columns_count; i++) {
                String temp = res.getString(i);
                String col_name = resmtdt.getColumnName(i);
                System.out.println(col_name + " : " + temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
