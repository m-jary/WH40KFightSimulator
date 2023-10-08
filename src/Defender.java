public class Defender {
    public final int toughness;
    public final int save;
    public final int invSave;
    public final int wounds;

    public Defender(int toughness, int save, int invSave, int wounds) {
        this.toughness = toughness;
        this.save = save;
        this.invSave = invSave;
        this.wounds = wounds;
    }
}
