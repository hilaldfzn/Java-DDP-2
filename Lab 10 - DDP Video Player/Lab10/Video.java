package Lab10;

/** Class Video merupakan parent class dari Movie dan DDPTubeVideo
 *  Terdapat 3 properti dan constructor dengan dua parameter
 */
public class Video { 
    private String title; 
    private int duration; 
    public static int videoAmount; 
    
    // Constructor class video
    public Video(String title, int duration) { 
        this.title = title; 
        this.duration = duration; 
        videoAmount++; 
    } 
    
    // Getter dan override method toString()
    public int getDuration() { 
        return duration; 
    } 
 
    public String getTitle() { 
        return title; 
    } 
 
    @Override 
    public String toString() { 
        return this.getTitle() + " - " + this.getDuration() + " menit"; 
    } 
}
