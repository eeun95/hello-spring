package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    // Jpa를 쓰려면 EntityManager를 주입받아야 한다
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;

        //JPA가 insert문 만들어서 집어넣어줌
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;

        ctrl T or command option N 해서 인라인으로 만들어줄 수 있다 참고
         */

        // Entity를 대상으로 쿼리를 날림 객체 자체를 select 하여 조회한다
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
