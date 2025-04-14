import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Transaction extends JFrame implements ActionListener{
    JButton text,deposit,withdrawal,ministatement,viewBal,fastCash,pinchange,balinquire,exit;
    String pinNo;
    public Transaction(String pinNo){
        this.pinNo = pinNo;
        setTitle("Banking Transactions");
        setLayout(null);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM1.jpg"));
        Image i2 = img1.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(i2);
        JLabel image = new JLabel(img2);
        image.setBounds(0,0,800,800);
        add(image);

        JLabel text = new JLabel("Select Transaction Type");
        text.setBounds(180,280,600,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial",Font.BOLD,20));
        image.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(140,400,120,30);
        deposit.setFont(new Font("Arial",Font.BOLD,15));
        deposit.setBackground(Color.LIGHT_GRAY);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawal = new JButton("Withdrawal");
        withdrawal.setBounds(140,435,120,30);
        withdrawal.setFont(new Font("Arial",Font.BOLD,15));
        withdrawal.setBackground(Color.LIGHT_GRAY);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        viewBal = new JButton("Balance Inquire");
        viewBal.setBounds(140,470,150,30);
        viewBal.setFont(new Font("Arial",Font.BOLD,15));
        viewBal.setBackground(Color.LIGHT_GRAY);
        viewBal.addActionListener(this);
        image.add(viewBal);

        fastCash = new JButton("Fast Cash");
        fastCash.setBounds(335,400,120,30);
        fastCash.setFont(new Font("Arial",Font.BOLD,15));
        fastCash.setBackground(Color.LIGHT_GRAY);
        fastCash.addActionListener(this);
        image.add(fastCash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(305,470,150,30);
        ministatement.setFont(new Font("Arial",Font.BOLD,15));
        ministatement.setBackground(Color.LIGHT_GRAY);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Pin Change");
        pinchange.setBounds(335,435,120,30);
        pinchange.setFont(new Font("Arial",Font.BOLD,15));
        pinchange.setBackground(Color.LIGHT_GRAY);
        pinchange.addActionListener(this);
        image.add(pinchange);

        // balinquire = new JButton("Balance Inquire");
        // balinquire.setBounds(140,503,150,25);
        // balinquire.setFont(new Font("Arial",Font.BOLD,15));
        // balinquire.setBackground(Color.LIGHT_GRAY);
        // balinquire.addActionListener(this);
        // image.add(balinquire);

        exit = new JButton("Exit");
        exit.setBounds(305,503,150,25);
        exit.setFont(new Font("Arial",Font.BOLD,15));
        exit.setBackground(Color.LIGHT_GRAY);
        exit.addActionListener(this);
        image.add(exit);
         
        setSize(800,800);
        setLocation(350,0);
        setUndecorated(true);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ex){

        if(ex.getSource() == exit){
            System.exit(0);

        }else if(ex.getSource() == deposit){
            setVisible(false);
            new Deposit(pinNo).setVisible(true);

        }else if(ex.getSource() == withdrawal){
            setVisible(false);
            new Withdrawal(pinNo).setVisible(true);

        }else if(ex.getSource() == fastCash){
            setVisible(false);
            new FastCash(pinNo).setVisible(true);
        }else if(ex.getSource() == pinchange){
            setVisible(false);
            new PinChange(pinNo).setVisible(true);
        }else if(ex.getSource() == viewBal){
            setVisible(false);
            new BalanceInquire(pinNo).setVisible(true);
        }else if(ex.getSource()== ministatement){
            setVisible(false);
            new MiniStatement(pinNo).setVisible(true);
        }
    }
    public static void main(String [] args){
        new Transaction("");
    }
}

