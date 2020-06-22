package leetCode.medium146;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LRUCache {
    private HashMap<Integer, Node> map;
    private Node head = null;
    private Node tail = null;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node node = this.map.get(key);
        if (node == null) {
            return -1;
        }
        this.toHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (head == null) {
            if (capacity == 0) {
                return;
            }
            Node node = new Node(key, value);
            // 新建状态
            map.put(key, node);
            head = node;
            tail = node;
            return;
        }
        if (key == this.head.key) {
            // 正好是链头
            map.get(key).val = value;
            return;
        }
        if (this.head == this.tail) {
            // 只有一个的时候插入第二个
            Node node = new Node(key, value);
            map.put(key, node);
            this.addHead(node);
            if (map.size() > this.capacity) {
                // 删掉最后一个
                map.remove(this.tail.key);
                this.removeTail();
            }
            return;
        }
        if (key == this.tail.key) {
            // 正好是链尾
            this.addHead(tail);
            this.removeTail();
            map.get(key).val = value;
            return;
        }


        Node node = map.get(key);
        if (node != null) {
            // 已存在的节点
            node.val = value;
            this.toHead(node);
            return;
        }

        // 新节点，而且此时至少有两个已存在的
        node = new Node(key, value);
        map.put(key, node);
        // 调整链
        this.addHead(node);
        if (map.size() > this.capacity) {
            // 删掉最后一个
            map.remove(this.tail.key);
            this.removeTail();
        }
    }

    private void toHead(Node node) {
        if (node.last != null) {
            // 调整链
            if (node == this.tail) {
                this.tail = tail.last;
            }
            Node temp = node.next;
            node.next = head;
            node.last.next = temp;
            if (temp != null) {
                temp.last = node.last;
            }
            node.last = null;
            head = node;
            head.next.last = this.head;
        }

    }

    private void addHead(Node node) {
        this.head.last = node;
        node.next = head;
        this.head = node;
    }
    private void removeTail() {
        this.tail = tail.last;
        tail.next = null;
    }

    private class Node {
        private int val;
        private final int key;

        private Node last;
        private Node next;

        private Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(10);
        cache.put(7,7);
        cache.put(7,77);
        cache.put(8,8);
        System.out.println(cache.get(6));
        cache.put(10,10);
        cache.put(8,88);
        System.out.println(cache.get(8));
        cache.put(6,6);
        cache.put(1,1);
        System.out.println(cache.get(6));
        cache.put(10,1010);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(13));
        cache.put(8,8);
        cache.put(1,11);
        System.out.println(cache.get(1));
        cache.put(13,13);
        System.out.println(cache.get(12));

    }
}
