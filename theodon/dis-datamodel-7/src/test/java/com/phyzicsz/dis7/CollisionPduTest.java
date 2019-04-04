// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 7.2.3 Collisions between entities shall be communicated by issuing a Collision PDU. COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 7.2.3 Collisions between entities shall be communicated by issuing a Collision PDU. COMPLETE */
public class CollisionPduTest {
  public CollisionPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.CollisionPdu openDis = new edu.nps.moves.dis7.CollisionPdu();
    com.phyzicsz.dis7.CollisionPdu dis = new CollisionPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
