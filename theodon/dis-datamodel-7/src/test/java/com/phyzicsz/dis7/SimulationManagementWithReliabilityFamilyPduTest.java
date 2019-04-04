// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Section 5.3.12: Abstract superclass for reliable simulation management PDUs
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Section 5.3.12: Abstract superclass for reliable simulation management PDUs */
public class SimulationManagementWithReliabilityFamilyPduTest {
  public SimulationManagementWithReliabilityFamilyPduTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.SimulationManagementWithReliabilityFamilyPdu openDis = new edu.nps.moves.dis7.SimulationManagementWithReliabilityFamilyPdu();
    com.phyzicsz.dis7.SimulationManagementWithReliabilityFamilyPdu dis = new SimulationManagementWithReliabilityFamilyPdu();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
