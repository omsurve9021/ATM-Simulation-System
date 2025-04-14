import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PinChange extends JFrame implements ActionListener{
    String pinNo;
    JPasswordField changepin,rechangepin;
    JButton changebtn,backbtn;
    public PinChange(String pinNo){
        this.pinNo = pinNo;
        setLayout(null);
        setTitle("Pin Change Window");

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM1.jpg"));
        Image i1 = img. getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon img1 = new ImageIcon(i1);
        JLabel image = new JLabel(img1);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.white);
        text.setFont(new Font("Arial",Font.BOLD,20));
        text.setBounds(235,330,400,30);
        image.add(text);

        JLabel change = new JLabel("Enter new pin: ");
        change.setForeground(Color.white);
        change.setFont(new Font("Arial",Font.BOLD,17));
        change.setBounds(170,380,400,30);
        image.add(change);

        JLabel rechange = new JLabel("Re-Enter new pin: ");
        rechange.setForeground(Color.white);
        rechange.setFont(new Font("Arial",Font.BOLD,17));
        rechange.setBounds(170,430,400,30);
        image.add(rechange);

        changepin = new JPasswordField();
        changepin.setFont(new Font("Arial",Font.BOLD,17));
        changepin.setBounds(310,380,150,30);
        image.add(changepin);

        rechangepin = new JPasswordField();
        rechangepin.setFont(new Font("Arial",Font.BOLD,17));
        rechangepin.setBounds(310,430,150,30);
        image.add(rechangepin);

        changebtn = new JButton("Change");
        changebtn.setFont(new Font("Arial",Font.BOLD,17));
        changebtn.setForeground(Color.black);
        changebtn.setBackground(Color.green);
        changebtn.setBounds(230,480,100,30);
        changebtn.addActionListener(this);
        image.add(changebtn);

        backbtn = new JButton("Back");
        backbtn.setFont(new Font("Arial",Font.BOLD,17));
        backbtn.setForeground(Color.black);
        backbtn.setBackground(Color.orange);
        backbtn.setBounds(340,480,100,30);
        backbtn.addActionListener(this);
        image.add(backbtn);


        setSize(900,900);
        setLocation(350,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ex){
        if(ex.getSource() == changebtn){
        try{
            String newpin = changepin.getText();
            String repin = rechangepin.getText();

            if(!newpin.equals(repin)){
                JOptionPane.showMessageDialog(this,"Pin not matched");
                return;
            }
            if(newpin.equals("")){
                JOptionPane.showMessageDialog(this,"Pin cannot be empty");
            }
            if(repin.equals("")){
                JOptionPane.showMessageDialog(this,"Pin cannot be empty");
            }

            DatabaseConnection db = new DatabaseConnection();
            String query = "UPDATE Login set pin ='"+newpin+"' where pin='"+pinNo+"'";
            String query1 = "UPDATE signup2 set pin ='"+newpin+"' where pin='"+pinNo+"'";
            String query2 = "UPDATE bank set pin ='"+newpin+"' where pin='"+pinNo+"'";
            db.smt.executeUpdate(query);
            db.smt.executeUpdate(query1);
            db.smt.executeUpdate(query2);

            JOptionPane.showMessageDialog(this,"Pin Changed Successfully");
            setVisible(false);
            new Transaction(pinNo).setVisible(true);
        }catch(Exception a){
            System.out.println(a.getMessage());
        }
    }else{
        setVisible(false);
        new Transaction(pinNo).setVisible(true);
    }
        
    }
    public static void main(String [] args){
        new PinChange("");
    }
}
