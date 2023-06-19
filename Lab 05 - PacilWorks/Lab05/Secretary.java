package Lab05;

public class Secretary extends Employee { 
    private double tunjangan; 

    Secretary (String nama, double tunjangan) { 
        super(nama); 
        this.tunjangan = tunjangan; 
    } 

    public String toString() { 
        return String.format("Nama: %s\nPengalaman Kerja: %d\nStatus: %b\nNetWorth: Rp%.2f\nJabatan: %s\nRole: Secretary\nBanyak Tunjangan: %.2f\n", 
                             nama, pengalamanKerja, status, netWorth, jabatan, tunjangan); 
    } 


    void nextYear(int n) { 
        int tahunBekerja = getPengalamanKerja(); 

        if (status == true) { 
            for (int i = 1; i <= n; i++) { 
                tahunBekerja++; 
                pembagianGajiTetap(tahunBekerja); 

                if (status == false) break; 
                super.setNetWorth(getNetWorth() + gaji + tunjangan); 
            } 
        } 
        super.nextYear(n); 
    } 

    void pembagianGajiTetap(int tahunBekerja) { 
        if (tahunBekerja <= 5) { 
            super.setJabatan("Junior"); 
            setGaji(3000000); 
        } else if (tahunBekerja <= 10) { 
            super.setJabatan("Senior"); 
            setGaji(6000000); 
        } else if (tahunBekerja <= 15) { 
            super.setJabatan("Expert"); 
            setGaji(9000000); 
        } else { 
            status = false; 
            super.setJabatan("Pensiun"); 
            setGaji(0); 
        } 
    } 
}
