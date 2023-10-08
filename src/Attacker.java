public class Attacker {
    public final int weaponSkill;
    public final int strength;
    public final int armourPenetration;
    public final int damage;
    public final int numberOfAttacks;

    public Attacker(int weaponSkill, int strength, int armourPenetration, int damage, int numberOfAttacks) {
        this.weaponSkill = weaponSkill;
        this.strength = strength;
        this.armourPenetration = armourPenetration;
        this.damage = damage;
        this.numberOfAttacks = numberOfAttacks;
    }
}
