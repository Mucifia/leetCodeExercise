package com.mmy.leetcode.hard;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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


  /**
   * You are given a string, s, and a list of words, words, that are all of the same length.
   * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
   *
   * 额，500多ms，差距有点大 第一版ac
   * @param s
   * @param words
   * @return
   */
  public List<Integer> findSubstring(String s, String[] words) {
    List<String>  stringList = new ArrayList<>(Arrays.asList(words));
    List<Integer> result = new ArrayList<>();
    if (s.length()==0||words.length==0){
      return result;
    }
    int wordNum = words.length;
    int wordLeng = words[0].length();
    for (int i =0;i<=s.length()-wordNum*wordLeng;i++){
      List<String> stringListCopy = new ArrayList<>(stringList);
      for (int j = 0;j<wordNum;j++){
        String subs = s.substring(i+j*wordLeng,i+(j+1)*wordLeng);
        if (!stringListCopy.contains(subs)){
          break;
        }
        stringListCopy.remove(subs);
      }
      if (stringListCopy.isEmpty()){
        result.add(i);
      }
    }
    return result;
  }

  /**
   * cause im suck, so i decide to optimize this algorithm
   * slide windows algorithm , decrease find times;
   * 33ms
   * @param s
   * @param words
   * @return
   */
  public List<Integer> findSubstring2(String s,String[] words){
    List<Integer> result = new ArrayList<>();
    if (words.length==0){
      return result;
    }
    int wordlen = words[0].length();
    Map<String,Integer> searchItems = new HashMap<>();
    for (String word : words){
      searchItems.put(word,searchItems.getOrDefault(word,0)+1);
    }
    int left,right;
    for (int i =0;i<wordlen;i++){
      //side windows  initialize wordlen times
      //cause we slide step is wordlen ,so we start wordlen times we can find all over occur.
      int matched=0;//how many items in words we matched
      Map<String,Integer> mapCopy = new HashMap<>(searchItems);//an map copy
      left=right=i;//index where we start
      while (left+(words.length-matched)*wordlen<=s.length()&&right+wordlen<=s.length()){
        String word = s.substring(right,right+wordlen);//we slide right pointer
        if (mapCopy.containsKey(word)){
          if (mapCopy.get(word)==0){
            //has mathed enough times
            //so we slide left pointer to try whether can match with this word;
//            String string = s.substring(left,left+wordlen);
//            mapCopy.put(string,mapCopy.get(string)+1);
//            matched--;
//            left=left+wordlen;
            //or we slide left pointer over  this same word : daum this save about 3ms
            String string = s.substring(left,left+wordlen);
            while (!word.equals(string)){
              left=left+wordlen;
              mapCopy.put(string,mapCopy.get(string)+1);
              matched--;
              string=s.substring(left,left+wordlen);
            }
            left=left+wordlen;
            right=right+wordlen;
          }else {
            //got right word
            right=right+wordlen;
            mapCopy.put(word,mapCopy.get(word)-1);
            matched++;
          }
          if(matched==words.length){
            //match finish
            //we slide left pointer to try wheter can we match with new word;
            result.add(left);
            word = s.substring(left, left + wordlen);
            mapCopy.put(word, mapCopy.get(word) + 1);
            left += wordlen;
            matched--;
          }
        }else {
          //unrelative words
          //rePoint pointers
          while (left<=right-wordlen){
            String string = s.substring(left,left+wordlen);
            mapCopy.put(string,mapCopy.get(string)+1);
            matched--;
            left=left+wordlen;
          }
          right=right+wordlen;
          left=right;

        }
      }
    }
    return result;
  }



  @Test
  public void test(){
    List<Integer> resulst = findSubstring2("foorbarfoosssssbarfoo",new String[]{"foo","bar"});
  }


}
