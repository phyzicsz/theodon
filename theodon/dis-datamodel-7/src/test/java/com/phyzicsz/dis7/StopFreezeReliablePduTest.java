// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 5.3.12.4: Stop freeze simulation, relaible. COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 5.3.12.4: Stop freeze simulation, relaible. COMPLETE */
public class StopFreezeReliablePduTest {
  public StopFreezeReliablePduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.StopFreezeReliablePdu openDis = new edu.nps.moves.dis7.StopFreezeReliablePdu();
    com.phyzicsz.dis7.StopFreezeReliablePdu dis = new StopFreezeReliablePdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
