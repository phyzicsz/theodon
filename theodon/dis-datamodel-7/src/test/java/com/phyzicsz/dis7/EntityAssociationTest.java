// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Association or disassociation of two entities.  Section 6.2.94.4.3
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Association or disassociation of two entities.  Section 6.2.94.4.3 */
public class EntityAssociationTest {
  public EntityAssociationTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.EntityAssociation openDis = new edu.nps.moves.dis7.EntityAssociation();
    com.phyzicsz.dis7.EntityAssociation dis = new EntityAssociation();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
