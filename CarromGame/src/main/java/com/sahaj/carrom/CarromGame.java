package main.java.com.sahaj.carrom;

import main.java.com.sahaj.carrom.coins.CoinType;
import main.java.com.sahaj.carrom.strikes.Strike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarromGame {

    private List<Strike> strikesList;
    private List<Player> playerList;
    private Map<String, CoinType> coinTypes = new HashMap<>();

    public List<Strike> getStrikesList() {
        return strikesList;
    }

    void setStrikesList(List<Strike> strikesList) {
        this.strikesList = strikesList;
    }

    void setCoinTypes(Map<String, CoinType> coinTypes) {
        this.coinTypes = coinTypes;
    }

    public Map<String, CoinType> getCoinTypes() {
        return this.coinTypes;
    }

    List<Player> getPlayerList() {
        return playerList;
    }

    void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

}

