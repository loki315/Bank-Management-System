import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePin implements ActionListener {
    int id;

    Font fontHeading = new Font("", Font.BOLD, 40);
    Font fontInput = new Font("", Font.TYPE1_FONT, 25);
    Font fontBigLabel = new Font("", Font.TRUETYPE_FONT, 24);
    Font fontButton = new Font("", Font.CENTER_BASELINE, 24);
    Font fontLabel = new Font("", Font.TRUETYPE_FONT, 16);
    Font fontSmallLabel = new Font("", Font.TYPE1_FONT, 15);
    Color mainColor = new Color(162, 89, 255);
    Color darkGreyColor = new Color(115, 115, 125);
    Color lightGreyColor = new Color(206, 206, 212);

    Frame change_Frame, error_Frame, success_Frame;

    Label changePin_Label, oldPin_Label, newPin_Label, reEnterPin_Label, error_Label, success_Label;

    Button back_Button, confirm_Button, ok_Button;

    TextField oldPin_TextField, newPin_TextField, reEnterPin_TextField;

    ChangePin(int id) {
        this.id = id;

        change_Frame = new Frame("Change Pin");

        changePin_Label = new Label("CHANGE PIN");
        changePin_Label.setFont(fontHeading);
        changePin_Label.setForeground(Color.black);
        changePin_Label.setBounds(270, 70, 260, 40);
        change_Frame.add(changePin_Label);

        oldPin_Label = new Label("Old Pin : -");
        oldPin_Label.setFont(fontBigLabel);
        oldPin_Label.setBounds(160, 185, 110, 30);
        change_Frame.add(oldPin_Label);

        newPin_Label = new Label("New Pin : -");
        newPin_Label.setFont(fontBigLabel);
        newPin_Label.setBounds(160, 275, 150, 30);
        change_Frame.add(newPin_Label);

        reEnterPin_Label = new Label("Re-enter New Pin : -");
        reEnterPin_Label.setFont(fontBigLabel);
        reEnterPin_Label.setBounds(160, 365, 220, 30);
        change_Frame.add(reEnterPin_Label);

        oldPin_TextField = new TextField(20);
        oldPin_TextField.setFont(fontInput);
        oldPin_TextField.setBounds(400, 185, 222, 35);
        change_Frame.add(oldPin_TextField);

        newPin_TextField = new TextField(20);
        newPin_TextField.setFont(fontInput);
        newPin_TextField.setBounds(400, 270, 222, 35);
        change_Frame.add(newPin_TextField);

        reEnterPin_TextField = new TextField(20);
        reEnterPin_TextField.setFont(fontInput);
        reEnterPin_TextField.setBounds(400, 355, 222, 35);
        change_Frame.add(reEnterPin_TextField);

        back_Button = new Button("BACK");
        back_Button.setFont(fontButton);
        back_Button.setBackground(lightGreyColor);
        back_Button.setForeground(darkGreyColor);
        back_Button.setBounds(235, 470, 150, 45);
        change_Frame.add(back_Button);
        back_Button.addActionListener(this);

        confirm_Button = new Button("CONFIRM");
        confirm_Button.setFont(fontButton);
        confirm_Button.setBackground(mainColor);
        confirm_Button.setForeground(Color.WHITE);
        confirm_Button.setBounds(415, 470, 150, 45);
        change_Frame.add(confirm_Button);
        confirm_Button.addActionListener(this);

        change_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                change_Frame.dispose();
            }
        });

        change_Frame.setSize(800, 600);
        change_Frame.setLayout(null);
        change_Frame.setVisible(true);
        change_Frame.setLocation(360, 140);

    }

    public static void main(String[] args) {
        new ChangePin(1);
    }

    public void actionPerformed(ActionEvent ae) {

        String oldPin = oldPin_TextField.getText();
        String newPin = newPin_TextField.getText();
        String renew = reEnterPin_TextField.getText();

        if (ae.getSource() == back_Button) {
            change_Frame.dispose();
            new Dashboard(id);
        } else if (ae.getSource() == confirm_Button) {

            if (oldPin.isEmpty() || newPin.isEmpty() || renew.isEmpty() || (!newPin.equals(renew))) {

                // --------POPUP SUCCESS ---------

                // Frame for balance
                error_Frame = new Frame("Error");
                ok_Button = new Button("OK");

                if(!newPin.equals(renew)){
                    error_Label = new Label("Re-enter same pin");
                }else{
                    error_Label = new Label("Fill all fields correctly");
                }

                // Label for balance
                error_Label.setBounds(30, 60, 240, 21);
                error_Label.setFont(fontLabel);
                error_Frame.add(error_Label);

                // Ok button for balance
                ok_Button.setBounds(120, 123, 60, 35);
                ok_Button.setFont(fontLabel);
                error_Frame.add(ok_Button);

                // working of close button for alance
                error_Frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        error_Frame.dispose();
                    }
                });

                // working of ok button to close popup
                ok_Button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        error_Frame.dispose();
                    }
                });

                error_Frame.setSize(300, 200);
                error_Frame.setLocation(610, 340);
                error_Frame.setLayout(null);
                error_Frame.setVisible(true);

            } else {

                try {
                    Mysqlconnect c1 = new Mysqlconnect();
                    ResultSet rs = c1.s.executeQuery("select pin from login where id = '" + id + "';");
                    rs.next();
                    int pin = rs.getInt("pin");

                    if (Integer.parseInt(oldPin) != pin) {
                        // --------POPUP SUCCESS ---------

                        // Frame for balance
                        error_Frame = new Frame("Error");
                        error_Label = new Label("Incorrect Pin");
                        ok_Button = new Button("OK");

                        // Label for balance
                        error_Label.setBounds(30, 60, 240, 21);
                        error_Label.setFont(fontLabel);
                        error_Frame.add(error_Label);

                        // Ok button for balance
                        ok_Button.setBounds(120, 123, 60, 35);
                        ok_Button.setFont(fontLabel);
                        error_Frame.add(ok_Button);

                        // working of close button for alance
                        error_Frame.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                error_Frame.dispose();
                            }
                        });

                        // working of ok button to close popup
                        ok_Button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                error_Frame.dispose();
                            }
                        });

                        error_Frame.setSize(300, 200);
                        error_Frame.setLocation(610, 340);
                        error_Frame.setLayout(null);
                        error_Frame.setVisible(true);

                    }else{
                        String query = "update login set pin = "+ Integer.parseInt(newPin) +" where id = " + id + ";";
                        c1.s.executeUpdate(query);

                        // --------POPUP SUCCESS ---------

                        // Frame for balance
                        error_Frame = new Frame("Success");
                        error_Label = new Label("Pin changed successfully");
                        ok_Button = new Button("OK");

                        // Label for balance
                        error_Label.setBounds(30, 60, 240, 21);
                        error_Label.setFont(fontLabel);
                        error_Frame.add(error_Label);

                        // Ok button for balance
                        ok_Button.setBounds(120, 123, 60, 35);
                        ok_Button.setFont(fontLabel);
                        error_Frame.add(ok_Button);

                        // working of close button for alance
                        error_Frame.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent e) {
                                change_Frame.dispose();
                                error_Frame.dispose();
                                new Dashboard(id);
                            }
                        });

                        // working of ok button to close popup
                        ok_Button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                change_Frame.dispose();
                                error_Frame.dispose();
                                new Dashboard(id);
                            }
                        });

                        error_Frame.setSize(300, 200);
                        error_Frame.setLocation(610, 340);
                        error_Frame.setLayout(null);
                        error_Frame.setVisible(true);

                    }

                } catch (Exception e) {

                }
            }

        }

    }
}

