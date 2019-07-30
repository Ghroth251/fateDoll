package com.fate.Dao;

import com.fate.bean.Mecha;
import com.fate.util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import static com.fate.util.MapUtils.*;

public class MechaDao extends BaseDao {

    public int addMecha(Mecha mecha){
        String sql ="INSERT INTO Mecha VALUES (null,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = { mecha.getMname(),mecha.getUid(),mecha.getmEn(),mecha.getmSt(),mecha.getmMo(),mecha.getmSe(),
                mapSave(mecha.getAttData()),mapSave(mecha.getWeapon1Data()),mapSave(mecha.getWeapon2Data()),
                mapSave(mecha.getWeapon3Data()),mapSave(mecha.getWeapon4Data())};
        return this.myexecuteUpdate(sql, params);
    }
    public Mecha getMechaById(String uid){
        Mecha mecha=null;
        String sql ="SELECT * FROM Mecha WHERE uid=?";
        Object[] params = {uid};
        ResultSet rs = this.myexecuteQuery(sql,params);
        try {
            while(rs.next()) {
                int id = rs.getInt(1);
                String uname = rs.getString(2);
                int mEn = rs.getInt(4);
                int mSt = rs.getInt(5);
                int mMo = rs.getInt(6);
                int mSe = rs.getInt(7);
                LinkedHashMap<String,Object> attData = mapLoad(rs.getString(8));
                LinkedHashMap<String,Object> weapon1Data = mapLoad(rs.getString(9));
                LinkedHashMap<String,Object> weapon2Data = mapLoad(rs.getString(10));
                LinkedHashMap<String,Object> weapon3Data = mapLoad(rs.getString(11));
                LinkedHashMap<String,Object> weapon4Data = mapLoad(rs.getString(12));
                mecha = new Mecha(id,uname,uid,mEn,mSt,mMo,mSe,attData,weapon1Data,weapon2Data,weapon3Data,weapon4Data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closConn();
        }
        return mecha;
    }
    public int updateMecha(Mecha m){
//        String sql = "UPDATE Mecha SET mname=? uid=? mEN=? mST=? mMO=? mSE=? " +
//                "attData=? weapon1Data=? weapon2Data=? weapon3Data=? weapon4Data=? where id=?";
        String sql = "UPDATE Mecha SET mname=?,uid=?,mEN=?,mST=?,mMO=?,mSE=?," +
                "attData=?,weapon1Data=?,weapon2Data=?,weapon3Data=?,weapon4Data=? where id=?";
        Object[] params = {m.getMname(),m.getUid(),m.getmEn(),m.getmSt(),m.getmMo(),m.getmSe(),
        mapSave(m.getAttData()),mapSave(m.getWeapon1Data()),mapSave(m.getWeapon2Data()),
                mapSave(m.getWeapon3Data()),mapSave(m.getWeapon4Data()),
        m.getId()};
//        System.out.println(m.getMname()+","+m.getUid()+","+m.getmEn()+","+m.getmSt()+","+m.getmMo()+","+m.getmSe()+","+
//                mapSave(m.getAttData())+","+mapSave(m.getWeapon1Data())+","+mapSave(m.getWeapon2Data())+","+
//                mapSave(m.getWeapon3Data())+","+mapSave(m.getWeapon4Data()));
        return this.myexecuteUpdate(sql, params);
    }



}
