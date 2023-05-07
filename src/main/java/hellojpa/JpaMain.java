package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        // ** Transaction START **
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("영희");

//            Member findMember = em.find(Member.class, 1L); // 조회
//            findMember.setName("소라");  // 기존의 1번이 "소라"로 변경됨

              List<Member> result = em.createQuery("select m from Member as m", Member.class)
                      .setFirstResult(5) // 페이징 처리
                      .setMaxResults(8) // 페이징 처리
                      .getResultList();
              for(Member member : result) {
                  System.out.println("member.name: "+member.getName());
              }

//            em.persist(member);

            tx.commit();
            // ** Transaction END **
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
