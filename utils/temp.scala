scala> {val s1 = "01/01/2020".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     | val s2 = "05/16/2021".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))}
val s1: Seq[Int] = ArraySeq(1, 1, 2020)
val d1: java.time.LocalDate = 2020-01-01
val s2: Seq[Int] = ArraySeq(5, 16, 2021)
val d2: java.time.LocalDate = 2021-05-16

scala> {
     |   val s1 = "01/01/2020".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "05/16/2021".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))}
val s1: Seq[Int] = ArraySeq(1, 1, 2020)
val d1: java.time.LocalDate = 2020-01-01
val s2: Seq[Int] = ArraySeq(5, 16, 2021)
val d2: java.time.LocalDate = 2021-05-16

scala> {
     |   val s1 = "01/01/2020".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "05/16/2021".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     | }
val s1: Seq[Int] = ArraySeq(1, 1, 2020)
val d1: java.time.LocalDate = 2020-01-01
val s2: Seq[Int] = ArraySeq(5, 16, 2021)
val d2: java.time.LocalDate = 2021-05-16

scala> {
     |   val s1 = "01/01/2020".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "05/16/2021".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(1, 1, 2020)
val d1: java.time.LocalDate = 2020-01-01
val s2: Seq[Int] = ArraySeq(5, 16, 2021)
val d2: java.time.LocalDate = 2021-05-16
val res14: java.time.Period = P1Y4M15D

scala> {
     |   val s1 = "05/17/2021".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "09/30/2022".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(5, 17, 2021)
val d1: java.time.LocalDate = 2021-05-17
val s2: Seq[Int] = ArraySeq(9, 30, 2022)
val d2: java.time.LocalDate = 2022-09-30
val res15: java.time.Period = P1Y4M13D

scala> {
     |   val s1 = "05/17/2021".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "09/30/2022".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(5, 17, 2021)
val d1: java.time.LocalDate = 2021-05-17
val s2: Seq[Int] = ArraySeq(9, 30, 2022)
val d2: java.time.LocalDate = 2022-09-30
val res16: java.time.Period = P1Y4M13D

scala> {
     |   val s1 = "01/01/2020".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "05/16/2021".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(1, 1, 2020)
val d1: java.time.LocalDate = 2020-01-01
val s2: Seq[Int] = ArraySeq(5, 16, 2021)
val d2: java.time.LocalDate = 2021-05-16
val res17: java.time.Period = P1Y4M15D

scala> {
     |   val s1 = "01/01/2020".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |                                                        val d2 = LocalDate.now
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(1, 1, 2020)
val d1: java.time.LocalDate = 2020-01-01
val d2: java.time.LocalDate = 2024-04-07
val res18: java.time.Period = P4Y3M6D

scala> {
     |   val s1 = "10/01/2022".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |                                                        val d2 = LocalDate.now
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(10, 1, 2022)
val d1: java.time.LocalDate = 2022-10-01
val d2: java.time.LocalDate = 2024-04-07
val res19: java.time.Period = P1Y6M6D

scala> {
     |   val s1 = "09/23/2013".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "12/31/2019".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(9, 23, 2013)
val d1: java.time.LocalDate = 2013-09-23
val s2: Seq[Int] = ArraySeq(12, 31, 2019)
val d2: java.time.LocalDate = 2019-12-31
val res20: java.time.Period = P6Y3M8D

scala> {
     |   val s1 = "04/10/2018".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |                                                        val d2 = LocalDate.now
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(4, 10, 2018)
val d1: java.time.LocalDate = 2018-04-10
val d2: java.time.LocalDate = 2024-04-07
val res21: java.time.Period = P5Y11M28D

scala> {
     |   val s1 = "12/23/2018".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "12/22/2020".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(12, 23, 2018)
val d1: java.time.LocalDate = 2018-12-23
val s2: Seq[Int] = ArraySeq(12, 22, 2020)
val d2: java.time.LocalDate = 2020-12-22
val res22: java.time.Period = P1Y11M29D

scala> {
     |   val s1 = "11/15/2019".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "11/30/2020".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(11, 15, 2019)
val d1: java.time.LocalDate = 2019-11-15
val s2: Seq[Int] = ArraySeq(11, 30, 2020)
val d2: java.time.LocalDate = 2020-11-30
val res23: java.time.Period = P1Y15D

scala> {
     |   val s1 = "08/13/2018".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "08/31/2019".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(8, 13, 2018)
val d1: java.time.LocalDate = 2018-08-13
val s2: Seq[Int] = ArraySeq(8, 31, 2019)
val d2: java.time.LocalDate = 2019-08-31
val res24: java.time.Period = P1Y18D

scala> {
     |   val s1 = "12/26/2018".split('/').map(_.toInt).toSeq; val d1 = LocalDate.of(s1(2), s1(0), s1(1))
     |   val s2 = "12/31/2020".split('/').map(_.toInt).toSeq; val d2 = LocalDate.of(s2(2), s2(0), s2(1))
     |
     |   Period.between(d1,d2)
     | }
val s1: Seq[Int] = ArraySeq(12, 26, 2018)
val d1: java.time.LocalDate = 2018-12-26
val s2: Seq[Int] = ArraySeq(12, 31, 2020)
val d2: java.time.LocalDate = 2020-12-31
val res25: java.time.Period = P2Y5D

scala>

