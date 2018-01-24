public class RefrigeratorItem extends PerishableItem {

    public RefrigeratorItem() {
        super();
    }
    public RefrigeratorItem(String n, float p, float w) {
        super(n,p,w);
    }

    public String toString() {
        String result;
        result = super.toString() + " [keep refridgerated]";

        return result;
    }

}
