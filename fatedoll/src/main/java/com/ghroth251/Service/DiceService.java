package com.ghroth251.Service;

import com.ghroth251.bean.Dice;

public interface DiceService {

    void saveDiceState(String State);
    Dice diceLoad();
}
