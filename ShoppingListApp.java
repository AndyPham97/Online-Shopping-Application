/**
 * Created by Andy on 2/19/2017.
 */

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class ShoppingListApp extends Application {
    private Shopper model;
    private ShoppingListView view;
    GroceryItem[] products = {
            new FreezerItem("Smart-Ones Frozen Entrees", 1.99f, 0.311f),
            new GroceryItem("SnackPack Pudding", 0.99f, 0.396f),
            new FreezerItem("Breyers Chocolate Icecream", 2.99f, 2.27f),
            new GroceryItem("Nabob Coffee", 3.99f, 0.326f),
            new GroceryItem("Gold Seal Salmon", 1.99f, 0.213f),
            new GroceryItem("Ocean Spray Cranberry Cocktail", 2.99f, 2.26f),
            new GroceryItem("Heinz Beans Original", 0.79f, 0.477f),
            new RefrigeratorItem("Lean Ground Beef", 4.94f, 0.75f),
            new FreezerItem("5-Alive Frozen Juice", 0.75f, 0.426f),
            new GroceryItem("Coca-Cola 12-pack", 3.49f, 5.112f),
            new GroceryItem("Toilet Paper - 48 pack", 40.96f, 10.89f),
            new RefrigeratorItem("2L Sealtest Milk", 2.99f, 2.06f),
            new RefrigeratorItem("Extra-Large Eggs", 1.79f, 0.77f),
            new RefrigeratorItem("Yoplait Yogurt 6-pack", 4.74f, 1.02f),
            new FreezerItem("Mega-Sized Chocolate Icecream", 67.93f, 15.03f)};

    public void start(Stage primaryStage) {
        model = new Shopper();
        view = new ShoppingListView(model);

        view.getBuyButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleBuyButtonPress();
            }
        });
        view.getReturnButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                handleReturnButtonPress();
            }
        });
        view.getCheckoutButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) { handleCheckoutButtonPress(); }
        });

        view.getProductList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                handleProductListSelection();
            }
        });
        view.getShoppingCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                handleShoppingCartListSelection();
            }
        });
        view.getContentsList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                handleContentsListSelection();
            }
        });

        primaryStage.setTitle("Grocery Store Application");
        primaryStage.setScene(new Scene(view, 740, 390));
        primaryStage.show();
        view.update();
    }

    private void handleBuyButtonPress() {
        int index = view.getProductList().getSelectionModel().getSelectedIndex();

        if (index >= 0)
            model.addItem(products[index]);

        view.update();
    }
    private void handleReturnButtonPress() {
        int index = view.getShoppingCartList().getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            for(int i = 0; i < products.length; i++) {
                if (products[i].getDescription() == model.getCart()[index].getDescription()) {
                    model.removeItem(products[i]);
                    break;
                }
            }
        }

        view.update();
    }
    private void handleCheckoutButtonPress() {
        float total = 0;
        if (view.getCheckoutButton().getText() == "Checkout"){
            for(int i = 0; i < model.getNumItems(); i++) {
                System.out.println(String.format("%-40s%1.2f", model.getCart()[i].getDescription(), model.getCart()[i].getPrice()));
                total += model.getCart()[i].getPrice();
            }
            System.out.println("----------------------------------------------");
            System.out.println(String.format("%-40s%1.2f", "TOTAL", total) + "\n");

            model.packBags();
            view.getCheckoutButton().setText("Restart Shopping");
            view.update();


        }
        else {
            int tempLength = model.getNumItems();
            for(int i = 0; i < tempLength; i++) {
                Carryable tempItems = model.getCart()[i];
                model.removeItem(tempItems);
            }
            view.getCheckoutButton().setText("Checkout");
            view.update();
        }
    }

    private void handleProductListSelection() { view.update(); }
    private void handleShoppingCartListSelection() { view.update(); }
    private void handleContentsListSelection() {
        view.update();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
