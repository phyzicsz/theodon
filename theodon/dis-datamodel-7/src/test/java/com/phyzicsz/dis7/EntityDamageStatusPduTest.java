// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// shall be used to communicate detailed damage information sustained by an entity regardless of the source of the damage Section 7.3.5  COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * shall be used to communicate detailed damage information sustained by an entity regardless of the source of the damage Section 7.3.5  COMPLETE */
public class EntityDamageStatusPduTest {
  public EntityDamageStatusPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.EntityDamageStatusPdu openDis = new edu.nps.moves.dis7.EntityDamageStatusPdu();
    com.phyzicsz.dis7.EntityDamageStatusPdu dis = new EntityDamageStatusPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
