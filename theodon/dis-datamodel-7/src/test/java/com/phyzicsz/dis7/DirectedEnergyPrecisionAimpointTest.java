// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// DE Precision Aimpoint Record. Section 6.2.20.3
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * DE Precision Aimpoint Record. Section 6.2.20.3 */
public class DirectedEnergyPrecisionAimpointTest {
  public DirectedEnergyPrecisionAimpointTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.DirectedEnergyPrecisionAimpoint openDis = new edu.nps.moves.dis7.DirectedEnergyPrecisionAimpoint();
    com.phyzicsz.dis7.DirectedEnergyPrecisionAimpoint dis = new DirectedEnergyPrecisionAimpoint();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
