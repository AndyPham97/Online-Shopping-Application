public class GroceryItem implements Carryable{
    private String name;
    private float price;
    private float weight;

    public GroceryItem() {
        name = "NA";
        price = 0.0f;
        weight = 0.0f;
    }

    public GroceryItem(String n, float p, float w) {
        name = n;
        price = p;
        weight = w;
    }


    public String getContents() {
        return "";
    }
    public String getDescription() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public float getWeight() {
        return weight;
    }

    public String toString() {
        String result;

        result = name + " weighing " + weight + "kg with price " + String.format("$%1.2f", price);

        return result;
    }
}
