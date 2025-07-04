package implicits

object Implicitly_Proof extends App {

    /**
     * Use implicitly[] to prove a Generalized Constraint
     */
    val proof = implicitly[Cable[UsbConnector] <:< Cable[UsbC[String]]]
    println(proof)

    trait Cable[-C] {
        def connect(c: C): Boolean
    }

    abstract class UsbConnector

    case class Usb(orientation: Boolean) extends UsbConnector

    case class Lightning(length: Int) extends UsbConnector

    case class UsbC[Kind](kind: Kind) extends UsbConnector

}
