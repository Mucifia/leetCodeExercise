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

    @Test
    public void test(){
      Interval i1 = new Interval(2,6);
      Interval i2 = new Interval(1,3);
      Interval i3 = new Interval(8,10);
      Interval i4 = new Interval(15,18);
      List<Interval> list = new ArrayList<>();
      list.add(i1);
      list.add(i2);
      list.add(i3);
      list.add(i4);
      List<Interval> result  = merge(list);

    }


  }


