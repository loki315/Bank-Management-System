import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Dashboard implements ActionListener {
    int id;

    Font fontHeading = new Font("", Font.BOLD, 40);
    Font fontInput = new Font("", Font.TYPE1_FONT, 20);
    Font fontButton = new Font("", Font.CENTER_BASELINE, 24);
    Font fontLabel = new Font("", Font.TRUETYPE_FONT, 16);
    Font fontSmallLabel = new Font("", Font.TYPE1_FONT, 15);
    Color mainColor = new Color(162, 89, 255);
    Color darkGreyColor = new Color(115, 115, 125);
    Color lightGreyColor = new Color(206, 206, 212);

    Frame dashboard_Frame, success_Frame;

    Label dashboard_Label, success_Label;

    Button deposit_Button, withdraw_Button, changePin_Button, balance_Button, transfer_Button, statement_Button,
            back_Button, successOk_Button;

    Dashboard(int id) {
        this.id = id;

        dashboard_Frame = new Frame("Dashboard");

        dashboard_Label = new Label("DASHBOARD");
        dashboard_Label.setFont(fontHeading);
        dashboard_Label.setForeground(Color.black);
        dashboard_Label.setBounds(270, 70, 260, 40);
        dashboard_Frame.add(dashboard_Label);

        deposit_Button = new Button("DEPOSIT");
        deposit_Button.setFont(fontButton);
        deposit_Button.setBackground(mainColor);
        deposit_Button.setForeground(Color.white);
        deposit_Button.setBounds(100, 170, 270, 45);
        dashboard_Frame.add(deposit_Button);
        deposit_Button.addActionListener(this);

        withdraw_Button = new Button("WITHDRAW");
        withdraw_Button.setFont(fontButton);
        withdraw_Button.setBackground(mainColor);
        withdraw_Button.setForeground(Color.white);
        withdraw_Button.setBounds(430, 170, 270, 45);
        dashboard_Frame.add(withdraw_Button);
        withdraw_Button.addActionListener(this);

        balance_Button = new Button("BALANCE ENQUIRY");
        balance_Button.setFont(fontButton);
        balance_Button.setBackground(mainColor);
        balance_Button.setForeground(Color.white);
        balance_Button.setBounds(100, 270, 270, 45);
        dashboard_Frame.add(balance_Button);
        balance_Button.addActionListener(this);

        changePin_Button = new Button("CHANGE PIN");
        changePin_Button.setFont(fontButton);
        changePin_Button.setBackground(mainColor);
        changePin_Button.setForeground(Color.white);
        changePin_Button.setBounds(430, 270, 270, 45);
        dashboard_Frame.add(changePin_Button);
        changePin_Button.addActionListener(this);

        transfer_Button = new Button("TRANSFER MONEY");
        transfer_Button.setFont(fontButton);
        transfer_Button.setBackground(mainColor);
        transfer_Button.setForeground(Color.white);
        transfer_Button.setBounds(100, 370, 270, 45);
        dashboard_Frame.add(transfer_Button);
        transfer_Button.addActionListener(this);

        statement_Button = new Button("MINI STATEMENT");
        statement_Button.setFont(fontButton);
        statement_Button.setBackground(mainColor);
        statement_Button.setForeground(Color.white);
        statement_Button.setBounds(430, 370, 270, 45);
        dashboard_Frame.add(statement_Button);
        statement_Button.addActionListener(this);

        back_Button = new Button("BACK");
        back_Button.setFont(fontButton);
        back_Button.setBackground(lightGreyColor);
        back_Button.setForeground(darkGreyColor);
        back_Button.setBounds(325, 470, 150, 45);
        dashboard_Frame.add(back_Button);
        back_Button.addActionListener(this);

        dashboard_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dashboard_Frame.dispose();
            }
        });

        dashboard_Frame.setSize(800, 600);
        dashboard_Frame.setLayout(null);
        dashboard_Frame.setVisible(true);
        dashboard_Frame.setLocation(360, 140);

    }

    public static void main(String[] args) {
        new Dashboard(5);
    }

    public void actionPerformed(ActionEvent ae) {
        try {

            Mysqlconnect c1 = new Mysqlconnect();

            if (ae.getSource() == back_Button) {
                dashboard_Frame.dispose();
                new Home();
            } else if (ae.getSource() == balance_Button) {

                ResultSet rs = c1.s.executeQuery("select balance from bank where id = '" + id + "';");
                rs.next();
                int balance = rs.getInt("balance");
                // ResultSet rs2 = c1.s.executeQuery("select ac_no from userdata where id = '" + id + "';");
                // rs2.next();
                // int ac_no = rs2.getInt("ac_no");
                

                // --------POPUP SUCCESS ---------

                // Frame for balance
                success_Frame = new Frame("Balance");
                // success_Label = new Label("Ac No: - " + ac_no +"Available balance is " + balance);
                success_Label = new Label("Available balance is " + balance);
                successOk_Button = new Button("OK");

                // Label for balance
                success_Label.setBounds(30, 60, 240, 21);
                success_Label.setFont(fontLabel);
                success_Frame.add(success_Label);

                // Ok button for balance
                successOk_Button.setBounds(120, 123, 60, 35);
                successOk_Button.setFont(fontLabel);
                success_Frame.add(successOk_Button);

                // working of close button for alance
                success_Frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        success_Frame.dispose();
                    }
                });

                // working of ok button to close popup
                successOk_Button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        success_Frame.dispose();
                    }
                });

                success_Frame.setSize(300, 200);
                success_Frame.setLocation(610, 340);
                success_Frame.setLayout(null);
                success_Frame.setVisible(true);
            }else if(ae.getSource() == changePin_Button){
                new ChangePin(id);
                dashboard_Frame.dispose();
            }else if(ae.getSource() == deposit_Button){
                new Deposit(id);
                dashboard_Frame.dispose();
            }else if(ae.getSource() == withdraw_Button){
                new Withdraw(id);
                dashboard_Frame.dispose();
            }
            else if(ae.getSource() == statement_Button){}
            else if(ae.getSource() == transfer_Button){
                new Transfer(id);
                dashboard_Frame.dispose();
            }

        } catch (Exception e) {

        }
    }

}
