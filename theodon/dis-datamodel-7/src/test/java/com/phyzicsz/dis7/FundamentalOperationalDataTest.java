// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Basic operational data for IFF. Section 6.2.40.
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Basic operational data for IFF. Section 6.2.40. */
public class FundamentalOperationalDataTest {
  public FundamentalOperationalDataTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.FundamentalOperationalData openDis = new edu.nps.moves.dis7.FundamentalOperationalData();
    com.phyzicsz.dis7.FundamentalOperationalData dis = new FundamentalOperationalData();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}
