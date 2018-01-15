// make subclass of PerishableItem
public class RefrigeratorItem extends PerishableItem{

    // make constructor and override instance variables from the super class
    public RefrigeratorItem (String name, float price, float weight){
        super(name, price, weight);
    }

    //toString method
    public String toString(){

        String itemDetails;

        String newPrice = new Float(getPrice()).toString(); //change price and weight to Strings
        String newWeight = new Float(getWeight()).toString();

        itemDetails = getDescription()+" weighting "+newWeight+"kg with price $"+newPrice+" (perishable) [keep refrigerated].";
        //print name, weight and price and mention item is perishable and has to be kept refrigerated

        return itemDetails; // return refrigerator item info
    }
} //end of class
