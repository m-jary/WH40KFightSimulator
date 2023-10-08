import java.util.Scanner;

public class UserInput {
    String input1;
    String input2;

    public Attacker attacker;
    public Defender defender;

    public UserInput() {

        // Get attacker profile from user
        Scanner myObj1 = new Scanner(System.in);
        System.out.println("Please provide the following values for the attacker: attacks, weapon skill, strength, armour penetration, damage.");
        input1 = myObj1.nextLine();

        // Get defender profile from user
        System.out.println("Please provide the following values for the defender: toughness, save, invulnerable save, wounds.");
        input2 = myObj1.nextLine();

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
            numberOfAttacks = DiceRoller.attackNoRoll(attackDice, attackDiceType, attackNoMod);
        }
        this.attacker = new Attacker(weaponSkill, strength, armourPenetration, damage, numberOfAttacks);
        this.defender = new Defender(toughness, save, invSave, wounds);
    }
}
