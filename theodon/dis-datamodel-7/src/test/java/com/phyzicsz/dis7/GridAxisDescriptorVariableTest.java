// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Grid axis descriptor fo variable spacing axis data. NOT COMPLETE. Need padding to 64 bit boundary.
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Grid axis descriptor fo variable spacing axis data. NOT COMPLETE. Need padding to 64 bit boundary. */
public class GridAxisDescriptorVariableTest {
  public GridAxisDescriptorVariableTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.GridAxisDescriptorVariable openDis = new edu.nps.moves.dis7.GridAxisDescriptorVariable();
    com.phyzicsz.dis7.GridAxisDescriptorVariable dis = new GridAxisDescriptorVariable();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}