import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static public List<Entity> initPopulation(DecisionMaker dm) {
        List<Entity> res = new LinkedList<>();
        for (int j = 0; j <= dm.maxEntity; j++) {
            char[] initDna = new char[dm.result.length];
            for (int i = 0; i < dm.result.length; i++) {
                initDna[i] = (char)(Math.random() * 256);
            }
            Entity entity = new Entity(initDna);
            res.add(entity);
        }
        return res;
    }
    static public List<Entity> sex(List<Entity> population) {
        List<Entity> res = new LinkedList<Entity>(population);
        for (Entity father : population) {
            for (Entity mother : population) {
                boolean needMutate = (Math.random() * 100) > 95;
                if (father != mother && father != null && mother != null) {
                    res.add(new Entity(father, mother, needMutate));
                }
            }
        }
        return res;
    }
    static public void printPopulation(List<Entity> population, DecisionMaker dm) {
        System.out.println("Population:");
        for (Entity entity : population) {
            System.out.println(entity + " " + (entity == null? null : dm.fitness(entity.dna)));
        }
    }
    public static void main(String[] args) {
        DecisionMaker dm = new DecisionMaker("Luiza".toCharArray(), 10, 1);
        List<Entity> initPopulation = initPopulation(dm);
        List<Entity> selectedPopulation = null;
        printPopulation(initPopulation, dm);
        for (int i = 0; i < 2000; i++) {
            List<Entity> childPopulation = sex(initPopulation);
            selectedPopulation = Arrays.asList(dm.doSelection(childPopulation));
            initPopulation = selectedPopulation;
            if (new String(selectedPopulation.get(0).dna).equals(new String(dm.result))) {
                System.out.println(String.format("Result found at age: %d", dm.age));
                break;
            }
        }
        printPopulation(selectedPopulation, dm);
    }
}