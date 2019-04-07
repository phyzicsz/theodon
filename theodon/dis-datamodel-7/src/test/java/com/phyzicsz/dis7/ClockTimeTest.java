// /**
// * Autogenerated by dis-codegen
// *
// DO NOT EDIT DIRECTLY
// */
// Time measurements that exceed one hour are represented by this record. The first field is the hours since the unix epoch (Jan 1 1970, used by most Unix systems and java) and the second field the timestamp units since the top of the hour. Section 6.2.14
package com.phyzicsz.dis7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Time measurements that exceed one hour are represented by this record. The first field is the hours since the unix epoch (Jan 1 1970, used by most Unix systems and java) and the second field the timestamp units since the top of the hour. Section 6.2.14 */
public class ClockTimeTest {
  public ClockTimeTest() {
  }

  @Test
  public void wirelineSizeTest() {
    edu.nps.moves.dis7.ClockTime openDis = new edu.nps.moves.dis7.ClockTime();
    com.phyzicsz.dis7.ClockTime dis = new ClockTime();
    int openDisSize = openDis.getMarshalledSize();
    int localSize = dis.wirelineSize();
    assertEquals(openDisSize,localSize);
  }
}