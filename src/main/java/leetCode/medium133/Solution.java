package leetCode.medium133;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
        public void add(Node ... nodes) {
            neighbors = Stream.of(nodes).collect(Collectors.toList());
        }
    }

    private final Node[] verify = new Node[101];

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node result = new Node(node.val);
        this.copy(node, result);

        return result;
    }

    public void copy(Node o, Node n) {
        int val = n.val;
        if (verify[val] != null) {
            // 已经走过这里
            return;
        }
        verify[val] = n;
        List<Node> nNeighbors = n.neighbors;
        for (Node node : o.neighbors) {
            int vv = node.val;
            Node next = verify[vv];
            if (next == null) {
                next = new Node(vv);
            }
            nNeighbors.add(next);
            this.copy(node, next);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.add(n2, n4);
        n2.add(n1, n3);
        n3.add(n2, n4);
        n4.add(n1, n3);

        Node node = solution.cloneGraph(n1);
    }
}
