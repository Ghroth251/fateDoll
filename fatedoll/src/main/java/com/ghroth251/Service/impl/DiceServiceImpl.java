package com.ghroth251.Service.impl;

import com.ghroth251.Dao.DiceDao;
import com.ghroth251.Service.DiceService;
import com.ghroth251.bean.Dice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ghroth251.util.StaticObjectUtils.myDice;

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
