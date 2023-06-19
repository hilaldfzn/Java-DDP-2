package Lab10;

//  Hal yang perlu diimplementasi: 
//  -) Buat class ini dapat menerima object apapun yang berupa Video 
//  -) Atribut videoList 
//  -) Constructor 
//  -) Method insertVideo(), deleteVideo(), getVideoList(), getFirst() 

import java.util.ArrayList; 
 
public class VideoList<T extends Video> { 
    private ArrayList<T> videoList; 
    
    /**
     * Constructor class VideoList.
     * Inisialisasi objek ArrayList untuk menyimpan daftar video.
     */
    public VideoList() { 
        videoList = new ArrayList<>(); 
    } 
    
    /**
     * Method untuk memasukkan video baru ke dalam daftar video.
     * @param newVideo Video baru yang akan dimasukkan.
     * @param isFront Flag untuk menentukan apakah video baru akan dimasukkan di bagian depan daftar atau belakang daftar.
     */
    public void insertVideo(T newVideo, boolean isFront) { 
        if (isFront) { 
            videoList.add(0, newVideo); 
        } else { 
            videoList.add(newVideo); 
        } 
    } 
    
    /**
     * Method untuk mendapatkan daftar video.
     * @return Daftar video.
     */
    public ArrayList<T> getVideoList() { 
        return videoList; 
    } 
    
    /**
     * Method untuk menghapus video pertama dari daftar video.
     * @return Video yang dihapus atau null jika daftar video kosong.
     */
    public T deleteVideo() { 
        if (!videoList.isEmpty()) { 
            return videoList.remove(0); 
        } 
        return null; 
    } 
    
    /**
     * Method untuk mendapatkan video pertama dari daftar video.
     * @return Video pertama atau null jika daftar video kosong.
     */
    public T getFirst() { 
        if (!videoList.isEmpty()) { 
            return videoList.get(0); 
        } 
        return null; 
    } 
    
    /**
     * Method untuk memutar video pertama dari daftar video.
     * Video pertama akan dipindahkan ke posisi terakhir dalam daftar.
     * @return Video yang diputar atau null jika daftar video kosong.
     */
    public T playVideo() { 
        if (!videoList.isEmpty()) { 
            T video = videoList.remove(0); 
            videoList.add(video); 
            return video; 
        } 
        return null; 
    } 
}
