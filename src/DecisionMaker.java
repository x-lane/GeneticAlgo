import java.math.BigInteger;
import java.util.List;
import java.util.TreeMap;

public class DecisionMaker {
    char[] result;
    int maxEntity;
    int cntLucky;
    int age;
    private DecisionMaker() {

    }
    public DecisionMaker(char[] result, int maxEntity, int cntLucky) {
        this.result = result;
        this.maxEntity = maxEntity;
        this.cntLucky = cntLucky;
        age = 0;
    }
    public int fitness(char[] dna) {
        int res = 0;
        for(int i = 0; i < dna.length; i++) {
            res += Math.abs(dna[i] - result[i]);
        }
        return res;
    }
    public Entity[] doSelection(List<Entity> population) {
        TreeMap<BigInteger, Entity> fit = new TreeMap<>();
        for (Entity entity : population) {
            fit.put(BigInteger.valueOf(fitness(entity.dna)), entity);
        }
        Entity[] res = new Entity[maxEntity];
        int i = 0;
        for (Entity entity : fit.values()) {
            if ((i < res.length - cntLucky)
                || (i >= res.length - cntLucky && entity.isLucky)) {
                res[i] = entity;
                i++;
            }
            if (i == res.length) {
                break;
            }
        }
        if (i != res.length) {
            int added = res.length - i;
            for (BigInteger key : fit.descendingKeySet()) {
                res[i] = fit.get(key);
                i++;
                if (i == res.length) {
                    break;
                }
            }
            //System.out.println(String.format("Not full population at age: %d. Added %d entities", age, added));
        }
        age++;
        return res;
    }
}
