// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 7.5.10. Change state information with the data contained in this. COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 7.5.10. Change state information with the data contained in this. COMPLETE */
public class SetDataPduTest {
  public SetDataPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.SetDataPdu openDis = new edu.nps.moves.dis7.SetDataPdu();
    com.phyzicsz.dis7.SetDataPdu dis = new SetDataPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}