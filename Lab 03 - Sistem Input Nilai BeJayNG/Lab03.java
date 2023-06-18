import java.util.Arrays;
import java.util.Scanner;

public class Lab03 {
    private static final Scanner input = new Scanner(System.in);
    private static String[] kumpulanNamaMahasiswa;
    private static String[] kumpulanNamaMatkul;
    private static int[][] score;
    private static int jumlahMatkul;

    public static void main(String[] args) {
        init();

        while (true) {
            printMenu();
            System.out.print("Masukkan pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1 -> menambahMahasiswa();
                case 2 -> menghapusMahasiswa();
                case 3 -> mencetakTabel();
                case 4 -> mencetakNilai();
                case 0 -> {
                    System.out.println("Terima kasih telah menggunakan BeJayNG!");
                    System.exit(0);
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void insertRow(int[] insertedRow, String namaMahasiswa) {
        kumpulanNamaMahasiswa = Arrays.copyOf(kumpulanNamaMahasiswa, kumpulanNamaMahasiswa.length + 1);
        kumpulanNamaMahasiswa[kumpulanNamaMahasiswa.length - 1] = namaMahasiswa;

        int[][] result = Arrays.copyOf(score, score.length + 1);
        result[score.length] = insertedRow;
        score = result;
    }

    static void init() {
        System.out.println("Selamat datang di BeJayNG!");
        System.out.println("==============Initial Input==============");
        System.out.print("Masukkan jumlah mata kuliah: ");
        
        jumlahMatkul = input.nextInt();
        input.nextLine();

        kumpulanNamaMatkul = new String[jumlahMatkul];
        kumpulanNamaMahasiswa = new String[0];
        score = new int[0][jumlahMatkul];

        for (int i = 0; i < jumlahMatkul; i++) {
            System.out.print("Masukkan nama mata kuliah: ");
            kumpulanNamaMatkul[i] = input.nextLine();
        }
    }

    static void printMenu() {
        System.out.println("==============Menu==============");
        System.out.println("[1] Menambah Mahasiswa");
        System.out.println("[2] Menghapus Mahasiswa");
        System.out.println("[3] Mencetak Tabel");
        System.out.println("[4] Mencetak Nilai");
        System.out.println("[0] Keluar");
    }

    static void menambahMahasiswa() {
        System.out.println("==============Menambah Mahasiswa==============");
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMahasiswa = input.nextLine();

        int[] nilaiNilaiMatkul = new int[jumlahMatkul];

        for (int i = 0; i < jumlahMatkul; i++) {
            System.out.print("Masukkan nilai " + kumpulanNamaMatkul[i] + ": ");
            nilaiNilaiMatkul[i] = input.nextInt();
        }
        input.nextLine();

        insertRow(nilaiNilaiMatkul, namaMahasiswa);

        System.out.printf("Nilai mahasiswa bernama %s berhasil diinput ke BeJayNG%n", namaMahasiswa);
    }

    static void menghapusMahasiswa() {
        System.out.println("==============Menghapus Mahasiswa==============");
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMahasiswa = input.nextLine();

        int index = -1;
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i++) {
            if (kumpulanNamaMahasiswa[i].equals(namaMahasiswa)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.printf("Mahasiswa bernama %s tidak ditemukan%n", namaMahasiswa);
            return;
        }

        score = removeElement(index);
        System.out.printf("Mahasiswa bernama %s telah dihapus dari BeJayNG%n", namaMahasiswa);
    }

    public static int[][] removeElement(int index) {
        String[] anotherArray = new String[kumpulanNamaMahasiswa.length - 1];

        System.arraycopy(kumpulanNamaMahasiswa, 0, anotherArray, 0, index);
        System.arraycopy(kumpulanNamaMahasiswa, index + 1, anotherArray, index, kumpulanNamaMahasiswa.length - index - 1);
        kumpulanNamaMahasiswa = anotherArray;

        int[][] result = new int[score.length - 1][jumlahMatkul];

        System.arraycopy(score, 0, result, 0, index);
        System.arraycopy(score, index + 1, result, index, score.length - index - 1);

        return result;
    }

    static void mencetakTabel() {
        System.out.println("==============Mencetak Tabel==============");

        int[][] newOrder = new int[kumpulanNamaMahasiswa.length][jumlahMatkul];

        String[] kumpulanMahasiswa = Arrays.copyOf(kumpulanNamaMahasiswa, kumpulanNamaMahasiswa.length);

        for (int i = 0; i < kumpulanMahasiswa.length; i++) {
            for (int j = 0; j < kumpulanNamaMahasiswa.length; j++) {
                if (kumpulanMahasiswa[i].equals(kumpulanNamaMahasiswa[j])) {
                    newOrder[i] = score[j];
                    break;
                }
            }
        }

        System.out.print("Nama\t\t\t");
        for (String namaMatkul : kumpulanNamaMatkul) {
            System.out.print(namaMatkul + "\t");
        }
        System.out.println();

        for (int i = 0; i < score.length; i++) {
            System.out.print(kumpulanMahasiswa[i] + "\t\t\t");
            for (int j = 0; j < jumlahMatkul; j++) {
                System.out.print(newOrder[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void mencetakNilai() {
        System.out.println("==============Mencetak Nilai==============");
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMahasiswa = input.nextLine();
        System.out.print("Masukkan nama mata kuliah: ");
        String namaMatkul = input.nextLine();

        int indexMahasiswa = -1;
        for (int i = 0; i < kumpulanNamaMahasiswa.length; i++) {
            if (kumpulanNamaMahasiswa[i].equals(namaMahasiswa)) {
                indexMahasiswa = i;
                break;
            }
        }

        int indexMatkul = -1;
        for (int i = 0; i < kumpulanNamaMatkul.length; i++) {
            if (kumpulanNamaMatkul[i].equals(namaMatkul)) {
                indexMatkul = i;
                break;
            }
        }

        System.out.printf("Nilai %s di mata kuliah %s adalah %d%n", namaMahasiswa, namaMatkul, score[indexMahasiswa][indexMatkul]);
    }
}
