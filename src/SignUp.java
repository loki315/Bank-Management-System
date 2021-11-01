import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp implements ActionListener {

    Font fontHeading = new Font("", Font.BOLD, 40);
    Font fontInput = new Font("", Font.TYPE1_FONT, 20);
    Font fontButton = new Font("", Font.CENTER_BASELINE, 30);
    Font fontLabel = new Font("", Font.TRUETYPE_FONT, 16);
    Font fontSmallLabel = new Font("", Font.TYPE1_FONT, 15);
    Color mainColor = new Color(162, 89, 255);
    Color darkGreyColor = new Color(115, 115, 125);
    Color lightGreyColor = new Color(206, 206, 212);

    Frame signup_Frame1;
    Frame error_Frame;

    Label createAccount_Label, fname_Label, lname_Label, email_Label, accountNo_Label, reEnterAccountNo_Label,
            mobile_Label, fillAll_Label, mismatch_Label;

    Button back_Button, next_Button, emptyOk_Button, mismatchOk_Button;

    TextField fname_TextField, lname_TextField, email_TextField, accountNo_TextField, reEnterAccountNo_TextField,
            mobile_TextField;

    SignUp() {

        signup_Frame1 = new Frame("SIGN UP FORM");

        createAccount_Label = new Label("CREATE ACCOUNT");
        createAccount_Label.setFont(fontHeading);
        createAccount_Label.setForeground(mainColor);
        createAccount_Label.setBounds(210, 60, 380, 40);
        signup_Frame1.add(createAccount_Label);

        // width = length * 7.5;
        fname_Label = new Label("First Name : -");
        fname_Label.setFont(fontLabel);
        fname_Label.setBounds(138, 155, 105, 16);
        signup_Frame1.add(fname_Label);

        lname_Label = new Label("Last Name : -");
        lname_Label.setFont(fontLabel);
        lname_Label.setBounds(138, 210, 100, 16);
        signup_Frame1.add(lname_Label);

        email_Label = new Label("Email Id : -");
        email_Label.setFont(fontLabel);
        email_Label.setBounds(138, 265, 95, 16);
        signup_Frame1.add(email_Label);

        accountNo_Label = new Label("Account Number : -");
        accountNo_Label.setFont(fontLabel);
        accountNo_Label.setBounds(138, 320, 137, 16);
        signup_Frame1.add(accountNo_Label);

        reEnterAccountNo_Label = new Label("Re-enter Account No : -");
        reEnterAccountNo_Label.setFont(fontLabel);
        reEnterAccountNo_Label.setBounds(138, 375, 175, 16);
        signup_Frame1.add(reEnterAccountNo_Label);

        mobile_Label = new Label("Mobile Number : -");
        mobile_Label.setFont(fontLabel);
        mobile_Label.setBounds(138, 430, 130, 16);
        signup_Frame1.add(mobile_Label);

        fname_TextField = new TextField(20);
        fname_TextField.setFont(fontInput);
        fname_TextField.setBounds(400, 155, 222, 30);
        signup_Frame1.add(fname_TextField);

        lname_TextField = new TextField(20);
        lname_TextField.setFont(fontInput);
        lname_TextField.setBounds(400, 210, 222, 30);
        signup_Frame1.add(lname_TextField);

        email_TextField = new TextField(30);
        email_TextField.setFont(fontInput);
        email_TextField.setBounds(400, 265, 222, 30);
        signup_Frame1.add(email_TextField);

        accountNo_TextField = new TextField(15);
        accountNo_TextField.setFont(fontInput);
        accountNo_TextField.setBounds(400, 320, 222, 30);
        signup_Frame1.add(accountNo_TextField);

        reEnterAccountNo_TextField = new TextField(15);
        reEnterAccountNo_TextField.setFont(fontInput);
        reEnterAccountNo_TextField.setBounds(400, 375, 222, 30);
        signup_Frame1.add(reEnterAccountNo_TextField);

        mobile_TextField = new TextField(10);
        mobile_TextField.setFont(fontInput);
        mobile_TextField.setBounds(400, 430, 222, 30);
        signup_Frame1.add(mobile_TextField);

        back_Button = new Button("BACK");
        back_Button.setFont(fontButton);
        back_Button.setBackground(lightGreyColor);
        back_Button.setForeground(darkGreyColor);
        back_Button.setBounds(235, 500, 150, 43);
        signup_Frame1.add(back_Button);
        back_Button.addActionListener(this);

        next_Button = new Button("NEXT");
        next_Button.setFont(fontButton);
        next_Button.setBackground(mainColor);
        next_Button.setForeground(Color.WHITE);
        next_Button.setBounds(415, 500, 150, 43);
        signup_Frame1.add(next_Button);
        next_Button.addActionListener(this);

        signup_Frame1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                signup_Frame1.dispose();
            }
        });

        signup_Frame1.setSize(800, 600);
        signup_Frame1.setLayout(null);
        signup_Frame1.setVisible(true);
        signup_Frame1.setLocation(360, 140);

        // --------POPUP MISMATCH ---------

        // Frame for mismatch account number
        error_Frame = new Frame("Warning");
        mismatch_Label = new Label("Account number mismatch");
        mismatchOk_Button = new Button("OK");

        // Label for mismatch account number
        mismatch_Label.setBounds(30, 60, 240, 21);
        mismatch_Label.setFont(fontLabel);
        error_Frame.add(mismatch_Label);

        // Ok button for mismatch account number
        mismatchOk_Button.setBounds(120, 123, 60, 35);
        mismatchOk_Button.setFont(fontLabel);
        error_Frame.add(mismatchOk_Button);

        // working of close button for mismatch account number
        error_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                error_Frame.dispose();
            }
        });

        // working of ok button to close popup
        mismatchOk_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                error_Frame.dispose();
            }
        });

        error_Frame.setSize(300, 200);
        error_Frame.setLocation(610, 340);
        error_Frame.setLayout(null);
        error_Frame.setVisible(false);

    }

    public static void main(String[] args) {

    new SignUp();

    }

    public void actionPerformed(ActionEvent ae) {

        String fname = fname_TextField.getText();
        String lname = lname_TextField.getText();
        String email = email_TextField.getText();
        String ac_no = accountNo_TextField.getText();
        String re_ac_no = reEnterAccountNo_TextField.getText();
        String mobile = mobile_TextField.getText();
        ResultSet rs;

        try {

            if (ae.getSource() == back_Button) {

                new Home();
                signup_Frame1.dispose();

            } else if (ae.getSource() == next_Button) {

                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || ac_no.isEmpty() || re_ac_no.isEmpty()
                        || mobile.isEmpty()) {
                    mismatch_Label.setText("Fill all fields correctly");
                    error_Frame.setVisible(true);

                } else if (!ac_no.equals(re_ac_no)) {

                    error_Frame.setVisible(true);

                } else {

                    Mysqlconnect c1 = new Mysqlconnect();

                    String query = "insert into userdata(fname, lname, email, ac_no, mobile) values('" + fname + "', '"
                            + lname + "', '" + email + "', '" + ac_no + "', '" + mobile + "');";
                    c1.s.executeUpdate(query);

                    String query2 = "select * from userdata where ac_no = '" + ac_no + "';";
                    rs = c1.s.executeQuery(query2);

                    int id=0;
                    while(rs.next()){
                        id = rs.getInt("id");
                    }
                    
                    new SignUp2(id);
                    signup_Frame1.setVisible(false);


                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}


