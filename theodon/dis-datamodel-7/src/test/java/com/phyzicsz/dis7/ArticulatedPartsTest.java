// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
//  articulated parts for movable parts and a combination of moveable/attached parts of an entity. Section 6.2.94.2
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *  articulated parts for movable parts and a combination of moveable/attached parts of an entity. Section 6.2.94.2 */
public class ArticulatedPartsTest {
  public ArticulatedPartsTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.ArticulatedParts openDis = new edu.nps.moves.dis7.ArticulatedParts();
    com.phyzicsz.dis7.ArticulatedParts dis = new ArticulatedParts();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}