/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proposedproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Norris G. Hipolito Jr.
 * @version 1.0
 * @date 3/10/2021
 */
public class DBHelper {
    Connection con = null;
    Statement stmt = null;
    
    public void connectDB()throws Exception{
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbrestaurant?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
    }
    public boolean insertUser(String username, String password,String fname, String mname, String lname,String email, int contact)throws Exception{
        boolean flag = false;
        try{
            stmt =con.createStatement();
            String sql = "INSERT INTO tblusers (username,password,firstname,middlename,lastname,email,contact,isAdmin)" + " VALUES ('"+username+"','"+password+"','"+fname+"','"+mname+"','"+lname+"','"+email+"',"+contact+","+flag+");";
            stmt.executeUpdate(sql);
            flag = true;
    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return flag;
    }
    public boolean reserve(String date, String time, int guestno,int tableno,int uid)throws Exception{
        boolean flag = false;
        try {
            flag = false;
            stmt = con.createStatement();
            String sql = "INSERT INTO tblreserve (date,time,guestsno,tableno,user_id)" + " VALUES ('" + date + "','" + time + "'," + guestno + "," + tableno + ","+uid+");";
            stmt.executeUpdate(sql);
            flag = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return flag;
    }
    public boolean insertSchedule(String date,String time,int qty){
        boolean flag = false;
        try {
            flag = false;
            stmt = con.createStatement();
            String sql = "INSERT INTO tblschedule (date,time,reserveesqty)" + " VALUES ('" + date + "','" + time + "'," + qty +");";
            stmt.executeUpdate(sql);
            flag = true;
        } catch (SQLException e) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, e);
        }
        return flag;
    }
    public boolean loginCheck(String username, String password){
        ResultSet rs = null;
        boolean check=false;
        try {
            stmt = con.createStatement();
            String sql ="SELECT * FROM tblusers WHERE username ='"+username+"'AND password=+'"+password+"'";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Session.uid = rs.getInt("user_id");
                Session.username=rs.getString("username");
                Session.password=rs.getString("password");
                Session.firstname =rs.getString("firstname");
                Session.middlename = rs.getString("middlename");
                Session.lastname = rs.getString("lastname");
                Session.email = rs.getString("email");
                Session.contact = rs.getInt("contact");
                Session.isAdmin = rs.getBoolean("isAdmin");
                check = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
       return check;
    }
    public ResultSet displayAllSchedule(){
        ResultSet rs = null;
        try {
            stmt =con.createStatement();
            String sql ="SELECT * FROM tblschedule";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        }
        return rs;
    }
    public ResultSet displayAllUsers() throws SQLException{
        ResultSet rs = null;
        try {

            stmt =con.createStatement();
            String sql ="SELECT * FROM tblusers";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        }
        return rs;
    }
    public ResultSet displayAllReservations()throws SQLException{
        ResultSet rs = null;
        try {
            stmt =con.createStatement();
            String sql ="SELECT * FROM tblreserve";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        }
        return rs;
    }
    public ResultSet displayAllReservationsByUserID(int id){
        ResultSet rs = null;
        try {
            stmt =con.createStatement();
            String sql ="SELECT * FROM tblreserve where user_id ="+id+"";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        }
        return rs;
    }
    public ResultSet getReservationByID(int id){
        ResultSet rs=null;
        try {
            stmt =con.createStatement();
            String sql = "SELECT date,time,guestsno FROM tblreserve WHERE reservation_id ="+id+"";
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return rs;
    }
    public int getQuantity(String date,String time){
        int res =0;
        ResultSet rs=null;
        try {
            stmt =con.createStatement();
            String sql = "SELECT date,time,reserveesqty FROM tblschedule WHERE date ='"+date+"' AND time='"+time+"'";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                res = rs.getInt("reserveesqty");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return res;
    }
    public boolean check(String date, String time){
        boolean flag =false;
        ResultSet rs = null;
        try {
            stmt =con.createStatement();
            String sql ="SELECT * FROM tblschedule WHERE date ='"+date+"' AND time='"+time+"';";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getInt("reserveesqty")<5)
                    flag = true;
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        }
        return flag;
    }
    public boolean updateQuantity(String date,String time,int qty){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "UPDATE tblschedule set reserveesqty='"+qty+"' WHERE date ='"+date+"' AND time='"+time+"'";
            if(stmt.executeUpdate(sql) == 1){
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public ResultSet getUserInfo(int id){
        ResultSet rs=null;
        try {
            stmt =con.createStatement();
            String sql = "SELECT * FROM tblusers WHERE user_id ='"+id+"'";
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return rs;
    }
    public ResultSet getSchedule(int id){
        ResultSet rs=null;
        try {
            stmt =con.createStatement();
            String sql = "SELECT * FROM tblschedule WHERE schedule_id ='"+id+"'";
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return rs;
    }
    public boolean updateSchedule(int id,String date, String time){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "UPDATE tblschedule set date='"+date+"',time='"+time+"' where schedule_id="+id+"";
            if(stmt.executeUpdate(sql) == 1){
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
        
    }
    public boolean updateUserInf(int id,String username, String password,String fname, String mname, String lname,String email, int contact){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "UPDATE tblusers set username='"+username+"',password='"+password+"',firstname='"+fname+"',middlename='"+mname+"',lastname='"+lname+"',email='"+email+"',contact="+contact+" where user_id="+id+"";
            if(stmt.executeUpdate(sql) == 1){
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return flag;
    }
    public boolean updateReservation(int id,String date, String time,int tableno, int guestno){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "UPDATE tblreserve set date='"+date+"',time='"+time+"',tableno="+tableno+", guestsno="+guestno+" where reservation_id="+id+"";
            if(stmt.executeUpdate(sql) == 1){
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return flag;
    }
    public boolean deleteUser(int id){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "DELETE from tblusers where user_id="+id+"";
            if(stmt.executeUpdate(sql) == 1){
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return flag;
    }
    public boolean deleteReservation(int id){
        boolean flag = false;
        String date="";
        String time="";
        int currQTY=0;
        try {
            stmt = con.createStatement();
            ResultSet rs =getReservationByID(id);
            while(rs.next()){
                date = rs.getString("date");
                time = rs.getString("time");
                currQTY = getQuantity(date,time);
            }
            String sql = "DELETE from tblreserve where reservation_id="+id+"";
            if(stmt.executeUpdate(sql) == 1){
                updateQuantity(date,time,currQTY-1);
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return flag;
    }
    public boolean deleteSchedule(int id){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "DELETE from tblschedule where schedule_id="+id+"";
            if(stmt.executeUpdate(sql) == 1){
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return flag;
    }
    public void disconnectDB(){
        try {
            if(con != null)
                con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
