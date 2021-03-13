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

            Member member2 = new Member();
            member2.setUsername("bs2");
            em.persist(member2);

            Team team1 = new Team();
            team1.setName("A");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("B");
            em.persist(team2);

            Team team3 = new Team();
            team3.setName("bs");
            em.persist(team3);

            member.setTeam(team1);
            member2.setTeam(team2);

            UnReleatedMember unrelatedMember = new UnReleatedMember();
            unrelatedMember.setName("bs");
            em.persist(unrelatedMember);

            em.flush();
            em.clear();
            // where은 연관관계가 없는 조인을 진행하면 exception 발생. on사용시 연관관계 없는 엔티티르 사용하여 필터후 조인처리를 할수 있음.
            List<Member> resultList = em.createQuery("select m from Member as m  JOIN UnReleatedMember as u on u.name = m.username", Member.class)
                    .getResultList();
            for (Member m : resultList) {
                if (m == null) {
                    System.out.println("null");
                } else {
                    System.out.println("print" + m.getUsername());
                }
            }
/*
            String query = "select m.username, 'HELLO', true From Member m";
            List<Object[]> resultList1 = em.createQuery(query)
                    .getResultList();

            for (Object[] objects : resultList1) {
                System.out.println("name" + (String)objects[0]);
                System.out.println("hello: " + (String)objects[1]);
                System.out.println("bool" + (Boolean)objects[2]);
            }

 */
            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                            "     when m.age >= 60 then '경로요금' " +
                            "     else '일반요금' " +
                            "end " +
                            "from Member m";
            List<String> resultList1 = em.createQuery(query, String.class)
                    .getResultList();
            for (String s : resultList1) {
                System.out.println(s);
            }
           /* List<TeamMember> resultList = em.createQuery("select new jpql.TeamMember(m,t) from Member as m LEFT JOIN Team as t on m.username = t.name", TeamMember.class)
                    .getResultList();

            for (TeamMember m : resultList) {
                if (m.getMember() == null) {
                    System.out.println("null");
                } else {
                    System.out.println("print" + m.getMember().getUsername());
                }
            }*/
            /*
            TypedQuery<Member> select_m_from_member_m = em.createQuery("select m from Member m", Member.class);
            List<Member> resjultList = select_m_from_member_m.getResultList(); //결과가 없으면 빈리스트 반환(null exception 발생하지 않음.)
            //Member m = select_m_from_member_m.getSingleResult(); //하나인 경우. (결과가 정확히 하나여야함. 그외에는 exception 발생함.)

            em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult(); //이름 기준 파라미터 바인딩 ( 위치기준 바인딩은 사용하지 않는게 좋음. 나중에 끼워넣고 하면 순서가 바뀔수있음.)


            Query query = em.createQuery("select m.username, m.age from Member m");
            */
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
    }

}
