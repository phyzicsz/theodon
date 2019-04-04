// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Operational data for describing the vectoring nozzle systems Section 6.2.96
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Operational data for describing the vectoring nozzle systems Section 6.2.96 */
public class VectoringNozzleSystemTest {
  public VectoringNozzleSystemTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.VectoringNozzleSystem openDis = new edu.nps.moves.dis7.VectoringNozzleSystem();
    com.phyzicsz.dis7.VectoringNozzleSystem dis = new VectoringNozzleSystem();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
