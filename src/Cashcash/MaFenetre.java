package Cashcash;
import javax.swing.*;

public class MaFenetre extends JFrame{
    private JPanel pan = new JPanel();
    JButton boutonXML = new JButton("Générer un fichier XML");


    public MaFenetre(){
        setSize(400, 200);
        setTitle("CashCashApp");
        pan.add(boutonXML);
        boutonXML.addActionListener(new EcouteurBouton());
        this.setContentPane(pan);
        this.setVisible(true);

    }
}
