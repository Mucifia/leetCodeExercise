package com.mmy.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

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
   * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if
   * necessary).
   *
   * You may assume that the intervals were initially sorted according to their start times.
   */
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new ArrayList<>();
    Interval combine = new Interval(newInterval.start, newInterval.end);
    for (Interval i : intervals) {
      if (combine.end <= i.end) {
        if (combine.end >= i.start) {
          combine.start = Math.min(combine.start, i.start);
          combine.end = i.end;
        } else {
          result.add(i);
        }
      } else {
        if (combine.start > i.end) {
          result.add(i);
        } else {
          combine.start = Math.min(i.start, combine.start);
        }
      }
    }
    result.add(combine);
    Collections.sort(result, new Comparator<Interval>() {
      @Override
      public int compare(Interval o1, Interval o2) {
        return o1.start - o2.start;
      }
    });
    return result;
  }


  /**
   * Validate if a given string is numeric. Some examples: "0" => true " 0.1 " => true "abc" =>
   * false "1 a" => false "2e10" => true 只通过了 1342/1421
   */
  public boolean isNumber(String s) {
    String sTrim = s.trim();
    char[] sChars = sTrim.toCharArray();
    boolean isValid = false;
    int dot = 0;
    int e = 0;
    for (int i = 0; i < sChars.length; i++) {
      if (sChars[i] >= '0' && sChars[i] <= '9') {
        isValid = true;
      } else if (sChars[i] == '.') {
        if (dot == 2) {
          return false;
        } else if (i == 0) {
          dot++;
        } else if (sChars[i - 1] < '0' || sChars[i - 1] > '9') {
          return false;
        } else {
          dot++;
        }
      } else if (sChars[i] == 'e') {
        if (e == 1) {
          return false;
        } else if (i == 0) {
          return false;
        } else if (sChars[i - 1] < '0' || sChars[i - 1] > '9') {
          return false;
        } else {
          e++;
        }
      } else {
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
    for (int i = 0; i < s.length(); i++) {
      if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
        numberSeen = true;
        numberAfterE = true;
      } else if (s.charAt(i) == '.') {
        if (eSeen || pointSeen) {
          return false;
        }
        pointSeen = true;
      } else if (s.charAt(i) == 'e') {
        if (eSeen || !numberSeen) {
          return false;
        }
        numberAfterE = false;
        eSeen = true;
      } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
        if (i != 0 && s.charAt(i - 1) != 'e') {
          return false;
        }
      } else {
        return false;
      }
    }

    return numberSeen && numberAfterE;
  }


  /**
   * Given an array of words and a width maxWidth, format the text such that each line has exactly
   * maxWidth characters and is fully (left and right) justified.
   *
   * You should pack your words in a greedy approach; that is, pack as many words as you can in each
   * line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
   *
   * Extra spaces between words should be distributed as evenly as possible. If the number of spaces
   * on a line do not divide evenly between words, the empty slots on the left will be assigned more
   * spaces than the slots on the right.
   *
   * For the last line of text, it should be left justified and no extra space is inserted between
   * words.
   *
   * Note:
   *
   * A word is defined as a character sequence consisting of non-space characters only. Each word's
   * length is guaranteed to be greater than 0 and not exceed maxWidth. The input array words
   * contains at least one word.
   */

  /**
   * 词间距没控制好，需要调整
   * @param words
   * @param maxWidth
   * @return
   */
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> result = new ArrayList<>();
    int index = 0;
    while (index < words.length) {
      int wordsNum = 0;
      int startIndex = index;
      StringBuilder sb = new StringBuilder();
      while (index < words.length
          && (wordsNum + words[index].length() + index - startIndex) <= maxWidth) {
        wordsNum += words[index].length();
        index++;
      }
      if (index >= words.length) {
        int space = maxWidth - wordsNum - (index - startIndex);
        while (startIndex < words.length) {
          sb.append(words[startIndex]);
          sb.append(' ');
          startIndex++;
        }
        for (int i = 0; i < space; i++) {
          sb.append(' ');
        }
      } else {
        int extraSpace = maxWidth - wordsNum;
        int last;
        int space;

        int step = index - startIndex - 1;
        space = 0;
        while (step!=0&&space* step < extraSpace) {
          space++;
        }
        last = extraSpace - space*(step-1);
        if(index-startIndex<=1){
          space=last;
        }
        int temp = startIndex;
        while (startIndex < index) {
          sb.append(words[startIndex]);
          if (startIndex == index - 2) {
            space = last;
          } else if (index - temp > 1 && startIndex == index - 1) {
            space = 0;
          }
          for (int i = 0; i < space; i++) {
            sb.append(' ');
          }
          startIndex++;
        }
      }
      result.add(sb.toString());
    }
    return result;
  }

  /**
   *
   */

  public List<String> fullJustify2(String[] words, int L) {
    List<String> lines = new ArrayList<String>();

    int index = 0;
    while (index < words.length) {
      int count = words[index].length();
      int last = index + 1;
      while (last < words.length) {
        if (words[last].length() + count + 1 > L) break;
        count += words[last].length() + 1;
        last++;
      }

      StringBuilder builder = new StringBuilder();
      int diff = last - index - 1;
      // if last line or number of words in the line is 1, left-justified
      if (last == words.length || diff == 0) {
        for (int i = index; i < last; i++) {
          builder.append(words[i] + " ");
        }
        builder.deleteCharAt(builder.length() - 1);
        for (int i = builder.length(); i < L; i++) {
          builder.append(" ");
        }
      } else {
        // middle justified
        int spaces = (L - count) / diff;
        int r = (L - count) % diff;
        for (int i = index; i < last; i++) {
          builder.append(words[i]);
          if (i < last - 1) {
            for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
              builder.append(" ");
            }
          }
        }
      }
      lines.add(builder.toString());
      index = last;
    }


    return lines;
  }

  @Test
  public void test() {
//    Interval i1 = new Interval(1,2);
//    Interval i2 = new Interval(3,5);
//    Interval i3 = new Interval(6,7);
//    Interval i4 = new Interval(8,10);
//    Interval i5 = new Interval(12,16);
//    Interval i6 = new Interval(4,8);
//    List<Interval> list = new ArrayList<>();
//    list.add(i1);
//    list.add(i2);
//    list.add(i3);
//    list.add(i4);
//    list.add(i5);
//    List<Interval> result  = insert(list,i6);

    String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
    String[] words2 = new String[]{"What", "must", "be", "acknowledgment", "shall", "be"};
    String[] words3 = new String[]{"Science", "is", "what", "we", "understand", "well", "enough",
        "to", "explain",
        "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
    String[] word4 =new String[]{"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
    List<String> result = fullJustify(words, 16);
    List<String> result2 = fullJustify(words2, 16);
    List<String> result3 = fullJustify(words3, 20);
    List<String> result4 =fullJustify(word4,16);
  }

}
