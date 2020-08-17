import explain.HashMap;
import explain.Map;
import explain.concurrent.aqs.Condition;
import explain.concurrent.aqs.rttl.ReentrantLock;
import leetCode.utils.GsonUtils;
import org.omg.CORBA.INTERNAL;

import java.util.*;

public class Solution {
    private class Node{
        int val;
        int left;
        int right;

        public Node(int val, int left, int right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private Node node(int l, int r) {
        return new Node(position[r] - position[l] ,l, r);
    }
    int[] position;
    Node[] nodes;
    TreeSet<Node> treeSet;
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        if (m < 3) {
            return position[position.length - 1] - position[0];
        }
        this.position = position;
        this.treeSet = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                int compare = Integer.compare(o1.val, o2.val);
                if (compare == 0) {
                    compare = Integer.compare(o2.left, o1.left);
                }
                return compare;
            }
        });
        this.nodes = new Node[position.length];
        int i = 1;
        while (i < m) {
            Node node = this.node(i - 1, i);
            treeSet.add(node);
            nodes[i] = node;
            ++i;
        }

        while (i < position.length) {
            int distance = position[i] - position[i - 1];
            if (distance > treeSet.first().val) {
                Node node = treeSet.pollFirst();
                Node newNode = this.node(i - 1, i);

                this.alter(node, newNode);
                ReentrantLock reentrantLock = new ReentrantLock();
                Condition condition = reentrantLock.newCondition();
                condition.signal();
            }
            ++i;
        }

        return treeSet.first().val;
    }

    private void alter(Node o, Node n) {
        this.treeSet.add(n);
        n = nodes[o.left];
        if (n != null) {
            treeSet.remove(n);
            n.right = o.right;
            n.val = position[n.right] - position[n.left];
            this.treeSet.add(n);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxDistance(new int[]{10,4,3,2,1,1000000000}, 3);
        System.out.println(i);
    }
}
