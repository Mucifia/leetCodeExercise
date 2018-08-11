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


  /**
   *Validate if a given string is numeric.
   Some examples:
   "0" => true
   " 0.1 " => true
   "abc" => false
   "1 a" => false
   "2e10" => true
   只通过了 1342/1421
   *
   * @param s
   * @return
   */
  public boolean isNumber(String s) {
    String sTrim = s.trim();
    char[] sChars = sTrim.toCharArray();
    boolean isValid =false;
    int dot = 0;
    int e =0;
    for (int i =0;i<sChars.length;i++){
      if (sChars[i]>='0'&&sChars[i]<='9'){
          isValid=true;
      }else if (sChars[i]=='.'){
        if (dot==2){
          return false;
        }else if (i==0){
          dot++;
        }else if (sChars[i-1]<'0'||sChars[i-1]>'9'){
          return false;
        }else {
          dot++;
        }
      }else if (sChars[i]=='e'){
        if (e==1){
          return false;
        }else if (i==0){
          return false;
        }else if (sChars[i-1]<'0'||sChars[i-1]>'9'){
          return false;
        }else {
          e++;
        }
      }else {
        return false;
      }
    }
    return isValid;
  }

  public boolean isNumber2(String s) {
    s = s.trim();

    boolean pointSeen = false;
    boolean eSeen = false;
    boolean numberSeen = false;
    boolean numberAfterE = true;
    for(int i=0; i<s.length(); i++) {
      if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
        numberSeen = true;
        numberAfterE = true;
      } else if(s.charAt(i) == '.') {
        if(eSeen || pointSeen) {
          return false;
        }
        pointSeen = true;
      } else if(s.charAt(i) == 'e') {
        if(eSeen || !numberSeen) {
          return false;
        }
        numberAfterE = false;
        eSeen = true;
      } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
        if(i != 0 && s.charAt(i-1) != 'e') {
          return false;
        }
      } else {
        return false;
      }
    }

    return numberSeen && numberAfterE;
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
