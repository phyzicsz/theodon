// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
//  SEES PDU, supplemental emissions entity state information. Section 7.6.6 COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *  SEES PDU, supplemental emissions entity state information. Section 7.6.6 COMPLETE */
public class SeesPduTest {
  public SeesPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.SeesPdu openDis = new edu.nps.moves.dis7.SeesPdu();
    com.phyzicsz.dis7.SeesPdu dis = new SeesPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
