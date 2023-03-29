//101371922
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        String filepath = "C:\\JAVA\\semester4\\assign01\\src\\wordlist.txt";
        dictionary.loadWordsFromFile(filepath);
        boolean exit = false;
        while (!exit) {
            System.out.println("Please select an option:");
            System.out.println("1: Add new word");
            System.out.println("2: Delete word");
            System.out.println("3: Get meaning");
            System.out.println("4: Dictionary list");
            System.out.println("5: Spell check a text file");
            System.out.println("6: Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Enter word:");
                    String wordToAdd = scanner.nextLine().toLowerCase();
                    System.out.println("Enter meaning:");
                    String meaningToAdd = scanner.nextLine();
                    boolean added = dictionary.add(wordToAdd, meaningToAdd);
                    if (added) {
                        System.out.println("Word added successfully.");
                    } else {
                        System.out.println("Failed to add word. Word already exists in dictionary.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter word:");
                    String wordToDelete = scanner.nextLine().toLowerCase();
                    boolean deleted = dictionary.delete(wordToDelete);
                    if (deleted) {
                        System.out.println("Word deleted successfully.");
                    } else {
                        System.out.println("Failed to delete word. Word does not exist in dictionary.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter word:");
                    String wordToGetMeaning = scanner.nextLine().toLowerCase();
                    String meaning = dictionary.getMeaning(wordToGetMeaning);
                    if (meaning != null) {
                        System.out.println("Meaning: " + meaning);
                    } else {
                        System.out.println("Word not found in dictionary.");
                    }
                }
                case 4 -> {
                    String wordList = dictionary.printWordList();
                    System.out.println(wordList);
                }
                case 5 -> {
                    System.out.println("Enter text file path:");
                    String filePath = scanner.nextLine();
                    File file = new File(filePath);
                    try {
                        Scanner fileScanner = new Scanner(file);
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] words = line.split("[ ,.]");
                            for (String word : words) {
                                if (!dictionary.exists(word.toLowerCase())) {
                                    System.out.println(word);
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    }
                }
                case 6 -> exit = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
