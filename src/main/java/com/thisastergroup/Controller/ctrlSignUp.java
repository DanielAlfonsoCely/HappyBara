package com.thisastergroup.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.mindrot.jbcrypt.BCrypt;

import com.thisastergroup.Model.SQLUserMethods;
import com.thisastergroup.Model.User;

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
import javafx.scene.layout.FlowPane;
import javafx.scene.control.PasswordField;

/*
 * Implement the methods for the SignUp method, it needs to verify the password and check if the user exists (email, username)
 * 
 */

public class CtrlSignUp implements Initializable {

    @FXML
    private TextField txtfUsername;
    @FXML
    private PasswordField txtfPassword;
    @FXML
    private TextField txtfEmail;
    @FXML
    private ChoiceBox<Integer> cbAge;
    @FXML
    private ComboBox<String> cbGender;
    @FXML
    private TextField txtfCountry;
    @FXML
    private Label msgSignUp;
    @FXML
    private FlowPane fpRegistration;

    private Label lblMessage = new Label("");

    private String[] genderOptions = { "Male", "Female", "Enby", "Other" };

    private Integer[] ageOptions = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
            24, 25, 26, 27, 28, 29, 30, 31, 32,
            33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 61, 62, 63, 64, 65, 66, 67, 68,
            69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
            96, 97, 98, 99, 100 };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cbGender.getItems().addAll(genderOptions);
        cbAge.getItems().addAll(ageOptions);
    }

    /**
     * Creates a new user in the db
     * 
     * It tries to create an User object with the information used in the UI.
     * Once the User object is created it checks if the username and email already
     * exists in the DB
     * Then it creates the user in the DB with the information provided.
     * When any of the above fails, it shows an error message.
     * Lastly it shows the main window or the login window.
     * 
     * @see User
     * 
     * @see CtrlLogin
     * 
     * @see SQLUserMethods
     */

    public void SignUp() {
        // Hash the password using BCrypt
        String hashedPwd = BCrypt.hashpw(txtfPassword.getText(), BCrypt.gensalt(15));

        try {

            User us = new User(txtfUsername.getText(), hashedPwd, txtfEmail.getText(),
                    cbGender.getValue(), (cbAge.getValue()), txtfCountry.getText(), 0,"");

            try {
                SQLUserMethods db_methods = new SQLUserMethods();

                String emailcito = txtfEmail.getText();
                String passw = txtfPassword.getText();
                Boolean mistake = true;

                if (db_methods.userExists(us.getEmail()) || db_methods.usernameExist(us.getUsername())) {
                    createMsg("User already exists");
                    txtfEmail.clear();
                    txtfUsername.clear();
                } else {
                    if (!us.checkEmail(emailcito)) {
                        createMsg("Invalid email address");
                        txtfEmail.clear();
                        mistake = false;
                    } else {
                        createMsg("");
                    }

                    if (!us.checkPassword(passw)) {
                        createMsg(
                                "The password must meet the requirements: 8 characters, 1 number, 1 uppercase, 1 lowercase, 1 (@#%$^), any space/tap");
                        txtfPassword.clear();
                        mistake = false;
                    } else {
                        createMsg("");
                    }

                    if (mistake) {
                        SQLUserMethods usSQL = new SQLUserMethods();
                        usSQL.signUp(us);
                        createMsg("User created successfully!");
                    }

                }

            } catch (Exception e) {
                createMsg("Error creating user in the DB: ");
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            createMsg("Error creating user");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Handles the go back to login hyperlink event
     * It shows the login window
     * 
     * @see CtrlLogin
     * 
     * @throws IOException
     */
    public void toLogin(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;

        root = FXMLLoader.load(getClass().getResource("..//view//Login.fxml"));
        scene = new Scene(root, 500, 480);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a message in the UI
     * 
     * It creates a label with the message provided and adds it to the end of the
     * FlowPane
     * 
     * Use it for error messages or success messages
     * 
     * @param msg
     * 
     */
    private void createMsg(String msg) {
        fpRegistration.getChildren().remove(lblMessage);
        lblMessage.setText(msg);        
        lblMessage.setWrapText(true);
        lblMessage.setMaxWidth(250);
        lblMessage.setStyle("-fx-text-fill: white;");

        fpRegistration.getChildren().add(lblMessage);
    }

}
