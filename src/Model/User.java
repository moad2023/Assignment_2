/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Admin.PersistenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Yahya
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String gender;//enum("male","female")
    private String role;//enum("user","admin")

    public User(String username, String password, String email, String gender, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    //create a new user in users table
    public int save() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "INSERT INTO USERS (ID,USERNAME,PASSWORD,EMAIL,GENDER,ROLE) VALUES (?, ?, ?, ?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2,this.getUsername());
        ps.setString(3, this.getPassword());
        ps.setString(4, this.getEmail());
        ps.setString(5, this.getGender());
        ps.setString(6, this.getRole());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    +" User was added successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    
     public static List<Users> getAllUsers() throws SQLException, ClassNotFoundException{
          EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();
        TypedQuery<Users> query = entityManager.createNamedQuery("Users.findAll", Users.class);
        List<Users> usersList = query.getResultList();
        entityManager.close();
        return usersList;
        
    }
     
     
    public int update() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "UPDATE USERS SET USERNAME=?, PASSWORD=?, EMAIL=? , GENDER=?,ROLE=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1,this.getUsername());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getEmail());
        ps.setString(4, this.getGender());
        ps.setString(5, this.getRole());
        ps.setInt(6, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("User with id : "+this.getId()+" was updated successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    
    
    public int delete() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "DELETE FROM USERS WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with id : "+this.getId()+" was deleted successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
     
    
    
    
    
    
    
    
}
