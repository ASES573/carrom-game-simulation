package main.java.com.sahaj.carrom;

public class Player implements Comparable<Player>{

    private String Name;
    private int Score;
    private int invalidMove;

    Player(String name, int score) {
        this.Name = name;
        this.Score = score;
        this.invalidMove = 0;
    }

    public int getInvalidMove() {
        return invalidMove;
    }

    public void setInvalidMove(int invalidMove) {
        this.invalidMove = invalidMove;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String toString() {
        return "Player => " + this.getName() + " | Score => " + this.getScore() + "\n";
    }

    public int compareTo(Player player) {
        return this.getScore() > player.getScore() ? -1 : 1;
    }
}
