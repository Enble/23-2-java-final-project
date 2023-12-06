package service;

import domain.Member;
import domain.RankMember;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RankService {
    /**
     * 싱글톤 패턴 관련 코드
     */
    private static final RankService instance = new RankService();
    /**
     * 랭킹 관련 코드
     */
    // 랭킹 리스트
    private final List<RankMember> easyRankBoard = new CopyOnWriteArrayList<>();
    private final List<RankMember> normalRankBoard = new CopyOnWriteArrayList<>();
    private final List<RankMember> hardRankBoard = new CopyOnWriteArrayList<>();
    private final List<RankMember> fireOceanRankBoard = new CopyOnWriteArrayList<>();
    private RankService() {
    }

    // 랭킹 정렬
    Comparator<RankMember> compare = new Comparator<>() {
        @Override
        public int compare(RankMember o1, RankMember o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    };

    public static RankService getInstance() {
        return instance;
    }

    // 랭킹 업데이트
    public void updateRankBoard(Member member) {
        RankMember rankMember = new RankMember(member.getId(), member.getScore());
        // 난이도별로 랭킹 업데이트
        switch (member.getDifficultyType()) {
            case EASY:
                checkAndUpdateRank(easyRankBoard, rankMember);
                easyRankBoard.sort(compare);
                break;
            case NORMAL:
                checkAndUpdateRank(normalRankBoard, rankMember);
                normalRankBoard.sort(compare);
                break;
            case HARD:
                checkAndUpdateRank(hardRankBoard, rankMember);
                hardRankBoard.sort(compare);
                break;
            case FIRE_OCEAN:
                checkAndUpdateRank(fireOceanRankBoard, rankMember);
                fireOceanRankBoard.sort(compare);
                break;
        }
    }

    private void checkAndUpdateRank(List<RankMember> rankBoard, RankMember rankMember) {
        // 랭킹 리스트에 있는지 확인
        // 있으면 점수 비교해서 더 높은 점수면 업데이트
        for (RankMember member : rankBoard) {
            if (member.getId().equals(rankMember.getId())) {
                if (member.getScore().compareTo(rankMember.getScore()) < 0) {
                    rankBoard.remove(member);
                    rankBoard.add(rankMember);
                }
                return;
            }
        }

        // 랭킹 리스트에 없으면 추가
        rankBoard.add(rankMember);
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

