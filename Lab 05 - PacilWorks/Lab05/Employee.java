package Lab05;

public class Employee { 
    protected String nama; 
    protected int pengalamanKerja;  
    protected boolean status; 
    protected double netWorth; 
    protected String jabatan;  
    protected double gaji; 

    Employee(String nama) { 
        this.nama = nama; 
        this.pengalamanKerja = 0; 
        this.status = true; 
        this.jabatan = "Junior"; 
        this.netWorth = 0.0; 
    } 

    void nextYear(int tahun) { 
        if (status == false && pengalamanKerja > 15) return; 
        this.pengalamanKerja += tahun; 
        if (pengalamanKerja > 15) pengalamanKerja = 16; 
    } 

    public String getNama() { 
        return nama; 
    } 

    public void setJabatan(String jabatan){ 
        this.jabatan = jabatan; 
    } 

    public void setNama(String nama) { 
        this.nama = nama; 
    } 

    public int getPengalamanKerja() { 
        return pengalamanKerja; 
    } 

    public double getNetWorth() { 
        return netWorth; 
    } 

    public void setNetWorth(double n) { 
        netWorth = n; 
    } 

    public void setGaji(double gaji) { 
        this.gaji = gaji; 
    } 

    public double getGaji() { 
        return gaji; 
    } 
}
