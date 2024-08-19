package com.thisastergroup.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.thisastergroup.model.User;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("..//view//Room.fxml"));
             
        scene = new Scene(root, 640, 480);
        scene.setFill(Color.TRANSPARENT); //Added for rounded corners
        stage.setScene(scene);
        //stage.initStyle(StageStyle.TRANSPARENT); //it hides the window frame
        stage.setTitle("TamagoChiguiro");        
        stage.setMinHeight(630);
        stage.setMinWidth(300);
        Image icon = new Image("file:src/main/resources/Icon.png");        
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}