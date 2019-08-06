package com.ghroth251.Service.impl;


import com.ghroth251.Dao.MechaDao;

import com.ghroth251.Service.MechaService;
import com.ghroth251.bean.Mecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MechaServiceImpl implements MechaService {

    @Autowired
    MechaDao mDo;

    @Override
    public Mecha updateMecha(Mecha m) {
        return mDo.save(m);
    }

    @Override
    public Mecha addMecha(Mecha mecha) {
        return mDo.save(mecha);
    }

    @Override
    public Mecha getMechaById(String uid) {
        return mDo.findMechaByUidLike(uid);
    }
}
