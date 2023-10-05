import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {
    public static int attackNoRoll(int attackDice, int attackDiceType, int attackNoMod) {
        int randomRoll;
        int min = 1;
        ArrayList<Integer> attackNoRollResults = new ArrayList<>();

        for (int i = 0; i < attackDice; i++) {
            randomRoll = (int) Math.floor(Math.random() * (attackDiceType - min + 1) + min);
            attackNoRollResults.add(randomRoll);
        }
        System.out.println("Let's see how many attacks attacker will be making. Here's the dice roll: " + attackNoRollResults);

        int attackNo = 0;
        for (Integer roll : attackNoRollResults) {
            attackNo += roll;
        }

        return attackNo + attackNoMod;
    }

    public static int hitRoll(int numberOfAttacks, int weaponSkill) {
        System.out.println("Attacker will be making a total of " + numberOfAttacks + " attacks.");
        int randomRoll;
        int min = 1;
        int max = 6;
        ArrayList<Integer> hitRollResults = new ArrayList<>();

        for (int i = 0; i < numberOfAttacks; i++) {
            randomRoll = (int) Math.floor(Math.random() * (max - min + 1) + min);
            hitRollResults.add(randomRoll);
        }
        System.out.println("Attacker hits on " + weaponSkill + "+ and here's the hit roll: " + hitRollResults);

        hitRollResults.removeIf(roll -> roll < weaponSkill);
        int hitNo = hitRollResults.size();
        System.out.println("Attacker makes " + hitNo + " successful hits.");
        return hitNo;
    }

    public static int woundRoll(int numberOfHits, int woundTarget) {
        int randomRoll;
        int min = 1;
        int max = 6;
        ArrayList<Integer> woundRollResults = new ArrayList<>();

        for (int i = 0; i < numberOfHits; i++) {
            randomRoll = (int) Math.floor(Math.random() * (max - min + 1) + min);
            woundRollResults.add(randomRoll);
        }
        System.out.println("Attacker wounds on " + woundTarget + "+ and here's the wound roll: " + woundRollResults);

        woundRollResults.removeIf(roll -> roll < woundTarget);
        int woundNo = woundRollResults.size();
        System.out.println("Attacker makes " + woundNo + " successful wounds.");
        return woundNo;
    }

    public static void saveRoll(int numberOfWounds, int saveTarget, int damage, int wounds) {
        int randomRoll;
        int min = 1;
        int max = 6;
        ArrayList<Integer> saveRollResults = new ArrayList<>();

        for (int i = 0; i < numberOfWounds; i++) {
            randomRoll = (int) Math.floor(Math.random() * (max - min + 1) + min);
            saveRollResults.add(randomRoll);
        }
        System.out.println("Defender saves on " + saveTarget + "+ and here's the save roll: " + saveRollResults);

        saveRollResults.removeIf(roll -> roll >= saveTarget);
        int saveNo = saveRollResults.size();
        if (saveNo < 1) {
            System.out.println("Defender saved all of the wounds.");
        } else if (saveNo == 1) {
            System.out.println("Defender didn't save " + saveNo + " wound.");
        } else {
            System.out.println("Defender didn't save " + saveNo + " wounds.");
        }

        int damageTaken = saveNo * damage;
        if (damageTaken >= wounds) {
            System.out.println("Defender has taken " + damageTaken + " damage and is dead.");
        } else if (damageTaken == 0) {
            System.out.println("Defender has taken " + wounds + " wounds remaining.");
        } else {
            System.out.println("Defender has taken " + damageTaken + " damage and they have " + (wounds - damageTaken) + " wounds remaining.");
        }
    }

    public static void main(String[] args) {
        // Get attacker profile from user
        Scanner myObj1 = new Scanner(System.in);
        System.out.println("Please provide the following values for the attacker: attacks, weapon skill, strength, armour penetration, damage.");
        String input1 = myObj1.nextLine();

        // Get attacker variables from input1
        String[] split1 = input1.split(" ");
        String attackerA = split1[0];
        String attackerWS = split1[1];
        String attackerS = split1[2];
        String attackerAP = split1[3];
        String attackerD = split1[4];
        int weaponSkill = Integer.parseInt(attackerWS);
        int strength = Integer.parseInt(attackerS);
        int armourPenetration = Integer.parseInt(attackerAP);
        int damage = Integer.parseInt(attackerD);

        // Get defender profile from user
        Scanner myObj2 = new Scanner(System.in);
        System.out.println("Please provide the following values for the defender: toughness, save, invulnerable save, wounds.");
        String input2 = myObj2.nextLine();

        // Get defender variables from input2
        String[] split2 = input2.split(" ");
        String defenderT = split2[0];
        String defenderSv = split2[1];
        String defenderInvSv = split2[2];
        String defenderW = split2[3];
        int toughness = Integer.parseInt(defenderT);
        int save = Integer.parseInt(defenderSv);
        int invSave = Integer.parseInt(defenderInvSv);
        int wounds = Integer.parseInt(defenderW);

        // Get number of attacks from input
        int attackDice;
        int attackDiceType;
        int attackNoMod;
        int numberOfAttacks;
        if (attackerA.length() == 1) {
            numberOfAttacks = Integer.parseInt(attackerA);
        } else {
            String[] split3 = attackerA.split("d");
            String diceNo = split3[0];
            String restOfAttacks = split3[1];
            String[] split4 = restOfAttacks.split("\\+");
            String diceType = split4[0];
            String modifier = split4[1];
            attackDice = Integer.parseInt(diceNo);
            attackDiceType = Integer.parseInt(diceType);
            attackNoMod = Integer.parseInt(modifier);
            numberOfAttacks = attackNoRoll(attackDice, attackDiceType, attackNoMod);
        }

        // Calculate on what attacker wounds
        int woundTarget;
        if (strength == toughness) {
            woundTarget = 4;
        } else if (strength > toughness && strength < toughness * 2) {
            woundTarget = 3;
        } else if (strength >= toughness * 2) {
            woundTarget = 2;
        } else if (strength < toughness && strength > toughness / 2) {
            woundTarget = 5;
        } else {
            woundTarget = 6;
        }

        // Calculate on what defender saves
        int saveTarget;
        if (invSave == 0) {
            saveTarget = save + armourPenetration;
        } else {
            saveTarget = Math.min(save + armourPenetration, invSave);
        }

        // Run functions
        int numberOfHits = hitRoll(numberOfAttacks, weaponSkill);
        int numberOfWounds = woundRoll(numberOfHits, woundTarget);
        saveRoll(numberOfWounds, saveTarget, damage, wounds);
    }
}