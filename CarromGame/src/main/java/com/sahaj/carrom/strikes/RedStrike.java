package main.java.com.sahaj.carrom.strikes;

import main.java.com.sahaj.carrom.coins.Coin;
import main.java.com.sahaj.carrom.Player;

public class RedStrike extends Strike {
    public RedStrike(String name, Coin coin,int PointsToAdd,int coinsToReduce, boolean isInvalid) {
        super(name,coin, PointsToAdd, coinsToReduce, isInvalid);
    }
    @Override
    public void computeScores(Player player) {
        if(checkCoin()) {
            player.setScore(player.getScore() + pointsToAdd);
            coin.getType().getCoinType().setGameCoins(coin.getType().getCoinType().getGameCoins() - coinsToReduce);
            updateInvalid(player);
        }
    }
}
