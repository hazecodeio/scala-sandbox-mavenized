package abstraction.traits

/**
 * Traits
 */
object BulletTrait extends App {

    var superman = new SuperHero("Superman")
    println(superman.fly)
    println(superman.hitByBulletProof)
    println(superman.ricochet(2500))

}

trait Flyable {
    def fly: String
}

trait BulletProof {
    //abstract method
    def hitByBulletProof: String

    //traits can have concrete method implementations
    def ricochet(startSpeed: Double): String = {
        "the bullet ricochets at a speed of %.1f ft/sec".format(startSpeed * 0.75)
    }
}

class SuperHero(name: String) extends Flyable with BulletProof {
    override def fly = "%s fly through the air".format(this.name)

    override def hitByBulletProof = "The bullet bounces off of %s".format(this.name)

}