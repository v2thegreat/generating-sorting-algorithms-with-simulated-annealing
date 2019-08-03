package datasetReader;
import java.io.*;
import java.util.Vector;

import static datasetReader.datasets.DataSetsPaths.*;

public class DataSetReader {
    public final int[][] randomInputsSmall = readRandomInputsSmall();

    public final int[][] permutations5 = readPermutations5();

    public final int[][][] allInputData = readAllInputData();

    private static int[][] readRandomInputsSmall(){
        return readCSVFileContents(new File(RandomInputsSmallPath));
    }

    private static int[][] readPermutations5() { return readCSVFileContents(new File(PermutationsOf5)); }

    private int[][][] readAllInputData(){
        return new int[][][]{randomInputsSmall, permutations5};
    }

    private static int[][] readCSVFileContents(File file){
        Vector<int[]> data = new Vector<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                data.add(getStringLineIntegerValues(line));
                line = br.readLine();
            }
            return data.toArray(new int[data.size()][data.get(0).length]);
        } catch (java.io.IOException e){
            System.out.println(e.toString());
        }
        return new int[][]{};
    }

    private static int[] getStringLineIntegerValues(String line) {
        String[] individualVals = line.split(",");
        int[] dataColumns = new int[individualVals.length];
        for (int i = 0; i < individualVals.length; i++)
            dataColumns[i] = Integer.valueOf(individualVals[i]);
        return dataColumns;
    }

    public int getDataSize(){
        return randomInputsSmall[0].length;
    }

    public int getDataSize(int dataType){
        return readAllInputData()[dataType][0].length;
    }
}
