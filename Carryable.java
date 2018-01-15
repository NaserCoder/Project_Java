// interface implemented in GroceryItem and GroceryBag
public interface Carryable {

    // methods to be overridden
    public String getContents();
    public String getDescription();
    public float getPrice();

} //end of class


/*
{

        ShoppingCartView aPane = new ShoppingCartView();

        // Create the labels and textfields
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

    // Create and position the "new item" TextField
        TextField newItemField = new TextField();
        newItemField.relocate(10, 10);
        newItemField.setPrefSize(150, 25);

    // Create and position the "Add" Button
        Button buyButton = new Button("Buy");
        buyButton.relocate(10, 355);
        buyButton.setPrefSize(200, 25);

    // Create and position the "Remove" Button
        Button returnButton = new Button("Return");
        returnButton.relocate(220, 355);
        returnButton.setPrefSize(200, 25);

        // Create and position the "Remove" Button
        Button checkoutButton = new Button("Checkout");
        checkoutButton.relocate(430, 355);
        checkoutButton.setPrefSize(120, 25);


        GroceryItem[] products = {
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
        String[] productNames= new String [products.length];
        for(int i = 0; i<products.length ; i++)
            productNames[i] = products[i].getDescription();

        // Create and position the "products" ListView with some fruits in it
        ListView<String> productList = new ListView<String>();
        productList.setItems(FXCollections.observableArrayList(productNames));
        productList.relocate(10, 45);
        productList.setPrefSize(200, 300);

        // Create and position the "shopping cart" ListView with some fruits in it
        ListView<String> shoppingCartList = new ListView<String>();
        shoppingCartList.setItems(FXCollections.observableArrayList(""));
        shoppingCartList.relocate(220, 45);
        shoppingCartList.setPrefSize(200, 300);

        // Create and position the "contents" ListView with some fruits in it
        ListView<String> contentsList = new ListView<String>();
        contentsList.setItems(FXCollections.observableArrayList(""));
        contentsList.relocate(430, 45);
        contentsList.setPrefSize(300, 300);

        float totalprice = 0.00f;
        // Create and position the "Total price" ListView with some fruits in it
        ListView<String> totalPriceList = new ListView<String>();
        //totalPriceList.setAlignment(Pos.CENTER_RIGHT);
        totalPriceList.setItems(FXCollections.observableArrayList("$0.00"));
        //totalPriceList.setAlignment(Pos.CENTER_RIGHT);
        totalPriceList.relocate(630, 355);
        totalPriceList.setPrefSize(100, 25);

    // Add all the components to the window
        aPane.getChildren().addAll(productList, shoppingCartList, contentsList, totalPriceList,
                buyButton, returnButton, checkoutButton, label1, label2, label3, label4);
        primaryStage.setTitle("Grocery Store Application"); // Set title of window
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane, 740,390)); // Set size of window
        primaryStage.show();

        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("That felt good!");
            }
        });

    }
 */