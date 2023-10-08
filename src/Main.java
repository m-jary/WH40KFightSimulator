import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        Simulator simulator = new Simulator();
        ArrayList<Integer> result = simulator.simulate(userInput.attacker, userInput.defender, 100);

        System.out.println(result);
    }
}