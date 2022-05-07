package Cashcash;
import javax.swing.*;

public class MaFenetre extends JFrame{
    private JPanel pan = new JPanel();
    JButton boutonXML = new JButton("Générer un fichier XML");
    JButton boutonTwo = new JButton("Deuxième bouton");
    JTextField text1 = new JTextField();

    public MaFenetre(){
        setSize(300, 500);
        setTitle("CashCashApp");
        pan.add(boutonXML);
        boutonXML.addActionListener(new EcouteurBouton());
        pan.add(boutonTwo);
        boutonTwo.addActionListener(new EcouteurBouton());
        text1.setBounds(20,40,200,28);
        pan.add(text1);

        this.setContentPane(pan);
        this.setVisible(true);
    }
}
