package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //create(emf,1L,"name");
        //select(emf,1L);
        //update(emf,1L,"JPA1");
        //jpql(emf,"select m from Member as m");
        //delete(emf,1L);

        /*EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("test");

            // 영속
            em.persist(member);
            System.out.println("persist");

            // 준영속 ( member를 영속성 컨텍스트에서 분리)
            em.detach(member);
            System.out.println("detach");

            // 삭제
            em.remove(member);
            System.out.println("remove");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }*/

        emf.close();

    }

    /**
     * 생성
     */
    public static void create(EntityManagerFactory emf,Long id, String name) {

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = new Member();
            member.setId(id);
            member.setName(name);
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * 조회
     */
    public static void select(EntityManagerFactory emf,Long id) {

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member findMember = em.find(Member.class, id);
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * 삭제
     */
    public static void delete(EntityManagerFactory emf,Long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member findMember = em.find(Member.class, id);
            em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * 수정
     */
    public static void update(EntityManagerFactory emf,Long id, String name) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member findMember = em.find(Member.class, id);
            findMember.setName(name);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * JPQL
     */
    public static void jpql(EntityManagerFactory emf, String Query) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            List<Member> result = em.createQuery(Query, Member.class)
                    //.setFirstResult(0)  //  페이징
                    //.setMaxResults(1)   //  "
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
