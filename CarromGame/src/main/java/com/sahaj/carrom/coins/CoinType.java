package main.java.com.sahaj.carrom.coins;

public class CoinType {
    private String name;
    private int gameCoins;

    public CoinType(String name, int gameCoins){
        this.name = name;
        this.gameCoins = gameCoins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoinType getCoinType() {
        return this;
    }

    public int getGameCoins() {
        return gameCoins;
    }

    public void setGameCoins(int gameCoins) {
        this.gameCoins = gameCoins;
    }
}
