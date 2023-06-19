package Lab06;

public class Tea extends Beverage {
    private boolean hasMilk;

    // Constructor class Tea
    public Tea(String nama, String size, boolean isCold) {
        super(nama, size, isCold);
        calculatePrice();
    }

    @Override
    public void calculatePrice() {
        // Set harga sesuai dengan ukuran/size teh
        if (size.equalsIgnoreCase("Tall") ){
            price += 15000;
        } else if (size.equalsIgnoreCase("Grande")) {
            price += 20000;
        } else if (size.equalsIgnoreCase("Venti")) {
            price += 25000;
        }
    }

    // Inisiasi milk pada tea
    public void addMilk() {
        if (!hasMilk) {
            price += 7000;
            hasMilk = true;
        }
    }

    @Override
    public String toString() {
        String res = super.toString();

        if (hasMilk) {
          res += " with Milk";
        }
    
        res += " Rp. " + this.getPrice() + ",-";
    
        return res;
    }
}
