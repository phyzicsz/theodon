// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// List of fixed and variable datum ID records. Section 6.2.17 
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * List of fixed and variable datum ID records. Section 6.2.17  */
public class DataQueryDatumSpecificationTest {
  public DataQueryDatumSpecificationTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.DataQueryDatumSpecification openDis = new edu.nps.moves.dis7.DataQueryDatumSpecification();
    com.phyzicsz.dis7.DataQueryDatumSpecification dis = new DataQueryDatumSpecification();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
