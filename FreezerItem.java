public class FreezerItem extends PerishableItem {

    public FreezerItem() {
        super();
    }
    public FreezerItem(String n, float p, float w) {
        super(n, p, w);
    }

    public String toString() {
        String result;
        result = super.toString() + " [keep frozen]";

        return result;
    }
}
