package Lab07;

import Lab07.card.Card;
import Lab07.card.ElectronicCard;
import Lab07.card.GiftCard;
import Lab07.card.Topupable;
import Lab07.item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    // Inisiasi scanner dan array untuk menyimpan items dan cards
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();

    public static void main(String[] args) {
        initItems();
        showMenu();
    }

    private static void initItems() {
        System.out.println("\n========== Buat Item ==========");
        System.out.printf("Masukkan jumlah item: ");
        int jumlahItem = in.nextInt();                           // Input banyaknya jumlah item
        in.nextLine();

        // Lalukan iterasi sesuai jumlah item dan ,eminta input nama dan harga tiap item
        for (int i = 0; i < jumlahItem; i++) {                                       
            System.out.printf("========== Item ke-%d ==========%n", i + 1); 
            System.out.print("Nama: ");     
            String name = in.nextLine();

            System.out.print("Harga: ");
            int price = in.nextInt();
            in.nextLine();

            Item item = new Item(name, price);                    // Membuat objek item
            items.add(item);                                      // Menambahkan objek item ke array items
        }
    }

    private static void showMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n========== Menu ==========");
            System.out.println("1. Tambahkan Kartu");
            System.out.println("2. Tampilkan Daftar Kartu");
            System.out.println("3. Beli Item");
            System.out.println("4. Topup");
            System.out.println("5. Keluar");
            System.out.print("Masukan pilihan: ");
            int pilihan = in.nextInt();
            in.nextLine();

            isRunning = execute(pilihan);
        }
    }

    private static boolean execute(int pilihan) {
        switch (pilihan) {
            case 1 -> addCard();
            case 2 -> showCard();
            case 3 -> buyItem();
            case 4 -> topup();
            default -> {
                return false;
            }
        }
        return true;
    }

    public static void addCard() {
        System.out.println("\n========== Tambah Kartu ==========");
        System.out.print("Company Name: ");                         
        String companyName = in.nextLine();                             // Input nama company dari card

        System.out.print("Tipe: ");
        String cardType = in.nextLine().toUpperCase();                  // Input tipe electronic card atau gift card

        System.out.print("Balance: ");                                  
        double balance = in.nextDouble();                               // Input balance (saldo) untuk card
        in.nextLine();  

        Card card = createCard(companyName, balance, cardType);         // Membuat objek card
        cards.add(card);                                                // Menambahkan objek card ke array cards

        System.out.println("Berhasil menambahkan kartu");
    }

    /* Method ini untuk membuat card berdasarkan tipenya
     * Tipe yang tersedia adalah gift card dan electronic card
     */
    private static Card createCard(String companyName, double balance, String type) {
        if (type.equalsIgnoreCase("gift")) {
            Card giftCard = new GiftCard(companyName, balance, type);
            return giftCard;
        } else if (type.equalsIgnoreCase("electronic")) {
            Card electronicCard = new ElectronicCard(companyName, balance, type);
            return electronicCard;
        }
        return null;                // Return null ketika tipe cardnya bukan electronic atau gift
    }

    private static void showCard() {
        sortListOfCards();
        printListOfCards();
    }

    public static void buyItem() {
        System.out.println("\n========== Daftar Item ==========");
        for (int i = 0; i < items.size(); i++) {                        // Iterasi item yang tersedia pada array items
            System.out.printf("[%d] %s%n", i, items.get(i));
        }
        System.out.print("Pilihan Item: ");
        int pilihan = in.nextInt();                                     // Input item yang akan dipilih
        in.nextLine();

        // Pilih item
        Item itemPilihan = items.get(pilihan);

        showCard();
        System.out.print("Pilih id Kartu yang tersedia: ");
        int cardId = in.nextInt();                                      // Input id card yang tersedia
        in.nextLine();

        // Pilih kartu
        Card card = getCardById(cardId);

        // Beli item dengan kartu yang dipilih
        if (card.getBalance() < itemPilihan.getPrice()) {
            System.out.printf("Uang tidak cukup!\n");
        } else {
            if (card instanceof GiftCard) {
                card.pay(itemPilihan);
                System.out.printf("Item %s dengan harga %d berhasil dibeli%n", itemPilihan.getName(), itemPilihan.getPrice());
            } else if (card instanceof ElectronicCard) {
                card.pay(itemPilihan);
                System.out.printf("Item %s dengan harga %d berhasil dibeli%n", itemPilihan.getName(), itemPilihan.getPrice());
            }
        }
    }
        
    public static void topup() {
        showCard();
        System.out.print("Pilih id Kartu yang tersedia: ");
        int cardId = in.nextInt();
        in.nextLine();

        // Pilih kartu
        Card card = getCardById(cardId);

        System.out.print("Amount: ");
        double amount = in.nextDouble();                        // Input jumlah nominal yang akan ditambahkan
        in.nextLine();  

        // Topup kartu yang dipilih
        if (card instanceof Topupable) {
            ((Topupable)card).topup(amount);                     // Jika tipe kartunya electronic, maka dapat melakukan topup (topupable)
            System.out.println("Berhasil topup kartu");
        } else {
            System.out.println("Kartu yang dipilih tidak bisa topup"); // Jika tipe kartunya gift, maka tidak dapat melakukan topup
        }

    }

    // Method untuk mencetak semua kartu yang ada dalam array cards
    private static void printListOfCards() {
        System.out.printf("========== Daftar Kartu ==========%n");

        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("[%d] %s %n", i, cards.get(i));
        }
    }

    // Method untuk mendapatkan kartu berdasarkan nomor id kartu
    private static Card getCardById(int id) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getId() == id)
                return card;
        }
        return null;
    }

    private static void sortListOfCards() {
        Collections.sort(cards);
    }
}
