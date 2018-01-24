import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;

public class ShoppingListView extends Pane {
    private Shopper model;
    private Button buyButton;
    private Button returnButton;
    private Button checkoutButton;
    private ListView<String> shoppingCartList;
    private ListView<String> productList;
    private ListView<String> contentsList;
    private TextField totalPriceField;

    public Button getBuyButton() { return buyButton; }
    public Button getReturnButton() {
        return returnButton;
    }
    public Button getCheckoutButton() {
        return checkoutButton;
    }
    public ListView<String> getShoppingCartList() {
        return shoppingCartList;
    }
    public ListView<String> getProductList() {
        return productList;
    }
    public ListView<String> getContentsList() {
        return contentsList;
    }
    public TextField getTotalPriceField() {
        return totalPriceField;
    }

    public ShoppingListView(Shopper m) {
        model = m;

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

        //Buy button
        buyButton = new Button("Buy");
        buyButton.relocate(10, 355);
        buyButton.setPrefSize(200, 25);

        //Return button
        returnButton = new Button("Return");
        returnButton.relocate(220, 355);
        returnButton.setPrefSize(200, 25);

        //Checkout button
        checkoutButton = new Button("Checkout");
        checkoutButton.relocate(430, 355);
        checkoutButton.setPrefSize(120, 25);

        //Total Price
        Label totalPrice = new Label("Total Price:");
        totalPrice.relocate(565, 355);

        totalPriceField = new TextField();
        totalPriceField.relocate(630, 355);
        totalPriceField.setPrefSize(100, 25);
        totalPriceField.setAlignment(Pos.CENTER_RIGHT);

        //Product List
        Label product = new Label("Products");
        product.relocate(10, 10);

        productList = new ListView<String>();
        productList.relocate(10, 45);
        productList.setPrefSize(200, 300);

        String[] productListDescription = new String[products.length];
        for(int i = 0; i < products.length; i++) {
            productListDescription[i] = products[i].toString();
        }
        productList.setItems(FXCollections.observableArrayList(productListDescription));

        // Shopping Cart List
        Label shoppingCart = new Label("Shopping Cart");
        shoppingCart.relocate(220, 10);

        shoppingCartList = new ListView<String>();
        shoppingCartList.relocate(220, 45);
        shoppingCartList.setPrefSize(200, 300);

        //Contents List
        Label contents = new Label("Contents");
        contents.relocate(430, 10);

        contentsList = new ListView<String>();
        contentsList.relocate(430, 45);
        contentsList.setPrefSize(300, 300);

        getChildren().addAll(buyButton, checkoutButton, returnButton,
                totalPrice, totalPriceField, product, productList,
                shoppingCart, shoppingCartList, contents, contentsList);
    }

    public void update() {
        float total = 0;
        if (checkoutButton.getText() == "Checkout") {
            buyButton.setDisable(productList.getSelectionModel().getSelectedIndex() < 0);
            returnButton.setDisable(shoppingCartList.getSelectionModel().getSelectedIndex() < 0);
            productList.setDisable(false);
            contentsList.setItems(FXCollections.observableArrayList(""));
        }
        else {
            buyButton.setDisable(true);
            returnButton.setDisable(true);
            productList.setDisable(true);
        }

        checkoutButton.setDisable(model.getNumItems() == 0);

        String[] exactList = new String[model.getNumItems()];
        for(int i = 0; i < model.getNumItems(); i++) {
            exactList[i] = model.getCart()[i].getDescription();
            total += model.getCart()[i].getPrice();
        }
        shoppingCartList.setItems(FXCollections.observableArrayList(exactList));

        int packedBagIndex = shoppingCartList.getSelectionModel().getSelectedIndex();

        if (packedBagIndex >= 0) {
            if (model.getCart()[packedBagIndex] instanceof GroceryBag) {
                GroceryBag tempBag = (GroceryBag) model.getCart()[packedBagIndex];
                String[] bagContents = new String[tempBag.getNumItems()];
                for(int j = 0; j < tempBag.getNumItems(); j++) {
                    bagContents[j] = tempBag.getItems()[j].toString();
                }
                contentsList.setItems(FXCollections.observableArrayList(bagContents));
            }
            else
                contentsList.setItems(FXCollections.observableArrayList(""));
        }

        totalPriceField.setText("$"+ String.format("%1.2f", total));


    }


}
