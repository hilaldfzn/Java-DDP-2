package Lab06;

public class Beverage {
    protected String name;
    protected String size;
    protected boolean isCold;
    protected int price = 0;

    // Constructor untuk class Beverage
    public Beverage(String name, String size, boolean isCold) {
      this.name = name;
      this.size = size;
      this.isCold = isCold;
    }
  
    // Method sengaja dikosongkan untuk dioverride di child classnya
    public void calculatePrice() {}
    
    // Method untuk mencetak output saat perintah ORDERAN dijalankan
    public String toString() {
      String output = "";
  
      if (isCold) {
        output += "COLD ";
      } else {
        output += "HOT ";
      }

      output += size + " " + name;
      return output;
    }

    public String getName() {
      return name;
    }

    public int getPrice() {
        return price;
    }
}
