package com.fate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fate.util.BaseDao;
import com.fate.bean.DataMsg;
import lemocclient.Lemocclient;
import static com.fate.util.MapUtils.*;

public class DataDao extends BaseDao{
	public ArrayList<DataMsg> datafind(String dataName,String dataType) {
		ArrayList<DataMsg> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("select * from ");
		sql.append(Lemocclient.myDice.getState());
		sql.append("data where 1 = 1");
		List<Object> parms = new ArrayList<>();
		if (dataName != null && !"".equals(dataName)) {
			sql.append(" and name like ?");
			parms.add("%" + dataName + "%");
		}
		if (dataType != null && !"".equals(dataType)) {
			sql.append(" and type like ?");
			parms.add("%" + dataType + "%");
		}
       // System.out.println("sql "+sql+"dataName "+dataName+"dataType "+dataType);
		ResultSet rs = this.myexecuteQuery(sql.toString(), parms.toArray());
		try {
			while (rs.next()) {
				DataMsg d = new DataMsg();
				d.setId(rs.getInt(1));
				d.setName(rs.getString(2));
				d.setType(rs.getString(3));
				d.setMsg(rs.getString(4));
                d.setDdata(mapLoad(rs.getString(5)));
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closConn();
		}
		return list;
	}
	public String huan(String s){
		s = s.replace("\r", "");
		s = s.replace("\n", "\\n");
		return s;
		
	}


}
