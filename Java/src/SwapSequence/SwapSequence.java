package SwapSequence;
import SwapSequence.Swap.Swap;

import java.util.Random;
import java.util.Vector;
import java.lang.Math.*;

public class SwapSequence {
    private static Random rndNum = new Random();
    private Vector<Swap> swapVector = new Vector<>();
    private int givenDataLength;
    public SwapSequence(int inputDataLength){
        givenDataLength = inputDataLength;
        for (int i = 0; i < Math.pow(inputDataLength, 1.25); i++)
            swapVector.add(new Swap(rndNum.nextInt(inputDataLength), rndNum.nextInt(inputDataLength)));
    }
    private SwapSequence(Vector<Swap> swpVector, int arrLen){
        swapVector = swpVector;
        givenDataLength = arrLen;
    }
    private Swap createNewRndSwap(){
        int a = rndNum.nextInt(givenDataLength), b = rndNum.nextInt(givenDataLength);
        while (a == b){
            a = rndNum.nextInt(givenDataLength);
            b = rndNum.nextInt(givenDataLength);
        }
        return new Swap(a, b);
    }
    private void addRandom(){
        swapVector.insertElementAt(createNewRndSwap(), rndNum.nextInt( Math.max(swapVector.size(), 1) ));
    }
    private void removeRandom(){
        swapVector.remove(rndNum.nextInt(swapVector.size()));
    }
    private void changeRandom(){
        swapVector.set(rndNum.nextInt(swapVector.size()), createNewRndSwap());
    }
    private void mutateSequence(){
        int rndChoice = rndNum.nextInt(3);
        if (swapVector.size() == 0)
            rndChoice = 0;
        switch (rndChoice){
            case 0:
                addRandom();
                break;
            case 1:
                removeRandom();
                break;
            case 2:
                changeRandom();
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    public SwapSequence getNewNeighbour(){
        SwapSequence neighbour = new SwapSequence(swapVector, givenDataLength);
        neighbour.mutateSequence();
        return neighbour;
    }
    public int getVoidlessSize(){
        int count = 0;
        for (Swap aSwapVector : swapVector)
            if (!aSwapVector.isVoid())
                count++;
        return count;
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Swap swp: swapVector)
            str.append(swp.toString()+"|");
        return str.toString();
    }
    public void runSwapSequence(int[] arr){
        for (Swap swap : swapVector)
            swap.runSwap(arr);
    }
    public static void main(String[] args){
        SwapSequence n = new SwapSequence(10);
        System.out.println(n.toString());
    }
}
