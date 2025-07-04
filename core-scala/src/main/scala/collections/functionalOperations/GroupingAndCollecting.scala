package collections.functionalOperations

object GroupingAndCollecting extends App {

    val animals = Seq(
        Animal("Babs", 12, Chicken),
        Animal("Lady", 4, Chicken),
        Animal("Babsie", 9, Cow),
        Animal("Bessy", 12, Cow),
        Animal("Lettie", 6, Cow),
        Animal("Douglas", 12, Horse),
        Animal("Cleo", 12, Dog),
        Animal("Bonnie", 9, Dog),
        Animal("Santiago", 12, Cat),
        Animal("Athena", 3, Cat)
    )

    sealed trait Species

    case class Animal(name: String, age: Int, species: Species)

    /**
     * Notice the case objects, not case classes!!
     */

    case object Cow extends Species

    case object Horse extends Species

    case object Chicken extends Species

    case object Dog extends Species

    case object Cat extends Species

    case object Wolf extends Species

    println("----------------- UsingFilters -----------------")

    object UsingFilters {

        println("print the animals whose age is < 10")
        animals.filter(_.age < 10).map(_.name).foreach(println)
        println


        println("print the Cows whose age is < 10")
        animals.filter(_.age < 10).filter(_.species == Cow).map(_.name).foreach(println)
        println
    }

    UsingFilters
    println

    // in the previous line, having two filters is annoying
    // let's do the two in one step

    println("----------------- Using Collect and PatternMatchers -----------------")

    object UsingCollectAndMatchers {


        /**
         * use collect and PatternMatchers
         */
        animals.collect {
            case Animal(name, age, species) if age < 10 && species == Cow => name
        }.foreach(println)
        println

        // ToDo: Report highlighting error in IntelliJ; Scala plugin
        println("the expanded PatternMatchers")
        animals.collect { animal =>
            animal match {
                case Animal(name, age, species) if age < 10 && species == Cow => name
            }
        }.foreach(println)
        println


        println("Collect the Animal objects instead of only names")
        animals.collect {
            case animal@Animal(name, age, species) if age < 10 && species == Cow => animal
        }.foreach(println)
        println

        println("Collecting Animals from a mixed Seq")

        val animalsAndNumbs = 1 +: 2 +: animals

        // This won't work -> age() isn't part of Any
        //  animalsAndNumbs.filter(_.age)

        // but this will work
        animalsAndNumbs.collect {
            case animal@Animal(name, age, species) if age < 10 && species == Cow => animal
        }.foreach(println)
        println

        println("Collecting First Cow")
        animals.collectFirst {
            case Animal(name, age, Cow) if age < 10 => name
        }.foreach(println) //option[String]
        println

        println("Collecting First Horse")
        animals.collectFirst {
            case Animal(name, age, Horse) if age < 10 => name
        }.foreach(println) //None
        println

    }

    UsingCollectAndMatchers
    println


    object UsingPartitioning {

        val (youngerAnimals, olderAnimals) = animals.partition(_.age < 10)
        println(youngerAnimals)
        println(olderAnimals)

    }

    UsingPartitioning
    println


    object UsingGroupBy {
        val speciesToAnimals: Map[Species, Seq[Animal]] = animals.groupBy(_.species)
        println(speciesToAnimals)

        val speciesToVectorOfAnimals: Map[Species, Vector[Animal]] = animals.toVector.groupBy(_.species)
        println(speciesToVectorOfAnimals)
    }

    UsingGroupBy
    println

    println("-------------------------- UsingGroupMap -----------------------")

    object UsingGroupMap {
        val mapOfSpeciesAndTheirNames: Map[Species, Seq[String]] = animals.groupMap(_.species)(_.name)
        println(mapOfSpeciesAndTheirNames)
    }

    UsingGroupMap
    println

    // It is equivalent to `groupBy(key).mapValues(_.map(f).reduce(reduce))`, but more efficient.

    /**
     * perform a function on the list of values of that particular key
     */
    object UsingGroupMapReduce {
        val mapOfSpeciesAndTheirAgesSummed: Map[Species, Int] = animals.groupMapReduce(_.species)(_.age)(_ + _)
        println(mapOfSpeciesAndTheirAgesSummed)

        val letterCounts = "this is actually a Scala Developer coming from Java"
            .split("")
            .filterNot(_ == " ")
            .map(c => c.toLowerCase -> 1)
            .groupMapReduce(tuple => tuple._1)(tuple => tuple._2)((val1, val2) => val1 + val2)
        println(letterCounts)
    }

    UsingGroupMapReduce
    println

    object UseLiftInsteadOfIndex {
        //using lift() to avoid Exceptions
        animals.lift(9).foreach(println) //get the 10th index if exists otherwise return None.
    }

    UseLiftInsteadOfIndex
    println

}
