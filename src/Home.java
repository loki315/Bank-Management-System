
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home implements ActionListener {

    Font fontHeading = new Font("", Font.BOLD, 40);
    Font fontInput = new Font("", Font.TRUETYPE_FONT, 30);
    Font fontButton = new Font("", Font.CENTER_BASELINE, 30);
    Font fontLabel = new Font("", Font.TRUETYPE_FONT, 16);
    Font fontSmallLabel = new Font("", Font.TYPE1_FONT, 15);
    Color mainColor = new Color(162, 89, 255);

    // home_Frame is main frame and error_Frame is popup frame
    Frame home_Frame;
	Frame error_Frame;

    //text_label is text of incorrect username and password
    Label welcome_Label, username_Label, password_Label, notAMember_Label, text_Label;

    // ok_Button is ok button of popup
    Button login_Button, createAccount_Button, ok_Button;

    // 
    TextField username_TextField, password_TextField;

    Home() {

        home_Frame = new Frame("JAVA Bank");

        welcome_Label = new Label("WELCOME");
        welcome_Label.setFont(fontHeading);
        welcome_Label.setForeground(mainColor);
        welcome_Label.setBounds(300, 116, 215, 40);
        home_Frame.add(welcome_Label);

        username_Label = new Label("Username");
        username_Label.setFont(fontLabel);
        username_Label.setBounds(250, 196, 97, 16);
        home_Frame.add(username_Label);

        password_Label = new Label("Password");
        password_Label.setFont(fontLabel);
        password_Label.setBounds(250, 287, 97, 16);
        home_Frame.add(password_Label);

        username_TextField = new TextField(20);
        username_TextField.setFont(fontInput);
        username_TextField.setBounds(250, 222, 300, 40);
        home_Frame.add(username_TextField);

        password_TextField = new TextField(15);
        password_TextField.setFont(fontInput);
        password_TextField.setBounds(250, 312, 300, 40);
        password_TextField.setEchoChar('*');
        home_Frame.add(password_TextField);

        login_Button = new Button("LOGIN");
        login_Button.setFont(fontButton);
        login_Button.setBackground(mainColor);
        login_Button.setForeground(Color.WHITE);
        login_Button.setBounds(275, 382, 240, 46);
        home_Frame.add(login_Button);
        login_Button.addActionListener(this);

        notAMember_Label = new Label("Not a member?");
        notAMember_Label.setFont(fontSmallLabel);
        notAMember_Label.setBounds(294, 444, 115, 15);
        home_Frame.add(notAMember_Label);

        createAccount_Button = new Button("Create Account");
        createAccount_Button.setFont(fontSmallLabel);
        createAccount_Button.setBounds(412, 444, 120, 15);
        createAccount_Button.setForeground(mainColor);
        home_Frame.add(createAccount_Button);
        createAccount_Button.addActionListener(this);

        // to make close button work
        home_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                home_Frame.dispose();
                error_Frame.dispose();
            }
        });

        home_Frame.setSize(800, 600);
        home_Frame.setLayout(null);
        home_Frame.setVisible(true);
        home_Frame.setLocation(360, 140);

        //--------------------------POPUP FOR INCORRECT USERNAME AND PASSWORD------------------------------

        // Frame for incorrect username or password
        error_Frame = new Frame("Try Again");
        text_Label = new Label("Incorrect username or password");
        ok_Button = new Button("OK");

        // Label for incorrect username or password
        text_Label.setBounds(30, 60, 240, 21);
        text_Label.setFont(fontLabel);
        error_Frame.add(text_Label);

        // Ok button for incorrect username or password
        ok_Button.setBounds(120, 123, 60, 35);
        ok_Button.setFont(fontLabel);
        error_Frame.add(ok_Button);

        //working of close button for incorrect username or password
        error_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                error_Frame.dispose();
            }
        });

        // working of ok button to clear username and password fields
        ok_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                error_Frame.dispose();
                username_TextField.setText("");
                password_TextField.setText("");
            }
        });

        error_Frame.setSize(300, 200);
        error_Frame.setLocation(610, 340);
        error_Frame.setLayout(null);
        error_Frame.setVisible(false);

    }

    public void actionPerformed(ActionEvent ae) {

        try {

            if (ae.getSource() == login_Button) {

                Mysqlconnect c1 = new Mysqlconnect();

                String username = username_TextField.getText();
                String password = password_TextField.getText();

                String query = "select * from login where username = '" + username + "' and pass = '" + password + "'";
                
                ResultSet rs = c1.s.executeQuery(query);
                
                if (!rs.isBeforeFirst()) {
                    error_Frame.setVisible(true);
                    
                } else {
                    rs.next();
                    int id = rs.getInt("id");

                    new Dashboard(id);
                    home_Frame.setVisible(false);

                }
            }else if(ae.getSource() == createAccount_Button){

                new SignUp();
                home_Frame.setVisible(false);

            }

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    
    public static void main(String[] args) {
        new Home();
    }

}


