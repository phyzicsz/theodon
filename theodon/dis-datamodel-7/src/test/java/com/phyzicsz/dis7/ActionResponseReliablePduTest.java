// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 5.3.12.7: Response from an entity to an action request PDU. COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 5.3.12.7: Response from an entity to an action request PDU. COMPLETE */
public class ActionResponseReliablePduTest {
  public ActionResponseReliablePduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.ActionResponseReliablePdu openDis = new edu.nps.moves.dis7.ActionResponseReliablePdu();
    com.phyzicsz.dis7.ActionResponseReliablePdu dis = new ActionResponseReliablePdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}