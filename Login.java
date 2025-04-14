import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton clear,signin,signup;
    JTextField cardTextField;
    JPasswordField pinTextField;
    public Login(){

        setTitle("Automated Teller Machine");
        setLayout(null);

        //import image from a folder
        ImageIcon im1 = new ImageIcon(ClassLoader.getSystemResource("Icons/Bank.jpg"));

        //set height and width of an image

        Image img1 = im1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
        ImageIcon im2 = new ImageIcon(img1);
        JLabel label = new JLabel(im2);
        label.setBounds(100,20,150,150);
        add(label);

        JLabel greetings = new JLabel("Welcome to ATM");
        greetings.setFont(new Font ("Arial",Font.BOLD,40));
        add(greetings);
        greetings.setBounds(260,70,400,40);

        JLabel CardNo = new JLabel("Enter Card No. :");
        CardNo.setFont(new Font ("Arial",Font.BOLD,20));
        add(CardNo);
        CardNo.setBounds(150,200,200,40);

        cardTextField = new JTextField();
        cardTextField.setBounds(320,210,250,30);
        cardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);

        JLabel PinNo = new JLabel("Enter Pin No. :");
        PinNo.setFont(new Font ("Arial",Font.BOLD,20));
        add(PinNo);
        PinNo.setBounds(150,260,200,40);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(320,270,250,30);
        pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);

        signin = new JButton("Sign In");
        signin.setBounds(330,330,100,30);
        signin.setBackground(Color.green);
        signin.addActionListener(this);
        add(signin);

        clear = new JButton("Clear");
        clear.setBounds(460,330,100,30);
        clear.setBackground(Color.red);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("Sign Up");
        signup.setBounds(345,380,200,30);
        signup.setBackground(Color.orange);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);
        setSize(800,500);
        setVisible(true);
        setLocation(350,200);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == signin){
            DatabaseConnection obj = new DatabaseConnection();
            String cardNo = cardTextField.getText();
            String pinNo = pinTextField.getText();
            String query = "SELECT * FROM Login WHERE cardno = '"+cardNo+"'and pin='"+pinNo+"'";
            try{
                ResultSet result = obj.smt.executeQuery(query);
                if(result.next()){
                    JOptionPane.showMessageDialog(this,"Login Successfully");
                    setVisible(false);
                    new Transaction(pinNo).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "Invalid Login Credentials");
                }
            }catch(Exception e){
                System.out.println(e);
            }

        }else if(ae.getSource() == clear){

            cardTextField.setText("");
            pinTextField.setText("");

        }else{
            if(ae.getSource() == signup){
            setVisible(false);
            new Signup().setVisible(true);

            }
        }
    }
    public static void main(String [] args){
        new Login();
    }
}