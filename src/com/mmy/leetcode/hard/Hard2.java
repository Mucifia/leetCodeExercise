package com.mmy.leetcode.hard;

import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/06/13
 * @description:
 */
public class Hard2 {

  public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode copy=head;
    ListNode num = head;
    int j;
    for(j=0;j<k;j++){
      if (num==null)break;
      num=num.next;
    }
    if (j!=k)return head;
    ListNode temp = new ListNode(-1);
    temp.next=head;
    ListNode result = temp;
    while (copy!=null){
      Partition(temp,copy,k);
      ListNode a = copy;
      int i;
      for (i=0;i<k;i++){
        if (a.next==null){
          break;
        }
          a=a.next;
          temp=temp.next;
      }
      if (i!=k){
        break;
      }
      copy=copy.next;
    }
    return result.next;
  }

  public void Partition(ListNode nodeBefore,ListNode node,int k){
    ListNode newNode = node;
    ListNode pre=node;
    node = node.next;
    for (int i =1; i<k;i++){
        ListNode next = node.next;
        newNode = reverse(newNode,pre,node);
        node= next;
    }
    nodeBefore.next=newNode;
  }


  public ListNode reverse(ListNode node1S,ListNode node1E,ListNode node2){
    ListNode listNode = new ListNode(-1);
    ListNode copy = listNode;
    node1E.next = node2.next;
    node2.next = node1S;
    listNode.next=node2;
    return copy.next;
  }

  /**
   * another answer on billboard. use Recursion
   * @param head
   * @param k
   * @return
   */
  public ListNode reverseKGroup2(ListNode head, int k) {
    ListNode curr = head;
    int count = 0;
    while (curr != null && count != k) { // find the k+1 node
      curr = curr.next;
      count++;
    }
    if (count == k) { // if k+1 node is found
      curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
      // head - head-pointer to direct part,
      // curr - head-pointer to reversed part;
      while (count-- > 0) { // reverse current k-group:
        ListNode tmp = head.next; // tmp - next head in direct part
        head.next = curr; // preappending "direct" head to the reversed list
        curr = head; // move head of reversed part to a new node
        head = tmp; // move "direct" head to the next node in direct part
      }
      head = curr;
    }
    return head;
  }





  @Test
  public void test(){
    ListNode node1 = new ListNode(1);
    node1.next = new ListNode(2);
    node1.next.next = new ListNode(3);
    node1.next.next.next = new ListNode(4);
    node1.next.next.next.next = new ListNode(5);
//    node1.next.next.next.next.next = new ListNode(6);
//    node1.next.next.next.next.next.next = new ListNode(1);
    ListNode result = reverseKGroup(node1,2);
  }


}
