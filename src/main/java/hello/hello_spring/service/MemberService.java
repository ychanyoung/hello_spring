package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    /**
     *
     * 회원가입
     */

    public Long join(Member member){
        //같은 이름 X, 비번 양식에 맞는가?


        validateDuplicateMember(member);
        validPW(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validPW(Member member){
        String pw = member.getPw();
        for(char ch : pw.toCharArray()){
            if(!(ch <= 126 && ch >= 33)) throw new IllegalStateException("잘못된 비밀번호 입니다.");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public List<Member> findMembers(){
        /**
         * 전체 회원 조회
         */
        return memberRepository.findAll();
        
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);

    }

}
