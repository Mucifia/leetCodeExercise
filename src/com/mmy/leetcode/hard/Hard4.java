package com.mmy.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;
import sun.nio.cs.ext.MacArabic;

/**
 * @author: mmy
 * @date: 2018/08/03
 * @description:
 */
public class Hard4 {

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
   * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
   *
   * You may assume that the intervals were initially sorted according to their start times.
   * @param intervals
   * @param newInterval
   * @return
   */
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new ArrayList<>();
    Interval combine = new Interval(newInterval.start,newInterval.end);
    for (Interval i : intervals){
      if (combine.end<=i.end){
        if (combine.end>=i.start){
          combine.start = Math.min(combine.start,i.start);
          combine.end = i.end;
        }else {
          result.add(i);
        }
      }else{
        if (combine.start>i.end){
          result.add(i);
        }else {
          combine.start = Math.min(i.start,combine.start);
        }
      }
    }
    result.add(combine);
    Collections.sort(result, new Comparator<Interval>() {
      @Override
      public int compare(Interval o1, Interval o2) {
        return  o1.start-o2.start;
      }
    });
    return result;
  }

  @Test
  public void test(){
    Interval i1 = new Interval(1,2);
    Interval i2 = new Interval(3,5);
    Interval i3 = new Interval(6,7);
    Interval i4 = new Interval(8,10);
    Interval i5 = new Interval(12,16);
    Interval i6 = new Interval(4,8);
    List<Interval> list = new ArrayList<>();
    list.add(i1);
    list.add(i2);
    list.add(i3);
    list.add(i4);
    list.add(i5);
    List<Interval> result  = insert(list,i6);
  }

}
