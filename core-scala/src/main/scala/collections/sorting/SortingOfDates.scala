package collections.sorting

import java.util.Comparator

object SortingOfDates extends App {
    println("------------------------ DateTimeOrdering ------------------------")

    import java.time.LocalDateTime

    val now = LocalDateTime.now // now

    val nowPlus5Days = LocalDateTime.now.plusDays(5) // after now

    val nowMinus2Days = LocalDateTime.now.minusDays(2) // before now

    val seqOfDates = Seq(now, nowPlus5Days, nowMinus2Days)
    println(seqOfDates)

    println("-- DateOrdering (_ isAfter _) --")
    implicit val dateOrdering_Dec = Ordering.fromLessThan[LocalDateTime](_ isAfter _) // equivalent to  "_ > _"
    println(seqOfDates.sorted) // Again an implicit ordering must be defined before hand (if no Ordering is available for the target)

    println("-- DateOrdering (_ isBefore _) --") // <- isBefore is the default Ordering; i.e. Increasing Order
    // we can't have more than an implicit val that takes and emits the same type as the other implicits!!!
    // hence commented and supplied explicitly
    //    implicit val dateOrdering_Inc = Ordering.fromLessThan[LocalDateTime](_ isBefore _) // equivalent to  "_ > _"
    println(seqOfDates.sorted(Ordering.fromLessThan[LocalDateTime](_ isBefore _))) // Again an implicit ordering must be defined before hand (if no Ordering is available for the target)
    println

    println("----- via sortWith ------")
    println(seqOfDates.sortWith(_ isAfter _))
    println(seqOfDates.sortWith(_ isBefore _))

    object ComparingTheJavaWay {

        import java.time.LocalDateTime

        /*
         * LocalDateTime is already a Comparable class
         */
        class DataComparator extends Comparator[LocalDateTime] {
            override def compare(o1: LocalDateTime, o2: LocalDateTime): Int = o1.compareTo(o2)
        }
    }
}

