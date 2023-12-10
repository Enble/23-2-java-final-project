package service;

import domain.Member;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    /**
     * 싱글톤 패턴 관련 코드
     */
    private static final MemberService instance = new MemberService();

    private MemberService() {
        try {
            // 회원 파일 읽기
            List<String> strings = Files.readAllLines(memberFilePath);
            // 회원 리스트에 저장
            for (String string : strings) {
                members.add(Member.toMember(string));
            }
        } catch (IOException e) {
            System.out.println("회원 파일 " + memberFilePath + "을 읽을 수 없습니다.");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    public static MemberService getInstance() {
        return instance;
    }

    /**
     * 회원가입, 로그인 관련 코드
     */
    // 회원 파일
    private final Path memberFilePath = Path.of("src/service/member.txt");
    // 회원 리스트
    private final List<Member> members = new ArrayList<>();

    public void save(String id, String password) {
        try {
            Member member = new Member(id, password);
            // 회원 파일에 회원 정보 저장
            Files.writeString(memberFilePath, Member.toString(member) + "\n");
            // 회원 리스트에 회원 정보 저장
            members.add(member);
        } catch (IOException e) {
            System.out.println("회원 파일 " + memberFilePath + "에 저장할 수 없습니다.");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    // 회원 정보 수정
    public void update(Member member) {
        for (Member m : members) {
            // 아이디가 동일한 경우
            if (m.getId().equals(member.getId())) {
                m.setDifficultyType(member.getDifficultyType());
                m.setScore(member.getScore());
                break;
            }
        }
    }

    // 로그인 검증
    public boolean isValidMember(String id, String password) {
        for (Member member : members) {
            // 아이디와 비밀번호가 동일한 경우
            if (member.getId().equals(id) && member.getPassword().equals(password)) {
                return true;
            }
        }
        // 아이디와 비밀번호가 동일한 회원이 없는 경우
        return false;
    }
}
