package com.ghroth251.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author bruceliu
 * @create 2019-07-06 16:34
 * @description JPA工具类
 */
public final class JPAUtil {


    // JPA的实体管理器工厂：相当于Hibernate的SessionFactory
    private static EntityManagerFactory em;
    // 使用静态代码块赋值
    static {
        // 注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
        em = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 使用管理器工厂生产一个管理器对象
     * @return
     */
    public static EntityManager getEntityManager() {
        return em.createEntityManager();
    }
}
