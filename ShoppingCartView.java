// name view class and import libraries
import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.geometry.Pos;

public class ShoppingCartView extends Pane {

    private Shopper model; // models to which this view is attached. 1 = product list and 2 = shopping list
    private Shopper model2;

    // LsitView components
    ListView<String> productList;
    ListView<String> shoppingCartList;
    ListView<String> contentsList;

    // Button components
    private Button buyButton;
    private Button returnButton;
    private Button checkoutButton;

    // Text field where total price is updated and displayed
    private TextField newItemField;

    // Total price value
    private float totalPrice;

    // Make a boolean to detect is bags were packed or not
    private boolean packedBags;


    // Button getters
    public Button getBuyButton() {
        return buyButton;
    }
    public Button getCheckoutButton() {
        return checkoutButton;
    }
    public Button getReturnButton() {
        return returnButton;
    }

    // ListView getters
    public ListView<String> getContentsList() {
        return contentsList;
    }
    public ListView<String> getProductList() {
        return productList;
    }
    public ListView<String> getShoppingCartList() {
        return shoppingCartList;
    }

    // Shopper objects getters
    public Shopper getModel() {
        return model;
    }
    public Shopper getModel2() {
        return model2;
    }

    // Getter for the total price text field
    public TextField getNewItemField() {
        return newItemField;
    }

    // Getter and setter for the packedbags variable
    public boolean getPackedBags() {
        return packedBags;
    }
    public void setPackedBags(boolean packedBags) {
        this.packedBags = packedBags;
    }

    // Getter for the total price value
    public float getTotalPrice() {
        return totalPrice;
    }


    public ShoppingCartView(Shopper m, Shopper m2) {

        packedBags = false; // Initlaize the packedBags to false
        model = m; // initialize model objects
        model2 = m2;

        // Create the labels
        Label label1 = new Label("Products");
        label1.relocate(10, 10);
        label1.setPrefSize(80, 30);
        Label label2 = new Label("Shopping cart");
        label2.relocate(220, 10);
        label2.setPrefSize(80, 30);
        Label label3 = new Label("Contents");
        label3.relocate(430, 10);
        label3.setPrefSize(80, 30);
        Label label4 = new Label("Total Price:");
        label4.relocate(565, 355);
        label4.setPrefSize(80, 30);

        // Create and position the "Buy" Button
        buyButton = new Button("Buy");
        buyButton.relocate(10, 355);
        buyButton.setPrefSize(200, 25);
        //System.out.println("we made the buybutton here");

        // Create and position the "Return" Button
        returnButton = new Button("Return");
        returnButton.relocate(220, 355);
        returnButton.setPrefSize(200, 25);

        // Create and position the "Checkout" Button
        checkoutButton = new Button("Checkout");
        checkoutButton.relocate(430, 355);
        checkoutButton.setPrefSize(120, 25);

        // Create and position the "products" ListView with some fruits in it
        productList = new ListView<String>();
        productList.setItems(FXCollections.observableArrayList(""));
        productList.relocate(10, 45);
        productList.setPrefSize(200, 300);

        // Create and position the "shopping cart" ListView with some fruits in it
        shoppingCartList = new ListView<String>();
        shoppingCartList.setItems(FXCollections.observableArrayList(""));
        shoppingCartList.relocate(220, 45);
        shoppingCartList.setPrefSize(200, 300);

        // Create and position the "contents" ListView with some fruits in it
        contentsList = new ListView<String>();
        contentsList.setItems(FXCollections.observableArrayList(""));
        contentsList.relocate(430, 45);
        contentsList.setPrefSize(300, 300);

        // Text field for price display and initialize to $0.00
        newItemField = new TextField("$0.00");
        newItemField.setEditable(false);
        newItemField.relocate(630, 355);
        newItemField.setPrefSize(100, 25);
        newItemField.setAlignment(Pos.CENTER_RIGHT);

        // Add all the components to the window
        getChildren().addAll(productList, shoppingCartList, contentsList,
                buyButton, returnButton, checkoutButton, label1, label2, label3, label4, newItemField);

        update(); // Call update method
    }

    public void update() {
        // Create and return a new array with the
        // exact size of the number of items in it for the product list by checking model object
        String[] exactList = new String[model.getNumItems()];
        for (int i = 0; i < model.getNumItems(); i++)
            exactList[i] = model.getCart()[i].toString();
        int selectedIndex = productList.getSelectionModel().getSelectedIndex();
        productList.setItems(FXCollections.observableArrayList(exactList));
        productList.getSelectionModel().select(selectedIndex);

        // Do the same for the shopping list by checking "model2"
        String[] exactList2 = new String[model2.getNumItems()];
        for (int i = 0; i < model2.getNumItems(); i++) {
            if (model2.getCart()[i] instanceof GroceryBag)
                exactList2[i] = model2.getCart()[i].getDescription();
            else
                exactList2[i] = model2.getCart()[i].toString();
        }
        int selectedIndex2 = shoppingCartList.getSelectionModel().getSelectedIndex();
        shoppingCartList.setItems(FXCollections.observableArrayList(exactList2));
        shoppingCartList.getSelectionModel().select(selectedIndex2);

        // If index selected from shopping list is an instance of bag display the items in contents list
        if (selectedIndex2 >= 0)
            if (model2.getCart()[selectedIndex2] instanceof GroceryBag) {
                contentsList.setItems(FXCollections.observableArrayList(((GroceryBag)model2.getCart()[selectedIndex2]).getContents()));
            }
            else // otherwise, don't display anything
            {
                contentsList.setItems(FXCollections.observableArrayList(""));
            }

        totalPrice = 0.00f; // initialize the total price value to 0.00
        for(int i=0; i<model2.getNumItems(); i++)
            totalPrice += model2.getCart()[i].getPrice(); // add all prices from the shopping cart items

        String newTotalPrice = String.format("%.2f", totalPrice); // format float to 2 decimals
        totalPrice = Float.parseFloat(newTotalPrice); // make the float a String "newTotalPrice"

        newItemField.setText("$"+newTotalPrice); // display the String of price in the text field

        // If nothing is selected from product list and bags are not packed, disable buy button
        buyButton.setDisable(productList.getSelectionModel().getSelectedIndex() < 0 || packedBags);

        // If nothing is selected from shopping list and bags are not packed, disable return button
        returnButton.setDisable(shoppingCartList.getSelectionModel().getSelectedIndex() < 0 || packedBags);

        // If packBags method is called, disable the product list
        productList.setDisable(packedBags);

        // If at least 1 item is in the shopping cart, enable the checkout button, otherwise disable it
        checkoutButton.setDisable(model2.getNumItems() <= 0);
    }

} // End of view Class


