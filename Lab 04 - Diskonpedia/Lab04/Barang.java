package Lab04;

class Barang { 
    private long harga; 
    private String nama; 
    private int stok; 
 
    public Barang(String nama, long harga, int stok){ 
        this.harga = harga; 
        this.nama = nama; 
        this.stok = stok; 
    } 
 
    public long getHarga() { 
        return harga; 
    } 
    
    public String getNama() { 
        return nama; 
    } 

    public int getStok() { 
        return stok; 
    } 
 
    public void setStok(int stok) { 
        this.stok = stok; 
    } 
}
