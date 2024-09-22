package com.thisastergroup.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//User class to store user information
//This class is used to create a user object with the information of the user that will be handled in the DB
/* 
 * Needs to create a method to check the password requirements (regex)
 * Needs to create a method to check the email format (regex)
 * Needs to check the age format (int)
 * We will use Male, Female, Non binary or Other (idk if there are labels that aren't under that umbrella, perhaps genderfluid?) as gender values (Deployable list in the view)
 * I would change the constructor to fit the previous reqs as well so it also covers scenarios where the user is created the first time
*/


public class User {

    private String username;
    private String password;
    private String email;
    private String gender;
    private int age;
    private String country;
    private int balance;
    private String lastTimes ;
    private String items = "";


    /**
     * Constructor for User
     * 
     * Creates a User with the username, password, email, gender, age and country
     * 
     * @param username the username of the user. It checks availability in the db
     * @param password the password of the user. It goes through a regex to check
     *                 the format
     * @param email    the email of the user. It goes through a regex to check the
     *                 format. And is also checked for availability in the db
     * @param gender   we use Male, Female, Non binary or Other as values
     * @param age      the age of the user as an int
     * @param country  the country of the user as a string
     * 
     */
    public User(String username, String password, String email, String gender, int age, String country, int balance, String items) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.balance = balance;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Username: " + username + " Password: " + password + " Email: " + email + " Gender: " + gender + " Age: "
                + age + " Country: " + country;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password) {
        String patroncito = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=]).*$";
        Pattern pattern = Pattern.compile(patroncito, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkEmail(String email) {
        String emailcito = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailcito, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("Match found");
            return true;
        } else {
            System.out.println("Match not found");
            return false;
        }
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return int return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    static public boolean check_age(String edad) {
        try {
            Integer.parseInt(edad);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return String return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return int return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public String getLastTimes() {
        return lastTimes;
    }
    public void setLastTimes(String lastTimes) {
        this.lastTimes = lastTimes;
    }

    public ArrayList<String> getArrayItems() {
        
        String[] itemsArray = this.items.split("&&");
        ArrayList<String> items = new ArrayList<>(Arrays.asList(itemsArray));
        return items;
    }
    public String getItems() {
        return items;
    }
    public void addItem(String item) {
        if(this.items== null )this.items = item;
        else this.items = this.items +"&&" + item;
    }
    public void setItems(String items) {
        this.items = items;
    }
    
}