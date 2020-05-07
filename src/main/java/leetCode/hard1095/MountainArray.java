package leetCode.hard1095;

public class MountainArray {

    private final int[] arr;


    public MountainArray(int[] arr) {
        this.arr = arr;
    }

    public int get(int index) {
        return arr[index];
    }

    public int length() {
        return arr.length;
    }
}
