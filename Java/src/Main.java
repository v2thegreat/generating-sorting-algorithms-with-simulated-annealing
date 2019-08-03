import SimulatedAnnealing.SimulatedAnnealing;
import SwapSequence.SwapSequence;

public class Main {
    public static void main(String[] args) {
        SimulatedAnnealing simulatedAnnealingObj = new SimulatedAnnealing();
        SwapSequence te;
        for (int i = 1; i < 10; i++) {
            te = simulatedAnnealingObj.runSimulatedAnnealingForDataset(
                    1000,
                    "Permutations of 5 " + i + " of 10",
                    1);
            System.out.println(te.toString());
        }
    }
}
