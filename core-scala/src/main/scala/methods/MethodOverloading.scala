package methods

object MethodOverloading extends App {

    def addNum(x: Int) = x + 1

    def addNum(y: Double) = y + 30.0

    def addNum(z: String) = z + 19

    println(addNum(30))
    println(addNum(40.0))
    println(addNum("Hello "))

}
