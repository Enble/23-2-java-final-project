package service;

import domain.Member;
import java.util.Comparator;
import java.util.List;
import util.TimeTransfer;

public class RankService {
    /**
     * 싱글톤 패턴 관련 코드
     */
    private static final RankService instance = new RankService();

    private RankService() {
    }

    public static RankService getInstance() {
        return instance;
    }

    /**
     * 랭킹 관련 코드
     */
    private List<Member> rankBoard;

    Comparator<Member> compare = new Comparator<>() {
        @Override
        public int compare(Member o1, Member o2) {
            return o2.getMaxScore().compareTo(o1.getMaxScore());
        }
    };

    public void updateRankBoard() {
        rankBoard = MemberService.getInstance().getMembers();
        rankBoard.sort(compare);
    }

    public List<Member> getRankBoard() {
        return rankBoard;
    }

}

