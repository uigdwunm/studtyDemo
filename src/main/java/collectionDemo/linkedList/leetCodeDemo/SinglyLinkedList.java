package collectionDemo.linkedList.leetCodeDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", nextNode=" + nextNode +
                    '}';
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
        if (index < 0 || index >= length) {
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
            this.addAtTail(val);
        } else if (index <= 0) {
            // 如果等于或小于0，则加在开头
            this.addAtHead(val);
        }
        // 超出不做处理
    }

    public void deleteAtIndex(int index) {
        // 只处理范围内的
        if (index >= 0 && index < length - 1) {
            Node lastNode = this.getlastNode(index);
            lastNode.setNextNode(lastNode.nextNode.nextNode);
            length--;
        } else if (index == length - 1) {
            // 如果是最后一个，要把尾节点指过来
            Node lastNode = this.getlastNode(index);
            lastNode.setNextNode(null);
            tailNode = lastNode;
            length--;
        }
    }


















    // 测试信息

    private void print() {
        System.out.println("leangh:" + length);
        System.out.println("tail:" + tailNode);
        System.out.println("head:" + headNode);
        Node tempNode = headNode.nextNode;
        int temp = 0;
        while (tempNode != null) {
            System.out.print(temp + ":" + tempNode + ", ");
            System.out.println();
            tempNode = tempNode.nextNode;
            temp++;

        }
    }

    private static void invoke(Method[] methods, Object o, String methodName, Object... args) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                method.invoke(o, args);
            }
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        SinglyLinkedList o = new SinglyLinkedList();

        Method[] methods = o.getClass().getMethods();

        String methodArr = "[\"MyLinkedList\",\"addAtHead\",    \"get\",          \"addAtTail\",     \"deleteAtIndex\",\"addAtHead\",     \"deleteAtIndex\",\"get\",          \"addAtTail\",     \"addAtHead\",     \"addAtTail\",    \"addAtTail\",     \"addAtTail\",    \"addAtIndex\",  \"get\",      \"addAtIndex\",   \"addAtHead\",   \"deleteAtIndex\",   \"addAtIndex\",   \"addAtHead\",   \"addAtIndex\",   \"deleteAtIndex\",   \"get\",   \"addAtTail\",   \"deleteAtIndex\",   \"deleteAtIndex\",   \"addAtTail\",   \"addAtTail\",   \"addAtIndex\",   \"addAtHead\",   \"get\",   \"get\",   \"addAtTail\",   \"addAtTail\",   \"addAtTail\",   \"addAtTail\",   \"addAtIndex\",   \"addAtIndex\",   \"addAtHead\",   \"addAtIndex\",   \"addAtTail\",   \"addAtHead\",   \"addAtHead\",   \"addAtHead\",   \"addAtHead\",   \"addAtHead\",   \"addAtHead\",   \"addAtTail\",   \"addAtHead\",   \"deleteAtIndex\",   \"addAtHead\",   \"get\",   \"addAtHead\",   \"get\",   \"addAtHead\",   \"addAtHead\",   \"addAtHead\",   \"addAtIndex\",   \"deleteAtIndex\",   \"addAtTail\",   \"deleteAtIndex\",   \"get\",   \"addAtIndex\",   \"addAtHead\",   \"addAtTail\",   \"deleteAtIndex\",   \"addAtHead\",   \"addAtIndex\",   \"deleteAtIndex\",   \"deleteAtIndex\",   \"deleteAtIndex\",   \"addAtHead\",   \"addAtTail\",   \"addAtTail\",   \"addAtHead\",   \"addAtTail\",   \"addAtIndex\",   \"deleteAtIndex\",   \"deleteAtIndex\",   \"addAtIndex\",   \"addAtHead\",   \"addAtHead\",   \"addAtTail\",   \"get\",   \"addAtIndex\",   \"get\",   \"addAtHead\",   \"addAtHead\",   \"addAtHead\",   \"addAtIndex\",   \"addAtIndex\",   \"get\",   \"addAtHead\",   \"get\",   \"get\",   \"addAtTail\",   \"addAtHead\",   \"addAtHead\",   \"addAtTail\",   \"addAtTail\",   \"get\",   \"addAtTail\"]";
        String methodArgs = "[[],            [8],            [1],            [81],            [2],            [26],            [2],            [1],            [24],            [15],            [0],            [13],            [1],            [6,33],         [6],       [2,91],         [82],          [6],               [4,11],         [3],            [7,14],            [1],            [6],    [99],          [11],              [7],               [5],           [92],          [7,92],         [57],          [2],     [6],     [39],          [51],          [3],           [22],          [5,26],         [9,52],          [69],         [5,58],         [79],          [7],            [41],          [33],         [88],          [44],          [8],           [72],          [93],          [18],              [1],           [9],     [46],          [9],     [92],          [71],          [69],          [11,54],        [27],              [83],          [12],              [20],    [19,97],        [77],          [36],          [3],               [35],          [16,68],        [22],              [36],              [17],              [62],          [89],          [61],          [6],           [92],          [28,69],        [23],              [28],              [7,4],          [0],            [24],         [52],          [1],     [23,3],         [7],     [6],           [68],          [79],          [45,90],        [41,52],        [28],    [25],          [9],     [32],    [11],          [90],          [24],          [98],          [36],          [34],    [26]]";

        List<String> mArr = getMArrList(methodArr);
        List<Object[]> argsArrList = getArgsArrList(methodArgs);
        for (int i = 0; i <mArr.size() ; i++) {
            System.out.println(mArr.get(i) + "   " + Arrays.toString(argsArrList.get(i)));
            invoke(methods, o, mArr.get(i), argsArrList.get(i));
        }

//        s.addAtHead(1);
        o.print();


    }

    private static List<String> getMArrList(String methodArr) {
        List<String> mArr = new ArrayList<String>();
        int i = methodArr.indexOf("\"") + 1;
        while (i > 0) {
            methodArr = methodArr.substring(i);
            i = methodArr.indexOf("\"");
            mArr.add(methodArr.substring(0, i));
            methodArr = methodArr.substring(i + 1);
            i = methodArr.indexOf("\"") + 1;
        }
        return mArr;
    }

    private static List<Object[]> getArgsArrList(String methodArr) {
        List<Object[]> argsArr = new ArrayList<Object[]>();
        int i = methodArr.indexOf("[") + 1;
        methodArr = methodArr.substring(i);
        i = methodArr.indexOf("[") + 1;
        while (i > 0) {
            methodArr = methodArr.substring(i);
            i = methodArr.indexOf("]");
            String args = methodArr.substring(0, i);
            if (args.indexOf(',') != -1) {
                String[] split = args.split(",");
                Object[] objects = new Object[split.length];
                for (int j = 0; j <split.length; j++) {
                    objects[j] = Integer.valueOf(split[j]);
                }
                argsArr.add(objects);
            } else {
                if ("".equals(args)) {
                    argsArr.add(null);
                } else {
                    argsArr.add(new Object[]{Integer.valueOf(Integer.valueOf(args))});
                }
            }

            methodArr = methodArr.substring(i + 1);
            i = methodArr.indexOf("[") + 1;
        }
        return argsArr;
    }
}
