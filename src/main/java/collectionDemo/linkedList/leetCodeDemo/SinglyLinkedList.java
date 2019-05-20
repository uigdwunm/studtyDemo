package collectionDemo.linkedList.leetCodeDemo;

import java.math.BigDecimal;

/**
 * @Description 单向链表
 * <p>
 * 所有值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库
 * @Author zhaolaiyuan
 * @Date 2019年2019/4/25 13:38
 * @Version 1.0
 **/
public class SinglyLinkedList implements LinkedList {

    private Node headNode;  // 头节点
    private Node tailNode;   // 尾节点
    private int length;     // 数据长度，从1开始

    private class Node {
        private Integer value;
        private Node nextNode;

        public Integer getValue() {
            return this.value;
        }

        public Node getNextNode() {
            return this.nextNode;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setNextNode(Node node) {
            this.nextNode = node;
        }

        Node(Integer value, Node nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }
    }

    SinglyLinkedList() {
        this.headNode = new Node(null, null);
        this.tailNode = headNode;
        this.length = 0;
    }

    private Node getlastNode(int index) {
        Node tempNode = headNode;
        while (index > 0) {
            tempNode = tempNode.getNextNode();
            index--;
        }
        return tempNode;
    }

    public int get(int index) {
        if(index < 0 || index >= length) {
            return -1;
        } else {
            return this.getlastNode(index).getNextNode().getValue();
        }
    }

    public void addAtHead(int val) {
        // 是否0长度链表
        if (length == 0) {
            tailNode = new Node(val, null);
            headNode.setNextNode(tailNode);
            length = 1;
        } else {
            headNode.setNextNode(new Node(val, headNode.getNextNode()));
            length++;
        }
    }

    public void addAtTail(int val) {
        // 是否0长度链表
        if (length == 0) {
            tailNode = new Node(val, null);
            headNode.setNextNode(tailNode);
            length = 1;
        } else {
            tailNode.setNextNode(new Node(val, null));
            tailNode = tailNode.getNextNode();
            length++;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index > 0 && index < length) {
            // index从零开始算，插入
            Node lastNode = this.getlastNode(index);
            Node oldNode = lastNode.getNextNode();
            Node newNode = new Node(val, oldNode);
            lastNode.setNextNode(newNode);
            length++;
        } else if (index == length) {
            // 等于链表长度，加在末尾
            tailNode.setNextNode(new Node(val, null));
            tailNode = tailNode.getNextNode();
            length++;
        } else if (index == 0) {
            // 加在开头
            Node node = new Node(val, headNode.getNextNode());
            headNode.setNextNode(node);
            length++;
        }
        // 超出不做处理
    }

    public void deleteAtIndex(int index) {
        if (index >= 0 && index < length) {
            // 只处理范围内的
            Node lastNode = this.getlastNode(index);
            lastNode.setNextNode(lastNode.nextNode.nextNode);
            length--;
        }
    }

    public static void main(String[] args) {

        SinglyLinkedList s = new SinglyLinkedList();
        s.addAtHead(8);
        System.out.println(s.get(1));
        s.addAtTail(8);
        s.deleteAtIndex(2);
        s.addAtHead(26);
        s.deleteAtIndex(2);
        s.get(1);
        s.addAtTail(24);
    }
}
