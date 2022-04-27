package Cashcash;
import javax.swing.*;

public class MaFenetre extends JFrame{
    private JPanel pan = new JPanel();
    JButton boutonXML = new JButton("Générer un fichier XML");
    JButton boutonTwo = new JButton("Deuxième bouton");


    public MaFenetre(){
        setSize(300, 500);
        setTitle("CashCashApp");
        pan.add(boutonXML);
        boutonXML.addActionListener(new EcouteurBouton());
        pan.add(boutonTwo);
        boutonTwo.addActionListener(new EcouteurBouton());
        this.setContentPane(pan);
        this.setVisible(true);
    }
}
