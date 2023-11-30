package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordService {
    private List<String> words = new ArrayList<>(30_000);
    private Scanner scanner;

    public WordService() {
        try {
            scanner = new Scanner(new FileReader("src/service/words.txt"));
            while(scanner.hasNext()) {
                String word = scanner.nextLine();
                words.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("words.txt 파일이 없습니다.");
            System.exit(0);
        } finally {
            scanner.close();
        }
    }

    public String nextWord() {
        int index = (int) (Math.random() * words.size());
        return words.get(index);
    }

    public void addWord(String word) {
        words.add(word);
    }
}
