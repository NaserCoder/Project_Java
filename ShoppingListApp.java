// name the class for GUI/Controller and import libraries
import javafx.application.Application;
import javafx.collections.FXCollections;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ShoppingListApp extends Application {
    private Shopper model; // object for product list
    private Shopper model2; // object for shopping list
    private ShoppingCartView view; // object from view class
    private Stage pStage; // instance variable for the Stage

    public void start(Stage primaryStage) {
        pStage = primaryStage; // Initialize the stage instance variable
        model = new Shopper(); // object of Shopper for the Product List
        model2 = new Shopper(); // object of Shopper for the Shopping Cart List

        view = new ShoppingCartView(model, model2); // Call from the view class

        Carryable[] products = { // Initialize the products present with Carryable type array
                new FreezerItem("Smart-Ones Frozen Entrees", 1.99f, 0.311f),
                new GroceryItem("SnackPack Pudding", 0.99f, 0.396f),
                new FreezerItem("Breyers Chocolate Icecream",2.99f,2.27f),
                new GroceryItem("Nabob Coffee", 3.99f, 0.326f),
                new GroceryItem("Gold Seal Salmon", 1.99f, 0.213f),
                new GroceryItem("Ocean Spray Cranberry Cocktail",2.99f,2.26f),
                new GroceryItem("Heinz Beans Original", 0.79f, 0.477f),
                new RefrigeratorItem("Lean Ground Beef", 4.94f, 0.75f),
                new FreezerItem("5-Alive Frozen Juice",0.75f,0.426f),
                new GroceryItem("Coca-Cola 12-pack", 3.49f, 5.112f),
                new GroceryItem("Toilet Paper - 48 pack", 40.96f, 10.89f),
                new RefrigeratorItem("2L Sealtest Milk",2.99f,2.06f),
                new RefrigeratorItem("Extra-Large Eggs",1.79f,0.77f),
                new RefrigeratorItem("Yoplait Yogurt 6-pack",4.74f,1.02f),
                new FreezerItem("Mega-Sized Chocolate Icecream",67.93f,15.03f)};

        for(int i = 0; i<products.length ; i++)
            model.addItem(products[i]); // add all items in model object

        String[] productNames= new String [products.length];
        for(int i = 0; i<products.length ; i++)
            productNames[i] = model.getCart()[i].toString(); // get the name of each item of the array

        view.getProductList().setItems(FXCollections.observableArrayList(productNames)); // set array list of product
        view.getShoppingCartList().setItems(FXCollections.observableArrayList("")); // set shopping array list as empty

        primaryStage.setTitle("Grocery Store Application"); // Set title of the window
        primaryStage.setResizable(false); // Set it to non-resizable
        primaryStage.setScene(new Scene(view, 740,390)); // Set the size of window
        primaryStage.show();

        view.getBuyButton().setOnAction(new EventHandler<ActionEvent>() { // Event of Buy Button
            public void handle(ActionEvent actionEvent) { handleBuyButtonPress(); }
        });

        view.getReturnButton().setOnAction(new EventHandler<ActionEvent>() { // Event of Return Button
            public void handle(ActionEvent actionEvent) { handleReturnButtonPress(); }
        });

        view.getCheckoutButton().setOnAction(new EventHandler<ActionEvent>() { // Event of Checkout Button
                public void handle(ActionEvent actionEvent) { handleCheckoutButtonPress();}
        });

        view.productList.setOnMouseClicked(new EventHandler<MouseEvent>() { // Event if product list is selected
            public void handle(MouseEvent event) {
                handleProductListSelection();
            }});

        view.shoppingCartList.setOnMouseClicked(new EventHandler<MouseEvent>() { // Event if Shopping list is selected
            public void handle(MouseEvent event) {
                handleShoppingListSelection();
            }});

    }

    private void handleProductListSelection() {
        view.update();
    } // Product list selection handling method

    private void handleShoppingListSelection() {
        view.update();
    } // Shopping list selection handling method

    private void handleBuyButtonPress() { // Buy button handling method
        int index = view.getProductList().getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            model2.addItem(model.getCart()[index]);
            //model.removeItem(model.getCart()[index]);
            view.update();
        }
    }

    private void handleReturnButtonPress() { // Return button handling method
        int index = view.getShoppingCartList().getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            //model.addItem(model2.getCart()[index]);
            model2.removeItem(model2.getCart()[index]);
            view.update();
        }
}

    private void handleCheckoutButtonPress() { // Checkout button handling method
        if (view.getCheckoutButton().getText() == "Checkout") {

            for(int i=0 ; i<model2.getNumItems() ; i++) { // Get price of each item to display on receipt
                String newPrice = String.format("%.2f", model2.getCart()[i].getPrice()); // format price to 2 decimals
                System.out.printf("%-30s %10s\n", model2.getCart()[i].getDescription(), newPrice);
            } // Make the price print aligned to the right and the name of the item to the left

            System.out.println("-----------------------------------------");

            float oldPrice = view.getTotalPrice(); // Display the total price after the above line
            String newTotalPrice = String.format("%.2f", oldPrice); // format float to 2 decimals
            oldPrice = Float.parseFloat(newTotalPrice); // convert back to float

            System.out.printf("%-30s %10s\n", "TOTAL", newTotalPrice);
            System.out.println("\n");

            model2.packBags(); // Call pack bags method
            view.setPackedBags(true); // Set the value to true for changes in the update method

            view.getCheckoutButton().setText("Restart Shopping"); // Change button text to "Restart Shopping"

            view.update();
        } else { // If the Checkout button isn't called "Checkout" anymore recall the start method
            start(pStage);
        }

    }
    public static void main(String[] args) {
        launch(args);
    } // Lauch the application

} // End of controller class
