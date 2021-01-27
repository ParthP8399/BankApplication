package com.company;

import com.company.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Admindata extends JFrame {

    JFrame f1;

   JTable table;

    public  Admindata(){
        DefaultTableModel model = new DefaultTableModel();
        f1=new JFrame("Results");
       f1.setLayout(new FlowLayout());
       f1.setSize(500,500);
       // setSize(350,400);
       // setVisible(true);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        table.setModel(model);
        table.setFillsViewportHeight(true);
        f1.add(new JScrollPane(table));
        model.addColumn("balance");
        model.addColumn("Name");
        model.addColumn("accountnumber");
        f1.setVisible(true);


//       String[] columnnames={"Balance","nameofaccountholder","accountnumber"};
//
//        DefaultTableModel model=new DefaultTableModel();
//        model.addColumn("Balance");
//        model.addColumn("AccountHolder");
//        model.addColumn("AccountNumber");
        try{
            ArrayList<String> userlist=new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sdldatabase?autoReconnect=true&useSSL=false", "root", "Africa@99");
            Statement stmt = con.createStatement();
            String query="select * from userdata";
            String bal="",acchol="",accnum="";
            PreparedStatement pat=con.prepareStatement(query);

            ResultSet rs3= stmt.executeQuery(query);
            while(rs3.next()){
                
                bal=rs3.getString(1);
                acchol=rs3.getString(2);
                accnum=rs3.getString(3);
                model.addRow(new Object[]{bal, acchol, accnum});

            }
            con.close();



        }
        catch (Exception e){
            System.out.println("error"+e);
        }


    }
}
