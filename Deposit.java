import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
public class Deposit extends JFrame implements ActionListener{
    JButton deposit,cancel;
    String pinNo;
    JTextField amount;
    public Deposit(String pinNo){
        this.pinNo = pinNo;
        setTitle("Deposit Page");
        setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM1.jpg"));
        Image i2 = img.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon img1 = new ImageIcon(i2);
        JLabel image = new JLabel(img1);
        image.setBounds(10,0,800,800);
        add(image);

        JLabel text = new JLabel("Enter Amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial",Font.BOLD,18));
        text.setBounds(150,300,400,35);
        image.add(text);

        amount = new JTextField();
        amount.setBounds(200,350,200,30);
        amount.setFont(new Font("Arial",Font.BOLD,15));
        amount.addActionListener(this);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(170,400,100,30);
        deposit.setFont(new Font("Arial",Font.BOLD,15));
        deposit.addActionListener(this);
        image.add(deposit);

        cancel = new JButton("Cancel");
        cancel.setBounds(330,400,100,30);
        cancel.setFont(new Font("Arial",Font.BOLD,15));
        cancel.addActionListener(this);
        image.add(cancel);


        setSize(800,800);
        setLocation(300,0);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent ex){
        if(ex.getSource() == deposit){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(this,"Enter Amount you want to deposit");
            }else{
                try {
                    DatabaseConnection db = new DatabaseConnection();
                    String query = "INSERT INTO bank (pin,date,type,amount) VALUES('"+pinNo+"','"+date+"','deposit','"+number+"')";
                    db.smt.executeUpdate(query);
                    JOptionPane.showMessageDialog(this,"Your Amount "+ number +" is successfully deposited");
                    setVisible(false);
                    new Transaction(pinNo).setVisible(true);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this,"Error in depositing amount");
                    e.printStackTrace();
                }
            }
        }else if(ex.getSource() == cancel){
            setVisible(false);
            new Transaction(pinNo).setVisible(true);
        }
    }
    public static void main(String [] args){
        new Deposit("");
    }
}
