// import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Signup1 extends JFrame implements ActionListener {

    JTextField incomeField,qualificationField,occupationField,panField,aadharField,scitizenField,abilityField,vidcardField;
    JButton next;
    String formno;
    JComboBox religionbox,Category; 
    public Signup1(String formno){
        this.formno = formno;
       
        setTitle("Account Opening Form Page 2");
        setLayout(null);

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Arial", Font.BOLD, 23));
        additionalDetails.setBounds(290, 60, 400, 30);
        add(additionalDetails);

        JLabel religion = new JLabel("Religion : ");
        religion.setFont(new Font("Arial", Font.BOLD, 15));
        religion.setBounds(100, 100, 200, 30);
        add(religion);

        String valReligion[]={"Hindu","Muslim","Sikh","Cristian","Buddha"};
        religionbox = new JComboBox(valReligion);
        religionbox.setBounds(250,100,400,30);
        religionbox.setBackground(Color.white);
        add(religionbox);

        JLabel category = new JLabel("Category : ");
        category.setFont(new Font("Arial", Font.BOLD, 15));
        category.setBounds(100, 150, 200, 30);
        add(category);

        String valCategory[]={"ST","OBC","SC","NTVJ","OPEN","GENERAL","OTHER"};
        Category = new JComboBox(valCategory);
        Category.setBounds(250,150,400,30);
        Category.setBackground(Color.white);
        add(Category);

        JLabel income = new JLabel("Income : ");
        income.setFont(new Font("Arial", Font.BOLD, 15));
        income.setBounds(100, 200, 200, 30);
        add(income);

        incomeField = new JTextField();
        incomeField.setBounds(250, 200, 400, 30);
        add(incomeField);

        JLabel vidcard = new JLabel("Voter's Id Card : ");
        vidcard.setFont(new Font("Arial", Font.BOLD, 15));
        vidcard.setBounds(100, 250, 200, 30);
        add(vidcard);

        vidcardField = new JTextField();
        vidcardField.setBounds(250, 250, 400, 30);
        add(vidcardField);

        JLabel qualification = new JLabel("Qualification : ");
        qualification.setFont(new Font("Arial", Font.BOLD, 15));
        qualification.setBounds(100, 300, 200, 30);
        add(qualification);

        qualificationField = new JTextField();
        qualificationField.setBounds(250, 300, 400, 30);
        add(qualificationField);

        JLabel occupation = new JLabel("Occupation : ");
        occupation.setFont(new Font("Arial", Font.BOLD, 15));
        occupation.setBounds(100, 350, 200, 30);
        add(occupation);

        occupationField = new JTextField();
        occupationField.setBounds(250, 300, 400, 30);
        add(occupationField);

        occupationField = new JTextField();
        occupationField.setBounds(250, 350, 400, 30);
        add(occupationField);

        JLabel panno = new JLabel("Pan Number : ");
        panno.setFont(new Font("Arial", Font.BOLD, 15));
        panno.setBounds(100, 400, 200, 30);
        add(panno);

        panField = new JTextField();
        panField.setBounds(250, 400, 400, 30);
        add(panField);

        JLabel aadhar = new JLabel("Aadhar Number : ");
        aadhar.setFont(new Font("Arial", Font.BOLD, 15));
        aadhar.setBounds(100, 450, 200, 30);
        add(aadhar);

        aadharField = new JTextField();
        aadharField.setBounds(250, 450, 400, 30);
        add(aadharField);

        JLabel scitizen = new JLabel("Senior Citizen : ");
        scitizen.setFont(new Font("Arial", Font.BOLD, 15));
        scitizen.setBounds(100, 500, 200, 30);
        add(scitizen);

        scitizenField = new JTextField();
        scitizenField.setBounds(250, 500, 400, 30);
        add(scitizenField);

        JLabel ability = new JLabel("Dissability : ");
        ability.setFont(new Font("Arial", Font.BOLD, 15));
        ability.setBounds(100, 550, 200, 30);
        add(ability);

        abilityField = new JTextField();
        abilityField.setBounds(250, 550, 400, 30);
        add(abilityField);

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
    public void actionPerformed(ActionEvent ex){
        String religion = (String) religionbox.getSelectedItem();
        String category = (String) Category.getSelectedItem();
        String income = incomeField.getText();
        String vidcard = vidcardField.getText();
        String qualification = qualificationField.getText();
        String occupation = occupationField.getText();
        String panno = panField.getText();
        String aadhar = aadharField.getText();
        String scitizen = scitizenField.getText();
        String ability = abilityField.getText();

        if (religion.isEmpty() || category.isEmpty() || income.isEmpty() || vidcard.isEmpty() || qualification.isEmpty() || occupation.isEmpty() || panno.isEmpty() || aadhar.isEmpty() || scitizen.isEmpty() || ability.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields");
            return;
        }

        try {
            DatabaseConnection obj = new DatabaseConnection();
            String query = "INSERT INTO signup1 (formno, religion, category, income, vidcard, qualification, occupation, panno, aadhar, scitizen, ability) VALUES ('"
                    + formno + "', '" + religion + "', '" + category + "', '" + income + "', '" + vidcard + "', '" + qualification + "', '"
                    + occupation + "', '" + panno + "', '" + aadhar + "', '" + scitizen + "', '"+ability+"')";
            obj.smt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Form Submitted Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
        if(ex.getSource() == next){
            setVisible(false);
            new Signup2(formno).setVisible(true);

        }
    }
    public static void main(String [] args){
        
        new Signup1("");
    }
}
