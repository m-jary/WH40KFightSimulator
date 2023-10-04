import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Get input from user
        Scanner myObj1 = new Scanner(System.in);
        System.out.println("How many and what type of dice are you rolling?");
        String input = myObj1.nextLine();

        // Split input
        String[] split1 = input.split("d");
        String restOfInput = split1[1];
        String[] split2 = restOfInput.split("\\+");

        // Get variables from input
        String dNumber = split1[0];
        String dType = split2[0];
        String dModifier = split2[1];

        // String to int conversion
        int diceNumber = Integer.parseInt(dNumber);
        int diceType = Integer.parseInt(dType);
        int diceModifier = Integer.parseInt(dModifier);

        // Print confirmation
        System.out.println("You are rolling " + diceNumber + " d" + diceType + " dice with a modifier of: " + diceModifier);

        DiceRoller rollThemDice = new DiceRoller(diceNumber, diceType, diceModifier);
        ArrayList<Integer> results = rollThemDice.roll();
        // Print dice rolls
        System.out.println("Here's what you rolled: " + results);

        // Print sum of dice rolls
        int sum = rollThemDice.sumOfRolls(results);
        System.out.println("The sum of your dice rolls and the modifier is " + sum);
    }
}