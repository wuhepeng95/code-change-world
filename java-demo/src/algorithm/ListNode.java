package algorithm;

/**
 * 链表
 */
public class ListNode {
    Object value;
    ListNode next;

    /**
     * 翻转链表1：while
     * @param listNode
     * @return
     */
    public ListNode reverseListNode(ListNode listNode) {
        // 如果……
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        // 循环
        ListNode curListNode = listNode;
        ListNode tempListNode;
        ListNode resultPreListNode = null;
        while (curListNode != null) {
            // 断开
            tempListNode = curListNode.next;
            curListNode.next = resultPreListNode;
            resultPreListNode = curListNode;
            curListNode = tempListNode;

        }
        return resultPreListNode;
    }

    /**
     * 翻转链表2：递归
     * @param head
     * @return
     */
    ListNode reverse(ListNode head) {
        // 递归
        if (head == null|| head.next == null) {
            return head;
        }

        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 删除某节点
     * @param index
     * @return
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }
        if (index==1){
//            this = this.next;
            return true;
        }
        int length = 2;
        ListNode next = this;
        while (next != null) {
            if (length == index) {
                ListNode temp = next;
                next.next = next.next.next;
                temp.next = next.next;
                return true;
            }
            length++;
            next = next.next;
        }
        return false;
    }

    /**
     * 长度
     * @return
     */
    public int length() {
        int length = 1;
        ListNode next = this.next;
        while (next != null) {
            length++;
            next = next.next;
        }
        return length;
    }
}
