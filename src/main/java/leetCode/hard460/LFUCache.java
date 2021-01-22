package leetCode.hard460;

import explain.map.HashMap;

public class LFUCache {

    private class Node {
        int count;
        Node last;
        Node next;
        ListNode head;
        ListNode tail;
    }
    private class ListNode {
        ListNode last;
        ListNode next;
        Node countNode;
        int key;
        int value;
    }

    // 链表的尾节点，是空节点，要删除就删这个对应的上一个，这里只有统计数量有意义，值没意义
    private Node tail;
    // 这里存的是真正的值对应的节点
    private HashMap<Integer, ListNode> valueMap;

    private final int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        tail = new Node();
        valueMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        // 访问次数加一
        ListNode listNode = valueMap.get(key);
        if (listNode == null) {
            return -1;
        }
        this.addCount(listNode);
        return listNode.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        ListNode listNode = valueMap.get(key);
        if (listNode != null) {
            // 已有的值，修改
            listNode.value = value;
            this.addCount(listNode);
            return;
        }
        if (valueMap.size() == capacity) {
            // 上限了需要删一个
            this.removeTail();
        }
        // 新建
        listNode = new ListNode();
        listNode.key = key;
        listNode.value = value;
        listNode.countNode = this.tail;
        valueMap.put(key, listNode);
        this.addCount(listNode);

    }

    // 已有的key访问次数加1
    private void addCount(ListNode listNode) {
        Node countNode = listNode.countNode;
        int count = countNode.count;
        count++;
        Node lastCount = countNode.last;
        // 接触这个节点所有的关系
        countNode = this.remove(listNode);

        if (lastCount != null && lastCount.count == count) {
            // 前一个存在
            lastCount.head.last = listNode;
            listNode.next = lastCount.head;
            lastCount.head = listNode;
        } else {
            // 前一个要新建
            Node node = new Node();
            node.count = count;
            node.last = lastCount;
            node.next = countNode;
            node.head = listNode;
            node.tail = listNode;
            if (lastCount != null) {
                lastCount.next = node;
            }
            countNode.last = node;
            lastCount = node;
        }
        listNode.countNode = lastCount;
    }

    // 调用这个方法保证，至少有一个值
    private void removeTail() {
        Node removeNode = this.tail.last;
        ListNode remove = removeNode.tail;
        this.remove(remove);
        valueMap.remove(remove.key);
    }

    /**
     * Description 接触这个节点的所有关系
     *
     * @author zhaolaiyuan
     * Date 2021/1/11 17:02
     **/
    private Node remove(ListNode remove) {
        Node removeNode = remove.countNode;
        if (removeNode == this.tail) {

        } else if (removeNode.head  == removeNode.tail) {
            // 只有一个，全删了，
            if (removeNode.last == null) {
                removeNode.next.last = null;
            } else {
                removeNode.last.next = removeNode.next;
                removeNode.next.last = removeNode.last;
            }
            return removeNode.next;
        } else if (remove == removeNode.head) {
            removeNode.head = remove.next;
            if (remove.next != null) {
                remove.next.last = null;
            }
        } else if (remove == removeNode.tail) {
            removeNode.tail = remove.last;
            if (remove.last != null) {
                remove.last.next = null;
            }
        } else {
            // 只删一个节点
            remove.last.next = remove.next;
            remove.next.last = remove.last;
        }

        return removeNode;
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(1);
        cache.put(2,1);
        System.out.println(cache.get(2));
        cache.put(3,2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}
