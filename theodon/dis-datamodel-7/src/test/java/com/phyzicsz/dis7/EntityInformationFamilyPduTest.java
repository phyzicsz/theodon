// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 5.3.3. Common superclass for EntityState, Collision, collision-elastic, and entity state update PDUs. This should be abstract. COMPLETE
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 5.3.3. Common superclass for EntityState, Collision, collision-elastic, and entity state update PDUs. This should be abstract. COMPLETE */
public class EntityInformationFamilyPduTest {
  public EntityInformationFamilyPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.EntityInformationFamilyPdu openDis = new edu.nps.moves.dis7.EntityInformationFamilyPdu();
    com.phyzicsz.dis7.EntityInformationFamilyPdu dis = new EntityInformationFamilyPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}