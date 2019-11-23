package main.java.com.sahaj.carrom.coins;

public class Coin {
    private CoinType type;

    public Coin(CoinType coinType) {
        this.type = coinType;
    }

    public CoinType getType() {
        return this.type;
    }
}
