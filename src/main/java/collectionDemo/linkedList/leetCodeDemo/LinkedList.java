package collectionDemo.linkedList.leetCodeDemo;

public interface LinkedList {

    /*
     * Description 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
     * @Author zhaolaiyuan
     * @Date 2019/4/25 13:20
     * @Param [index]
     * @return int
     **/
    int get(int index);

    /*
     * Description 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
     * @Author zhaolaiyuan
     * @Date 2019/4/25 13:21
     * @Param [val]
     * @return void
     **/
    void addAtHead(int val);

    /*
     * Description 将值为 val 的节点追加到链表的最后一个元素。
     * @Author zhaolaiyuan
     * @Date 2019/4/25 13:22
     * @Param [val]
     * @return void
     **/
    void addAtTail(int val);

    /*
     * Description 在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
     * @Author zhaolaiyuan
     * @Date 2019/4/25 13:36
     * @Param [index, val]
     * @return void
     **/
    void addAtIndex(int index, int val);

    /*
     * Description 如果索引 index 有效，则删除链表中的第 index 个节点。
     * @Author zhaolaiyuan
     * @Date 2019/4/25 13:37
     * @Param [index]
     * @return void
     **/
    void deleteAtIndex(int index);
}
