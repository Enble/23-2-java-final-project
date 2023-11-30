package repository;

import domain.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    /**
     * 싱글톤 패턴 관련 코드
     */
    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {
        // admin 계정 기본 생성
        save("admin", "admin");
    }

    public static MemberRepository getInstance() {
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
