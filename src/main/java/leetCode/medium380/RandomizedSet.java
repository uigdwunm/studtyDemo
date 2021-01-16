package leetCode.medium380;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
    private Map<Integer, Integer> map = new HashMap<>();
    private int[] vals = new int[2];
    private int size = 0;
    private Random r = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, size);
            this.add(val);
            return true;
        }
        return false;
    }

    private void add(int val) {
        if (this.size == this.vals.length) {
            int[] copy = new int[size * 2];
            System.arraycopy(this.vals, 0, copy, 0, this.vals.length);
            this.vals = copy;
        }
        vals[size] = val;
        ++size;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = map.remove(val);
        if (index != null) {
            this.delete(index);
            return true;
        }
        return false;
    }
    private void delete(int index) {
        --size;
        if (size == 0) {
            return;
        }
        if (index != size) {
            this.vals[index] = this.vals[size];
            map.put(vals[index], index);
        }

        if (this.vals.length == size * 2) {
            int[] copy = new int[size];
            System.arraycopy(this.vals, 0, copy, 0, copy.length);
            this.vals = copy;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int i = r.nextInt(size);
        return vals[i];
    }

    public static void main(String[] args) {
        RandomizedSet randomSet = new RandomizedSet();


        System.out.println(randomSet.insert(0));
        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.insert(3));
        System.out.println(randomSet.insert(4));
        System.out.println(randomSet.insert(5));

        System.out.println(randomSet.remove(5));
        System.out.println(randomSet.remove(4));
        System.out.println(randomSet.remove(3));
        System.out.println(randomSet.remove(2));
        System.out.println(randomSet.remove(1));

        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.insert(3));
        System.out.println(randomSet.insert(4));
        System.out.println(randomSet.insert(5));
    }
}
