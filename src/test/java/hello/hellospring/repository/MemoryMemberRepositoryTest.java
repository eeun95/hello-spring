package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 순서대로 안되기 떄문에 테스트 끝날때마다 repo 깔끔하게 지워줘야함
    // 메소드가 실행이 끝날 때마다 동작 callback 메소드라고 보면 됨
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("eeun");

        repository.save(member);

        // get으로 꺼내는 방법은 좋은 방법은 아님 !!
        Member result = repository.findById(member.getId()).get();

        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("eun1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("eun2");
        repository.save(member2);

        Member result = repository.findByName("eun1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("eun1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("eun2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
