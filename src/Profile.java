import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Profile implements ActionListener
{
    Font fontHeading = new Font("", Font.BOLD, 40);
    Font fontInput = new Font("", Font.TYPE1_FONT, 20);
    Font fontButton = new Font("", Font.CENTER_BASELINE, 30);
    Font fontLabel = new Font("", Font.TRUETYPE_FONT, 16);
    Font fontSmallLabel = new Font("", Font.TYPE1_FONT, 15);
    Color mainColor = new Color(162, 89, 255);
    Color darkGreyColor = new Color(115, 115, 125);
    Color lightGreyColor = new Color(206, 206, 212);

    Frame profile_Frame;
    Frame error_Frame;
    int id;

    Label createAccount_Label, fname_Label, lname_Label, email_Label, accountNo_Label, balance_Label,
            mobile_LVmobileVaVlue_Labelabel, fillAll_Label, mismatch_Label, fnameValue_Label, lnameValue_Label,
            emailValue_Label, accountnoValue_Label, balanceValue_Label, mobileValue_Label;

    Button back_Button, show_Button, emptyOk_Button, mismatchOk_Button;

    Profile(int id){
        
        this.id = id;
        profile_Frame = new Frame("PROFILE");

        createAccount_Label = new Label("MY ACCOUNT");
        createAccount_Label.setFont(fontHeading);
        createAccount_Label.setForeground(mainColor);
        createAccount_Label.setBounds(260, 60, 380, 40);
        profile_Frame.add(createAccount_Label);

        // width = length * 7.5;
        fname_Label = new Label("First Name : -");
        fname_Label.setFont(fontLabel);
        fname_Label.setBounds(138, 155, 105, 16);
        profile_Frame.add(fname_Label);

        lname_Label = new Label("Last Name : -");
        lname_Label.setFont(fontLabel);
        lname_Label.setBounds(138, 210, 100, 16);
        profile_Frame.add(lname_Label);

        email_Label = new Label("Email Id : -");
        email_Label.setFont(fontLabel);
        email_Label.setBounds(138, 265, 95, 16);
        profile_Frame.add(email_Label);

        accountNo_Label = new Label("Account Number : -");
        accountNo_Label.setFont(fontLabel);
        accountNo_Label.setBounds(138, 320, 137, 16);
        profile_Frame.add(accountNo_Label);

        mobile_LVmobileVaVlue_Labelabel = new Label("Mobile Number : -");
        mobile_LVmobileVaVlue_Labelabel.setFont(fontLabel);
        mobile_LVmobileVaVlue_Labelabel.setBounds(138, 375, 175, 16);
        profile_Frame.add(mobile_LVmobileVaVlue_Labelabel);

        balance_Label = new Label("Available Balance : -");
        balance_Label.setFont(fontLabel);
        balance_Label.setBounds(138, 430, 130, 16);
        profile_Frame.add(balance_Label);

        fnameValue_Label = new Label("********");
        fnameValue_Label.setFont(fontInput);
        fnameValue_Label.setBounds(400, 155, 222, 30);
        profile_Frame.add(fnameValue_Label);

        lnameValue_Label = new Label("********");
        lnameValue_Label.setFont(fontInput);
        lnameValue_Label.setBounds(400, 210, 222, 30);
        profile_Frame.add(lnameValue_Label);

        emailValue_Label = new Label("********");
        emailValue_Label.setFont(fontInput);
        emailValue_Label.setBounds(400, 265, 222, 30);
        profile_Frame.add(emailValue_Label);

        accountnoValue_Label = new Label("********");
        accountnoValue_Label.setFont(fontInput);
        accountnoValue_Label.setBounds(400, 320, 222, 30);
        profile_Frame.add(accountnoValue_Label);

        mobileValue_Label = new Label("********");
        mobileValue_Label.setFont(fontInput);
        mobileValue_Label.setBounds(400, 375, 222, 30);
        profile_Frame.add(mobileValue_Label);

        balanceValue_Label = new Label("********");
        balanceValue_Label.setFont(fontInput);
        balanceValue_Label.setBounds(400, 430, 222, 30);
        profile_Frame.add(balanceValue_Label);

        back_Button = new Button("BACK");
        back_Button.setFont(fontButton);
        back_Button.setBackground(lightGreyColor);
        back_Button.setForeground(darkGreyColor);
        back_Button.setBounds(235, 500, 150, 43);
        profile_Frame.add(back_Button);
        back_Button.addActionListener(this);

        show_Button = new Button("SHOW");
        show_Button.setFont(fontButton);
        show_Button.setBackground(mainColor);
        show_Button.setForeground(Color.WHITE);
        show_Button.setBounds(415, 500, 150, 45);
        profile_Frame.add(show_Button);
        show_Button.addActionListener(this);


        profile_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                profile_Frame.dispose();
            }
        });

        profile_Frame.setSize(800, 600);
        profile_Frame.setLayout(null);
        profile_Frame.setVisible(true);
        profile_Frame.setLocation(360, 140);

    }

    public static void main(String[] args) {
        new Profile(2);
    }

    public void actionPerformed(ActionEvent ae) {
        
        try {

            if (ae.getSource() == show_Button) {
                
                Mysqlconnect c1 = new Mysqlconnect();
                ResultSet data = c1.s.executeQuery("select * from userdata where id = '" + id + "';");
                data.next();
                fnameValue_Label.setText(data.getString(2));
                lnameValue_Label.setText(data.getString(3));
                emailValue_Label.setText(data.getString(4));
                accountnoValue_Label.setText(data.getString(5));
                mobileValue_Label.setText(data.getString(6));
                data.close();
                ResultSet bank = c1.s.executeQuery("select * from bank where id = '" + id + "';");
                bank.next();
                balanceValue_Label.setText(bank.getString(2));
                bank.close();

            }else if(ae.getSource() == back_Button){

                profile_Frame.dispose();
                new Dashboard(id);

            }

        } catch (Exception e) {

            System.out.println(e);

        } 
        
    }
    
}
