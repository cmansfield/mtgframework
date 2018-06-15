package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.CardConstants;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class CardTest {

  @Test
  public void test_placeholder() {
    List<List<String>> test = new ArrayList<>();
    test.add(Collections.singletonList("text1"));
    test.add(Collections.singletonList("text2"));
    List<String> list1 = new ArrayList<>();
    list1.add("text3");
    list1.add("text4");
    test.add(list1);

    List<List<String>> test2 = new ArrayList<>();
    test2.add(Collections.singletonList("text1"));
    test2.add(Collections.singletonList("text2"));
    List<String> list2 = new ArrayList<>();
    list2.add("text3");
    list2.add("text4");
    test2.add(list2);
    
    assertTrue(test.equals(test2));
  }
}
