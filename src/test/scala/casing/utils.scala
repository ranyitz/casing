package casing.test

import minitest.api._

object TestUtils {
  def expect(received: Seq[String], expected: Seq[String])(implicit
    pos: SourceLocation
  ): Unit = {
    val receivedStr = received.mkString("[", ", ", "]")
    val expectedStr = expected.mkString("[", ", ", "]")

    if (receivedStr != expectedStr)
      throw new AssertionException(
        Asserts.format(
          "received {0} != expected {1}",
          s"${receivedStr}",
          s"${expectedStr}"
        ),
        pos
      )

  }
}
