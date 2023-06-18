import java.util.Scanner;

public class Lab02 {
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int option = getUserInput("Pilihan: ");

            switch (option) {
                case 1 -> createCoupon();
                case 2 -> validateCoupon();
                case 3 -> isRunning = false;
                default -> System.out.println("Pilihan tidak valid.");
            }
            System.out.println();
        }
        s.close();
    }

    private static void displayMenu() {
        System.out.println("Halo! Apa yang ingin kamu lakukan?");
        System.out.println("[1] Buat kupon");
        System.out.println("[2] Validasi kupon");
        System.out.println("[3] Keluar");
    }

    private static int getUserInput(String message) {
        System.out.print(message);
        return s.nextInt();
    }

    private static String getInputString(String message) {
        System.out.print(message);
        s.nextLine(); 
        return s.nextLine();
    }

    private static void createCoupon() {
        String couponName = getInputString("Nama kupon: ");
        String coupon = generateCoupon(couponName);
        System.out.println("Kode kupon adalah: " + coupon);
    }

    private static void validateCoupon() {
        String couponId = getInputString("Kupon: ");
        
        if (validateCoupon(couponId)) {
            System.out.println("Kupon yang diberikan valid");
        } else {
            System.out.println("Kupon yang diberikan tidak valid");
        }
    }

    private static int generateChecksum(String coupon) {
        if (coupon.isEmpty()) {
            return 0;
        }

        int charValue = coupon.charAt(0) - 'A';
        return (charValue + generateChecksum(coupon.substring(1))) % 26;
    }

    private static String generateCoupon(String couponName) {
        String coupon = couponName + (char) ('A' + generateChecksum(couponName));
        coupon += (char) ('A' + generateChecksum(coupon));
        return coupon;
    }

    private static boolean validateCoupon(String coupon) {
        String couponName = coupon.substring(0, coupon.length() - 2);
        String newCoupon = generateCoupon(couponName);
        return newCoupon.equals(coupon);
    }
}
