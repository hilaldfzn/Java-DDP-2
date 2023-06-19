package Lab10;

import java.util.NoSuchElementException; 
import java.util.Scanner; 

/**
 * Class VideoPlayer untuk pengelolaan video dengan fitur tambah video, putar video selanjutnya, hapus
 * video, dan lihat daftar video. Menyimpan dua objek VideoList, yaitu movieList dan ddpTubeVideoList.
 */
public class VideoPlayer { 
    private static VideoList<Movie> movieList; 
    private static VideoList<DDPTubeVideo> ddpTubeVideoList; 
    private static final Scanner in = new Scanner(System.in); 

    /**
     * Main method untuk menjalankan program VideoPlayer.
     * Melakukan loop menu hingga pengguna memilih untuk keluar.
     */

    public static void main(String[] args) { 
        boolean isFinish = false; 
 
        movieList = new VideoList<>(); 
        ddpTubeVideoList = new VideoList<>(); 
 
        System.out.println("Selamat datang di DEDEPE Player!"); 
        while (!isFinish) { 
            try { 
                printMainMenu(); 
                int menu = Integer.parseInt(in.nextLine()); 
                switch (menu) { 
                    case 1 -> addVideo(); 
                    case 2 -> nextVideo(); 
                    case 3 -> deleteVideo(); 
                    case 4 -> printVideoList(); 
                    default -> { 
                        System.out.println("Terima kasih sudah menggunakan DEDEPE Player!"); 
                        isFinish = true; 
                    } 
                } 
            } catch (IllegalArgumentException e) { 
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); 
                System.out.println("WRONG INPUT FORMAT!!\nError code: 401"); 
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); 
            } catch (NoSuchElementException e) { 
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); 
                System.out.println("NO VIDEO FOUND!!\nError code: 402"); 
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); 
            } catch (Exception e) { 
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); 
                System.out.println("UNKNOWN ERROR!!\nError code: 444"); 
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); 
            } 
        } 
    } 
    
    /**
     * Method untuk menambahkan video ke dalam movieList atau DDPTubeVideoList.
     * User diminta untuk memasukkan jenis, judul, durasi, dan atribut khusus sesuai jenis video yang ditambahkan.
     * Jika input tidak valid, akan menghasilkan IllegalArgumentException.
     */
    public static void addVideo() { 
        System.out.println("---------------TAMBAH VIDEO----------------"); 
        System.out.println("Tambah Video Baru"); 
        System.out.print("Masuk di (1) paling depan atau (2) paling belakang: "); 
        int pos = Integer.parseInt(in.nextLine()); 
        System.out.print("Jenis: "); 
        String type = in.nextLine(); 
        System.out.print("Judul: "); 
        String title = in.nextLine(); 
        System.out.print("Durasi (dalam menit): "); 
        int duration = Integer.parseInt(in.nextLine()); 
 
        if (duration <= 0) throw new IllegalArgumentException(); 
 
        if (type.equalsIgnoreCase("Movie")) { 
            System.out.print("Sutradara: "); 
            String director = in.nextLine(); 
            System.out.print("Rating: "); 
            Double rating = Double.valueOf(in.nextLine()); 
 
            if (rating > 5.0) throw new IllegalArgumentException(); 
 
            Movie newVideo = new Movie(title,duration,director, rating); 
 
            if (pos == 1) { 
                movieList.insertVideo(newVideo, true); 
            } else { 
                movieList.insertVideo(newVideo, false); 
            } 
        } 
 
        else if (type.equalsIgnoreCase("DDPTube")) { 
            System.out.print("Creator: "); 
            String creator = in.nextLine(); 
 
            DDPTubeVideo newVideo = new DDPTubeVideo(title,duration,creator); 
            if (pos == 1) { 
                ddpTubeVideoList.insertVideo(newVideo, true); 
            } else { 
                ddpTubeVideoList.insertVideo(newVideo, false); 
            }
        } else { 
            throw new IllegalArgumentException(); 
        } 
    } 
    
    /**
     * Method untuk memutar video selanjutnya dari movieList atau DDPTubeVideoList.
     * User diminta untuk memilih jenis video yang akan diputar.
     * Jika video tidak tersedia, akan menampilkan pesan sesuai jenis video yang diminta.
     * Jika input tidak valid, akan menghasilkan IllegalArgumentException.
     */
    public static void nextVideo() { 
        System.out.print("Putar (1) movie atau (2) DDPTube video selanjutnya? "); 
        int type = Integer.parseInt(in.nextLine()); 
 
        switch (type) { 
            case 1: 
                Video movie = movieList.playVideo(); 
                if (movie == null) { 
                    System.out.println("Tidak ada Movie yang tersedia."); 
                } 
                break; 
            case 2: 
                Video ddpTubeVideo = ddpTubeVideoList.playVideo(); 
                if (ddpTubeVideo == null) { 
                    System.out.println("Tidak ada DDPTube Video yang tersedia."); 
                } 
                break; 
            default: 
                throw new IllegalArgumentException(); 
        } 
    } 

    /**
     * Method untuk menghapus video dari movieList atau DDPTubeVideoList.
     * User diminta untuk memilih jenis video yang akan dihapus.
     * Jika video tidak tersedia, akan menghasilkan NoSuchElementException.
     * Jika input tidak valid, akan menghasilkan NoSuchElementException.
     */
    public static void deleteVideo() { 
        System.out.println("---------------HAPUS VIDEO-----------------"); 
 
        System.out.print("Hapus (1) movie atau (2) DDPTube video? "); 
        int type = Integer.parseInt(in.nextLine()); 
 
        Video video; 
        switch (type) { 
            case 1: 
                video = movieList.deleteVideo(); 
                if (video != null) { 
                    System.out.println(video.getTitle() + " - " + video.getDuration() + " dihapus!"); 
                    Video.videoAmount--; 
                } else { 
                    throw new NoSuchElementException(); 
                } 
                break; 
            case 2: 
                video = ddpTubeVideoList.deleteVideo(); 
                if (video != null) { 
                    System.out.println(video.getTitle() + " - " + video.getDuration() + " dihapus!"); 
                    Video.videoAmount--; 
                } else { 
                    throw new NoSuchElementException(); 
                } 
                break; 
            default: 
                throw new NoSuchElementException(); 
        } 
    } 

    /**
     * Method untuk mencetak daftar video yang ada di movieList dan DDPTubeVideoList.
     * Jika tidak ada video, akan menampilkan pesan sesuai jenis video yang kosong.
     */
    public static void printVideoList() { 
        System.out.println("---------------DAFTAR VIDEO----------------"); 
 
        int counter = 0; 
 
        System.out.println("Movie anda: "); 
        for (Video video : movieList.getVideoList()) { 
            counter++; 
            System.out.println(counter + ". " + video.getTitle() + " - " + video.getDuration()); 
        } 

        if (counter<1) { 
            System.out.println("List movie anda kosong"); 
        } 
 
        System.out.println(); 
        counter = 0; 
        System.out.println("DDPTube Video anda: "); 
        for (Video video : ddpTubeVideoList.getVideoList()) { 
            counter++; 
            System.out.println(counter + ". " + video.getTitle() + " - " + video.getDuration()); 
        } 

        if (counter<1) { 
            System.out.println("List DDPTube video anda kosong"); 
        } 
    } 
    
    /**
     * Method untuk mencetak menu utama program.
     * Menampilkan total jumlah video, video yang sedang diputar dari movieList, dan video yang sedang diputar dari DDPTubeVideoList.
     */
    public static void printMainMenu() { 
        System.out.println("-------------------------------------------"); 
        System.out.println("Total jumlah video sekarang: " + Video.videoAmount); 
        playMovie(); 
        playDdpTubeVideo(); 
        System.out.println("-------------------------------------------"); 
        System.out.print(""" 
                Silakan pilih menu:\s 
                1. Tambah video 
                2. Putar video selanjutnya 
                3. Hapus video 
                4. Lihat daftar video 
                5. Keluar 
                Pilihan:\s"""); 
    } 

    /**
     * Method untuk mencetak video yang sedang diputar dari movieList.
     * Jika tidak ada video, akan menampilkan pesan "Tidak ada".
     */
    public static void playMovie() { 
        Video current = movieList.getFirst(); 

        if (current != null) { 
            System.out.println("Movie sekarang: \n" + current); 
        } else { 
            System.out.println("Movie sekarang: \nTidak ada"); 
        } 
    } 

    /**
     * Method untuk mencetak video yang sedang diputar dari DDPTubeVideoList.
     * Jika tidak ada video, akan menampilkan pesan "Tidak ada".
     */
    public static void playDdpTubeVideo() { 
        Video current = ddpTubeVideoList.getFirst(); 

        if (current != null) { 
            System.out.println("DDPTube video sekarang: \n" + current); 
        } else { 
            System.out.println("DDPTube video sekarang: \nTidak ada"); 
        } 
    } 
}
