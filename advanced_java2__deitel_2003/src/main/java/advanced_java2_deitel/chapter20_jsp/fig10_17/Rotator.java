package advanced_java2_deitel.chapter20_jsp.fig10_17;

// Fig. 10.17: Rotator.java
// A JavaBean that rotates advertisements.
public class Rotator {
    private String images[] = {"images/jhtp3.jpg",
            "images/xmlhtp1.jpg", "images/ebechtp1.jpg",
            "images/iw3htp1.jpg", "images/cpphtp3.jpg"};

    private String links[] = {
            "http://www.amazon.com/exec/obidos/ASIN/0130125075/deitelassociatin",
            "http://www.amazon.com/exec/obidos/ASIN/0130284173/deitelassociatin",
            "http://www.amazon.com/exec/obidos/ASIN/013028419X/deitelassociatin",
            "http://www.amazon.com/exec/obidos/ASIN/0130161438/deitelassociatin",
            "http://www.amazon.com/exec/obidos/ASIN/0130895717/deitelassociatin"};

    private int selectedIndex = 0;

    // returns image file name for current ad
    public String getImage() {
        return images[selectedIndex];
    }

    // returns the URL for ad's corresponding Web site
    public String getLink() {
        return links[selectedIndex];
    }

    // update selectedIndex so next calls to getImage and getLink return a different advertisement
    public void nextAd() {
        selectedIndex = (selectedIndex + 1) % images.length;
    }

}
