import java.util.Scanner;

public class Lab01 {
    public static void main(String[] args) {
        // Inisialiasi scanner dan variabel total pendapatan
        Scanner sc = new Scanner(System.in);
        double totalPendapatan = 0;

        System.out.println("Selamat datang di Toko Fotokopi Dek Depe!");
        System.out.println("--------------------------------------------------------");
        System.out.print("Masukkan jumlah mahasiswa yang ingin melakukan fotokopi: ");
        int jumlahMahasiswa = sc.nextInt();
        sc.nextLine();

        // Lakukan loop per mahasiswa
        for (int i = 1; i <= jumlahMahasiswa; i++) {
            System.out.printf("--------------------DATA MAHASISWA %d--------------------\n", i);
            
            // Meminta input nama, IPK, dan jumlah lembar
            System.out.print("Nama: ");
            String namaMahasiswa = sc.nextLine();
            System.out.print("IPK: ");
            double ipkMahasiswa = sc.nextDouble();
            System.out.print("Jumlah lembar: ");
            int jumlahLembar = sc.nextInt();

            // Inisialisasi nilai temporary
            double totalHarga; 
            int diskonVal;

            // Buat kondisi untuk menentukan diskon sesuai IPK mahasiswa
            if (ipkMahasiswa > 3.5 && ipkMahasiswa <= 4.0) {
                diskonVal = 50;
            } else if (ipkMahasiswa > 3.0 && ipkMahasiswa <= 3.5) {
                diskonVal = 35;
            } else if (ipkMahasiswa > 2.5 && ipkMahasiswa <= 3.0) {
                diskonVal = 25;
            } else {
                diskonVal = 10;
            }

            totalHarga = jumlahLembar * 555 * ((double)(100 - diskonVal)/100);
            totalPendapatan += totalHarga;

            // Mencetak harga yang harus dibayar tiap mahasiswa beserta diskon yang didapat
            System.out.printf("%s membayar seharga %.2f dengan diskon sebesar %d",
                              namaMahasiswa, totalHarga, diskonVal);
            System.out.print("%\n");
        }

        // Output ringkasan
        System.out.println("---------------------RINGKASAN DATA---------------------");
        System.out.printf("Hasil pendapatan yang diperoleh Toko Fotokopi dari %d mahasiswa adalah %.2f",
                          jumlahMahasiswa, totalPendapatan);
        
        sc.close();
    }
}
