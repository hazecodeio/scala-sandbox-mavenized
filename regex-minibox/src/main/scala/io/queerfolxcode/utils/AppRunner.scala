package io.queerfolxcode.utils

object AppRunner extends App {


  val versionsStr_FilteredByDigits = _DATA.versionsStr
    .split("""\n""")
    .filter(v => """v(\d+.?)+""".r.matches(v))
  versionsStr_FilteredByDigits.foreach(println)


  val versionsRegEx_MajorMinor = """(v\d+\.\d+).*""".r
  //  versionsRegEx_MajorMinor.findFirstMatchIn("v2.3.4.3").map(_.group(1)).foreach(println)

  val versionsMap_GroupedByMax = versionsStr_FilteredByDigits
    .groupMapReduce(versionsRegEx_MajorMinor.findFirstMatchIn(_).map(_.group(1)).get)(identity)(Seq(_, _).sorted(Ordering.String.reverse)(0))
  versionsMap_GroupedByMax.foreach(println)


}


object _DATA {
  def versionsStr = {
    """v3.3.0
      |v3.3.0-rc6
      |v3.3.0-rc5
      |v3.3.0-rc4
      |v3.3.0-rc3
      |v3.3.0-rc2
      |v3.3.0-rc1
      |v3.2.2
      |v3.2.2-rc1
      |v3.2.1
      |v3.2.1-rc2
      |v3.2.1-rc1
      |v3.2.0
      |v3.2.0-rc7
      |v3.2.0-rc6
      |v3.2.0-rc5
      |v3.2.0-rc4
      |v3.2.0-rc3
      |v3.2.0-rc2
      |v3.2.0-rc1
      |v3.1.3
      |v3.1.3-rc4
      |v3.1.3-rc3
      |v3.1.3-rc2
      |v3.1.3-rc1
      |v3.1.2
      |v3.1.2-rc1
      |v3.1.1
      |v3.1.1-rc3
      |v3.1.1-rc2
      |v3.1.1-rc1
      |v3.1.0-rc1
      |v3.0.3
      |v3.0.3-rc1
      |v3.0.2
      |v3.0.2-rc1
      |v3.0.1
      |v3.0.1-rc3
      |v3.0.1-rc2
      |v3.0.1-rc1
      |v3.0.0
      |v3.0.0-rc3
      |v3.0.0-rc2
      |v3.0.0-rc1
      |v3.0.0-preview-rc2
      |v3.0.0-preview-rc1
      |v3.0.0-preview2
      |v3.0.0-preview2-rc2
      |v3.0.0-preview2-rc1
      |v3.0.0-preview
      |v2.4.8
      |v2.4.8-rc4
      |v2.4.8-rc3
      |v2.4.8-rc2
      |v2.4.8-rc1""".stripMargin
  }
}