// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 5.3.12.9: initializing or chaning internal state information, reliable. Needs manual intervention to fix     padding on variable datums. UNFINISHED
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 5.3.12.9: initializing or chaning internal state information, reliable. Needs manual intervention to fix     padding on variable datums. UNFINISHED */
public class SetDataReliablePduTest {
  public SetDataReliablePduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.SetDataReliablePdu openDis = new edu.nps.moves.dis7.SetDataReliablePdu();
    com.phyzicsz.dis7.SetDataReliablePdu dis = new SetDataReliablePdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}