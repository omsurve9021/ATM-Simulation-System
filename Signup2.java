import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Signup2 extends JFrame implements ActionListener {
    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JButton submit,cancel;
    String formno;
    public Signup2(String formno){
        this.formno = formno;
        setTitle("Account Details");
        setLayout(null);
        JLabel j1 = new JLabel("Page 3: Account Details");
        j1.setBounds(200,30,400,30);
        j1.setFont(new Font("Arial",Font.BOLD,25));
        add(j1);

        JLabel atype = new JLabel("Account Type: ");
        atype.setBounds(80,80,200,30);
        atype.setFont(new Font("Arial",Font.BOLD,15));
        add(atype);

        r1 = new JRadioButton("Saving");
        r1.setBounds(250,80,100,30);
        r1.setFont(new Font("Arial",Font.BOLD,14));
        r1.setBackground(Color.WHITE);
        add(r1);

        r2 = new JRadioButton("Current");
        r2.setBounds(350,80,100,30);
        r2.setFont(new Font("Arial",Font.BOLD,14));
        r2.setBackground(Color.WHITE);
        add(r2);

        r3 = new JRadioButton("Fixed Deposit Account");
        r3.setBounds(250,110,100,30);
        r3.setFont(new Font("Arial",Font.BOLD,14));
        r3.setBackground(Color.WHITE);
        add(r3);

        r4 = new JRadioButton("Reccuring Deposit Account");
        r4.setBounds(350,110,400,30);
        r4.setFont(new Font("Arial",Font.BOLD,14));
        r4.setBackground(Color.WHITE);
        add(r4);

        ButtonGroup accountgrp = new ButtonGroup();
        accountgrp .add(r1);
        accountgrp.add(r2);
        accountgrp.add(r3);
        accountgrp.add(r4);

        JLabel cardno = new JLabel("Card Number: ");
        cardno.setBounds(80,140,400,30);
        cardno.setFont(new Font("Arial",Font.BOLD,15));
        add(cardno);

        JLabel demo = new JLabel("XXXX-XXXX-XXXX-4857");
        demo.setBounds(250,140,400,30);
        demo.setFont(new Font("Arial",Font.BOLD,15));
        add(demo);

        JLabel pin = new JLabel("Pin Number: ");
        pin.setBounds(80,170,400,30);
        pin.setFont(new Font("Arial",Font.BOLD,15));
        add(pin);

        JLabel pinno = new JLabel("XX89");
        pinno.setBounds(250,170,400,30);
        pinno.setFont(new Font("Arial",Font.BOLD,15));
        add(pinno);
        
        JLabel service = new JLabel("Services Required: ");
        service.setBounds(80,200,400,30);
        service.setFont(new Font("Arial",Font.BOLD,15));
        add(service);

        c1 = new JCheckBox("ATM Card");
        c1.setBounds(250,200,200,30);
        c1.setFont(new Font("Arial",Font.BOLD,15));
        c1.setBackground(Color.WHITE);
        add(c1);

        c2 = new JCheckBox("DEBIT Card");
        c2.setBounds(450,200,300,30);
        c2.setFont(new Font("Arial",Font.BOLD,15));
        c2.setBackground(Color.WHITE);
        add(c2);

        c3 = new JCheckBox("Internet Banking");
        c3.setBounds(250,230,300,30);
        c3.setFont(new Font("Arial",Font.BOLD,15));
        c3.setBackground(Color.WHITE);
        add(c3);

        c4 = new JCheckBox("Mobile Banking");
        c4.setBounds(450,260,200,30);
        c4.setFont(new Font("Arial",Font.BOLD,15));
        c4.setBackground(Color.WHITE);
        add(c4);

        c5 = new JCheckBox("Email and SMS Alert");
        c5.setBounds(250,260,500,30);
        c5.setFont(new Font("Arial",Font.BOLD,15));
        c5.setBackground(Color.WHITE);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBounds(450,230,300,30);
        c6.setFont(new Font("Arial",Font.BOLD,15));
        c6.setBackground(Color.WHITE);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the above information is true best of my knowledge ");
        c7.setBounds(80,300,580,20);
        c7.setFont(new Font("Arial",Font.BOLD,14));
        c7.setBackground(Color.WHITE);
        add(c7);

        submit = new JButton("Submit");
        submit.setBounds(200,350,120,25);
        submit.setFont(new Font("Arial",Font.BOLD,18));
        submit.setBackground(Color.orange);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(350,350,120,25);
        cancel.setFont(new Font("Arial",Font.BOLD,18));
        cancel.setBackground(Color.red);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(600,600);
        setVisible(true);
        setLocation(350,10);
    }

    @Override
    public void actionPerformed(ActionEvent x){
        if(x.getSource() == submit){
            String atype = null;
            if(r1.isSelected()){
                atype = "Savings";
            }else if(r2.isSelected()){
                atype = "Current";
            }else if(r3.isSelected()){
                atype = "Fixed Deposit Account";
            }else if(r4.isSelected()){
                atype = "Reccuring Deposit Account";
            }

            Random random = new Random();
            String cardno ="" + Math.abs((random.nextLong() % 90000000L)+7058658000000000L);
            String pin = ""+ Math.abs((random.nextInt()%10000));

            String service = "";
            if(c1.isSelected()){
                service = service + " ATM Card";
            }else if(c2.isSelected()){
                service = service + " Debit Card";
            }else if(c3.isSelected()){
                service = service +" Internet Banking";
            }else if(c4.isSelected()){
                service = service +" Mobile Banking";
            }else if(c5.isSelected()){
                service = service + " Email and SMS Alert";
            }
            else if(c6.isSelected()){
                service = service + " E-Statement";
            }
            
            if(atype.isEmpty() || cardno.isEmpty() || service.isEmpty() || !c7.isSelected()){
                JOptionPane.showMessageDialog(this,"Fill all the required fields");
            }
            

            try{
                DatabaseConnection obj = new DatabaseConnection();
                String query ="INSERT INTO signup2 (formno,atype,cardno,pin,service) VALUES('"+formno+"','"+atype+"','"+cardno+"','"+pin+"','"+service+"')";
                String query1 ="INSERT INTO Login (formno,cardno,pin) VALUES('"+formno+"','"+cardno+"','"+pin+"')";
                obj.smt.executeUpdate(query);
                obj.smt.executeUpdate(query1);
                JOptionPane.showMessageDialog(this,"Date inserted Successfully");

                JOptionPane.showMessageDialog(this,"Your Card No: "+cardno+" \nYour Pin No: "+pin);


                setVisible(false);
                new Login().setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(x.getSource() == cancel){
            setVisible(false);
            new Login().setVisible(true);

        }
    }
    public static void main(String [] args){
        new Signup2("");
    }
}
