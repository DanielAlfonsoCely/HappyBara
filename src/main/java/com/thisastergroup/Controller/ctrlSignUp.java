package com.thisastergroup.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thisastergroup.model.SQLUserMethods;
import com.thisastergroup.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

/*
 * Implement the methods for the SignUp method, it needs to verify the password and check if the user exists (email, username)
 * 
 */

public class ctrlSignUp implements Initializable{
   
    
    @FXML
    private TextField SUUsername;
    @FXML 
    private PasswordField SUPassword;
    @FXML
    private TextField SUEmail;
    @FXML
    private ChoiceBox <Integer> boxi1;
    @FXML
    private ComboBox<String> SUGender;
    @FXML
    private TextField SUCountry;
    @FXML
    private Label msgSignUp;
    @FXML
    private Label label_password;
    @FXML
    private Label label_email;
    @FXML
    private Label label_age;



    
    

    private String[] genderOptions = {"Male", "Female", "Enby", "Other"};

    private Integer[] ageOptions = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 
        33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 
        69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100};



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {        
        SUGender.getItems().addAll(genderOptions);
        boxi1.getItems().addAll(ageOptions);
        
    }

    

    
    
    /*
     * Creates a new user in the db
     * 
     * It tries to create an User object with the information used in the UI. If it fails it should show an error message.
     * If the User object is created it checks if the username and email already exists in the DB. And also shows a message.
     * Then it creates the user in the DB with the information provided. 
     * Lastly it shows the main window or the login window.
     * 
     * @see User
     * @see ctrlLogin
     * @see SQLUserMethods
     */

   

    public void SignUp(ActionEvent event){
        
        try{
            
            

            User us = new User(SUUsername.getText(), SUPassword.getText(), SUEmail.getText(), SUGender.getValue().toString(), (boxi1.getValue()), SUCountry.getText());
    
                try{
                    SQLUserMethods db_methods = new SQLUserMethods();

                    String emailcito = SUEmail.getText();
                    String passw = SUPassword.getText();
                    Boolean mistake = true;

                   if (db_methods.userExists(us.getEmail()) || db_methods.usernameExist(us.getUsername())  ) {
                        msgSignUp.setText("User already exists");
                        SUEmail.clear();
                        SUUsername.clear();
                   } 
                    else {
                        if(!us.checkEmail(emailcito)){
                            label_email.setText("The email must have the requirements");
                            SUEmail.clear();
                            mistake = false;
                        }
                        else {
                            label_email.setText("");
                        }

                        if(!us.checkPassword(passw)){
                            label_password.setText("The password must have the requirements");
                            SUPassword.clear();
                            mistake = false;
                        }
                        else {
                            label_password.setText(""); 
                        }

                        if(mistake){
                            SQLUserMethods usSQL = new SQLUserMethods();
                            usSQL.signUp(us);
                            msgSignUp.setText("User created");
                        }

                    }
          
                    
                } catch (Exception e){
                    msgSignUp.setText("Error creating user in the DB: ");
                    System.out.println(e.getMessage());
                }
        
                           
        }       catch (Exception e){
                msgSignUp.setText("Error creating user");
                System.out.println(e.getMessage());
            }


    }
    
    /*
     * Handles the go back to login hyperlink event
     * It shows the login window
     * 
     * @see ctrlLogin
     * @throws IOException
     */
    public void toLogin(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        Scene scene;

        root = FXMLLoader.load(getClass().getResource("..//view//Login.fxml"));
        scene = new Scene(root, 500, 480);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();     
    }

}
