package type_parameterization

/**
 * it's a challenge to resolve the generic type if it's a type of a type such as;
 *     - List[T]
 */
object GenericTypeResolution extends App {

    /**
     *
     * @param in
     * @tparam T
     */
    def inspectListOfT[T](in: List[T]) = {
        println(in.getClass) // return the type at runtime
    }

    inspectListOfT(List(1)) // class scala.collection.immutable.$colon$colon
    println

    /**
     * Notice the [T: Manifest]
     *
     * @param in
     * @tparam T
     */
    def inspectListOfT2[T: Manifest](in: List[T]) = {
        println(in.getClass) // return the type at runtime
        println(manifest[T].getClass) // for List[Int]: returns class scala.reflect.ManifestFactory$IntManifest
        println(manifest[T].erasure) // returns the original type <- Deprecated
        println(manifest[T].runtimeClass) // returns the original type
    }

    inspectListOfT2(List(1))
    println
    inspectListOfT2(List("test"))
    println

    object WithTraitsAndClasses {

        trait Animal

        case object Dog extends Animal

        case object Cat extends Animal

        trait FarmAnimal extends Animal

        case object Cow extends FarmAnimal

        case object Horse extends FarmAnimal

        /**
         * Notice the [T: Manifest]
         *
         * @param in
         * @tparam T
         */
        def inspectListOfT[T: Manifest](in: List[T]) = {
            println(in.getClass)
            println(manifest[T].getClass)
            println(manifest[T].erasure)
        }

        /**
         * class scala.collection.immutable.$colon$colon
         * class scala.reflect.ManifestFactory$IntersectionTypeManifest
         * interface scala.Product <--- what is this?
         */
        inspectListOfT(List(Dog, Cat, Cow, Horse))
        println

        // Now let's make the type explicit
        val animals: List[Animal] = List(Dog, Cat, Cow, Horse)

        /**
         * class scala.collection.immutable.$colon$colon
         * class scala.reflect.ManifestFactory$ClassTypeManifest
         * interface type_parameterization.GenericTypeResolution$WithAbstraction$Animal <--- now this is correct!
         */
        inspectListOfT(animals)
    }

    WithTraitsAndClasses
    println

    object WithSubTyping {

        trait Animal

        case object Dog extends Animal

        case object Cat extends Animal

        trait FarmAnimal extends Animal

        case object Cow extends FarmAnimal

        case object Horse extends FarmAnimal

        /**
         * Notice the [T <: FarmAnimal : Manifest]
         * T is a subtype of FarmAnimal
         *
         * @param in
         * @tparam T
         */
        def inspectListOfT[T <: FarmAnimal : Manifest](in: List[T]) = {
            println(in.getClass)
            println(manifest[T].getClass)
            println(manifest[T].erasure)
        }

        /**
         * class scala.collection.immutable.$colon$colon
         * class scala.reflect.ManifestFactory$ClassTypeManifest
         * interface type_parameterization.GenericTypeResolution$WithSubTyping$FarmAnimal <-- surprisingly this returns FarmAnimal because of the subtyping
         */
        inspectListOfT(List(Cow, Horse))
        println

        // Now let's make the type explicit
        val animals: List[FarmAnimal] = List(Cow, Horse)

        /**
         * class scala.collection.immutable.$colon$colon
         * class scala.reflect.ManifestFactory$ClassTypeManifest
         * interface type_parameterization.GenericTypeResolution$WithSubTyping$FarmAnimal <--- same as the previous one
         */
        inspectListOfT(animals)
        println

        println("-- Having fun with collect{} --")
        inspectListOfT(List(Dog, Cat, Cow, Horse).asInstanceOf[List[Animal]].collect {
            case fa: FarmAnimal => fa
        })
    }

    WithSubTyping
    println
}
