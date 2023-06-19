package Lab10;

/** Class Movie ini merupakan child class dari class Video
 *  Terdapat dua properti dan constructor dengan empat parameter
 **/
public class Movie extends Video {
    private String director; 
    private double rating; 
    
    // Constructor class Movie
    public Movie(String title, int duration, String director, double rating) { 
        super(title, duration); 
        this.director = director; 
        this.rating = rating; 
    } 
    
    /**
     * Method untuk Mengembalikan seberapa bagus rating suatu Movies dalam bentuk String.
     **/
    private String isRatingBagus() { 
        if (rating <= 1.0) return "sangat rendah"; 
        if (rating <= 2.0) return "rendah"; 
        if (rating <= 3.0) return "cukup bagus"; 
        if (rating <= 4.0) return "bagus"; 
        return "sangat bagus"; 
    } 
 
    @Override 
    public String toString() { 
        return super.toString() + ". Disutradarai oleh " + this.director + " dan memiliki rating " + isRatingBagus(); 
    } 
}
