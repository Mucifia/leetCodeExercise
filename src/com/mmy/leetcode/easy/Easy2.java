package com.mmy.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

/**
 * @author: mmy
 * @date: 2018/06/10
 * @description:
 */
public class Easy2 {

  public boolean isValid(String s) {
    Stack<Character> stack = new Stack();
    Map<Character, Character> map = new HashMap();
    map.put('{', '}');
    map.put('[', ']');
    map.put('(', ')');
    for (int i = 0; i < s.length(); i++) {
      if (map.get(s.charAt(i)) != null) {
        stack.push(s.charAt(i));
      } else {
        if (!stack.empty()) {

          char c = stack.peek();
          if (map.get(c) == s.charAt(i)) {
            stack.pop();
          } else {
            return false;
          }
        } else {
          return false;
        }
      }
    }
    return stack.empty();
  }

  @Test
  public void test() {
    isValid("}");
  }
}
