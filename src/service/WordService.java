package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 단어 관리 서비스
public class WordService {
    // words.txt 파일의 경로
    private static final Path WORD_PATH = Path.of("src/service/words.txt");
    // 랜덤 객체
    private static final Random RAND = new Random();
    // 단어 리스트
    private static final List<String> words;

    // Static 클래스로 만들어서 외부에서 인스턴스를 생성하지 못하도록 한다.
    private WordService() {
    }

    // static 초기화 블록을 이용해서 words.txt 파일을 읽어서 words 리스트에 저장한다.
    static {
        try {
            // 파일이 없으면 파일을 생성한다.
            if (!Files.exists(WORD_PATH)) {
                Files.createFile(WORD_PATH);
            }
            // 파일의 내용을 읽어서 words 리스트에 저장한다.
            words = new ArrayList<>(Files.readAllLines(WORD_PATH));
        } catch (IOException e) {
            System.out.println("words.txt 파일을 읽을 수 없습니다.");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    // words 리스트에서 랜덤하게 단어를 하나 가져온다.
    public static String nextWord() {
        return words.get(RAND.nextInt(words.size()));
    }

    // 파일과 리스트에 단어를 추가한다.
    public static void addWord(String word) {
        try {
            Files.writeString(WORD_PATH, word + "\n", StandardOpenOption.APPEND);
            words.add(word);
        } catch (IOException e) {
            System.out.println("words.txt 파일을 읽을 수 없습니다.");
        }
    }
}
