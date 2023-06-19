package Lab05;

public class Manager extends Employee { 
    private double raise; 

    Manager (String nama, double raise) { 
        super(nama); 
        setGaji(2000000); 
        this.raise = raise; 
    } 

    public String toString() { 
        return String.format("Nama: %s\nPengalaman Kerja: %d\nStatus: %b\nNetWorth: Rp%.2f\nJabatan: %s\nRole: Manager\n", 
                             nama, pengalamanKerja, status, netWorth, jabatan); 
    } 


    void nextYear(int n) { 
        int tahunBekerja = getPengalamanKerja(); 

        if (status == true) { 
            for (int i = 1; i <= n; i++) { 
                tahunBekerja++; 
                pembagianJabatanTetap(tahunBekerja); 

                if (status == false) break; 
                super.setGaji(getGaji() * raise); 
                super.setNetWorth(getNetWorth() + getGaji());  
            } 
        } 
        super.nextYear(n); 
    } 

    void pembagianJabatanTetap (int tahunBekerja) { 
        if (tahunBekerja <= 5) { 
            super.setJabatan("Junior"); 
        } else if (tahunBekerja <= 10) { 
            super.setJabatan("Senior"); 
        } else if (tahunBekerja <= 15) { 
            super.setJabatan("Expert"); 
        } else { 
            status = false; 
            super.setJabatan("Pensiun"); 
            super.setGaji(0); 
        } 
    } 
}
