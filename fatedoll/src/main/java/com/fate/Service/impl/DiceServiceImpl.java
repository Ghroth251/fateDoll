package com.fate.Service.impl;

import com.fate.Dao.DiceDao;
import com.fate.Service.DiceService;
import com.fate.bean.Dice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fate.util.StaticObjectUtils.myDice;

@Service
public class DiceServiceImpl implements DiceService{

    @Autowired
    DiceDao dd;

    @Override
    public void saveDiceState(String State) {
        myDice.setState(State);
        dd.save(myDice);
    }
    @Override
    public Dice diceLoad() {
        return dd.findOne(1L);
    }
}
