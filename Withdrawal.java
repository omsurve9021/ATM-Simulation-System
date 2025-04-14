import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;
public class Withdrawal extends JFrame implements ActionListener{
    JButton withdraw,cancel;
    String pinNo;
    JTextField amount;
    public Withdrawal(String pinNo){
        this.pinNo = pinNo;
        setTitle("Withdrawal Page");
        setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM1.jpg"));
        Image i2 = img.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon img1 = new ImageIcon(i2);
        JLabel image = new JLabel(img1);
        image.setBounds(10,0,800,800);
        add(image);

        JLabel text = new JLabel("Enter Withdrawal Amount");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial",Font.BOLD,18));
        text.setBounds(180,300,400,35);
        image.add(text);

        amount = new JTextField();
        amount.setBounds(200,350,200,30);
        amount.setFont(new Font("Arial",Font.BOLD,15));
        amount.addActionListener(this);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(170,400,120,30);
        withdraw.setFont(new Font("Arial",Font.BOLD,15));
        withdraw.addActionListener(this);
        image.add(withdraw);

        cancel = new JButton("Cancel");
        cancel.setBounds(320,400,120,30);
        cancel.setFont(new Font("Arial",Font.BOLD,15));
        cancel.addActionListener(this);
        image.add(cancel);


        setSize(800,800);
        setLocation(300,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ex){
        if(ex.getSource() == withdraw){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(this,"Enter Amount you want to deposit");
            }else{

                try{
                DatabaseConnection dbc = new DatabaseConnection();
                ResultSet rs = dbc.smt.executeQuery("SELECT * FROM bank where pin = '"+pinNo+"'");
                int balance = 0;
                while(rs.next()){
                    if(rs.getString("type").equals("deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if( balance < Integer.parseInt(number)){
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                    return;
                }else{
                    DatabaseConnection db = new DatabaseConnection();
                    String query = "INSERT INTO bank (pin,date,type,amount) VALUES('"+pinNo+"','"+date+"','Withdraw','"+number+"')";
                    db.smt.executeUpdate(query);
                    JOptionPane.showMessageDialog(this,"Your Amount "+ number +" is successfully Withdrawal");
                    setVisible(false);
                    new Transaction(pinNo).setVisible(true);
                }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }else if(ex.getSource() == cancel){
            setVisible(false);
            new Transaction(pinNo).setVisible(true);
        }
    }
    public static void main(String [] args){
        new Withdrawal("");
    }
}
