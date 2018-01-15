// name the model class
public class Shopper {

    private static final int MAX_CART_ITEMS = 100; // declare the static variables of max cart items and max weight
    private static final double MAX_WEIGHT = 5;

    private Carryable[] cart; //cart can contain bags and items so it is a Carryable type which is change in the methods
    private int numItems;
    private int numBags; // make instance variables for array of items in cart, number of items, number of bags
    private GroceryBag[] bags; // and an array of bags

    public Shopper() {

        cart = new Carryable[MAX_CART_ITEMS]; // the constructor initializes the cart items to max cart items
        this.bags = new GroceryBag[MAX_CART_ITEMS];  // the size of bag array to max cart items
        this.numItems = 0; // the total number of items and bags are initialized to 0
        this.numBags = 0;
    }

    //setters
    public void setCart(Carryable[] cart) {
        this.cart = cart;
    }
    public void setBags(GroceryBag[] bags) {
        this.bags = bags;
    }
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }
    public void setNumBags(int numBags) {
        this.numBags = numBags;
    }

    //getters
    public Carryable[] getCart() {
        return this.cart;
    }
    public GroceryBag[] getBags() {
        return this.bags;
    }
    public int getNumItems() {
        return numItems;
    }
    public int getNumBags() {
        return numBags;
    }

    //toString method
    public String toString(){
        String newNumItems = new Integer(getNumItems()).toString();

        String cartDetails = "Shopper with shopping cart containing "+newNumItems+" items.";

        return cartDetails;
    }

    // method to add item in the cart (Carryable type)
    public void addItem(Carryable product){

        if(this.numItems<MAX_CART_ITEMS){ // if the number of items in cart are less than the max, the item is added
            cart[this.numItems] = product; // the array element is equaled to the item called for the method
            this.numItems++; // after the item is added, the number of items is incremented
        }
    }

    // method to remove an item in the cart (Carryable type)
    public void removeItem(Carryable product){
        for(int j=0; j<numItems; j++) { //loop thru each item in the bag by doing it on each array element from cart
            if (cart[j].equals(product)) {      // if the item object is the same
                cart[j] = cart[numItems - 1];   // make it equal to the last item
                cart[numItems - 1] = null;      // then, make the last item as null
                numItems = numItems - 1;        // and decrement the number of items
                break;                          // the break is there to not loop thru the other items in case an item
            }                                   // object is the same so we avoided removing other items that are same
        }
    }

    // method to pack the bags doesn't return anything but adds all packed bags to the cart
    public void packBags(){
        bags[0] = new GroceryBag(); // initialize first element of the array which is a bag object

        for(int j=0; j<numItems; j++) { // loop thru each item in the cart
            GroceryItem item = (GroceryItem)cart[j]; // cast cart[i] from Carryable to GroceryItem to access the weight
            if(item.getWeight() < MAX_WEIGHT) // if the weight of the item is greater than the max, leave it in
            {                                   // the cart by skipping it and loop to next item
                if ((item.getWeight() + bags[numBags].getWeight()) <= MAX_WEIGHT ) { //if the weight of item and the
                    bags[numBags].addItem(cart[j]);// bag weight is >= max, proceed by adding the item in the bag
                    removeItem(cart[j]); //after the addition, call the remove method and remove item from the cart
                    j--; // decrement the "j" because the remove items method decrements the "numItems"
                }
                else { // if the first condition is respected but not the second one, we create a new bag
                    numBags++; // it is done by incrementing the number of bags
                    bags[numBags]= new GroceryBag(); // initialize the next array element object of bag
                    j--; // decrement the "j" because the item respect the first condition and we don't want to skip it
                }
            }

        }
        numBags++;
        //numBags++; // increment the number of bags after the loop is done because the first element number of an array
        // starts at 0. So if our number of bags went initially at numBags=0 then 1,2,3,4; the array size would be 5.
        // therefore, incrementing is needed.

        Carryable[] realBags = new Carryable[numBags];// size a new array bags to the number of bags with Carryable

        for(int i=0; i<numBags; i++) {
                realBags[i]=bags[i];// stored all the bags to that new array
            }

        for(int j=0; j<numBags ;j++) // add the packed bags in the cart
            addItem(realBags[j]);

    }

    // display contents method
    public String displayCartContents(){
        String showContents = ""; // initialize the String to be returned

        for(int i=0; i<numItems; i++) // loop through all cart items & bags
        {
            if(cart[i].getContents()=="") // the contents of items returns an empty string, if the contents is empty
                // we detected an item in the cart
                showContents = showContents + cart[i].getDescription() + cart[i].getContents()+"\n";
            else // otherwise it is a bag
                showContents = showContents + cart[i].getDescription() + cart[i].getContents();
        }
        return showContents; // return all the added Strings together
    }

    // removePerishable method
    public PerishableItem[] removePerishables(){

        PerishableItem[] otherPerishable = new PerishableItem[100]; // initialize an array of PerishableItems to 100
        int numOtherPerish = 0; // initialize the number of perishables to 0

        for (int i = 0; i < numItems; i++) { // loop thru all items
            if (cart[i] instanceof PerishableItem) { // detect if it is an instance of PerishableItem
                otherPerishable[numOtherPerish] = (PerishableItem) cart[i]; //If yes, cast item[i] to PerishableItem
                numOtherPerish++; // typecast is required to we add PerishableItem to array. Increment numOtherPerish
                removeItem(cart[i]); // remove that perished item from the cart
                i--; // decrement the "i" because the remove items method decrements the "numItems"
            }
            else if (cart[i] instanceof GroceryBag){// detect if it is an instance of GroceryBag

                    GroceryBag bagCart = (GroceryBag) cart[i]; // typecast cart[i] from Carryable to GroceryBag
                    PerishableItem[] bagPerished = bagCart.unpackPerishables(); // call unpackPerishable and set an
                    //array of called "bagPerished"
                    for(int j=0; j<bagPerished.length; j++){ //loop thru the items of "bagPerished"
                        otherPerishable[numOtherPerish] = bagPerished[j]; // add PerishableItem to array
                        numOtherPerish++; // Increment numOtherPerished
                    }
                }
            }

        PerishableItem[] allPerishables = new PerishableItem[numOtherPerish];
        // create a new PerishableItem array of size numOtherPerish

        for (int i = 0; i < numOtherPerish; i++) // store all the perishable items in that new array "allPerishables"
                allPerishables[i] = otherPerishable[i];

            return allPerishables; // return allPerishables
    }

    // method of computerFreezerItemCost
    public float computeFreezerItemCost(){
        float freezerCost = 0.0f; // initialize freezerCost to 0

        for (int i = 0; i < numItems; i++) { // loop thru all the items
            if (cart[i] instanceof FreezerItem) { // if cart[i] is instance of FreezerItem, typecast it to FreezerItem
                freezerCost += ((FreezerItem)cart[i]).getPrice(); // and get the price
            }
            else{ // otherwise, if it is an instance of GroceryBag,
                if (cart[i] instanceof GroceryBag){

                    GroceryBag bagCart = (GroceryBag) cart[i]; // typecast cart[i] from Carryable to GroceryBag
                    GroceryItem[] bagItems = bagCart.getItems(); // set an bagItem array and access all items in the bag
                    for(int j=0; j<bagItems.length; j++){ // loop thru all bag items
                        if(bagItems[j] instanceof FreezerItem) // if one of the items is an instance of FreezerItem,
                            freezerCost += ((GroceryItem)bagItems[j]).getPrice();//typecast to GroceryItem and get price
                    }
                }
            }
        }
        String freezerItemCost = String.format("%.2f", freezerCost); // format float to 2 decimals
        freezerCost = Float.parseFloat(freezerItemCost); // convert back to float

        return freezerCost;
    }

    // method of computerTotalCost
    public float computeTotalCost(){
        float totalCost = 0.00f; // initialize the totalCost to 0

        for (int i = 0; i < numItems; i++) {
            if (cart[i].getContents()=="") { // the contents of items returns an empty string, if the contents is empty
                // we detected an item in the cart
                totalCost += ((GroceryItem)cart[i]).getPrice(); // typecast cart[i] to GroceryItem and get the price
            }
            else{ // otherwise we know it is a bag
                totalCost += ((GroceryBag)cart[i]).getPrice(); // typecast cart[i] to GroceryBag and get the price
                }
            }
        String oldTotalCost = String.format("%.2f", totalCost); // format float to 2 decimals
        totalCost = Float.parseFloat(oldTotalCost); // convert back to float

            return totalCost; // return the sum of all cost
        }


} // End of model class
