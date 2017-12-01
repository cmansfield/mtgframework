package io.github.cmansfield.simulator.constants;

import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class ZoneTest {

  @Test
  public void test_find() {
    String zoneName = "Hand";
    Zone hand = Zone.find(zoneName);
    assertEquals(hand, Zone.HAND);
  }

  @Test
  public void test_find_notFound() {
    String zoneName = "twilight_zone";
    Zone twilightZone = Zone.find(zoneName);
    assertNull(twilightZone);
  }

  @Test
  public void test_contains() {
    String zoneName = "Hand";
    assertTrue(Zone.contains(zoneName));
  }

  @Test
  public void test_find_doesNotContain() {
    String zoneName = "twilight_zone";
    assertFalse(Zone.contains(zoneName));
  }

  @Test
  public void test_getPropertyValue() {
    assertTrue(Zone.BATTLEFIELD.getPropertyValue(ZoneProperty.SHARED));
    assertFalse(Zone.BATTLEFIELD.getPropertyValue(ZoneProperty.ORDERED));
    assertTrue(Zone.BATTLEFIELD.getPropertyValue(ZoneProperty.PUBLIC));
  }

  @Test (expectedExceptions = IllegalStateException.class)
  public void test_getMissingProperty() {
    Zone.BATTLEFIELD.getPropertyValue((ZoneProperty) null);
  }

  @Test
  public void test_getPropertyValue_string() {
    assertTrue(Zone.BATTLEFIELD.getPropertyValue(ZoneProperty.SHARED.toString()));
    assertFalse(Zone.BATTLEFIELD.getPropertyValue(ZoneProperty.ORDERED.toString()));
    assertTrue(Zone.BATTLEFIELD.getPropertyValue(ZoneProperty.PUBLIC.toString()));
  }

  @Test (expectedExceptions = NullPointerException.class)
  public void test_getPropertyValue_badString() {
    Zone.BATTLEFIELD.getPropertyValue("This isn't really a zone property");
  }
}
