package SimulatedAnnealing;

import SwapSequence.SwapSequence;
import arrayEvaluation.ArrayEvaluation;

import java.io.FileWriter;
import java.util.Random;
import java.util.Vector;

public class SimulatedAnnealing {
    private static float calcTemp(int k, int runSteps) {
        return (float) k / runSteps;
    }

    private boolean isNewSwapSeqBetterThanOldSwapSeq(SwapSequence oldSwapSequence, SwapSequence newSwapSequence, float temp, int dataType) {
        double oldSwapSeqFitness = 1 - ArrayEvaluation.evalSwapSeqFitness(oldSwapSequence, temp, dataType);
        double newSwapSeqFitness = 1 - ArrayEvaluation.evalSwapSeqFitness(newSwapSequence, temp, dataType);
        if (newSwapSeqFitness < oldSwapSeqFitness)
            return true;
        return (Math.exp(-(newSwapSeqFitness - oldSwapSeqFitness) / temp)) >= Math.random();
    }

    private void saveToFile(Vector<Double> dat, String prefix, SwapSequence oldSwapSequence) {
        try {
            FileWriter performanceWritter = new FileWriter("src\\" + prefix + " performance data.txt");
            FileWriter sequenceWritter = new FileWriter("src\\" + prefix + " sequence data.txt");
            for (double d : dat)
                performanceWritter.append(Double.toString(d)).append("\\n");
            performanceWritter.close();

            sequenceWritter.append(oldSwapSequence.toString());
            sequenceWritter.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error Ran");
        }
    }

    public SwapSequence runSimulatedAnnealingForDataset(int numberOfTimesToRun, String dataSaverPrefix, int dataType) {
        Vector<Double> fitnessDataSaver = new Vector<>();
        float temp;
        SwapSequence newSwapSequence;
        SwapSequence oldSwapSequence = new SwapSequence(ArrayEvaluation.dataReader.getDataSize(dataType));
        System.out.println(ArrayEvaluation.dataReader.getDataSize(dataType));

        for (int k = 0; k < numberOfTimesToRun; k++) {
            temp = calcTemp(k, numberOfTimesToRun);
            System.out.print("Processing: " + (temp * 100) + "\t% |");
            newSwapSequence = oldSwapSequence.getNewNeighbour();
            if (isNewSwapSeqBetterThanOldSwapSeq(oldSwapSequence, newSwapSequence, temp, dataType)) {
                fitnessDataSaver.add(ArrayEvaluation.evalSwapSeqFitness(newSwapSequence, temp, dataType));
                oldSwapSequence = newSwapSequence;
            }
            System.out.print("\r");
        }

        System.out.println("Completed!");
        saveToFile(fitnessDataSaver, dataSaverPrefix, oldSwapSequence);
        return oldSwapSequence;
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        long CurrentTime = System.currentTimeMillis();
        int[] randomArr = ArrayEvaluation.dataReader.permutations5[rnd.nextInt(ArrayEvaluation.dataReader.permutations5.length)];

        System.out.println("Sortedness before: " + ArrayEvaluation.getSortedness(randomArr));

        SimulatedAnnealing te = new SimulatedAnnealing();
        SwapSequence testSwapSequence = te.runSimulatedAnnealingForDataset(1000, "Random Numbers", 1);
        testSwapSequence.runSwapSequence(randomArr);
        System.out.println("Sortedness after: " + ArrayEvaluation.getSortedness(randomArr));
        System.out.println("Final Swap Seq: = " + testSwapSequence.toString());
        System.out.println("SwapSeq Size: " + testSwapSequence.getVoidlessSize());
        System.out.println("Time Taken: " + (System.currentTimeMillis() - CurrentTime));
    }
}