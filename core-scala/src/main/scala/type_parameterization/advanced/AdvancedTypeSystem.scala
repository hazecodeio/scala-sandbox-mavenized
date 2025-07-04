package type_parameterization.advanced

//ToDo: very advanced use of type.. Go figure!!
object AdvancedTypeSystem extends App {

    type Hom[T] = {
        type Right[X] = Function1[X, T] // Co-vector
        type Left[X] = Function1[T, X] // Vector
    }

}
