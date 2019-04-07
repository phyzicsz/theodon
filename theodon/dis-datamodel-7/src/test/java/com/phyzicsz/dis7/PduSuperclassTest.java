// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// The superclass for all PDUs, including classic and Live Entity (LE) PDUs. This incorporates the PduHeader record, section 7.2.2
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The superclass for all PDUs, including classic and Live Entity (LE) PDUs. This incorporates the PduHeader record, section 7.2.2 */
public class PduSuperclassTest {
  public PduSuperclassTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.PduSuperclass openDis = new edu.nps.moves.dis7.PduSuperclass();
    com.phyzicsz.dis7.PduSuperclass dis = new PduSuperclass();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}