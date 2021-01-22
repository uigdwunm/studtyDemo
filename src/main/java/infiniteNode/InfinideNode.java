package infiniteNode;

import java.util.*;

/**
 * Description 对某个无限层级节点方案的扩展（名字忘了），用long类型存左右值，流出大量空隙
 *
 * @author zhaolaiyuan
 * Date 2021/3/14 20:48
 **/
public class InfinideNode<T> {

    // 节点上的数据
    private final T data;

    // 结构关系维护
    // 父节点，如果是根节点则为null
    private InfinideNode<T> parent;
    // 子节点
    private LinkedList<InfinideNode<T>> children;

    // 左节点不一定从根节点开始(索引0)，因为根节点可能会换(那么左节点可能为负数)
    // 逻辑关系维护，边界则为null
    // 顺序左节点
    private InfinideNode<T> leftNode;
    // 顺序右节点
    private InfinideNode<T> rightNode;

    // 当前节点左边的数
    private long left;
    // 当前节点右边的数
    private long right;

    // 结构关系其下所有节点数量（包括自己）(每次变更节点都要更新)
    private int count;

    public InfinideNode(T data, InfinideNode<T> parent, LinkedList<InfinideNode<T>> children, InfinideNode<T> leftNode, InfinideNode<T> rightNode, long left, long right, int count) {
        this.data = data;
        this.parent = parent;
        this.children = children;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.left = left;
        this.right = right;
        this.count = count;
    }

    // 更新统计其下所有节点数量（包括自己）
//    public int updateCount() {
//        int count = 1;
//        for (InfinideNode<T> child : children) {
//            count += child.updateCount();
//        }
//        this.count = count;
//        return count;
//    }
    private void incrCount(int n) {
        this.count += n;
        if (this.parent != null) {
            this.parent.incrCount(n);
        }
    }

    /**
     * Description 返回的是 添加这个节点需要移动修改的节点
     *
     * @author zhaolaiyuan
     * Date 2021/1/15 17:45
     **/
//    public List<InfinideNode<T>> addNode(T ... datas) {
//        int l = datas.length;
//        if (l == 0) {
//            return Collections.emptyList();
//        }
//
//        // 当前使用的间隔值
//        long interval = this.right - this.left;
//
//        LinkedList<InfinideNode<T>> children = this.children;
//        if (children == null) {
//            children = new LinkedList<>();
//            this.children = children;
//        }
//
//        for (T data : datas) {
//            InfinideNode<T> leftNode;
//            if (this.children.isEmpty()) {
//                leftNode = this.parent;
//            } else {
//                leftNode = this.children.getLast();
//            }
//
//            InfinideNode<T> rightNode = this.parent;
//
//            InfinideNode<T> node = new InfinideNode<>(
//                    data,
//                    this,
//                    new LinkedList<>(),
//                    leftNode,
//                    rightNode,
//
//
//                    );
//
//            children.add(node);
//        }
//        // 增加统计
//        this.incrCount(l);
//    }

    /**
     * Description 插入新节点时，重新调整间隙
     *
     * @param node 调节的起始点，从这个节点向右进行调整，直到有足够的空隙
     * @param expect 期望调整后得到的空隙数量
     *
     * @author zhaolaiyuan
     * Date 2021/1/18 9:11
     **/
//    private List<InfinideNode<T>> regapForInsert(InfinideNode<T> node, int expect) {
//        InfinideNode<T> curr = node;
//        InfinideNode<T> next = curr.rightNode;
//        List<InfinideNode<T>> updateList = new LinkedList<>();
//        long gap = next.left - curr.right;
//        while () {
//            if (next.rightNode == null) {
//                // 到了末节点，干脆就重新调整所有节点
//
//            }
//        }
//    }

//    public void addNode(Collection<T> datas) {
//        LinkedList<InfinideNode<T>> children = this.children;
//        if (children == null) {
//            children = new LinkedList<>();
//            this.children = children;
//        }
//        children.addAll(nodes);
//        // 增加统计
//        this.incrCount(nodes.size());
//    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    static private class Builder<T> {

    }
}