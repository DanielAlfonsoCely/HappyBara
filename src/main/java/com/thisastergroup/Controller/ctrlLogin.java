package com.thisastergroup.controller;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.fxml.FXML;

import java.io.IOException;

import com.thisastergroup.model.SQLUserMethods;

/*
 * Implement the methods for the Login method, it needs to check if the email exists and if the password is correct
 */

import com.thisastergroup.model.User;

public class ctrlLogin {
    @FXML
    private Label msgLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;

    /*
     * Handles the login button event
     * First checks if the user email exists in the DB.
     * Then it checks if the password is correct.
     * Lastly it logs in the user and shows the main window.
     * 
     */
    public void Login(ActionEvent event){
        SQLUserMethods db_methods = new SQLUserMethods();
        if(db_methods.userExists(txtEmail.getText())){
            try{
                User us = db_methods.getUser(txtEmail.getText(), txtPassword.getText());
                msgLogin.setText("Welcome "+ us.getUsername());
                toMainScreen(event);

            }
            catch(Exception e){
                msgLogin.setText("Wrong password");
                System.out.println(e);
                txtPassword.clear();
            }

        }
        else {
            msgLogin.setText("User doesn't exist");
        }
        

    }

    /*
     * Handles the sign up hyperlink event
     * It shows the sign up window
     * @see ctrlSignUp
     * @throws IOException
     */
    public void toSignUp(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        Scene scene;

        root = FXMLLoader.load(getClass().getResource("..//view//SignUp.fxml"));
        scene = new Scene(root, 640, 480);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();     
    }

    public void toMainScreen (ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        Scene scene;

        root = FXMLLoader.load(getClass().getResource("..//view//Room.fxml"));
        scene = new Scene(root, 640, 480);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();   
    }
    
}