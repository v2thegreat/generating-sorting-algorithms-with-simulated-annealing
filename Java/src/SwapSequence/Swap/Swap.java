package SwapSequence.Swap;

public class Swap {
    public int pos1;
    public int pos2;
    public Swap(int pos1InputVal, int pos2InputVal){
        pos1 = pos1InputVal;
        pos2 = pos2InputVal;
    }
    public void runSwap(int[] arr){
        if (pos1 < arr.length || pos2 < arr.length) {
            if (pos1 != pos2 && arr[Math.min(pos1, pos2)] >= arr[Math.max(pos1, pos2)])
                swap(arr, pos1, pos2);
        }
    }
    public void printSwap() {
        System.out.println(pos1 + " " + pos2);
    }
    public boolean isVoid(){
        return pos1 == pos2;
    }
    public String toString(){
        return pos1 + "-" + pos2;
    }
    private static void swap(int[] arr, int a, int b){
        int tempSwapValHolder = arr[a];
        arr[a] = arr[b];
        arr[b] = tempSwapValHolder;
    }
    public static void main(String[] args){
        int[] a = {1,2,3,4};
        int[] b = {4,3,2,1};
        Swap te = new Swap(3,0);
        te.runSwap(a);
        te.runSwap(b);
        for (int i: a)
            System.out.print(i);
        System.out.println();
        for (int i: b)
            System.out.print(i);
    }
}
