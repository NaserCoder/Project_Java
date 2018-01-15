// class name with implementation of the interface and has subclass: PerishableItem
public class GroceryItem implements Carryable {

    //instance variables
    private String name;
    private float price;
    private float weight;

    //instance variables 3 constructors: 0-parameter, 3 parameters with perishable equal false and 4 parameters
    public GroceryItem (){
        this.name = "not known";
        this.price = 0;
        this.weight = 0;
    }

    public GroceryItem (String name, float price, float weight){
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    //setters
    public void setName(String name){this.name=name;}
    public void setPrice(float price){this.price=price;}
    public void setWeight(float weight){this.weight=weight;}

    //getters
    public String getName() {return this.name;}
    public float getWeight() {return this.weight;}

    //toString method
    public String toString(){

        String itemDetails;

        String newPrice = new Float(getPrice()).toString(); //change price and weight to Strings
        String newWeight = new Float(getWeight()).toString();

        itemDetails = this.name+" weighting "+newWeight+"kg with price $"+newPrice+"."; //print name, weight and price

        return itemDetails; // return item info
    }

    //override the getContents from the interface
    public String getContents(){
        return "";
    } // returns empty String

    //override the getDescription from the interface
    public String getDescription(){
        return name; // return the name of the item
    }

    //override the getPrice from the interface
    public float getPrice(){
        return price; // return item price
    }

}//end of class
