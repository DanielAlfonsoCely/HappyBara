package com.thisastergroup.controller;

import com.thisastergroup.Model.SQLItemMethods;
import com.thisastergroup.Model.Item;
import com.thisastergroup.controller.CtrlLogin;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class CtrlShop {
    @FXML
    private AnchorPane apIntroPromo;
    @FXML
    private Button btnIcon;
    @FXML
    private AnchorPane apBanner;
    @FXML
    private FlowPane fpShop;

    private SQLItemMethods sqlItemMethods = new SQLItemMethods();
    private ArrayList<Item> items;

    public void initialize() {
        //GridPane.setColumnSpan(apIntroPromo, 3);
        //GridPane.setColumnSpan(apBanner, 2);

        items = sqlItemMethods.getAllItems();
        //loadItems();
    }

    public void loadItems() {
        // Button creation for each item
        for (int i = 0; i < items.size(); i++) {
            AnchorPane apNewItem = new AnchorPane();
            ImageView ivItem = new ImageView();
            Label price = new Label();
            Label discount = new Label();

            // ivItem.setImage(new Image("file:src/main/resources/" + items.get(i).getName()
            // + ".png"));
            ivItem.fitHeightProperty().add(70);
            ivItem.fitWidthProperty().add(70);

            AnchorPane.setLeftAnchor(ivItem, 5.0);
            AnchorPane.setTopAnchor(ivItem, 5.0);
            AnchorPane.setRightAnchor(ivItem, 5.0);
            AnchorPane.setBottomAnchor(ivItem, 5.0);

            price.setText(items.get(i).getPrice() + "m");
            AnchorPane.setRightAnchor(price, 5.0);
            AnchorPane.setBottomAnchor(price, 5.0);

            apNewItem.getStyleClass().add("item-card");
            apNewItem.setPrefSize(80, 80);
            apNewItem.getChildren().add(ivItem);
            apNewItem.getChildren().add(price);
            if (items.get(i).getDiscount() > 0) {
                discount.setText(items.get(i).getDiscount() + "% off!");
                apNewItem.getChildren().add(discount);
                AnchorPane.setTopAnchor(discount, 5.0);
                AnchorPane.setLeftAnchor(discount, 5.0);
                apNewItem.getStyleClass().add("promo-card");
            }

            apNewItem.setId(items.get(i).getName());
            apNewItem.setOnMouseClicked(e -> 
                System.out.println("Item:" + apNewItem.getId() + " clicked")
            );
            fpShop.getChildren().add(apNewItem);
        }
    }

}
