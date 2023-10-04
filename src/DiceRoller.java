import java.util.ArrayList;

public class DiceRoller {
    private final int dices;
    private final int type;
    private final int modifier;

    public DiceRoller(int dices, int type, int modifier) {
        this.dices = dices;
        this.type = type;
        this.modifier = modifier;
    }

    // Generate random rolls
    public ArrayList<Integer> rolls() {
        ArrayList<Integer> results = new ArrayList<>();
        int randomRoll;
        int min = 1;

        for (int i = 0; i < dices; i++) {
            randomRoll = (int) Math.floor(Math.random() * (type - min + 1) + min);
            results.add(randomRoll);
        }
        return results;
    }

    // Summarise all rolls and add modifier
    public int sumOfRolls(ArrayList<Integer> all) {
        int sum = 0;
        for (Integer roll : all) {
            sum += roll;
        }
        sum += modifier;
        return sum;
    }
}
