// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Information about elastic collisions in a DIS exercise shall be communicated using a Collision-Elastic PDU. Section 7.2.4. COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Information about elastic collisions in a DIS exercise shall be communicated using a Collision-Elastic PDU. Section 7.2.4. COMPLETE */
public class CollisionElasticPduTest {
  public CollisionElasticPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.CollisionElasticPdu openDis = new edu.nps.moves.dis7.CollisionElasticPdu();
    com.phyzicsz.dis7.CollisionElasticPdu dis = new CollisionElasticPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}