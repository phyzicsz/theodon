// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Association or disassociation of two entities.  Section 6.2.94.5
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Association or disassociation of two entities.  Section 6.2.94.5 */
public class EntityTypeVPTest {
  public EntityTypeVPTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.EntityTypeVP openDis = new edu.nps.moves.dis7.EntityTypeVP();
    com.phyzicsz.dis7.EntityTypeVP dis = new EntityTypeVP();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}