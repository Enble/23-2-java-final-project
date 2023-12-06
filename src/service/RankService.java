package service;

import domain.Member;
import domain.RankMember;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import view.rank.RankDifficultyPanel;

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

    public void updateRankBoard(Member member) {
        RankMember rankMember = new RankMember(member.getId(), member.getScore());
        switch (member.getDifficultyType()) {
            case EASY:
                easyRankBoard.add(rankMember);
                easyRankBoard.sort(compare);
                break;
            case NORMAL:
                normalRankBoard.add(rankMember);
                normalRankBoard.sort(compare);
                break;
            case HARD:
                hardRankBoard.add(rankMember);
                hardRankBoard.sort(compare);
                break;
            case FIRE_OCEAN:
                fireOceanRankBoard.add(rankMember);
                fireOceanRankBoard.sort(compare);
                break;
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

