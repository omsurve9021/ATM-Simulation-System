import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class MiniStatement extends JFrame {
    public MiniStatement(String pinNo){
        setLayout(null);
        setTitle("Mini Statement Window");

        JLabel mini = new JLabel();
        mini.setBounds(20,100,400,200);
        add(mini);

        JLabel bank = new JLabel("State Bank Of India");
        bank.setBounds(150,20,200,30);
        add(bank);

        JLabel balance = new JLabel();
        balance.setBounds(20,100,400,20);
        add(balance);

        JLabel cardno = new JLabel();
        cardno.setBounds(20,80,300,20);
        add(cardno);

        try{
            DatabaseConnection db = new DatabaseConnection();
            ResultSet rs = db.smt.executeQuery("SELECT * FROM Login WHERE pin ='"+pinNo+"'");
            while(rs.next()){
                cardno.setText("Card No: "+rs.getString("cardno").substring(0,4)+"XXXXXXXX"+rs.getString("cardno").substring(12));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            int bal = 0;
            DatabaseConnection db = new DatabaseConnection();
            ResultSet rs = db.smt.executeQuery("SELECT * FROM bank WHERE pin='"+pinNo+"'");
            while(rs.next()){
                mini.setText(mini.getText()+"<html>"+rs.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount")+"<br><br><html>");
                if(rs.getString("type").equals("deposit")){
                    bal += Integer.parseInt(rs.getString("amount"));
                }else{
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your current account balance is Rs "+bal);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }
    public static void main(String [] args){
        new MiniStatement("");
    }   
}
