// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// more laconically named EntityIdentifier
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * more laconically named EntityIdentifier */
public class EntityIDTest {
  public EntityIDTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.EntityID openDis = new edu.nps.moves.dis7.EntityID();
    com.phyzicsz.dis7.EntityID dis = new EntityID();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}