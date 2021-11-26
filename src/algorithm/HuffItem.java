package algorithm;

public class HuffItem {
    String sym;
    int freq;

    public HuffItem(String sym, int freq) {
        this.sym = sym;
        this.freq = freq;
    }

    public String getSym() {
        return sym;
    }

    public int getFreq() {
        return freq;
    }

}
