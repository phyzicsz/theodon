// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// A Simulation Address record shall consist of the Site Identification number and the Application Identification number. Section 6.2.79 
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A Simulation Address record shall consist of the Site Identification number and the Application Identification number. Section 6.2.79  */
public class SimulationAddressTest {
  public SimulationAddressTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.SimulationAddress openDis = new edu.nps.moves.dis7.SimulationAddress();
    com.phyzicsz.dis7.SimulationAddress dis = new SimulationAddress();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}