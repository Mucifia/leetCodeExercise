package com.mmy.leetcode.medium;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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

  /**
   * can't get dulplicate candidate
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<Integer> candidatesList = new ArrayList<>();
    Set lists = new HashSet();
    for (int i : candidates){
      candidatesList.add(i);
    }
    searchCandidates(lists,new ArrayList<>(),target,candidatesList,0);
    return new ArrayList<>(lists);
  }

  public boolean searchCandidates(Set lists,List<Integer> list,int target,List<Integer> candidates,int index){
    if (target>0){
      for (int i=index;i<candidates.size()&&candidates.get(i)<=target;i++){
        List<Integer> listCopy =new ArrayList<>(list);
        int num = candidates.get(i);
        listCopy.add(num);
        candidates.remove(i);
        if (searchCandidates(lists,listCopy,target-num,new ArrayList<>(candidates),i)){
          lists.add(listCopy);
        }else {
          listCopy.remove(listCopy.size()-1);
          candidates.add(i,num);
        }
      }
    }else if (target==0) {
      return true;
    }
      return false;

  }
//  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//    Arrays.sort(candidates);
//    List<List<Integer>> results = new ArrayList<>();
//    backtracking (results, new ArrayList<>(), candidates, target, 0);
//    return results;
//  }
  //网上最快答案
//  private void backtracking(List<List<Integer>> results, List<Integer> temp, int[]candidates, int target, int start){
//    if (target == 0){
//      results.add (new ArrayList<>(temp));
//      return;
//    }
//    for (int i=start; i<candidates.length && candidates[i]<=target; i++){
//      if (i>start && candidates[i]==candidates[i-1]){
//        continue;
//      }
//      temp.add(candidates[i]);
//      backtracking(results, temp, candidates, target-candidates[i], i+1);
//      temp.remove(temp.size()-1);
//    }
//
//  }

  /**
   * 存不下这么大
   * @param num1
   * @param num2
   * @return
   */
  public String multiply(String num1, String num2) {
    if (num1.equals("0")||num2.equals("0")){
      return "0";
    }
    int num1Len = num1.length();
    int num2Len = num2.length();
    int index=num1Len-1;
    long result= Long.valueOf(0);
    while (index>=0){
      long mul = Long.valueOf(0);
      int numA = (num1.charAt(index)-'0');
      for (int k = num2Len-1;k>=0;k--){
        int numB = (num2.charAt(k)-'0');
        mul+=numA*Math.pow(10,num1Len-index-1)*numB*Math.pow(10,num2Len-k-1);
      }
      result+=mul;
      index--;
    }
    return String.valueOf(result);
  }

  /**
   * 利用数组来解决
   */
  public String multiplyOptimize(String num1, String num2) {
    if (num1.equals("0")||num2.equals("0")){
      return "0";
    }
    int num1Len = num1.length();
    int num2Len = num2.length();
    int index=num1Len-1;
    int[] result = new int[num1Len+num2Len];
    while (index>=0){
      int numA = (num1.charAt(index)-'0');
      for (int k = num2Len-1;k>=0;k--){
        int numB = (num2.charAt(k)-'0');
        int mul = numA*numB+result[index+k+1];
        int a = (mul)/10;
        int b = (mul)%10;
        result[index+k+1]=b;
        result[index+k]+=a;
      }
      index--;
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int i=0;i<result.length;i++){
      if (result[i]!=0||stringBuilder.length()!=0){
        stringBuilder.append(result[i]);
      }
    }

    return stringBuilder.toString();
  }





  @Test
  public void test(){
    int[] nums=  new int[]{1,2,3,6,7};
    List<List<Integer>> lists =combinationSum2(nums,7);

    String result  = multiplyOptimize("22","34");
  }
}
