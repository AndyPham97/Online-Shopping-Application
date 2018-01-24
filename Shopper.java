public class Shopper {
    public static final int   MAX_CART_ITEMS = 100;

    private Carryable[]   cart;
    private int             numItems;

    public Shopper() {
        cart = new Carryable[MAX_CART_ITEMS];
        numItems = 0;
    }

    public Carryable[] getCart() { return cart; }
    public int getNumItems() { return numItems; }

    public String toString() {
        return "Shopper with shopping cart containing " + numItems + " items";
    }

    public float totalCost() {
        float total = 0;
        for (int i=0; i<numItems; i++) {
            total += cart[i].getPrice();
        }
        return total;
    }

    public void addItem(Carryable g) {
        if (numItems < MAX_CART_ITEMS)
            cart[numItems++] = g;
    }

    public void removeItem(Carryable g) {
        for (int i=0; i<numItems; i++) {
            if (cart[i] == g) {
                cart[i] = cart[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }

    public void packBags() {
        GroceryBag[] packedBags = new GroceryBag[numItems];
        int   bagCount = 0;

        GroceryBag currentBag = new GroceryBag();
        for (int i=0; i<numItems; i++) {
            Carryable item = cart[i];
            if (((GroceryItem)item).getWeight() <= GroceryBag.MAX_WEIGHT) {
                if (!currentBag.canHold((GroceryItem)item)) {
                    packedBags[bagCount++] = currentBag;
                    currentBag = new GroceryBag();
                }
                currentBag.addItem((GroceryItem)item);
                removeItem((GroceryItem) item);
                i--;
            }
        }
        // Check this in case there were no bagged items
        if (currentBag.getWeight() > 0)
            packedBags[bagCount++] = currentBag;

        // Now create a new bag array which is just the right size
        for (int i=0; i<bagCount; i++) {
            this.addItem(packedBags[i]);
        }

        return;
    }

    public PerishableItem[] removePerishables() {
        int perishableCount = 0;

        for(int i = 0; i < numItems; i++) {
            if (cart[i] instanceof PerishableItem)
                perishableCount++;
        }

        PerishableItem[] perishables = new PerishableItem[perishableCount];
        perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (cart[i] instanceof PerishableItem) {
                perishables[perishableCount++] = (PerishableItem) cart[i];
                removeItem(cart[i]);
                i--;
            }
        }
        return perishables;
    }

    public float computeFreezerItemCost() {
        float freezerTotal = 0;

        for(int i = 0; i < numItems; i++) {
            Carryable freezerItems = cart[i];
            if (freezerItems instanceof GroceryItem) {
                if ((GroceryItem)freezerItems instanceof FreezerItem) {
                    freezerTotal += freezerItems.getPrice();
                }
            }
            else if (freezerItems instanceof GroceryBag) {
                for(int j = 0; j < ((GroceryBag)freezerItems).getNumItems(); j++) {
                    Carryable packed = cart[i];
                    packed = ((GroceryBag)freezerItems).getItems()[j];
                    if (packed instanceof FreezerItem)
                        freezerTotal += packed.getPrice();
                }
            }
        }
        return freezerTotal;
    }

    public float computeTotalCost() {
        float total = 0;

        for(int i = 0; i < numItems; i++) {
            Carryable contents = cart[i];
            if (contents != null)
                total += contents.getPrice();
            else {
                total += contents.getPrice();
            }

        }

        return total;
    }

    public void displayCartContents() {

        for (int i = 0; i < numItems; i++) {
            Carryable contents = cart[i];
            System.out.println(contents.getDescription());
            System.out.print(contents.getContents());
        }


    }
}