// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 5.3.12.8: request for data from an entity. COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 5.3.12.8: request for data from an entity. COMPLETE */
public class DataQueryReliablePduTest {
  public DataQueryReliablePduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.DataQueryReliablePdu openDis = new edu.nps.moves.dis7.DataQueryReliablePdu();
    com.phyzicsz.dis7.DataQueryReliablePdu dis = new DataQueryReliablePdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
