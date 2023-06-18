package Lab04;

import java.io.*; 
import java.util.StringTokenizer; 
 
public class Diskonpedia { 
    private static InputReader in = new InputReader(System.in); 
    private static PrintWriter out = new PrintWriter(System.out); 
    private static Barang[] listBarang; 
    private static Pembeli[] listPembeli; 
 
    /* 
     * Method utama program 
     */ 
    public static void main(String[] args) { 
        int jumlahBarang = in.nextInt();            // Inisiasi Barang 
        listBarang = new Barang[jumlahBarang]; 
 
        for (int i = 0; i < jumlahBarang; i++) { 
            String namaBarang = in.next(); 
            long harga = in.nextLong(); 
            int stok = in.nextInt(); 
            listBarang[i] = new Barang(namaBarang, harga, stok); 
        } 
 
        int jumlahPembeli = in.nextInt();           // Inisiasi Pembeli 
        listPembeli = new Pembeli[jumlahPembeli]; 
 
        for (int i = 0; i < jumlahPembeli; i++) { 
            String namaPembeli = in.next(); 
            long jumlahUang = in.nextLong(); 
            listPembeli[i] = new Pembeli(namaPembeli, jumlahUang); 
        } 
 
        // Jalanin Query 
        int jumlahPerintah = in.nextInt(); 
 
        for (int i = 0; i < jumlahPerintah; i++) { 
            String perintah = in.next(); 
            switch (perintah) { 
                case "PESAN" -> { 
                    String namaPembeli = in.next(); 
                    String namaBarang = in.next(); 
                    int jumlah = in.nextInt(); 
                    pesan(namaPembeli, namaBarang, jumlah); 
                    break; 
                } 
                case "BAYAR" -> { 
                    String namaPembeli = in.next(); 
                    bayar(namaPembeli); 
                    break; 
                } 
                case "DISKON" -> { 
                    String namaPembeli = in.next(); 
                    diskon(namaPembeli); 
                    break; 
                } 
                case "RESTOCK" -> { 
                    String namaBarang = in.next(); 
                    int jumlah = in.nextInt(); 
                    restock(namaBarang, jumlah); 
                    break; 
                } 
            } 
        } 
        out.close(); 
    } 
 
    /* 
     * Method untuk perintah PESAN 
     */ 
    public static void pesan(String namaPembeli, String namaBarang, int jumlah) { 
        // Cari barang dan pembeli 
        Barang barang = cariBarang(namaBarang); 
        Pembeli pembeli = cariPembeli(namaPembeli); 
 
        // Jalanin method tambahPesanan 
        out.println(pembeli.tambahPesanan(barang, jumlah)); 
    } 
 
    /* 
     * Method untuk perintah BAYAR 
     */ 
    public static void bayar(String namaPembeli) { 
        // Cari pembeli 
        Pembeli pembeli = cariPembeli(namaPembeli); 
 
        // Ambil total pembayaran 
        long totalPembayaran = pembeli.totalHargaPesanan(); 
        out.printf("%s berhasil melakukan pembelian barang dan pembayaran!\n", 
                   pembeli.getNama(), totalPembayaran); 
 
        // Cetak detail pembayaran (detail setiap pesanan) 
        out.println("########## Detail Pembayaran ##########"); 
        Pesanan[] listPesanan = pembeli.getListPesanan(); 
        for (Pesanan pesanan : listPesanan) { 
            if (pesanan == null) break; 
 
            out.printf("%s: %d * %d = %d\n", pesanan.getBarang().getNama(), pesanan.getBarang().getHarga(), 
                       pesanan.getJumlahBarang(), pesanan.totalHarga()); 
        } 
        out.println("_______________________________________"); 
 
        // Cari dan cetak total harga, diskon yang didapat, dan harga yang perlu dibayar 
        out.printf("Total harga = %d\n",totalPembayaran); 
        long diskon = hitungDiskon(pembeli) * totalPembayaran / 100; 
        out.printf("Diskon = (%d)\n",diskon); 
        long hargaBayar = totalPembayaran - diskon; 
        out.printf("Harga bayar = %d\n",hargaBayar); 
 
        // Cari sisa uang dari pembeli 
        long sisaUang = pembeli.getJumlahUang() - hargaBayar; 
        pembeli.setJumlahUang(sisaUang); 
        out.printf("Sisa uang = %d\n", sisaUang); 
        out.println("#######################################"); 
 
        // Reset pesanan pembeli 
        pembeli.resetPesanan(); 
    } 
 
    /* 
     * Method untuk perintah RESTOCK 
     */ 
    public static void restock(String namaBarang, int jumlah) { 
        //Cari barangnya 
        Barang barang = cariBarang(namaBarang); 
 
        // Cek apakah stok yg dimasukkan valid (> 0 atau tidak) 
        if (jumlah <= 0) out.println("Maaf, stok tambahan yang dimasukkan tidak valid"); 
        else { 
            // Tambahin stok barang 
            barang.setStok(barang.getStok() + jumlah); 
            out.printf("Berhasil menambahkan stok barang %s. Sisa stok sekarang = %d\n", 
                    namaBarang, barang.getStok()); 
        } 
    } 
 
    /* 
     * Method untuk perintah DISKON. 
     */ 
    public static void diskon(String namaPembeli) { 
        // Cari pembeli 
        Pembeli pembeli = cariPembeli(namaPembeli); 
 
        // Hitung diskon yang didapat 
        int diskon = hitungDiskon(pembeli); 
        out.printf("%s mendapatkan diskon sebesar %d%%\n", namaPembeli, diskon); 
    } 
 
    /* 
     * Method untuk mencari persentase diskon yang didapat oleh pembeli. 
     * Method ini mengembalikan persentase diskon yang didapat. 
     */ 
    public static int hitungDiskon(Pembeli pembeli) { 
        return pembeli.totalJumlahPesanan(); 
    } 
 
    /* 
     * Method untuk mencari Barang berdasarkan nama 
     */ 
    public static Barang cariBarang(String nama) { 
        for (Barang barang : listBarang) { 
            if (barang.getNama().equals(nama)) 
                return barang; 
        } 
        return null; 
    } 
 
    /* 
     * Method untuk mencari Pembeli berdasarkan nama 
     */ 
    public static Pembeli cariPembeli(String nama) { 
        for (Pembeli pembeli: listPembeli) { 
            if (pembeli.getNama().equals(nama)) 
                return pembeli; 
        } 
        return null; 
    } 
 
    // taken from https://codeforces.com/submissions/Petr 
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out 
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO) 
    static class InputReader { 
        public BufferedReader reader; 
        public StringTokenizer tokenizer; 
 
        public InputReader(InputStream stream) { 
            reader = new BufferedReader(new InputStreamReader(stream), 32768); 
            tokenizer = null; 
        } 
 
        public String next() { 
            while (tokenizer == null || !tokenizer.hasMoreTokens()) { 
                try { 
                    tokenizer = new StringTokenizer(reader.readLine()); 
                } catch (IOException e) { 
                    throw new RuntimeException(e); 
                } 
            } 
            return tokenizer.nextToken(); 
        } 
 
        public int nextInt() { 
            return Integer.parseInt(next()); 
        } 
 
        public long nextLong() { 
            return Long.parseLong(next()); 
        } 
 
        public double nextDouble() { 
            return Double.parseDouble(next()); 
        } 
    } 
}
