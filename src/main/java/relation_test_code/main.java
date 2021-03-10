package relation_test_code;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.concurrent.locks.Lock;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
          //  em.persist(child1); //영속성 전이 / cascade를 선억하면 연관된 child에 대한 영속성 등록이 필요없음.(연관관계와 상관없음.)
          //  em.persist(child2); //주의: cascade를 ALL로 선언할때는 다른 객체와 연관관계가 없고,
                                 // 확실한 라이프 사이클이 동일할때만 사용해야함 - 완전종속/단일소유( 예: 게시물에 포함된 첨부파일 객체..)
                                  //그렇지 않고, 다른 객체와도 연관관계가 있는 경우, 문제가 발생될수있음.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
