package com.mmy.leetcode.medium;

import com.mmy.leetcode.medium.Medium1.ListNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

/**
 * @author: mmy
 * @date: 2018/07/16
 * @description:
 */
public class Medium6 {



//  public List<List<Integer>> combinationSum(int[] candidates, int target) {
//    List<Integer> list = new ArrayList<>();
//    Set set = new HashSet();
//    Arrays.sort(candidates);
//    searchCandidates(set,list,candidates,target);
//    return new ArrayList<>(set);
//  }
//
//  public boolean searchCandidates(Set set,List<Integer> list,int[] candidates,int target){
//
//    if (target>0){
//      for (int i=0;i<candidates.length;i++){
//        List<Integer> listCopy = new ArrayList<>(list);
//        listCopy.add(candidates[i]);
//        target=target-candidates[i];
//        if (searchCandidates(set,listCopy,candidates,target)){
//          Collections.sort(listCopy);
//          set.add(listCopy);
//        }else {
//          listCopy.remove(Integer.valueOf(candidates[i]));
//          target=target+candidates[i];
//        }
//      }
//    }else if (target==0){
//      return true;
//    }
//    return false;
//  }


  /**
   * 优化后
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> list = new ArrayList<>();
    Set set = new HashSet();
    Arrays.sort(candidates);
    searchCandidates(set,list,candidates,target,0);
    return new ArrayList<>(set);
  }

  /**
   *
   * @param set
   * @param list
   * @param candidates
   * @param target
   * @param index promise we will not get dulplicate List
   * @return
   */
  public boolean searchCandidates(Set set,List<Integer> list,int[] candidates,int target,int index){

    if (target>0){
      for (int i=index;i<candidates.length&&target>=candidates[i];i++){
        List<Integer> listCopy = new ArrayList<>(list);
        listCopy.add(candidates[i]);
        if (searchCandidates(set,listCopy,candidates,target-candidates[i],i)){
          set.add(listCopy);
        }else {
          listCopy.remove(listCopy.size()-1);
          //BackTrace
        }
      }
    }else if (target==0){
      return true;
    }
    return false;
  }

  @Test
  public void test(){
    int[] nums=  new int[]{1,2,3,6,7};
    List<List<Integer>> lists =combinationSum(nums,7);
  }
}
