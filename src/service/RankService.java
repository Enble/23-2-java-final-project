package service;

import domain.Member;
import domain.RankMember;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 랭킹 관련 기능을 담당하는 서비스
public class RankService {
    /**
     * 싱글톤 패턴 관련 코드
     */
    private static final RankService instance = new RankService();

    private RankService() {
        loadRankBoard(easyRankBoardFilePath, easyRankBoard);
        loadRankBoard(normalRankBoardFilePath, normalRankBoard);
        loadRankBoard(hardRankBoardFilePath, hardRankBoard);
        loadRankBoard(fireOceanRankBoardFilePath, fireOceanRankBoard);
    }

    // 싱글톤 객체 반환
    public static RankService getInstance() {
        return instance;
    }

    /**
     * 랭킹 관련 코드
     */
    // 랭크 보드 파일
    private final Path easyRankBoardFilePath = Path.of("src/service/easy_rank_board.txt");
    private final Path normalRankBoardFilePath = Path.of("src/service/normal_rank_board.txt");
    private final Path hardRankBoardFilePath = Path.of("src/service/hard_rank_board.txt");
    private final Path fireOceanRankBoardFilePath = Path.of("src/service/fire_ocean_rank_board.txt");

    // 랭킹 리스트
    private final List<RankMember> easyRankBoard = new ArrayList<>();
    private final List<RankMember> normalRankBoard = new ArrayList<>();
    private final List<RankMember> hardRankBoard = new ArrayList<>();
    private final List<RankMember> fireOceanRankBoard = new ArrayList<>();

    // 랭킹 파일 읽은 후 랭킹 리스트에 저장
    private void loadRankBoard(Path filePath, List<RankMember> rankBoard) {
        try {
            // 파일이 없으면 파일을 생성한다.
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            // 파일 읽기
            List<String> strings = Files.readAllLines(filePath);
            // 랭킹 리스트에 저장
            for (String string : strings) {
                rankBoard.add(RankMember.toRankMember(string));
            }
        } catch (IOException e) {
            System.out.println("랭킹 파일 " + filePath + "을 읽을 수 없습니다.");
            System.exit(0);
            throw new RuntimeException(e);
        }
        rankBoard.sort(compare);
    }

    // 랭킹 정렬을 위한 Comparator
    Comparator<RankMember> compare = new Comparator<>() {
        @Override
        public int compare(RankMember o1, RankMember o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    };

    // 랭킹 업데이트
    public void updateRankBoard(Member member) {
        RankMember rankMember = new RankMember(member.getId(), member.getScore());
        // 난이도별로 랭킹 업데이트
        switch (member.getDifficultyType()) {
            case EASY:
                checkAndUpdateRank(easyRankBoardFilePath, easyRankBoard, rankMember);
                break;
            case NORMAL:
                checkAndUpdateRank(normalRankBoardFilePath, normalRankBoard, rankMember);
                break;
            case HARD:
                checkAndUpdateRank(hardRankBoardFilePath, hardRankBoard, rankMember);
                break;
            case FIRE_OCEAN:
                checkAndUpdateRank(fireOceanRankBoardFilePath, fireOceanRankBoard, rankMember);
                break;
        }
    }

    // 랭킹 업데이트
    private void checkAndUpdateRank(Path filePath, List<RankMember> rankBoard, RankMember rankMember) {
        // 랭킹 리스트에 있는지 확인
        for (RankMember member : rankBoard) {
            // 있으면 점수 비교해서 더 높은 점수면 업데이트
            if (member.getId().equals(rankMember.getId())) {
                if (member.getScore().compareTo(rankMember.getScore()) < 0) {
                    removeRankMember(filePath, rankBoard, member);
                    addRankMember(filePath, rankBoard, rankMember);
                }
                return;
            }
        }

        // 랭킹 리스트에 없으면 추가
        addRankMember(filePath, rankBoard, rankMember);
    }

    // 랭킹 저장
    // 파일 및 랭킹 리스트에 저장
    private void addRankMember(Path filePath, List<RankMember> rankBoard, RankMember rankMember) {
        try {
            // 파일에 추가
            Files.writeString(filePath, RankMember.toString(rankMember) + "\n", StandardOpenOption.APPEND);
            // 랭킹 리스트에 추가
            rankBoard.add(rankMember);
            // 랭킹 리스트 정렬
            rankBoard.sort(compare);
        } catch (IOException e) {
            System.out.println("랭킹 파일 " + filePath + "에 저장할 수 없습니다.");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    // 랭크 멤버 삭제
    private void removeRankMember(Path filePath, List<RankMember> rankBoard, RankMember rankMember) {
        try {
            // 파일에서 삭제
            String id = rankMember.getId();
            List<String> strings = Files.readAllLines(filePath);
            for (String string : strings) {
                if (string.split(",")[0].equals(id)) {
                    strings.remove(string);
                    break;
                }
            }
            Files.write(filePath, strings);
            // 랭킹 리스트에서 삭제
            rankBoard.remove(rankMember);
            // 랭킹 리스트 정렬
            rankBoard.sort(compare);
        } catch (IOException e) {
            System.out.println("랭킹 파일 " + filePath + "을 읽을 수 없습니다.");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    public List<RankMember> getEasyRankBoard() {
        return easyRankBoard;
    }

    public List<RankMember> getNormalRankBoard() {
        return normalRankBoard;
    }

    public List<RankMember> getHardRankBoard() {
        return hardRankBoard;
    }

    public List<RankMember> getFireOceanRankBoard() {
        return fireOceanRankBoard;
    }
}

