package com.fate.Dao;

import com.fate.bean.QQuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;

public interface UserDao extends JpaRepository<QQuser,Long>,JpaSpecificationExecutor<QQuser>{

        QQuser findByUserIDLikeAndUsergroupLike(String userID,String groupID);
        ArrayList<QQuser> findByUjoinstateLike(String state);
}
