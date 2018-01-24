public class GroceryBag implements Carryable {
    public static final float MAX_WEIGHT = 5;
    public static final int   MAX_ITEMS = 25;

    private GroceryItem[]   items;
    private int             numItems;
    private float           weight;

    public GroceryBag() {
        items = new GroceryItem[MAX_ITEMS];
        numItems = 0;
        weight = 0;
    }

    public GroceryItem[] getItems() { return items; }
    public int getNumItems() { return numItems; }
    public float getWeight() { return weight; }

    public String getContents() {
        String result = "";

        for(int i = 0; i < numItems; i++) {
            result += "   " + items[i].toString() + "\n";
        }
        return result;
    }
    public String getDescription() {
        String result;

        result = "GROCERY BAG " + "(" + weight + " kg)";

        return result;
    }
    public float getPrice() {
        float total = 0;

        for (int i = 0; i < numItems; i++) {
            total += items[i].getPrice();
        }
        return total;
    }


    public boolean canHold(GroceryItem g) {
        return (((weight + g.getWeight()) <= MAX_WEIGHT) && (numItems <= MAX_ITEMS));
    }

    public void addItem(GroceryItem g) {
        if (canHold(g)) {
            items[numItems++] = g;
            weight += g.getWeight();
        }
    }

    public void removeItem(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                weight -= items[i].getWeight();
                items[i] = items[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }

    public GroceryItem heaviestItem() {
        if (numItems == 0)
            return null;
        GroceryItem heaviest = items[0];
        for (int i=0; i<numItems; i++) {
            if (items[i].getWeight() > heaviest.getWeight()) {
                heaviest = items[i];
            }
        }
        return heaviest;
    }

    public boolean has(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
    }

    public PerishableItem[] unpackPerishables() {

        int perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i] instanceof PerishableItem)
                perishableCount++;
        }
        PerishableItem[] perishables = new PerishableItem[perishableCount];
        perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i] instanceof PerishableItem) {
                perishables[perishableCount++] = (PerishableItem) items[i];
                removeItem(items[i]);
                i--;
            }
        }
        return perishables;
    }

    public String toString() {
        String result;

        if (this.weight > 0.0f && this.numItems > 0) {
            result = "A " + this.weight + "kg grocery bag with " + this.numItems + " items";

            return result;
        }
        else {
            result = "An empty grocery bag.";

            return result;
        }
    }
}
