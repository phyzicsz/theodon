// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 5.3.11: Abstract superclass for synthetic environment PDUs
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 5.3.11: Abstract superclass for synthetic environment PDUs */
public class SyntheticEnvironmentFamilyPduTest {
  public SyntheticEnvironmentFamilyPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.SyntheticEnvironmentFamilyPdu openDis = new edu.nps.moves.dis7.SyntheticEnvironmentFamilyPdu();
    com.phyzicsz.dis7.SyntheticEnvironmentFamilyPdu dis = new SyntheticEnvironmentFamilyPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
