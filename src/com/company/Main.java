package com.company;

import javax.naming.Name;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {
        Abc abc=new Abc();
        BankAccount bk= new BankAccount();
        int check =0;
        int x;
        Scanner input = new Scanner(System.in);
        while(check!=5){
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("            * Welcome to Banking Mangament System in Java *");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("0. Admin");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit in Account");
            System.out.println("3. Loan Sanction");
            System.out.println("4. Withdraw From Account");
            System.out.println("5.list of all accounts");
            //System.out.println("5.Admin chat");
            //System.out.println("6.User chat");

            check=input.nextInt();
            switch (check){
                case 0:
                    bk.showaccountofuserstoadmin();
                    break;
                case 1:
                    bk.createaccount();
                    //bk.showdatatousers();
                    break;
                case 2:

                    Scanner scanner=new Scanner(System.in);
                    System.out.println("Enter Account id");
                    String na=scanner.nextLine();
                    bk.deposit(na);
                    bk.showdatatousers(na);
                    break;
                case 3:
                    Scanner sc=new Scanner(System.in);
                    System.out.println("Enter Account id");
                    String naa=sc.nextLine();
                    bk.loan(naa);
                    break;
                case 4:
                    Scanner scs=new Scanner(System.in);
                    System.out.println("Enter withdrawal amount");
                    int mo=scs.nextInt();
                    scs.nextLine();

                    System.out.println("Enter Account id");
                    String na1="";
                    na1=scs.nextLine();
                    bk.withdrawal(na1,mo);
                    break;


                case 5:
                    new NewThread("one");
                    try{
                        Thread.sleep(10000);

                    }
                    catch (InterruptedException e){
                        System.out.println("Main thread interrupted");
                    }
                    System.out.println("Main thread exiting");






            }

        }


    }
}
class Abc extends JFrame implements ActionListener{

    JLabel l1,l2,l3;
    JTextField t1,t2,t3;
    JButton b;
    JRadioButton r1,r2,r3;
    public Abc(){
       setLayout(new FlowLayout());
       setTitle("Welcome to Banking Management System");
        l1=new JLabel("Select user type ");



        r1=new JRadioButton("ADMIN SIGN IN");
        r2=new JRadioButton("USER LOG IN ");
        r3=new JRadioButton("USER SIGN IN");

        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);

        b=new JButton("Submit");

        add(l1);

        add(r1);
        add(r2);
        add(r3);
        add(b);


        b.addActionListener(this);

        setVisible(true);
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(r1.isSelected()){
            Admin obj2=new Admin();

        }
        if(r2.isSelected()){
            User obj2=new User();
        }
        if(r3.isSelected()){
                UserSign obj1=new UserSign();
        }
    }
}
class UserSign extends JFrame implements ActionListener {
    JLabel l1, l2,l3,l4,l5;
    JTextField t1, t2,t3,t4;
    JButton b1;

    public UserSign() {
        setLayout(new FlowLayout());
        setVisible(true);
        l1 = new JLabel("Enter Account Id");
        t1 = new JTextField(20);
        l2 = new JLabel("Enter Password");
        t2 = new JTextField(20);
        l3=new JLabel("Enter Name");
        t3=new JTextField(20);
        l5=new JLabel("Initital Balance");
            t4=new JTextField(20);
        l4=new JLabel("RESULT ? ");
        b1 = new JButton("Submit");

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l5);
        add(t4);

        add(l4);
        add(b1);
        setSize(350, 400);
        b1.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sdldatabase?autoReconnect=true&useSSL=false", "root", "Africa@99");
            Statement stmt = con.createStatement();

            String accountid;
            String name;
            int bal;
            accountid=t1.getText();
            bal=Integer.parseInt(t4.getText());
            name=t3.getText();

            String query = "insert into userdata values('" +bal+ "', '" +name+ "', '"+accountid +"')";
            int count =stmt.executeUpdate(query);
            //ResultSet rs3= stmt.executeQuery(query);

            l4.setText("Created Successfully");
            stmt.close();
            con.close();


        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }
}

class User extends JFrame implements ActionListener{
    JLabel l1, l2;
    JTextField t1, t2;
    JRadioButton r1,r2,r3;
    JButton b;
    public User() {
        setLayout(new FlowLayout());
        setVisible(true);
        l1 = new JLabel("Select Any of the following:");

        r1=new JRadioButton("DEPOSIT");
        r2=new JRadioButton("WITHDRAW");
        r3=new JRadioButton("LOAN SANCTION");
        b=new JButton("SUBMIT");

        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        add(l1);
        add(r1);
        add(r2);
        add(r3);
        add(b);
        b.addActionListener(this);

        setSize(500, 400);



    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
               if(r1.isSelected()){
                  Frame2 frame2=new Frame2();
               }
               if(r2.isSelected()){
                   Withdraw withdraw=new Withdraw();
               }
               if(r3.isSelected()){
                    Loan loan=new Loan();
               }
    }
}
class Loan extends JFrame implements ActionListener{
        JLabel l1,l2;
        JTextField t1,t2;
        JButton b;
        public Loan(){
            setVisible(true);
            setSize(250,400);
            setLayout(new FlowLayout());
            l1=new JLabel("Enter Account Id");
            t1=new JTextField(20);
            l2=new JLabel("Enter Loan Amount to be driven");
            t2=new JTextField(20);
            b=new JButton("Submit");
            add(l1);
            add(t1);
            add(l2);
            add(t2);
            add(b);
            b.addActionListener(this);

        }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sdldatabase?autoReconnect=true&useSSL=false", "root", "Africa@99");
            Statement stmt = con.createStatement();

            String accountid;
            int money;

            String sqlSelectQuery ="select  balance from userdata where accountnumber = ? ";
            PreparedStatement pstmt1=con.prepareStatement(sqlSelectQuery);
            String accid1=t1.getText();
            pstmt1.setString(1,accid1);

            ResultSet resultSet = null;
            resultSet=pstmt1.executeQuery();
            resultSet.next();
            int inimoney=resultSet.getInt(1);



            int loanamount=Integer.parseInt(t2.getText());

                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f, "Loan Sanctioned, Thank You");



            stmt.close();
            con.close();


        } catch (Exception e) {
            System.out.println("error" + e);
        }

    }
}

class Withdraw extends JFrame implements ActionListener{
    JLabel l1,l2;
    JTextField t1,t2;
    JButton b;
    public  Withdraw(){
        setVisible(true);
        setSize(250,400);
        setLayout(new FlowLayout());
        l1=new JLabel("Enter AccountId");
        t1=new JTextField(20);
        l2=new JLabel("Enter Money amount to be witdrawn");
        t2=new JTextField(20);
        b=new JButton("Submit");
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b);
        b.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sdldatabase?autoReconnect=true&useSSL=false", "root", "Africa@99");
            Statement stmt = con.createStatement();

            String accountid;
            int money;

            String sqlSelectQuery ="select  balance from userdata where accountnumber = ? ";
            PreparedStatement pstmt1=con.prepareStatement(sqlSelectQuery);
            String accid1=t1.getText();
            pstmt1.setString(1,accid1);

            ResultSet resultSet = null;
            resultSet=pstmt1.executeQuery();
            resultSet.next();
            int inimoney=resultSet.getInt(1);


            String sqlUpdate = "UPDATE userdata "
                    + "SET balance= ? "
                    + "WHERE accountnumber = ?";
            String m=null;

            PreparedStatement pstmt = con.prepareStatement(sqlUpdate);
            accountid=t1.getText();
            money=Integer.parseInt(t2.getText());
            pstmt.setInt(1, inimoney-money);
            pstmt.setString(2,accountid);
            int rowAffected = pstmt.executeUpdate();


            OptionPaneExample2 optionPaneExample2=new OptionPaneExample2(inimoney-money);

            stmt.close();
            con.close();


        } catch (Exception e) {
            System.out.println("error" + e);
        }

    }
}
class OptionPaneExample2 {
    JFrame f;

    OptionPaneExample2(int money) {
        f = new JFrame();
        JOptionPane.showMessageDialog(f, "Moeny Withdrawn ! : Remaining Amount:- "+money);
    }
}
class Frame2 extends JFrame implements ActionListener
{
    JTextField t1,t2;
    JLabel l1,l2;
    JButton b;
    public Frame2()
    {
        setVisible(true);
        setSize(250,400);
        setLayout(new FlowLayout());

        l1=new JLabel("ENTER AccountID");
        t1=new JTextField(20);
        l2=new JLabel("Enter Balance to be deposited");
        t2=new JTextField(20);
        b=new JButton("Submit");
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sdldatabase?autoReconnect=true&useSSL=false", "root", "Africa@99");
                Statement stmt = con.createStatement();

                String accountid;
                int money;

                String sqlSelectQuery ="select  balance from userdata where accountnumber = ? ";
                PreparedStatement pstmt1=con.prepareStatement(sqlSelectQuery);
                String accid1=t1.getText();
                pstmt1.setString(1,accid1);

                ResultSet resultSet = null;
                resultSet=pstmt1.executeQuery();
                resultSet.next();
                int inimoney=resultSet.getInt(1);


                String sqlUpdate = "UPDATE userdata "
                        + "SET balance= ? "
                        + "WHERE accountnumber = ?";
                String m=null;

                PreparedStatement pstmt = con.prepareStatement(sqlUpdate);
                accountid=t1.getText();
                money=Integer.parseInt(t2.getText());
                pstmt.setInt(1, money+inimoney);
                pstmt.setString(2,accountid);
                int rowAffected = pstmt.executeUpdate();

                OptionPaneExample1 optionPaneExample1=new OptionPaneExample1(money+inimoney);


                stmt.close();
                con.close();


            } catch (Exception e) {
                System.out.println("error" + e);
            }

        }
    }

class OptionPaneExample1 {
    JFrame f;

    public  OptionPaneExample1(int money) {
        f = new JFrame();
        JOptionPane.showMessageDialog(f, "Money Deposited ! : Total Amount:- "+money);
    }
}


class Admin extends JFrame implements ActionListener{
        JLabel l1,l2;
          JLabel temp;
        JTextField t1,t2;
        JButton b;
            public Admin(){
                setLayout(new FlowLayout());
                setVisible(true);
                l1=new JLabel("Enter Admin Id :");
                t1=new JTextField(20);

                l2=new JLabel("Enter Password :");
                t2=new JTextField(20);

                b=new JButton("Submit");

                temp=new JLabel();
                add(l1);
                add(t1);
                add(l2);
                add(t2);
                add(b);
                add(temp);

                b.addActionListener(this);
                setSize(350,400);
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String adminid=t1.getText();
        String passwo=t2.getText();
        if(adminid.equals("abc") &&passwo.equals("123")){
            String s1="Login SuccessFull";
            temp.setText(s1);
            new OptionPaneExample();
            dispose();
            Admindata obj1=new Admindata();



        }
        else{
            String s1="Wrong Credentials";
            temp.setText(s1);
        }
    }
}
class OptionPaneExample {
    JFrame f;

    OptionPaneExample() {
        f = new JFrame();
        JOptionPane.showMessageDialog(f, "Hello, Admin");
    }
}

