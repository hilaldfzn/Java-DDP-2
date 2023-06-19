package Lab06;

public class Coffee extends Beverage {
    private boolean hasWhipCream = false;

    // Constructor class Coffee
    public Coffee(String nama, String size, boolean isCold) {
      super(nama, size, isCold);
      calculatePrice();
    }

    @Override
    public void calculatePrice() {
      // Set harga sesuai dengan size/ukuran kopi
      if (size.equalsIgnoreCase("Tall")) {
          price += 20000;
      } else if (size.equalsIgnoreCase("Grande")) {
          price += 25000;
      } else if (size.equalsIgnoreCase("Venti")) {
          price += 30000;
      }
    }
    
    // Inisiasi whip cream pada coffee
    public void addWhipCream() {
      if (!hasWhipCream) {
        price += 5000;
        hasWhipCream = true;
      }
    }

    public String toString() {
      String res = super.toString();
      
      if (hasWhipCream) {
        res += " with Whip Cream";
      }

      res += " Rp. " + super.getPrice() + ",-";

      return res;
    }
}
