package main.java.com.sahaj.carrom;

import main.java.com.sahaj.carrom.coins.Coin;
import main.java.com.sahaj.carrom.coins.CoinType;
import main.java.com.sahaj.carrom.strikes.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Path inputPath = Paths.get(new File("src/main/resources/data/input.txt").getAbsolutePath());
        Path strikesPath = Paths.get(new File("src/main/resources/data/strikes.txt").getAbsolutePath());

        CarromGame cg = new CarromGame();

        try {
            SetStrikeRules(strikesPath, cg);
            PopulateCarromData(inputPath, cg);
        } catch (IllegalStateException i) {
            System.out.println("GAME ENDED");
        }
        PrintResult(cg);
    }

    private static void PopulateCarromData(Path inputPath, CarromGame cg) {
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String currentLine;
            List<Player> playerList = new ArrayList<>();
            int NumberOfPlayers = 0;
            Map<String, CoinType> coinTypes = cg.getCoinTypes();
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("No_of_players")) {
                    NumberOfPlayers = Integer.parseInt(currentLine.split("\\|")[1]);
                } else if (currentLine.startsWith("Names")) {
                    String[] names = currentLine.split("\\|");
                    for (int i = 1; i <= NumberOfPlayers; i++) {
                        playerList.add(new Player(names[i], 0));
                    }
                    cg.setPlayerList(playerList);
                } else {
                    try {
                        PrintCurrentStrike(cg, currentLine);
                    } catch (IllegalStateException i) {
                        throw i;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void PrintCurrentStrike(CarromGame cg, String currentLine) {
        String[] strikes = currentLine.split("\\|");
        System.out.println("___________________________________________________________________________________________");
        Strike strike = null;
        for (Strike s : cg.getStrikesList()) {
            if (s.getStrikeName().equalsIgnoreCase(strikes[1])) {
                strike = s;
                break;
            }
        }
        try {
            strike.computeScores(cg.getPlayerList().get(Integer.parseInt(strikes[0]) - 1));
        } catch (IllegalStateException i) {
            throw i;
        }
        System.out.printf("\nCurrent strike ==> Player: %s \t Strike: %s\n\n", cg.getPlayerList().get(Integer.parseInt(strikes[0]) - 1).getName(), strikes[1]);
        for (String c : cg.getCoinTypes().keySet()) {
            if (c.equalsIgnoreCase("none")) continue;
            System.out.printf("\n Coin : " + c + " Count: " + cg.getCoinTypes().get(c).getGameCoins());
        }
        System.out.println("\n\n Scorecard after current strike");
        System.out.print(cg.getPlayerList().toString().replaceAll("[\\[\\],]", "").replaceAll(" ", ""));
        System.out.println("___________________________________________________________________________________________");
    }

    private static Strike getStrike(String[] strikes, CarromGame cg) {
        Strike strike = null;

        switch (strikes[0]) {
            case "Strike":
                strike = new StrikeStrike(strikes[0], new Coin(cg.getCoinTypes().get(strikes[1])), Integer.parseInt(strikes[3]), Integer.parseInt(strikes[2]), strikes[4].equals("1"));
                break;
            case "Multi-strike":
                strike = new MultiStrike(strikes[0], new Coin(cg.getCoinTypes().get(strikes[1])), Integer.parseInt(strikes[3]), Integer.parseInt(strikes[2]), strikes[4].equals("1"));
                break;
            case "Red-strike":
                strike = new RedStrike(strikes[0], new Coin(cg.getCoinTypes().get(strikes[1])), Integer.parseInt(strikes[3]), Integer.parseInt(strikes[2]), strikes[4].equals("1"));
                break;
            case "Striker-strike":
                strike = new StrikerStrike(strikes[0], new Coin(cg.getCoinTypes().get(strikes[1])), Integer.parseInt(strikes[3]), Integer.parseInt(strikes[2]), strikes[4].equals("1"));
                break;
            case "Defunct-coin":
                strike = new DefunctCoinStrike(strikes[0], new Coin(cg.getCoinTypes().get(strikes[1])), Integer.parseInt(strikes[3]), Integer.parseInt(strikes[2]), strikes[4].equals("1"));
                break;
            default:
                strike = new InvalidStrike(strikes[0], new Coin(cg.getCoinTypes().get(strikes[1])), Integer.parseInt(strikes[3]), Integer.parseInt(strikes[2]), strikes[4].equals("1"));
                break;
        }
        return strike;
    }

    private static void SetStrikeRules(Path strikesPath, CarromGame cg) {
        try (BufferedReader reader = Files.newBufferedReader(strikesPath)) {
            String currentLine;
            List<Strike> strikeRules = new ArrayList<>();
            Map<String, CoinType> coinTypes = cg.getCoinTypes();
            while ((currentLine = reader.readLine()) != null) {
                String[] strikes = currentLine.split("\\|");
                if (strikes[0].equals("coin")) {
                    coinTypes.putIfAbsent(currentLine.split("\\|")[1], new CoinType(currentLine.split("\\|")[1], Integer.parseInt(currentLine.split("\\|")[2])));
                } else {
                    Strike strike = getStrike(strikes, cg);
                    strikeRules.add(strike);
                }
            }
            cg.setStrikesList(strikeRules);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void PrintResult(CarromGame cg) {
        Collections.sort(cg.getPlayerList());
        if (cg.getPlayerList().get(0).getScore() >= (cg.getPlayerList().get(1).getScore() + 3)) {
            System.out.print("\n\n **********************************************");
            System.out.printf("\n WINNER IS %s ", cg.getPlayerList().get(0).toString());
            System.out.print("**********************************************");
        } else {
            System.out.println(" ***** GAME ENDS IN DRAW *****");
        }
    }
}
