import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
public class BalanceInquire extends JFrame implements ActionListener {
    String pinNo;
    public BalanceInquire(String pinNo){
        this.pinNo= pinNo;
        setLayout(null);
        setTitle("Balance Inquire Window");

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM1.jpg"));
        Image i1 = img.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        JLabel image = new JLabel(i2);
        image.setBounds(0,0,800,800);
        add(image);

        JButton backbtn = new JButton("Back");
        backbtn.setBounds(330,400,120,30);
        backbtn.setFont(new Font("Arial",Font.BOLD,18));
        backbtn.setBackground(Color.orange);
        backbtn.setForeground(Color.WHITE);
        backbtn.addActionListener(this);
        image.add(backbtn);

         DatabaseConnection db = new DatabaseConnection();
         int balance = 0;
            try{
                ResultSet rs = db.smt.executeQuery("SELECT * FROM bank where pin = '"+pinNo+"'");
                while(rs.next()){
                    if(rs.getString("type").equals("deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            JLabel bal = new JLabel("YOUR ACCOUNT BALANCE IS "+balance+" Rs");
            bal.setForeground(Color.WHITE);
            bal.setFont(new Font("Arial",Font.BOLD,16));
            bal.setBounds(150,330,400,30);
            image.add(bal);

        setLocation(350,10);
        setSize(800,800);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent ex){
        setVisible(false);
        new Transaction(pinNo).setVisible(true);
    }
    public static void main(String [] args){
        new BalanceInquire("");
    }
}
