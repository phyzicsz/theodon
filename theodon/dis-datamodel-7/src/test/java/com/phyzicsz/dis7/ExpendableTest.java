// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// An entity's expendable (chaff, flares, etc) information. Section 6.2.36
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * An entity's expendable (chaff, flares, etc) information. Section 6.2.36 */
public class ExpendableTest {
  public ExpendableTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.Expendable openDis = new edu.nps.moves.dis7.Expendable();
    com.phyzicsz.dis7.Expendable dis = new Expendable();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}