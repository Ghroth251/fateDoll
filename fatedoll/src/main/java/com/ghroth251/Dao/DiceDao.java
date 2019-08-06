package com.ghroth251.Dao;

import com.ghroth251.bean.Dice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiceDao extends JpaRepository<Dice,Long>,JpaSpecificationExecutor<Dice> {


 //    private static EntityManager em = null;
//    private static EntityTransaction tx = null;
//    public void saveDiceState(String State){
//        Object[] params = { State};
//        this.myexecuteUpdate("update dice set state=?", params);
//    }
//    public Dice diceLoad() {
//        Dice dice=null;
//        try {
//            //获取实体管理对象
//            em = JPAUtil.getEntityManager();
//            //获取事务对象
//            tx = em.getTransaction();
//            tx.begin();
//            //创建query对象
//            String jpql = "from Dice where id = 1 ";
//            Query query = em.createQuery(jpql);
//            Object obj = query.getSingleResult();
//            dice = (Dice)obj;
//            tx.commit();
//        }catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        return dice;
//    }
}
