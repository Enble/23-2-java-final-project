package repository;

import domain.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private final List<Member> members = new ArrayList<>();

    public void save(Member member) {
        members.add(member);
    }

    public boolean login(String id, String password) {
        for (Member member : members) {
            if (member.getId().equals(id) && member.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
