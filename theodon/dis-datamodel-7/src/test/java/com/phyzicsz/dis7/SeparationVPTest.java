// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Physical separation of an entity from another entity.  Section 6.2.94.6
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Physical separation of an entity from another entity.  Section 6.2.94.6 */
public class SeparationVPTest {
  public SeparationVPTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.SeparationVP openDis = new edu.nps.moves.dis7.SeparationVP();
    com.phyzicsz.dis7.SeparationVP dis = new SeparationVP();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}