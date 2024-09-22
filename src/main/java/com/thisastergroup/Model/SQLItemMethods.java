package com.thisastergroup.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.thisastergroup.Model.Item;
import java.util.ArrayList;

/*
 * 
 * What we need
 * 
 * The idea of the Item DB is to create a catalogue or compendium of the items available in the store.
 * This way it the shop doesn't have to be updated every time a new item is added nor be hard coded into the app. 
 * Each item has certain attributes that you can check in the class:
 * @see Item.java
 * 
 * Same as the user each item will have an indepent ID that will be used to identify it in the DB.
 * 
 * The DB also needs a relation with the users table to keep track of the items that each user has bought.
 * 
 */
public class SQLItemMethods extends SQLConnection {

    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    Connection conn = getConnection();
    ArrayList<Item> items;

    /**
     * Function to read an item from the DB
     * 
     * @return item - It gets the name, clothingType, price, discount and collection
     *         of the item
     *         based on the name.
     * 
     * @param item - to get the item name
     * 
     */
    public Item getItem(String itemName) {
        
        try {
            String query = "SELECT name, clothingType, price, discount, collection, path FROM Items WHERE name = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, itemName);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("name"));
                return new Item(rs.getString("name"), rs.getString("clothingType"), rs.getInt("price"),
                        rs.getDouble("discount"), rs.getString("collection"), rs.getString("path"));

                
            }
            return null;
        } catch (SQLException exSQL) {
            System.err.println("Error: " + exSQL.getMessage());
        } /*finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }*/
        return null;
    }

    /**
     * Function to get all the items from the DB
     * 
     * @return items - It gets all the data from the items table
     * 
     */
    public ArrayList<Item> getAllItems() {
        items = new ArrayList<>();
        try {
            items = new ArrayList<>();
            String query = "SELECT name, clothingType, price, discount, collection, path FROM Items;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            rsmd = (ResultSetMetaData) rs.getMetaData();

            while (rs.next()) {
                items.add(new Item(rs.getString("name"), rs.getString("clothingType"), rs.getInt("price"),
                        rs.getDouble("discount"), rs.getString("collection"), rs.getString("path")));
            }
            return items;
        } catch (SQLException exSQL) {
            System.err.println("Error: " + exSQL.getMessage());
        } /*finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }*/
        return new ArrayList<>();
    }

    public ArrayList<Item> getUserItems(User us) {
        items = new ArrayList<>();
        ArrayList<String> strings = us.getArrayItems();
        ArrayList<Item> items_user = new ArrayList<>();
        try {
            for (String string : strings) {
                String query = "SELECT name, clothingType, price, discount, collection, path FROM Items WHERE name = ?;";
                ps = conn.prepareStatement(query);
                ps.setString(1, string);
                rs = ps.executeQuery();

                while (rs.next()) {
                    items_user.add(new Item(rs.getString("name"), rs.getString("clothingType"), rs.getInt("price"),
                            rs.getDouble("discount"), rs.getString("collection"), rs.getString("path")));
                }
            }
            return items_user;
        } catch (SQLException exSQL) {
            System.err.println("Error: " + exSQL.getMessage());
        } /*finally {
            try {
                conn.close();
            } catch (SQLException ex2SQL) {
                System.err.println(ex2SQL);
            }
        }*/
        return items_user;
    }
}