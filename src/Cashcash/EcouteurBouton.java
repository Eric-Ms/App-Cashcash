package Cashcash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurBouton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Générer un fichier XML")){
            System.out.println("Le fichier à été généré");
        }
    }
}
