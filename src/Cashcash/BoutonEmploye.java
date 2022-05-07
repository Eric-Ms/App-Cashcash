package Cashcash;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BoutonEmploye implements ActionListener {

    private JTextField textField;
    private Connection cnx;

    public BoutonEmploye(JTextField textField, Connection cnx) {
        this.textField = textField;
        this.cnx = cnx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String num_matricule = textField.getText();
            Statement stmt = cnx.createStatement();
            String sql_employe = "SELECT * FROM cashcash.employes WHERE numMatricule = " + num_matricule;
            ResultSet res = stmt.executeQuery(sql_employe);
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
