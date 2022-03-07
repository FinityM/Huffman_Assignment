package algorithm;

public class HuffItem {

    // Declare variables for the letters on the text file and the frequencies
    String sym;
    int freq;

    // Add a constructor for the letters and the frequencies
    public HuffItem(String sym, int freq) {
        this.sym = sym;
        this.freq = freq;
    }

    // Get method for the letters
    public String getSym() {
        return sym;
    }

    // Get methods for the frequencies
    public int getFreq() {
        return freq;
    }

}
