import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day7 {

    static int[] readInput(String path){
        try {
            return Arrays.stream(Files.readString(Path.of(path))
                    .trim()
                    .split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int fuel(int steps){
        int acc = 0;
        for (int i = 1; i <= steps; i++) {
            acc += i;
        }
        return acc;
    }

    public static void main(String[] args) {
        int[] input = readInput(args[args.length - 1]);
        System.out.println("Input");
        System.out.println(Arrays.toString(input));

        int[] fuelCosts = new int[input.length];

        for (int i = 0; i < fuelCosts.length; i++) {
            for (int j = 0; j < input.length; j++) {
                fuelCosts[i] += Math.abs(i - input[j]);
            }
        }

        System.out.println("Fuel cost");
        System.out.println(Arrays.toString(fuelCosts));


        int leastCost = fuelCosts[0];
        for (int i = 0; i < fuelCosts.length; i++) {
            if (leastCost > fuelCosts[i]) leastCost = fuelCosts[i];
        }
        System.out.println(leastCost);

        int[] fuelCostsIncremental = new int[input.length];

        for (int i = 0; i < fuelCostsIncremental.length; i++) {
            for (int j = 0; j < input.length; j++) {
                fuelCostsIncremental[i] += fuel(Math.abs(i - input[j]));
            }
        }

        int leastCostIncremental = fuelCostsIncremental[0];
        for (int i = 0; i < fuelCostsIncremental.length; i++) {
            if (leastCostIncremental > fuelCostsIncremental[i]) leastCostIncremental = fuelCostsIncremental[i];
        }
        System.out.println(leastCostIncremental);
    }
}
