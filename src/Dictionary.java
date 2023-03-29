//101371922
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Dictionary {
    private WordInfo[] words;
    private int count;

    public Dictionary() {
        this.words = new WordInfo[1500];
        this.count = 0;
        String filePath = "C:\\JAVA\\semester4\\assign01\\src\\wordlist.txt"; // replace  file path
        File file = new File(filePath);

    }

    public boolean add(String word, String meaning) {
        if (exists(word.toLowerCase())) {
            return false;
        }
        if (this.count >= this.words.length) {
            return false;
        }
        this.words[count] = new WordInfo(word.toLowerCase(), meaning);
        this.count++;
        Arrays.sort(this.words, 0, this.count);
        return true;
    }

    public boolean delete(String word) {
        int index = binarySearch(word.toLowerCase());
        if (index == -1) {
            return false;
        }
        for (int i = index; i < this.count - 1; i++) {
            this.words[i] = this.words[i + 1];
        }
        this.count--;
        this.words[this.count] = null;
        return true;
    }

    public boolean exists(String word) {
        return binarySearch(word.toLowerCase()) != -1;
    }

    public String getMeaning(String word) {
        int index = binarySearch(word.toLowerCase());
        if (index == -1) {
            return "Word not found";
        }
        return this.words[index].getMeaning();
    }

    public int getCount() {
        return this.count;
    }

    public String printWordList() {
        StringBuilder wordList = new StringBuilder();
        for (int i = 0; i < this.count; i++) {
            wordList.append(this.words[i].getWord()).append("\n");
        }
        return wordList.toString();
    }

    public void printDictionary() {
        for (int i = 0; i < this.count; i++) {
            System.out.println(this.words[i].getWord() + ": " + this.words[i].getMeaning());
        }
    }

    private int binarySearch(String word) {
        int left = 0;
        int right = this.count - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = word.compareTo(this.words[mid].getWord());
            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public void loadWordsFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine() && this.count < this.words.length) {
                String word = scanner.nextLine().trim().toLowerCase();
                this.words[this.count] = new WordInfo(word, "Undefined word");
                this.count++;
            }
            Arrays.sort(this.words, 0, this.count);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading wordlist file.");
        }
    }
}
