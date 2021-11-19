import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp2 implements ActionListener {
    int id;

    Font fontHeading = new Font("", Font.BOLD, 40);
    Font fontInput = new Font("", Font.TYPE1_FONT, 20);
    Font fontButton = new Font("", Font.CENTER_BASELINE, 30);
    Font fontLabel = new Font("", Font.TRUETYPE_FONT, 16);
    Font fontSmallLabel = new Font("", Font.TYPE1_FONT, 15);
    Color mainColor = new Color(162, 89, 255);
    Color darkGreyColor = new Color(115, 115, 125);
    Color lightGreyColor = new Color(206, 206, 212);    

    Frame signup_Frame2, error_Frame, success_Frame;

    Label createAccount_Label, username_Label, password_Label, reEnterPassword_Label, pin_Label, reEnterPin_Label,
            fillAll_Label, error_Label, success_Label;

    Button back_Button, next_Button, emptyOk_Button, errorOk_Button, successOk_Button;

    TextField username_TextField, password_TextField, reEnterPassword_TextField, pin_TextField, reEnterPin_TextField;

    SignUp2(int id) {

        this.id = id;

        signup_Frame2 = new Frame("SIGN UP FORM");

        createAccount_Label = new Label("CREATE ACCOUNT");
        createAccount_Label.setFont(fontHeading);
        createAccount_Label.setForeground(mainColor);
        createAccount_Label.setBounds(210, 60, 380, 40);
        signup_Frame2.add(createAccount_Label);

        // width = length * 7.5;
        username_Label = new Label("Username : -");
        username_Label.setFont(fontLabel);
        username_Label.setBounds(138, 155, 105, 16);
        signup_Frame2.add(username_Label);

        password_Label = new Label("Password : -");
        password_Label.setFont(fontLabel);
        password_Label.setBounds(138, 210, 100, 16);
        signup_Frame2.add(password_Label);

        reEnterPassword_Label = new Label("Re-enter Password : -");
        reEnterPassword_Label.setFont(fontLabel);
        reEnterPassword_Label.setBounds(138, 265, 160, 16);
        signup_Frame2.add(reEnterPassword_Label);

        pin_Label = new Label("Enter Pin : -");
        pin_Label.setFont(fontLabel);
        pin_Label.setBounds(138, 320, 137, 16);
        signup_Frame2.add(pin_Label);

        reEnterPin_Label = new Label("Re-enter Pin : -");
        reEnterPin_Label.setFont(fontLabel);
        reEnterPin_Label.setBounds(138, 375, 125, 16);
        signup_Frame2.add(reEnterPin_Label);

        username_TextField = new TextField(20);
        username_TextField.setFont(fontInput);
        username_TextField.setBounds(400, 155, 222, 30);
        signup_Frame2.add(username_TextField);

        password_TextField = new TextField(20);
        password_TextField.setFont(fontInput);
        password_TextField.setBounds(400, 210, 222, 30);
        signup_Frame2.add(password_TextField);

        reEnterPassword_TextField = new TextField(30);
        reEnterPassword_TextField.setFont(fontInput);
        reEnterPassword_TextField.setBounds(400, 265, 222, 30);
        signup_Frame2.add(reEnterPassword_TextField);

        pin_TextField = new TextField(15);
        pin_TextField.setFont(fontInput);
        pin_TextField.setBounds(400, 320, 222, 30);
        signup_Frame2.add(pin_TextField);

        reEnterPin_TextField = new TextField(15);
        reEnterPin_TextField.setFont(fontInput);
        reEnterPin_TextField.setBounds(400, 375, 222, 30);
        signup_Frame2.add(reEnterPin_TextField);

        back_Button = new Button("BACK");
        back_Button.setFont(fontButton);
        back_Button.setBackground(lightGreyColor);
        back_Button.setForeground(darkGreyColor);
        back_Button.setBounds(235, 500, 150, 43);
        signup_Frame2.add(back_Button);
        back_Button.addActionListener(this);

        next_Button = new Button("NEXT");
        next_Button.setFont(fontButton);
        next_Button.setBackground(mainColor);
        next_Button.setForeground(Color.WHITE);
        next_Button.setBounds(415, 500, 150, 43);
        signup_Frame2.add(next_Button);
        next_Button.addActionListener(this);

        signup_Frame2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                signup_Frame2.dispose();
            }
        });

        signup_Frame2.setSize(800, 600);
        signup_Frame2.setLayout(null);
        signup_Frame2.setVisible(true);
        signup_Frame2.setLocation(360, 140);

        // --------POPUP MISMATCH ---------

        // Frame for mismatch account number
        error_Frame = new Frame("Warning");
        error_Label = new Label("Account number mismatch");
        errorOk_Button = new Button("OK");

        // Label for mismatch account number
        error_Label.setBounds(30, 60, 240, 21);
        error_Label.setFont(fontLabel);
        error_Frame.add(error_Label);

        // Ok button for mismatch account number
        errorOk_Button.setBounds(120, 123, 60, 35);
        errorOk_Button.setFont(fontLabel);
        error_Frame.add(errorOk_Button);

        // working of close button for mismatch account number
        error_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                error_Frame.dispose();
            }
        });

        // working of ok button to close popup
        errorOk_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                error_Frame.dispose();
            }
        });

        error_Frame.setSize(300, 200);
        error_Frame.setLocation(610, 340);
        error_Frame.setLayout(null);
        error_Frame.setVisible(false);

        // --------POPUP SUCCESS ---------

        // Frame for user created successfully
        success_Frame = new Frame("Successful");
        success_Label = new Label("User Created Successfully");
        successOk_Button = new Button("LOGIN NOW");

        // Label for mismatch account number
        success_Label.setBounds(30, 60, 240, 21);
        success_Label.setFont(fontLabel);
        success_Frame.add(success_Label);

        // Ok button for mismatch account number
        successOk_Button.setBounds(120, 123, 120, 35);
        successOk_Button.setFont(fontLabel);
        success_Frame.add(successOk_Button);

        // working of close button for mismatch account number
        success_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                success_Frame.dispose();
            }
        });

        // working of ok button to close popup
        successOk_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                success_Frame.dispose();
                new Home();
                signup_Frame2.setVisible(false);
            }
        });

        success_Frame.setSize(300, 200);
        success_Frame.setLocation(610, 340);
        success_Frame.setLayout(null);
        success_Frame.setVisible(false);

    }

    public void actionPerformed(ActionEvent ae) {

        try {

            String username = username_TextField.getText();
            String password = password_TextField.getText();
            String password2 = reEnterPassword_TextField.getText();
            String pin = pin_TextField.getText();
            String pin2 = reEnterPin_TextField.getText();

            if (ae.getSource() == back_Button) {

                // SignUp.signup_Frame1.setVisible(true);
                new SignUp().signup_Frame.setVisible(true);
                signup_Frame2.dispose();

            } else if (ae.getSource() == next_Button) {

                if (username.isEmpty() || password.isEmpty() || password2.isEmpty() || pin.isEmpty()
                        || pin2.isEmpty()) {

                    error_Label.setText("Fill all fields correctly");
                    error_Frame.setVisible(true);

                } else if ((!password.equals(password2)) || (!pin.equals(pin2))) {

                    if (!password.equals(password2)) {
                        error_Label.setText("Password do not match");
                    } else {
                        error_Label.setText("Pin do not match");
                    }

                    error_Frame.setVisible(true);

                } else {

                    Mysqlconnect c1 = new Mysqlconnect();

                    ResultSet rs = c1.s.executeQuery("select username from login where username = '" + username + "';");

                    if (rs.isBeforeFirst()) {
                        error_Label.setText("Username not available");
                        error_Frame.setVisible(true);
                    } else {

                        String query = "insert into login(id, username, pass, pin) values('" + id +
                        "', '"
                        + username + "', '" + password + "', '" + pin + "');";
                        String q2 = "insert into bank(id, balance) values('" + id + "', '0');" ;
                        c1.s.executeUpdate(query);
                        c1.s.executeUpdate(q2);

                        success_Frame.setVisible(true);
                    }

                }

            }

        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        new SignUp2(0);
    }

}
