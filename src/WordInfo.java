//101371922
public class WordInfo implements Comparable<WordInfo> {
    private String word;
    private String meaning;

    public WordInfo(String word, String meaning) {
        this.word = word.toLowerCase();
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public int compareTo(WordInfo other) {
        return this.word.compareTo(other.word);
    }
}
