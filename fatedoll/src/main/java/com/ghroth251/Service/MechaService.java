package com.ghroth251.Service;

import com.ghroth251.bean.Mecha;

public interface MechaService {


    Mecha updateMecha(Mecha m);
    Mecha addMecha(Mecha mecha);
    Mecha getMechaById(String uid);
}
