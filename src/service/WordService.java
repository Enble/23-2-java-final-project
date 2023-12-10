package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordService {
    private static final String WORD_PATH = "src/service/words.txt";
    private static final Random RAND = new Random();
    private static final List<String> words;

    private WordService() {
    }

    static {
        try {
            words = new ArrayList<>(Files.readAllLines(Path.of(WORD_PATH)));
        } catch (IOException e) {
            System.out.println("words.txt 파일을 읽을 수 없습니다.");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    public static String nextWord() {
        return words.get(RAND.nextInt(words.size()));
    }

    public static void addWord(String word) {
        try {
            Files.writeString(Path.of(WORD_PATH), word + "\n", StandardOpenOption.APPEND);
            words.add(word);
        } catch (IOException e) {
            System.out.println("words.txt 파일을 읽을 수 없습니다.");
        }
    }
}
