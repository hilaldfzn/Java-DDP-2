package Lab10;

/** Class DDPTubeVideo merupakan child class dari Video
 *  Hanya terdapat satu properti dan constructor dengan tiga parameter
 **/
class DDPTubeVideo extends Video { 
    private String creator; 
    
    // Constructor class DDPTubeVideo
    public DDPTubeVideo(String title, int duration, String creator) { 
        super(title, duration); 
        this.creator = creator; 
    } 
 
    @Override 
    public String toString() { 
        return super.toString() + ". Dibuat oleh " + this.creator; 
    } 
}
