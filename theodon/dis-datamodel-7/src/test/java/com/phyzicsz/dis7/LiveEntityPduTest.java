// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// The live entity PDUs have a header with some different field names, but the same length. Section 9.3.2
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The live entity PDUs have a header with some different field names, but the same length. Section 9.3.2 */
public class LiveEntityPduTest {
  public LiveEntityPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.LiveEntityPdu openDis = new edu.nps.moves.dis7.LiveEntityPdu();
    com.phyzicsz.dis7.LiveEntityPdu dis = new LiveEntityPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}