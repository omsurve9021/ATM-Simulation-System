import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
    JButton text,w100,w500,w1000,w2000,w5000,w10000,balinquire,back;
    String pinNo;
    public FastCash(String pinNo){
        this.pinNo = pinNo;
        setTitle("Fast Cash");
        setLayout(null);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM1.jpg"));
        Image i2 = img1.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(i2);
        JLabel image = new JLabel(img2);
        image.setBounds(0,0,800,800);
        add(image);

        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(180,280,600,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial",Font.BOLD,20));
        image.add(text);

        w100 = new JButton("Rs 100");
        w100.setBounds(140,400,120,30);
        w100.setFont(new Font("Arial",Font.BOLD,15));
        w100.setBackground(Color.LIGHT_GRAY);
        w100.addActionListener(this);
        image.add(w100);

        w500 = new JButton("Rs 500");
        w500.setBounds(140,435,120,30);
        w500.setFont(new Font("Arial",Font.BOLD,15));
        w500.setBackground(Color.LIGHT_GRAY);
        w500.addActionListener(this);
        image.add(w500);

        w1000 = new JButton("Rs 1000");
        w1000.setBounds(140,470,150,30);
        w1000.setFont(new Font("Arial",Font.BOLD,15));
        w1000.setBackground(Color.LIGHT_GRAY);
        w1000.addActionListener(this);
        image.add(w1000);

        w2000 = new JButton("Rs 2000");
        w2000.setBounds(335,400,120,30);
        w2000.setFont(new Font("Arial",Font.BOLD,15));
        w2000.setBackground(Color.LIGHT_GRAY);
        w2000.addActionListener(this);
        image.add(w2000);

        w5000 = new JButton("Rs 5000");
        w5000.setBounds(305,470,150,30);
        w5000.setFont(new Font("Arial",Font.BOLD,15));
        w5000.setBackground(Color.LIGHT_GRAY);
        w5000.addActionListener(this);
        image.add(w5000);

        w10000 = new JButton("Rs 10000");
        w10000.setBounds(335,435,120,30);
        w10000.setFont(new Font("Arial",Font.BOLD,15));
        w10000.setBackground(Color.LIGHT_GRAY);
        w10000.addActionListener(this);
        image.add(w10000);

        // balinquire = new JButton("Balance Inquire");
        // balinquire.setBounds(140,503,150,25);
        // balinquire.setFont(new Font("Arial",Font.BOLD,15));
        // balinquire.setBackground(Color.LIGHT_GRAY);
        // balinquire.addActionListener(this);
        // image.add(balinquire);

        back = new JButton("Back");
        back.setBounds(305,503,150,25);
        back.setFont(new Font("Arial",Font.BOLD,15));
        back.setBackground(Color.LIGHT_GRAY);
        back.addActionListener(this);
        image.add(back);
         
        setSize(800,800);
        setLocation(350,0);
        setUndecorated(true);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ex){

        if(ex.getSource() == back){
            setVisible(false);
            new Transaction(pinNo).setVisible(true);
        }else {
            String fcamount = ((JButton)ex.getSource()).getText().substring(3);
            DatabaseConnection db = new DatabaseConnection();
            try{
                ResultSet rs = db.smt.executeQuery("SELECT * FROM bank where pin = '"+pinNo+"'");
                int balance = 0;
                while(rs.next()){
                    if(rs.getString("type").equals("deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if(ex.getSource()!=back && balance < Integer.parseInt(fcamount)){
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "INSERT INTO bank values('"+pinNo+"','"+date+"','Withdraw','"+fcamount+"')";
                db.smt.executeUpdate(query);
                JOptionPane.showMessageDialog(this,"Rs "+fcamount+" debited Successfully");

                setVisible(false);
                new Transaction(pinNo).setVisible(true);
            }catch(Exception a){
                System.out.println(a.getMessage());
            }
        }
    }
    public static void main(String [] args){
        new FastCash("");
    }

}
