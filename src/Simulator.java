import java.util.Scanner;
import java.util.ArrayList;

public class Simulator {
    public static void main(String[] args) {
        // Get attacker profile from user
        Scanner myObj1 = new Scanner(System.in);
        System.out.println("Please provide the following values for the attacker: A, type of dice, modifiers, BS/WS, S, AP and D.");
        String input1 = myObj1.nextLine();

        // Get defender profile from user
        Scanner myObj2 = new Scanner(System.in);
        System.out.println("Please provide the following values for the defender: T, SV, invSV and W.");
        String input2 = myObj2.nextLine();

        // Split input
        String[] split1 = input1.split(" ");
        String[] split2 = input2.split(" ");

        // Get attacker variables from input1
        String attacks = split1[0];
        String diceType = split1[1];
        String modifier = split1[2];
        String weaponSkill = split1[3];
        String strength = split1[4];
        String armourPenetration = split1[5];
        String damage = split1[6];

        // Get defender variables from input2
        String toughness = split2[0];
        String save = split2[1];
        String invulnerableSave = split2[2];
        String wounds = split2[3];

        // String to int conversion
        int a = Integer.parseInt(attacks);
        int type = Integer.parseInt(diceType);
        int mod = Integer.parseInt(modifier);
        int wS = Integer.parseInt(weaponSkill);
        int s = Integer.parseInt(strength);
        int aP = Integer.parseInt(armourPenetration);
        int d = Integer.parseInt(damage);

        int t = Integer.parseInt(toughness);
        int sv = Integer.parseInt(save);
        int invSv = Integer.parseInt(invulnerableSave);
        int w = Integer.parseInt(wounds);

        // Print attacker's profile
        System.out.println("You are making " + a + " attacks using d" + type + " dice, with an attack number modifier of " + mod + ".");

       /* // Print defender's profile
        System.out.println("Your defense stats: toughness " + t + ", save " + sv + "+, invulnerable save " + invSv + "+, wounds " + w);*/

        // Hit roll
        DiceRoller hitRoll = new DiceRoller(a, type, mod);
        ArrayList<Integer> hitResults = hitRoll.roll();
        // Print dice rolls
        System.out.println("You hit on " + wS + "+ and here's what you rolled: " + hitResults);

        // Wound roll
        hitResults.removeIf(roll -> roll < wS);
        int woundDice = hitResults.size();
        int woundMod = 0;
        int woundTarget;

        if (s == t) {
            woundTarget = 4;
        } else if (s > t && s < t * 2) {
            woundTarget = 3;
        } else if (s >= t * 2) {
            woundTarget = 2;
        } else if (s < t && s > t / 2) {
            woundTarget = 5;
        } else {
            woundTarget = 6;
        }

        DiceRoller woundRoll = new DiceRoller(woundDice, type, woundMod);
        ArrayList<Integer> woundResults = woundRoll.roll();
        // Print dice rolls
        System.out.println("You wound on " + woundTarget + "+ and here's what you rolled: " + woundResults);

        // Save roll
        woundResults.removeIf(roll -> roll < woundTarget);
        int saveDice = woundResults.size();
        int saveMod = 0;
        int saveTarget = Math.min(sv + aP, invSv);

        DiceRoller saveRoll = new DiceRoller(saveDice, type, saveMod);
        ArrayList<Integer> saveResults = saveRoll.roll();
        // Print dice rolls
        System.out.println("You save on " + saveTarget + "+ and here's what you rolled: " + saveResults);

        // Allocate damage
        saveResults.removeIf(roll -> roll >= saveTarget);
        int damageTaken = saveResults.size() * d;

        if (damageTaken >= w) {
            System.out.println("You have taken " + damageTaken + " damage and your unit is dead.");
        } else {
            System.out.println("You have taken " + damageTaken + " damage and you have " + (w - damageTaken) + " wounds remaining.");
        }
    }
}
