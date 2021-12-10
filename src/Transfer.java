import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Transfer implements ActionListener {
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

    Frame transfer_Frame, error_Frame, success_Frame;

    Label transfer_Label, username_Label, acNo_Label, amount_Label, pin_Label, error_Label, success_Label;

    TextField username_TextField, acNo_TextField, amount_TextField, pin_TextField;

    Button back_Button, confirm_Button, ok_Button;

    Transfer(int id) {
        this.id = id;

        transfer_Frame = new Frame("Transfer Money");

        transfer_Label = new Label("TRANSFER MONEY");
        transfer_Label.setFont(fontHeading);
        transfer_Label.setForeground(Color.black);
        transfer_Label.setBounds(212, 70, 375, 40);
        transfer_Frame.add(transfer_Label);

        username_Label = new Label("Receiver's Username : -");
        username_Label.setFont(fontBigLabel);
        username_Label.setBounds(145, 175, 255, 30);
        transfer_Frame.add(username_Label);

        acNo_Label = new Label("Receiver's A/c No : -");
        acNo_Label.setFont(fontBigLabel);
        acNo_Label.setBounds(145, 235, 216, 30);
        transfer_Frame.add(acNo_Label);

        amount_Label = new Label("Enter Amount : -");
        amount_Label.setFont(fontBigLabel);
        amount_Label.setBounds(145, 295, 174, 30);
        transfer_Frame.add(amount_Label);

        pin_Label = new Label("Enter Pin : -");
        pin_Label.setFont(fontBigLabel);
        pin_Label.setBounds(145, 355, 127, 30);
        transfer_Frame.add(pin_Label);

        username_TextField = new TextField(20);
        username_TextField.setFont(fontInput);
        username_TextField.setBounds(405, 175, 222, 35);
        transfer_Frame.add(username_TextField);

        acNo_TextField = new TextField(20);
        acNo_TextField.setFont(fontInput);
        acNo_TextField.setBounds(405, 235, 222, 35);
        transfer_Frame.add(acNo_TextField);

        amount_TextField = new TextField(20);
        amount_TextField.setFont(fontInput);
        amount_TextField.setBounds(405, 295, 222, 35);
        transfer_Frame.add(amount_TextField);

        pin_TextField = new TextField(20);
        pin_TextField.setFont(fontInput);
        pin_TextField.setBounds(405, 355, 222, 35);
        transfer_Frame.add(pin_TextField);

        back_Button = new Button("BACK");
        back_Button.setFont(fontButton);
        back_Button.setBackground(lightGreyColor);
        back_Button.setForeground(darkGreyColor);
        back_Button.setBounds(235, 470, 150, 45);
        transfer_Frame.add(back_Button);
        back_Button.addActionListener(this);

        confirm_Button = new Button("CONFIRM");
        confirm_Button.setFont(fontButton);
        confirm_Button.setBackground(mainColor);
        confirm_Button.setForeground(Color.WHITE);
        confirm_Button.setBounds(415, 470, 150, 45);
        transfer_Frame.add(confirm_Button);
        confirm_Button.addActionListener(this);

        transfer_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                transfer_Frame.dispose();
            }
        });

        transfer_Frame.setSize(800, 600);
        transfer_Frame.setLayout(null);
        transfer_Frame.setVisible(true);
        transfer_Frame.setLocation(360, 140);

    }

    public void actionPerformed(ActionEvent ae) {

        String username = username_TextField.getText();
        String ac_no = acNo_TextField.getText();
        String amount = amount_TextField.getText();
        String pin = pin_TextField.getText();

        int receiver_id, receiver_ac_no, sender_balance, receiver_balance, sender_pin;
        String receiver_username = "";

        if (ae.getSource() == back_Button) {
            transfer_Frame.dispose();
            new Dashboard(id);
        } else if (ae.getSource() == confirm_Button) {

            if (Integer.parseInt(amount) <= 0 || !validateUserName(username) || !validateAmount(amount)
                    || !validatePin(pin) || !validateAccountNo(ac_no)) {

                // --------POPUP SUCCESS ---------

                // Frame for balance
                error_Frame = new Frame("Error");
                ok_Button = new Button("OK");
                if (Integer.parseInt(amount) <= 0 || !validateAmount(amount)) {
                    error_Label = new Label("Enter valid amount");
                } else if (!validateUserName(username)) {
                    error_Label = new Label("Enter valid username");
                } else if (!validatePin(pin)) {
                    error_Label = new Label("Enter valid 4-digit pin");
                } else if (!validateAccountNo(ac_no)) {
                    error_Label = new Label("Enter 8-digit valid Account number");
                } else {
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

                    ResultSet rs_receiver_id = c1.s
                            .executeQuery("select id from login where username = '" + username + "';");

                    if (!rs_receiver_id.isBeforeFirst()) {
                        // --------POPUP SUCCESS ---------

                        // Frame for balance
                        error_Frame = new Frame("Error");
                        ok_Button = new Button("OK");

                        error_Label = new Label("User not found");

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
                        rs_receiver_id.next();
                        receiver_id = rs_receiver_id.getInt("id");

                        ResultSet rs_receiver_ac_no = c1.s
                                .executeQuery("select ac_no from userdata where id = '" + receiver_id + "';");
                        rs_receiver_ac_no.next();
                        receiver_ac_no = rs_receiver_ac_no.getInt("ac_no");

                        ResultSet rs_sender_pin = c1.s.executeQuery("select pin from login where id = '" + id + "';");
                        rs_sender_pin.next();
                        sender_pin = rs_sender_pin.getInt("pin");

                        ResultSet rs_sender_balance = c1.s
                                .executeQuery("select balance from bank where id = '" + id + "';");
                        rs_sender_balance.next();
                        sender_balance = rs_sender_balance.getInt("balance");

                        if ((receiver_ac_no != Integer.parseInt(ac_no)) || (sender_pin != Integer.parseInt(pin))
                                || (sender_balance < Integer.parseInt(amount))) {
                            // --------POPUP SUCCESS ---------

                            // Frame for balance
                            error_Frame = new Frame("Error");
                            ok_Button = new Button("OK");

                            if (sender_pin != Integer.parseInt(pin)) {
                                error_Label = new Label("Incorrect pin");
                            } else if (sender_balance < Integer.parseInt(amount)) {
                                error_Label = new Label("Insufficient balance");
                            } else {
                                error_Label = new Label("Username and A/c No do not match");
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

                            ResultSet rs = c1.s
                                    .executeQuery("select balance from bank where id = '" + receiver_id + "';");
                            rs.next();
                            receiver_balance = rs.getInt("balance");
                            receiver_balance += Integer.parseInt(amount);

                            String query = "update bank set balance = " + receiver_balance + " where id = "
                                    + receiver_id + ";";
                            c1.s.executeUpdate(query);

                            sender_balance -= Integer.parseInt(amount);

                            String query2 = "update bank set balance = " + sender_balance + " where id = " + id + ";";
                            c1.s.executeUpdate(query2);

                            // Frame for balance
                            error_Frame = new Frame("Success");
                            ok_Button = new Button("OK");

                            error_Label = new Label("Rs." + amount + " successfully transferred into "
                                    + receiver_username + "'s account");

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
                                    transfer_Frame.dispose();
                                    new Dashboard(id);
                                }
                            });

                            // working of ok button to close popup
                            ok_Button.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    error_Frame.dispose();
                                    transfer_Frame.dispose();
                                    new Dashboard(id);
                                }
                            });

                            error_Frame.setSize(300, 200);
                            error_Frame.setLocation(610, 340);
                            error_Frame.setLayout(null);
                            error_Frame.setVisible(true);

                        }

                    }

                } catch (Exception e) {

                }
            }
        }

    }

    public static void main(String[] args) {
        new Transfer(0);
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateMobile(String mobile) {
        if (mobile == null || mobile.isEmpty()) {
            return false;
        }
        if (mobile.matches("[6-9]\\d{9}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatePin(String pin) {
        if (pin == null || pin.isEmpty()) {
            return false;
        }
        if (pin.matches("[0-9]\\d{3}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateAmount(String amount) {
        if (amount == null || amount.isEmpty()) {
            return false;
        }
        if (amount.matches("[0-9]{0,7}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateAccountNo(String ac_no) {
        if (ac_no == null || ac_no.isEmpty()) {
            return false;
        }
        if (ac_no.matches("[0-9]\\d{7}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (name.matches("[a-zA-Z]{3,20}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateUserName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (name.matches("[a-zA-Z0-9_]{3,20}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        if (password.matches("[a-zA-Z0-9@]{3,20}")) {
            return true;
        } else {
            return false;
        }
    }

}
