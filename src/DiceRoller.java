import java.util.ArrayList;

public class DiceRoller {
    public static RollResults roll(double numberOfDices) {
        ArrayList<Integer> singleRoll = new ArrayList<>();
        int randomRoll;
        int min = 1;
        int max = 6;

        for (int i = 0; i < numberOfDices; i++) {
            randomRoll = (int) Math.floor(Math.random() * (max - min + 1) + min);
            singleRoll.add(randomRoll);
        }
        return new RollResults(singleRoll);
    }

    public static int attackNoRoll(int attackDice, int attackDiceType, int attackMod) {
        int randomRoll;
        int min = 1;
        ArrayList<Integer> attackNoRollResults = new ArrayList<>();

        for (int i = 0; i < attackDice; i++) {
            randomRoll = (int) Math.floor(Math.random() * (attackDiceType - min + 1) + min);
            attackNoRollResults.add(randomRoll);
        }

        int attackNo = 0;
        for (Integer roll : attackNoRollResults) {
            attackNo += roll;
        }

        return attackNo + attackMod;
    }
}