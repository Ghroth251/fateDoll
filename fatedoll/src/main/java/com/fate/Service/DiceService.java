package com.fate.Service;

import com.fate.bean.Dice;

public interface DiceService {

    void saveDiceState(String State);
    Dice diceLoad();
}
