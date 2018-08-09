package com.mmy.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;

/**
 * @author: mmy
 * @date: 2018/08/02
 * @description:
 */

class Interval {

  int start;
  int end;

  Interval() {
    start = 0;
    end = 0;
  }

  Interval(int s, int e) {
    start = s;
    end = e;
  }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
  /**
   * Input: [[1,3],[2,6],[8,10],[15,18]]
   * Output: [[1,6],[8,10],[15,18]]
   * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
   * 35ms
   */
  public class Medium8 {

    public List<Interval> merge(List<Interval> intervals) {
      List<Interval> arrList = new ArrayList(intervals);
      Collections.sort(arrList, new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
          return o1.start-o2.start;
        }
      });
      for (int i =0;i<arrList.size()-1;i++){
        int j =i+1;
        while (j<arrList.size()){
          Interval interval1 =arrList.get(i);
          Interval interval2 = arrList.get(j);
          if (interval1.end>=interval2.start){
            if (interval1.end<=interval2.end){
              interval1.end=interval2.end;
            }
            arrList.remove(j);
          }else {
            j++;
          }
        }
      }
      return arrList;
    }

    /**
     * 9ms anwser
     * @param intervals
     * @return
     */
    public List<Interval> merge2(List<Interval> intervals) {
      int n = intervals.size();
      int[] start = new int[n];
      int[] end = new int[n];
      for(int i=0; i<n; i++){
        start[i] = intervals.get(i).start;
        end[i] = intervals.get(i).end;
      }
      Arrays.sort(start);
      Arrays.sort(end);
      List<Interval> res = new ArrayList();
      int laststart = 0;
      for(int i=0; i<n; i++){
        if(i==n-1 || start[i+1] > end[i]){
          res.add(new Interval(start[laststart], end[i]));
          laststart = i+1;
        }
      }
      return res;
    }

    /**
     * 59. Spiral Matrix II
     * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
      if (n==0){
        return null;
      }
      int[] xMove = new int[]{0,1,0,-1};
      int[] yMove = new int[]{1,0,-1,0};
      boolean[][] visited = new boolean[n][n];
      int[][] result = new int[n][n];
      int x=0,y=0;
      int xOffset=0,yOffset=0;
      int temp =1;
      while (!visited[x][y]){
        result[x][y]=temp++;
        visited[x][y]=true;
        int nextX = x+xMove[xOffset];
        int nextY = y+yMove[yOffset];
        if (nextX==n
            ||nextY==n
            ||nextY==-1
            ||visited[nextX][nextY]){
          xOffset=(xOffset+1)%xMove.length;
          yOffset=(yOffset+1)%yMove.length;
        }
        x=x+xMove[xOffset];
        y=y+yMove[yOffset];
        if (x<0
            ||x>=n
            ||y<0
            ||y>=n){
          break;
        }
      }

      return result;
    }


    /**
     * The set [1,2,3,...,n] contains a total of n! unique permutations.
     *
     * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     * 超时了！
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
      List<String> result = new ArrayList<>();
      boolean[] visited= new boolean[n];
      DFS(result,new StringBuilder(),visited,k,n);
      return result.get(result.size()-1);
    }

    void DFS(List<String> result ,StringBuilder temp ,boolean[] visited ,int k ,int n ){
      if (result.size()==k){
        return;
      }
      for(int i=0;i<visited.length;i++){
        if (!visited[i]){
          temp.append(i+1);
          visited[i]=true;
          DFS(result,new StringBuilder(temp),visited,k,n);
          temp.deleteCharAt(temp.length()-1);
          visited[i]=false;
        }
      }
      if (temp.length()==n){
        result.add(temp.toString());

      }

    }

    /**
     * 快解法
     * 依靠结构来计算
     * 例如，n个数能凑成的字符为n！个
     * 一个一个的确定结果的每一个字符
     * 如何确定一个位置的字符后，此时最小的位置都会是a*(n-1)!+b*(n-2)!+...
     * 然后用k减去，依次类推
     * 真他妈人才！
     * @param n
     * @param k
     * @return
     */
    public String getPermutation2(int n, int k) {
      int pos = 0;
      List<Integer> numbers = new ArrayList<>();
      int[] factorial = new int[n+1];
      StringBuilder sb = new StringBuilder();

      // create an array of factorial lookup
      int sum = 1;
      factorial[0] = 1;
      for(int i=1; i<=n; i++){
        sum *= i;
        factorial[i] = sum;
      }
      // factorial[] = {1, 1, 2, 6, 24, ... n!}

      // create a list of numbers to get indices
      for(int i=1; i<=n; i++){
        numbers.add(i);
      }
      // numbers = {1, 2, 3, 4}

      k--;

      for(int i = 1; i <= n; i++){
        int index = k/factorial[n-i];
        sb.append(String.valueOf(numbers.get(index)));
        numbers.remove(index);
        k-=index*factorial[n-i];
      }

      return String.valueOf(sb);
    }


    /**
     * Given a linked list, rotate the list to the right by k places, where k is non-negative.
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
      ListNode node = head;
      ListNode tail = head;
      int length = 0;
      while (node!=null){
        length++;
        node=node.next;
        if (node!=null){
          tail=node;
        }
      }
      if (length==0){
        return head;
      }
      int rotate = k%length;
      if (rotate==0){
        return head;
      }
      int index = 0;
      ListNode rotateNode = head;
      ListNode preRotareNode=rotateNode;
      while (index+rotate!=length){
        preRotareNode=rotateNode;
        rotateNode=rotateNode.next;
        index++;
      }
      preRotareNode.next=null;
      tail.next=head;
      return  rotateNode;
    }


    /**
     *BackTrace TLS
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
      if (m==0||n==0){
        return 0;
      }
      boolean[][] map = new boolean[m][n];
      map[0][0] = true;
      Count count=new Count(0);
      searchHelper(map,0,0,count);
      return count.count;
    }


    void searchHelper(boolean[][] map,int m,int n,Count count ){
      if (!map[map.length-1][map[0].length-1]){
        if (m+1<map.length&&!map[m+1][n]){
          map[m+1][n]=true;
          searchHelper(map,m+1,n,count);
          map[m+1][n]=false;
        }
        if(n+1<map[0].length&&!map[m][n+1]){
          map[m][n+1]=true;
          searchHelper(map,m,n+1,count);
          map[m][n+1]=false;
        }
      }else {
        count.count++;
      }
    }

    class Count{
      int count;
      Count(int val){
        count=val;
      }
    }


    /**
     * try dynamic programing
     * 由于两种路径来源不会有交集，所以直接加即可
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n){
      int[][] result = new int[m][n];
      for (int i =0;i<m;i++){
        result[i][0]=1;
      }
      for (int j =0;j<n;j++){
        result[0][j]=1;
      }
      for (int i =1 ; i<m;i++){
        for (int j =1;j<n;j++){
          result[i][j]=result[i-1][j]+result[i][j-1];
        }
      }
      return result[m-1][n-1];
    }




    @Test
    public void test(){
//      Interval i1 = new Interval(2,6);
//      Interval i2 = new Interval(1,3);
//      Interval i3 = new Interval(8,10);
//      Interval i4 = new Interval(15,18);
//      List<Interval> list = new ArrayList<>();
//      list.add(i1);
//      list.add(i2);
//      list.add(i3);
//      list.add(i4);
//      List<Interval> result  = merge(list);

//      String result =  getPermutation(5,6);


//      ListNode node1 = new ListNode(1);
//      ListNode node2 = new ListNode(2);
//      ListNode node3 = new ListNode(3);
//      ListNode node4 = new ListNode(4);
//      ListNode node5 = new ListNode(5);
//      node1.next=node2;
//      node2.next=node3;
//      node3.next=node4;
//      node4.next=node5;
//      ListNode result = rotateRight(node1,2);

        int m =7 ;
        int n =3;
        int result = uniquePaths2(m,n);
    }


  }


