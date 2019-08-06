package com.ghroth251.Dao;

import com.ghroth251.bean.QQuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;

public interface UserDao extends JpaRepository<QQuser,Long>,JpaSpecificationExecutor<QQuser>{

        QQuser findByUserIDAndUsergroup(long userID, long groupID);
        ArrayList<QQuser> findByUjoinstateLike(String state);
}
