// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Removable parts that may be attached to an entity.  Section 6.2.93.3
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Removable parts that may be attached to an entity.  Section 6.2.93.3 */
public class AttachedPartsTest {
  public AttachedPartsTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.AttachedParts openDis = new edu.nps.moves.dis7.AttachedParts();
    com.phyzicsz.dis7.AttachedParts dis = new AttachedParts();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
