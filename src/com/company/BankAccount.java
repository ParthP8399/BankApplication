package com.company;


import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.*;
class NewThread implements Runnable{
    String name;
    Thread t;
    NewThread(String threadname){
        name=threadname;
        t=new Thread(this,name);
        System.out.println("New Thread"+ t);
        t.start();
    }
    public void run(){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sdldatabase?autoReconnect=true&useSSL=false", "root", "Africa@99");
            Statement stmt = con.createStatement();
            ResultSet rs3= stmt.executeQuery("select * from userdata");
            while (rs3.next()){
                System.out.println("account balance"+rs3.getInt(1)+" account name "+rs3.getString(2)+"account number"+rs3.getString(3));
                System.out.println("******************");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println(name+"exiting");
    }

}

public class BankAccount {
    Enumeration e, t;
    int n;
    private String accountnumber;
    private double balance;
    private String phonenumber;
    private String name, email;
     public Hashtable user = new Hashtable();
    LinkedHashMap loan = new LinkedHashMap();
    Vector loansrequested = new Vector();
      Hashtable admin = new Hashtable();

    public BankAccount() {
        this("5679", 2.50, "default name", "default address", "default phone");
        //System.out.println("Empty Constructor Called");

    }

    public BankAccount(String accountnumber, double balance, String name, String email, String phonenumber) {
        //System.out.println("Parametrized Constructor called");
        this.accountnumber = accountnumber;
        this.balance = balance;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;

    }

    public void loan(String name) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Loan Amount :");
        int temp = s.nextInt();
        loan.put(name, temp);
        loansrequested.add(name);
    }

    public void createaccount() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sdldatabase?autoReconnect=true&useSSL=false", "root", "Africa@99");
            Statement stmt = con.createStatement();


            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter N: number of accounts to be opened/created");
            n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                Scanner scs = new Scanner(System.in);
                System.out.println("Enter balance to be deposited initially:-");
                int dinitial = scs.nextInt();
                scs.nextLine();


                System.out.println("Enter account number:-");
                String temp = scs.nextLine();


                user.put(temp, dinitial);
                //*************
                System.out.println("Enter account holder name:-");
                String temp1=scs.nextLine();
                int balance=dinitial;
                String accountnumber=temp;
                //  String name="temp";
                //inserting data into mysql
                PreparedStatement st=con.prepareStatement("insert into userdata values(?,?,?)");
                st.setInt(1,balance);
                st.setString(2,temp1);
                st.setString(3,accountnumber);

                int a=st.executeUpdate();

            }
            con.close();



        }
        catch(Exception e){ System.out.println(e);}

    }

    public void showdatatousers(String name) {


        int money = (int) user.get(name);
        System.out.println("Account with id " + name + " Balance left " + money);


    }

    public void showaccountofuserstoadmin() {
        Scanner scs = new Scanner(System.in);
        System.out.println("Enter Admin ID :");

        int temp = scs.nextInt();
        scs.nextLine();
        System.out.println("Enter admin password:");
        String s1 = scs.nextLine();
        admin.put(s1, temp);
        if (admin.containsValue(123)) {
            String r;
            e = user.keys();
            while (e.hasMoreElements()) {
                r = (String) e.nextElement();
                System.out.println("Account Number of User " + r + " -- " + " Balance of User : " + user.get(r));

            }
            System.out.println();

            System.out.println("Users requested for Loan ");
            t = loansrequested.elements();
            while (t.hasMoreElements()) {
                System.out.println(t.nextElement());
            }
            System.out.println();
        }
        else

        {
            System.out.println("Wrong Credentials ");
        }

    }





    public void  deposit(String name){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Deposit Amount");
        n = scanner.nextInt();
        if(n>=1000){
            n=n+1000;
            System.out.println("VIp person credited with extra benefits:");
        }
        int money=(int)user.get(name);
        money=money+n;
        user.put(name,money);
        System.out.println("Present Balance now:"+user.get(name));


    }
    public void withdrawal(String namee,int withdrawalAmount){
        int money=(int)user.get(namee);
        if(money-withdrawalAmount<0){
            System.out.println("Only "+ money +" available. Withdrawal not processed ");
        }
        else{
            user.put(namee,money-withdrawalAmount);
            System.out.println("Withdrawl of "+ withdrawalAmount +" processed .Remaining Balance= "+user.get(namee));

        }
    }


    public String getAccountnumber() {
        return accountnumber;
    }



    public double getBalance() {
        return balance;
    }



    public String  getPhonenumber() {
        return phonenumber;
    }


    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
    }

}
