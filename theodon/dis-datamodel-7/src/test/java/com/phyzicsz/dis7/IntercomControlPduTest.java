// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
//  Detailed inofrmation about the state of an intercom device and the actions it is requestion         of another intercom device, or the response to a requested action. Required manual intervention to fix the intercom parameters,        which can be of varialbe length. Section 7.7.5 UNFINSISHED
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *  Detailed inofrmation about the state of an intercom device and the actions it is requestion         of another intercom device, or the response to a requested action. Required manual intervention to fix the intercom parameters,        which can be of varialbe length. Section 7.7.5 UNFINSISHED */
public class IntercomControlPduTest {
  public IntercomControlPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.IntercomControlPdu openDis = new edu.nps.moves.dis7.IntercomControlPdu();
    com.phyzicsz.dis7.IntercomControlPdu dis = new IntercomControlPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}