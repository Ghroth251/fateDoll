package com.fate.Service.impl;

import com.fate.Dao.DiceDao;
import com.fate.Service.DiceService;
import com.fate.bean.Dice;

public class DiceServiceImpl implements DiceService{
    DiceDao dd = new DiceDao();

    @Override
    public void saveDiceState(String State) {
        dd.saveDiceState(State);
    }
    @Override
    public Dice diceLoad() {
        return dd.diceLoad();
    }
}
