package jpql;

import javax.persistence.*;
import java.util.List;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("bs");
            em.persist(member);

            TypedQuery<Member> select_m_from_member_m = em.createQuery("select m from Member m", Member.class);
            List<Member> resjultList = select_m_from_member_m.getResultList(); //결과가 없으면 빈리스트 반환(null exception 발생하지 않음.)
            //Member m = select_m_from_member_m.getSingleResult(); //하나인 경우. (결과가 정확히 하나여야함. 그외에는 exception 발생함.)

            em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult(); //이름 기준 파라미터 바인딩 ( 위치기준 바인딩은 사용하지 않는게 좋음. 나중에 끼워넣고 하면 순서가 바뀔수있음.)


            Query query = em.createQuery("select m.username, m.age from Member m");

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
    }

}
