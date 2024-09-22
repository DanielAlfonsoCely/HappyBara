package com.thisastergroup.controller;


import com.thisastergroup.Model.SQLItemMethods;
import com.thisastergroup.Model.Item;
import com.thisastergroup.Model.User;
import com.thisastergroup.controller.CtrlLogin;
import com.thisastergroup.Model.SQLUserMethods;


import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
public class CtrlShop_prueba {
    @FXML
    private Button prueba;

    @FXML
    private Button room;
    
    private SQLItemMethods sqlItemMethods = new SQLItemMethods();
    private ArrayList<Item> items;
    private ArrayList<Item> userItems;
    private User user = CtrlLogin.getUser();


    public void initialize() {
        //GridPane.setColumnSpan(apIntroPromo, 3);
        //GridPane.setColumnSpan(apBanner, 2);

        items = sqlItemMethods.getAllItems();
        //loadItems();
    }

    public void prueba(ActionEvent event) throws IOException {
        SQLItemMethods sql = new SQLItemMethods();
        SQLUserMethods sqlUser = new SQLUserMethods();
        System.out.println("prueba");
        user.addItem("sunglasses");
        user.addItem("santa_suit");
        user.addItem("aviator_glasses");
        user.addItem("christmas_lights");
        user.addItem("hoodie");
        user.addItem("shirt_sunny");
        sqlUser.updateitems(user);
        userItems = sql.getUserItems(user);
        System.out.println(userItems);


        
    }
    public void room (ActionEvent event) throws IOException {
        System.out.println("room");
    }

}
