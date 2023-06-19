package Lab07.card;

import Lab07.item.Item; 
 
public class GiftCard extends Card { 
    public GiftCard(String companyName, double balance, String type){ 
        super(companyName, balance, type); 
    } 
     
    // Method bayar yang mengatur balance setelah melakukan pembayaran 
    @Override 
    public void pay(Item item) { 
        setBalance(getBalance() - (item.getPrice()* 0.9)); // memberikan diskon 10% 
    }
}
