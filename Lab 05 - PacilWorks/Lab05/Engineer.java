package Lab05;

public class Engineer extends Employee { 
    private int banyakSideJobs;  
 
    Engineer (String nama, int banyakSideJobs) { 
        super(nama); 
        setGaji(4000000); 
        this.banyakSideJobs = banyakSideJobs; 
    } 
   
    public String toString() { 
        return String.format("Nama: %s\nPengalaman Kerja: %d\nStatus: %b\nNetWorth: Rp%.2f\nJabatan: %s\nRole: Engineer\nBanyak SideJobs: %d\n", 
                             nama, pengalamanKerja, status, netWorth, jabatan, banyakSideJobs); 
    } 
     
    void nextYear(int n) { 
        int tahunBekerja = getPengalamanKerja(); 

        if (status == true) {  
            for (int i = 1; i <= n; i++) { 
                tahunBekerja++; 
                pembagianGajiTetap(tahunBekerja); 

                if (status == false) break; 
                super.setGaji(gaji); 
                super.setNetWorth(getNetWorth() + gaji + banyakSideJobs * 500000); 
            } 
        } 
        super.nextYear(n); 
    } 
   
    void pembagianGajiTetap(int tahunBekerja) { 
        if (tahunBekerja <= 5) { 
            super.setJabatan("Junior"); 
            setGaji(4000000); 
        } else if (tahunBekerja <= 10) { 
            super.setJabatan("Senior"); 
            setGaji(8000000); 
        } else if (tahunBekerja <= 15) { 
            super.setJabatan("Expert"); 
            setGaji(12000000); 
        } else { 
            status = false; 
            super.setJabatan("Pensiun"); 
            setGaji(0); 
        } 
    } 
}
