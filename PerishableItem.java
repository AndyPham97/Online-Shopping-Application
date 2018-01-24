public abstract class PerishableItem extends GroceryItem{

    public PerishableItem() {
        super();
    }

    public PerishableItem(String n, float p ,float w) {
        super(n, p ,w);
    }

    public String toString() {
        String result;
        result = super.toString() + " (perishable)";

        return result;
    }
}
