public class Entity {
    char[] dna;
    boolean isLucky;
    protected Entity() {

    }
    public Entity(char[] dna) {
        this.dna = dna.clone();
        isLucky = Math.random() * 100 > 90;
    }
    public Entity(Entity father, Entity mother, boolean mutate) {
        this.dna = father.dna.clone();
        isLucky = Math.random() * 100 > 90;
        for (int i = dna.length/2; i < dna.length; i++) {
            dna[i] = mother.dna[i];
        }
        if (mutate) {
            int index = Math.round((float)Math.random() * (dna.length - 1));
            dna[index] = (char)(Math.random() * 256);
        }
    }
    private String printDna(char[] arr) {
        return new String(arr);
    }
    @Override
    public String toString() {
        return String.format("Entity: dna=%s isLucky=%s", printDna(dna), isLucky);
    }
}
