package com.ghroth251.Dao;

import com.ghroth251.bean.Mecha;
import com.ghroth251.bean.QQuser;
import com.ghroth251.util.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import static com.ghroth251.util.MapUtils.*;

public interface MechaDao extends JpaRepository<Mecha,Long>,JpaSpecificationExecutor<Mecha> {
    Mecha findMechaByUidLike(String uid);
}
