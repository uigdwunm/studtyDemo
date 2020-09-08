package leetCode.medium347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    private class Node {
        private final int val;
        private int count;
        private Node prev;
        private Node next;

        private Node(int val) {
            this.val = val;
            this.count = 1;
        }
        private Node(int val, int count) {
            this.val = val;
            this.count = count;
        }

        private void increase() {

            record.compute(this.count, (k, v) -> {
                if (v == this) {
                    if (v.next.count == k) {
                        return v.next;
                    }
                    return null;
                }
                return v;
            });

            ++this.count;
            record.compute(this.count, (k, v) -> v == null ? this : v);

            Node target = this.find(this.count);

            if (target != this) {
                record.compute(target.count, (k, v) -> {
                    if (v.next.count == k) {
                        return v.next;
                    }
                    return v;
                });
                // 交换位置
                if (target == this.prev) {
                    // 挨着的情况
                    Node p = this.prev;
                    Node pp = p.prev;
                    Node n = this.next;

                    pp.next = this;
                    this.prev = pp;

                    p.next = n;
                    n.prev = p;

                    this.next = p;
                    p.prev = this;
                } else {
                    // 不挨着的情况
                    this.swap(this, target);
                }
            }

        }

        private void swap(Node a, Node b) {
            Node ap = a.prev;
            Node an = a.next;
            Node bp = b.prev;
            Node bn = b.next;

            ap.next = b;
            b.prev = ap;
            an.prev = b;
            b.next = an;

            bp.next = a;
            a.prev = bp;
            bn.prev = a;
            a.next = bn;
        }

        private Node find(int count) {
            int pc = this.prev.count;
            if (pc < count) {
                return record.get(pc);
            }
            return this;
        }
    }

    private Map<Integer, Node> map;
    private Map<Integer, Node> record;

    private final Node tail = new Node(0, Integer.MIN_VALUE);

    public int[] topKFrequent(int[] nums, int k) {
        map = new HashMap<>(k);
        record = new HashMap<>(k);
        Node head = new Node(0, Integer.MAX_VALUE);
        this.tail.prev = head;
        head.next = this.tail;
        for (int num : nums) {
            map.compute(num, (kk, v) -> {
                if (v == null) {
                    return this.add(num);
                } else {
                    v.increase();
                    return v;
                }
            });
        }

        int[] result = new int[k];
        --k;
        head = head.next;
        while (k > -1) {
            result[k] = head.val;
            head = head.next;
            --k;
        }
        return result;
    }

    private Node add(int val) {
        Node node = new Node(val);
        record.compute(1, (k, v) -> v == null ? node : v);

        Node p = this.tail.prev;
        p.next = node;
        node.prev = p;

        node.next = this.tail;
        this.tail.prev = node;

        return node;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        int[] ints = solution.topKFrequent(nums, 10);
        System.out.println(Arrays.toString(ints));
    }
}
