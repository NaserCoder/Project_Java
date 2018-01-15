// class name with implementation of the interface
public class GroceryBag implements Carryable {

    // 2 static variables for max weight of bag and max items in bag
    private static final double MAX_WEIGHT = 5;
    private static final int MAX_ITEMS = 25;

    // instance variables
    private GroceryItem[] items;
    private int numItems;
    private float weight;

    // 0-parameter constructor where the number of items is initialized to zero and size items array to the max items
    public GroceryBag() {
        this.numItems = 0;
        this.items = new GroceryItem[MAX_ITEMS];
    }

    //setters
    public void setItems(GroceryItem[] items) {
        this.items = items;
    }
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    //getters
    public GroceryItem[] getItems() {
        return items;
    }
    public int getNumItems() {
        return this.numItems;
    }
    public float getWeight() {
        return this.weight;
    }

    //toString method
    public String toString() {
        String bagDetails;

        if (this.numItems == 0) { //if there is no items in bag we state the bag is empty
            bagDetails = "An empty grocery bag.";
            return bagDetails;
        } else { //otherwise we print the total number of items in the bag and the total weight
            String newNumItems = new Integer(getNumItems()).toString();
            String newTotalWeight = new Float(getWeight()).toString();

            bagDetails = "A "+ newTotalWeight +"kg grocery bag with " + newNumItems + " items.";
        }
        return bagDetails;
    }

    //method to add items in the bag
    public void addItem(Carryable product) {
        // the items is only added if, after the addition of its weight in the bag, doesn't exceed the max weight
        GroceryItem item = (GroceryItem)product;
        if ((item.getWeight() + weight) <= MAX_WEIGHT) {
            this.weight = item.getWeight() + this.weight;
            items[this.numItems] = item; // the item object is obtained for each array element
            this.numItems++; //the number of items in incremented after each addition
        }
    }

    //method to remove item from the bag
    public void removeItem(Carryable product){
        for(int j=0; j<numItems; j++) { //loop thru each item in the bag by doing it on each array element from items
            if (items[j].equals(product)) {     // if the item object is the same
                weight = weight - items[j].getWeight(); // deduct the weight of the item from the bag
                items[j] = items[numItems - 1]; // make it equal to the last item
                items[numItems - 1] = null;     // then, make the last item as null
                numItems = numItems - 1;        // and decrement the number of items
                break;                          // the break is there to not loop thru the other items in case an item
            }                                   // object is the same so we avoided removing other items that are same
        }
    }

    // method to detect which item is the heaviest
    public Carryable heaviestItem() {
        int heavyItemNum = 0; // initialize the heaviest item number from the array of item objects
        float heaviestWeight = 0; // initialize the heaviest weight to 0

        if (this.weight == 0) { // if the bag is empty, the weight is 0 so we return null
            return null;
        } else { // otherwise we loop thru each item in the bag
            for (int j = 0; j < numItems; j++) {
                GroceryItem item = (GroceryItem)items[j];
                if (item.getWeight() > heaviestWeight) { // if the item object weight is bigger than the heaviest
                    heaviestWeight = item.getWeight();   // weight stated, the new heavy weight is changed
                    heavyItemNum = j; //also, we store the array element number of that heavy weight so return it
                }
            }
            return items[heavyItemNum]; // once looping is done we return the heaviest item
        }
    }

    // method to detect if item is present in the bag
    public boolean has(GroceryItem item) {
        for (int j = 0; j < numItems; j++)
            if (items[j] == item) // while looping if the item object is equal to the one called by the method
                return true;       // we return true

        return false; // if the loop doesn't detect anything it will not return anything so we return false
    }

    // method to remove perishable items from the bag
    public PerishableItem[] unpackPerishables() {

        PerishableItem[] perishables = new PerishableItem[100]; //size the array of perishable items to 100

        int numberPerishables = 0; // initialize the number of perishables to zero

        for (int i = 0; i < numItems; i++) { // loop thru the list of items

            if (items[i] instanceof PerishableItem) { // detect if the item is an object from Refrigerator or Freezer
                perishables[numberPerishables] = (PerishableItem) items[i];
                // typecast that "items[i]" from GroceryItem to PerishableItem

                numberPerishables++;  // increment the number of perishables
                removeItem(items[i]); // remove the item from the bag by calling the method
                i--; // decrement the "i" because the remove items method decrements the "numItems"

            }
        }

        PerishableItem[] realPerishables = new PerishableItem[numberPerishables];
        // size a new array items to the number of the perishables in the bags

        for (int i = 0; i < numberPerishables; i++) {
            realPerishables[i] = perishables[i]; // stored all the perishable items to that new array
        }

        return realPerishables; // return the newly sized array of perishable items
    }

    //override the getContents from the interface
    public String getContents(){
        String contents = "";

        for (int i = 0; i < numItems; i++) { // loop thru bag items
            contents += " " +items[i].toString()+"\n"; // indent each item description and make a new line
        }
        return contents; // return all bag contents
    }

    //override the getDescription from the interface
    public String getDescription(){
        return "GROCERY BAG ("+weight+" kg)"; // return weight info of bag
    }

    //override the getPrice from the interface
    public float getPrice(){
        float totalPrice= 0; // initialize total price

        for(int i=0; i<numItems; i++) // loop thru all bag items
            totalPrice = totalPrice+items[i].getPrice(); // sum all the price together

        return totalPrice; // return total price of items in the bag
    }

}//end of class