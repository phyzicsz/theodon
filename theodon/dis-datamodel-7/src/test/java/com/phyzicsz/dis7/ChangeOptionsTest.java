// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// This is wrong and breaks serialization. See section 6.2.13 aka B.2.41
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This is wrong and breaks serialization. See section 6.2.13 aka B.2.41 */
public class ChangeOptionsTest {
  public ChangeOptionsTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.ChangeOptions openDis = new edu.nps.moves.dis7.ChangeOptions();
    com.phyzicsz.dis7.ChangeOptions dis = new ChangeOptions();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
