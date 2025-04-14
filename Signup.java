import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener {

    Random num;
    JTextField nameField, fnameField, emailField, addressField, pincodeField, stateField, cityField;
    JDateChooser dateChooser;
    JButton next;
    ButtonGroup gendergrp, maritalgrp;
    JRadioButton male, female, married, unmarried;
    int random;

    public Signup() {
        setTitle("Application Form");
        setLayout(null);

        num = new Random();
        random = Math.abs(num.nextInt() % 10000);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Arial", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Arial", Font.BOLD, 23));
        personalDetails.setBounds(290, 60, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name : ");
        name.setFont(new Font("Arial", Font.BOLD, 15));
        name.setBounds(100, 100, 200, 30);
        add(name);

        nameField = new JTextField();
        nameField.setBounds(250, 100, 400, 30);
        add(nameField);

        JLabel fname = new JLabel("Father's Name : ");
        fname.setFont(new Font("Arial", Font.BOLD, 15));
        fname.setBounds(100, 150, 200, 30);
        add(fname);

        fnameField = new JTextField();
        fnameField.setBounds(250, 150, 400, 30);
        add(fnameField);

        JLabel dob = new JLabel("Date of Birth : ");
        dob.setFont(new Font("Arial", Font.BOLD, 15));
        dob.setBounds(100, 200, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(250, 200, 400, 30);
        add(dateChooser);

        JLabel gender = new JLabel("Gender : ");
        gender.setFont(new Font("Arial", Font.BOLD, 15));
        gender.setBounds(100, 250, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(250, 250, 120, 30);
        male.setBackground(Color.white);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 250, 120, 30);
        female.setBackground(Color.white);
        add(female);

        gendergrp = new ButtonGroup();
        gendergrp.add(male);
        gendergrp.add(female);

        JLabel email = new JLabel("Email Address : ");
        email.setFont(new Font("Arial", Font.BOLD, 15));
        email.setBounds(100, 300, 200, 30);
        add(email);

        emailField = new JTextField();
        emailField.setBounds(250, 300, 400, 30);
        add(emailField);

        JLabel maritalStatus = new JLabel("Marital Status : ");
        maritalStatus.setFont(new Font("Arial", Font.BOLD, 15));
        maritalStatus.setBounds(100, 350, 200, 30);
        add(maritalStatus);

        married = new JRadioButton("Married");
        married.setBounds(250, 350, 120, 30);
        married.setBackground(Color.white);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 350, 120, 30);
        unmarried.setBackground(Color.white);
        add(unmarried);

        maritalgrp = new ButtonGroup();
        maritalgrp.add(married);
        maritalgrp.add(unmarried);

        JLabel address = new JLabel("Address : ");
        address.setFont(new Font("Arial", Font.BOLD, 15));
        address.setBounds(100, 400, 200, 30);
        add(address);

        addressField = new JTextField();
        addressField.setBounds(250, 400, 400, 30);
        add(addressField);

        JLabel city = new JLabel("City : ");
        city.setFont(new Font("Arial", Font.BOLD, 15));
        city.setBounds(100, 450, 200, 30);
        add(city);

        cityField = new JTextField();
        cityField.setBounds(250, 450, 400, 30);
        add(cityField);

        JLabel state = new JLabel("State : ");
        state.setFont(new Font("Arial", Font.BOLD, 15));
        state.setBounds(100, 500, 200, 30);
        add(state);

        stateField = new JTextField();
        stateField.setBounds(250, 500, 400, 30);
        add(stateField);

        JLabel pincode = new JLabel("Pin Code : ");
        pincode.setFont(new Font("Arial", Font.BOLD, 15));
        pincode.setBounds(100, 550, 200, 30);
        add(pincode);

        pincodeField = new JTextField();
        pincodeField.setBounds(250, 550, 400, 30);
        add(pincodeField);

        next = new JButton("Next");
        next.setFont(new Font("Arial", Font.BOLD, 18));
        next.setBounds(700, 700, 100, 30);
        next.setBackground(Color.orange);
        next.addActionListener(this); // Add action listener for the button
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setVisible(true);
        setLocation(350, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String formno = "" + random;
        String fname = fnameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String pincode = pincodeField.getText();
        String dob = ((JTextField) dateChooser .getDateEditor().getUiComponent()).getText();

        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        String marital = null;
        if (married.isSelected()) {
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        }

        if (name.isEmpty() || email.isEmpty() || fname.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields");
            return;
        }

        try {
            DatabaseConnection obj = new DatabaseConnection();
            String query = "INSERT INTO signup (formno, name, fname, gender, marital, email, dob, address, city, state, pincode) VALUES ('"
                    + formno + "', '" + name + "', '" + fname + "', '" + gender + "', '" + marital + "', '" + email + "','" + dob + "', '"
                    + address + "', '" + city + "', '" + state + "', '" + pincode + "')";
            obj.smt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Form Submitted Successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if(e.getSource() == next){
            setVisible(false);
            new Signup1(formno).setVisible(true);

        }
    }
    public static void main(String[] args) {
        new Signup();
    }
}
