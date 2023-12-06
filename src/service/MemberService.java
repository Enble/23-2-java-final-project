package service;

import domain.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    /**
     * 싱글톤 패턴 관련 코드
     */
    private static final MemberService instance = new MemberService();

    private MemberService() {
    }

    public static MemberService getInstance() {
        return instance;
    }

    /**
     * 회원가입, 로그인 관련 코드
     */
    private final List<Member> members = new ArrayList<>();

    public void save(String id, String password) {
        Member member = new Member(id, password);
        members.add(member);
    }

    public void update(Member member) {
        for (Member m : members) {
            if (m.getId().equals(member.getId())) {
                m.setDifficultyType(member.getDifficultyType());
                m.setScore(member.getScore());
                break;
            }
        }
    }

    public boolean isValidMember(String id, String password) {
        for (Member member : members) {
            if (member.getId().equals(id) && member.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<Member> getMembers() {
        return members;
    }
}
