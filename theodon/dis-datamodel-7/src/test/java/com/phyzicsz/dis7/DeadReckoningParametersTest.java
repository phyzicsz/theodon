// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Not specified in the standard. This is used by the ESPDU
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Not specified in the standard. This is used by the ESPDU */
public class DeadReckoningParametersTest {
  public DeadReckoningParametersTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.DeadReckoningParameters openDis = new edu.nps.moves.dis7.DeadReckoningParameters();
    com.phyzicsz.dis7.DeadReckoningParameters dis = new DeadReckoningParameters();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}