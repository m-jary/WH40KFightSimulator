import java.util.ArrayList;

public class RollResults {
    ArrayList<Integer> rolls;

    public RollResults(ArrayList<Integer> rolls) {
        this.rolls = rolls;
    }

    public RollResults getAbove(int rollTarget) {
        ArrayList<Integer> successfulRolls = new ArrayList<>(rolls);
        successfulRolls.removeIf(roll -> roll < rollTarget);
        return new RollResults(successfulRolls);
    }

    public RollResults getBelow(int rollTarget) {
        ArrayList<Integer> successfulRolls = new ArrayList<>(rolls);
        successfulRolls.removeIf(roll -> roll > rollTarget);
        return new RollResults(successfulRolls);
    }

    public int size() {
        return rolls.size();
    }
}
