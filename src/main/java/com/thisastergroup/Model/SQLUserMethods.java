package com.thisastergroup.Model;

import java.sql.Connection;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
SQLUserMethods implementation and usage
    -I think the methods are kinda intuitive, but it could be a good idea to check the notion 'SQL' page for a better understanding of the methods.
    For signing up use signUp(User):
        !!!Before using this method you should check if the username and email already exists in the DB using usernameExist(String) and userExists(String) respectively.
        This method requires a User object as a parameter, which require all the parameters.
        Since is a void method the controller requires to check all de data format and values including the password requirements.        
    For checking if the username exists use usernameExist(String):
        This method requires a String username as a parameter.
        This method returns a boolean, true if the username exists in the DB, otherwise it returns false.
    For checking if the user exists use userExists(String Email):
        This method requires a String email as a parameter.
        This method returns a boolean, true if the user exists in the DB, otherwise it returns false.
    For Login use getUser(String, String):
        This method requires a String email and a String password as parameters.
        This method returns a User object if the user exists in the DB, otherwise it returns null.
        If the method returns null the controller should show an error message (user or password incorrect), you can use userExists(String email) to see which one failed.
    For updating user information use updateUser(User):
        This method requires a User object as a parameter, which require all the parameters.
        Since is a void method the controller requires to check all de data format and values including the password requirements.
        Bear in mind that the user should be logged in to update the information.
    For checking if the user exists use userExists(String):
        This method requires a String email as a parameter.
        This method returns a boolean, true if the user exists in the DB, otherwise it returns false.
    For deleting a user use deleteUser(User):
        !!! Be careful with this method since there's no rollback.
        This method requires a User object as a parameter, which require all the parameters.
        Since is a void method the controller requires to check all de data format and values including the password requirements.
        Bear in mind that the user should be logged in to delete the account.
        You can use getUser(String, String) to check if the user exists and the password is correct. 
 */

//Methods and other SQL related functions for users Login and SignUp

public class SQLUserMethods extends SQLConnection {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = getConnection();

    // Method to create a new user in the DB
    public void signUp(User us) {
        try {
            String query = "INSERT INTO Users (username, email, password, age, gender, Country) VALUES (?, ?, ? , ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, us.getUsername());
            ps.setString(2, us.getEmail());
            ps.setString(3, us.getPassword());
            ps.setInt(4, us.getAge());
            ps.setString(5, us.getGender());
            ps.setString(6, us.getCountry());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }
    }

    // Methods to check if the user exists in the DB
    public boolean userExists(String email) {
        try {
            String query = "SELECT username FROM Users WHERE email = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } /*
           * finally{
           * try
           * {
           * conn.close();
           * }catch(SQLException SQLex2)
           * {
           * System.err.println(SQLex2);
           * }
           * }
           */
    }

    public boolean usernameExist(String username) {
        try {
            String query = "SELECT * FROM Users WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return ps.executeQuery().next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } /*
           * finally {
           * try {
           * conn.close();
           * } catch (SQLException SQLex2) {
           * System.err.println(SQLex2);
           * }
           * }
           */

    }

    // Method to get the user information from the DB (LOGIN)
    public User getUser(String email, String password) {
        try {            
            String query = "SELECT * FROM Users WHERE email = ? ";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);            
            rs = ps.executeQuery();            
            if (rs.next()) {
                System.out.println("Verifying password...");
                if (email.equals(rs.getString("email")) && BCrypt.checkpw(password, rs.getString("password"))) {
                    System.out.println("Password verified");
                    System.out.println(rs.getString("items"));
                    return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"),
                            rs.getString("Gender"), rs.getInt("age"), rs.getString("Country"), rs.getInt("balance"), rs.getString("items"));

                }
                System.out.println("Password incorrect");

                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } /*finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }*/
        return null;
    }

    // Method to modify or update the user information in the DB

    public void updateUser(User us) {
        try {
            String query = "UPDATE Users SET username = ?, email = ?, password = ?, age = ?, gender = ?, country = ? WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, us.getUsername());
            ps.setString(2, us.getEmail());
            ps.setString(3, us.getPassword());
            ps.setInt(4, us.getAge());
            ps.setString(5, us.getGender());
            ps.setString(6, us.getCountry());
            ps.setString(7, us.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }
    }

    // Method to delete the user from the DB
    public void deleteUser(User us) {
        try {
            String query = "DELETE FROM Users WHERE username = ? AND email = ? AND password = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, us.getUsername());
            ps.setString(2, us.getEmail());
            ps.setString(3, us.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }
    }

    public void updateLastTimes(User us) {
        try {
            String query = "UPDATE Users SET lasttimes = ?  WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, us.getLastTimes());
            ps.setString(2, us.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }
    }
   
    public String getlastTimes(User user) {
    
        try {            
            String query = "SELECT lasttimes FROM users WHERE username = ? ";
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());            
            rs = ps.executeQuery();            
            if (rs.next()) {
                System.out.println("Getting lastimes...");
                System.out.println("Lastimes retrieved");
                    return rs.getString("lasttimes");
                
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } /*finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }*/
        return null;
    }

    public void updateitems(User us) {
        try {
            String query = "UPDATE Users SET items = ?  WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, us.getItems());
            ps.setString(2, us.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            /*try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }*/
        }
    }
    
     
    public String getItems(User us){
        try {
            String query = "SELECT items FROM Users WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, us.getUsername());
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Getting items...");
                System.out.println("Items retrieved");
                
                return rs.getString("items");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } /*finally {
        try {
            conn.close();
        } catch (SQLException ex2SQL) {
            System.err.println(ex2SQL);
        }
    }*/
    return null;
}




}



