package Lab04;

class Pembeli { 
    private String nama; 
    private long jumlahUang; 
    private Pesanan[] listPesanan = new Pesanan[20]; 
    private final int MAKS_JUMLAH_BARANG = 20; 
 
    public Pembeli(String nama, long jumlahUang) { 
        this.nama = nama; 
        this.jumlahUang = jumlahUang; 
    } 
 
    /* 
     * Method yang akan mengembalikan sebuah String yang merupakan pesan hasil dari 
     * query BELI. 
     */ 
    public String tambahPesanan(Barang barang, int jumlah) { 
        if (barang.getStok() >=  jumlah) {                                              // Cek jumlah stok barang  
            if (totalJumlahPesanan() + jumlah <= MAKS_JUMLAH_BARANG) {                  // Cek apakah keranjang cukup buat pesan barang
                if (totalHargaPesanan() + barang.getHarga() * jumlah <= jumlahUang) {    // Cek apakah jumlah uang mencukupi 
                    Pesanan pernahPesan = cariPesanan(barang); 
 
                    // Cek apakah udah pernah dipesan 
                    if (pernahPesan != null) { 
                        pernahPesan.addJumlah(jumlah); 
                    } else { 
                        Pesanan baru = new Pesanan(barang, jumlah); 
 
                        // Masukin pesanan baru ke list 
                        for (int i = 0; i < listPesanan.length; i++) { 
                            if (listPesanan[i] == null) { 
                                listPesanan[i] = baru; 
                                break; 
                            } 
                        } 
                    } 
 
                    barang.setStok(barang.getStok() - jumlah); 
 
                    return String.format("%s berhasil memesan %s sebanyak %d buah", 
                            nama, barang.getNama(),jumlah); 
                } else { 
                    return String.format("Tidak bisa memesan %s sebanyak %d buah. Uang %s tidak cukup", 
                            barang.getNama(), jumlah, nama); 
                } 
            } else { 
                return String.format("Tidak bisa memesan %s sebanyak %d buah. List pesanan %s melebihi kapasitas", 
                        barang.getNama(), jumlah, nama); 
            } 
        } else { 
            return String.format("Tidak bisa memesan %s sebanyak %d buah. Stok barang tidak cukup", 
                    barang.getNama(), jumlah); 
        } 
    } 
 
    /* 
     * Method yang mengembalikan pesanan dari barang yang sudah pernah dipesan. 
     * Jika pesanan tidak ditemukan, akan mengembalikan nilai null. 
     */ 
    public Pesanan cariPesanan(Barang barang){ 
        for (Pesanan pesanan: listPesanan) { 
            if (pesanan==null) break; 
            if (pesanan.getBarang().getNama().equals(barang.getNama())) return pesanan; 
        } 
        return null; 
    } 
 
    /* 
     * Method yang mengembalikan total harga barang yang sudah dipesan 
     */ 
    public long totalHargaPesanan() { 
        long total = 0; 

        for (Pesanan pesanan : listPesanan) { 
            if (pesanan ==null) break; 
            total += pesanan.totalHarga(); 
        } 
        return total; 
    } 
 
    /* 
     * Method yang mengembalikan total jumlah barang yang sudah dipesan 
     */ 
    public int totalJumlahPesanan() { 
        int total = 0; 

        for (Pesanan pesanan : listPesanan) { 
            if (pesanan == null) break; 
            total += pesanan.getJumlahBarang(); 
        } 
        return total; 
    } 
 
    /* 
     * Method untuk mengosongkan list pesanan 
     */ 
    public void resetPesanan() { 
        listPesanan = new Pesanan[20]; 
    } 
 
    public String getNama() { 
        return nama; 
    } 
 
    public long getJumlahUang() { 
        return jumlahUang; 
    } 
    
    public Pesanan[] getListPesanan() { 
        return listPesanan; 
    } 
    
    public void setJumlahUang(long jumlahUang) { 
        this.jumlahUang = jumlahUang; 
    } 
}
