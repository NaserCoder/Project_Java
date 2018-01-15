// make subclass of GroceryItem as an abstract class and has subclass: RefrigeratorItem and FreezerItem
public abstract class PerishableItem extends GroceryItem {

    // make constructor and override instance variables from the super class
    public PerishableItem(String name, float price, float weight){
        super(name, price, weight);
    }

}//end of class
