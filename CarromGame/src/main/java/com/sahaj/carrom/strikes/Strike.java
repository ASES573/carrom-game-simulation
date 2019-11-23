package main.java.com.sahaj.carrom.strikes;

import main.java.com.sahaj.carrom.coins.Coin;
import main.java.com.sahaj.carrom.Player;

public abstract class Strike {
    Coin coin;
    boolean isInvalid;
    int pointsToAdd;
    String strikeName;
    int coinsToReduce;

    public int getCoinsToReduce() {
        return coinsToReduce;
    }

    public String getStrikeName() {
        return strikeName;
    }


    public Coin getCoin() {
        return coin;
    }

    public boolean isInvalid() {
        return isInvalid;
    }

    public int getPointsToAdd() {
        return pointsToAdd;
    }

    Strike(String name, Coin coin, int PointsToAdd, int coinsToReduce, boolean isInvalid) {
        this.strikeName = name;
        this.coin = coin;
        this.isInvalid = isInvalid;
        this.pointsToAdd = PointsToAdd;
        this.coinsToReduce = coinsToReduce;
    }

    boolean checkCoin() {
        if(coin.getType().getGameCoins() <= 0) {
            System.out.println("NO MORE COINS TO STRIKE---");
            throw new IllegalStateException("No coins");
        }
        return true;
    }

    void updateInvalid(Player player) {
        player.setInvalidMove(isInvalid? player.getInvalidMove() + 1 : 0);
        player.setScore(player.getInvalidMove() >= 3 ? player.getScore() - 1 : player.getScore());
    }


    public abstract void computeScores(Player player);
}
