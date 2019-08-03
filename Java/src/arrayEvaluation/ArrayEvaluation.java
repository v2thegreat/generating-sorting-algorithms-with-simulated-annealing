package arrayEvaluation;
import SwapSequence.SwapSequence;
import datasetReader.DataSetReader;

import java.util.Arrays;
import java.util.Random;

public class ArrayEvaluation{
    public static DataSetReader dataReader = new DataSetReader();
    private static Random rndNum = new Random();
    private static int[] getRandomIndexes(int[] arr){
        int[] temp = new int[2];
        while (temp[0] == temp[1]){
            temp[0] = rndNum.nextInt(arr.length);
            temp[1] = rndNum.nextInt(arr.length);
        }
        return temp;
    }
    private static boolean isElementsSorted(int a, int b, int[] arr){
        return (arr[Math.min(a,b)] <= arr[Math.max(a,b)]);
    }
    public static float getSortedness(int[]arr1){
        int a;
        int b;
        int count = 0;
        int[] radArrayIndices;
        float maxArrayRuns = (float) Math.pow(arr1.length, 0.75);
        for (int i=0; i < (int) maxArrayRuns; i++){
            radArrayIndices = getRandomIndexes(arr1);
            a = radArrayIndices[0];
            b = radArrayIndices[1];
            if (isElementsSorted(a,b, arr1))
                count++;
        }
        return (float) count/maxArrayRuns;
    }
    private static double evalEfficiency(SwapSequence swpSeq){
        return (double) 2/swpSeq.getVoidlessSize();
    }
    public static double evalSwapSeqFitness(SwapSequence swpSeq, float temp, int dataType){
        return (evalEffectiveness(swpSeq, dataType) + temp * evalEfficiency(swpSeq));
    }
    private static double evalEffectiveness(SwapSequence inputSwapSequence, int dataType){
        int[][] inputData = new int[dataReader.allInputData[dataType].length][];
        for (int i = 0; i < dataReader.allInputData[dataType].length; i++)
            inputData[i] = Arrays.copyOf(dataReader.allInputData[dataType][i], dataReader.allInputData[dataType][i].length);

        double arrCount = 0;
        double sortednessSum = 0;

        for (int[] inputDatum : inputData) {
            inputSwapSequence.runSwapSequence(inputDatum);
            sortednessSum += getSortedness(inputDatum);
            arrCount++;
        }
        return sortednessSum/arrCount;
    }
    public static void main(String[] args){
    }
}
