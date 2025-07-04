package abstraction.traits

object TraitStacking extends App {

    /**
     * see trait [[ExcellentAbstraction.Animal]]
     */
    trait Animal {
        // Some animals don't walk!!
        def liveOneDay: Seq[String] = Seq("Walk")
    }

    object BadModelingAndAbstraction {


        class Wolf extends Animal {
            // Why not separating characteristics into separate traits?
            override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack farm" :+ "eat animal"
        }

        val wolfLiveOneDay: Seq[String] = (new Wolf).liveOneDay
        println(wolfLiveOneDay)


        class Fox extends Animal {
            // Why copy & paste?
            override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack farm" :+ "eat animal" :+ "attack henhouse"
        }

        val foxLiveOneDay = (new Fox).liveOneDay
        println(foxLiveOneDay)
    }

    BadModelingAndAbstraction
    println()

    object GoodAbstraction {

        trait AttackingAnimal extends Animal {
            override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack farm"
        }

        trait CarnivoreAnimal extends Animal {
            override def liveOneDay: Seq[String] = super.liveOneDay :+ "eat animal"
        }

        trait HenAttackerAnimal extends Animal {
            override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack henhouse"
        }

        println(" with stacking traits:")

        class Wolf1 extends Animal with AttackingAnimal with CarnivoreAnimal

        println((new Wolf1).liveOneDay)
        println

        println("Order of stacked traits matter:")

        class Wolf2 extends Animal with CarnivoreAnimal with AttackingAnimal

        println((new Wolf2).liveOneDay)

        class Fox extends Animal with CarnivoreAnimal with AttackingAnimal with HenAttackerAnimal

        println((new Fox).liveOneDay)

    }

    GoodAbstraction
    println

    object ExcellentAbstraction {

        /**
         * Animal is just an interface that has no implementation
         */
        trait Animal {
            // abstract
            def liveOneDay: Seq[String]
        }

        /**
         * Error:(80, 52) method liveOneDay in trait Animal is accessed from super. It may not be abstract unless it is overridden by a member declared `abstract' and `override'
         * override def liveOneDay: Seq[String] = super.liveOneDay :+ "fly"
         *
         * Note: because traits are stackable in any order, we don't know which super trait the super.liveOneDay will come from
         *     - use "override abstract"
         */
        trait FlyingAnimal extends Animal {
            override abstract def liveOneDay: Seq[String] = super.liveOneDay :+ "fly"
        }

        trait CarnivoreAnimal extends Animal {
            override abstract def liveOneDay: Seq[String] = super.liveOneDay :+ "eat animal"
        }

        /**
         * Error:(94, 11) class Raven needs to be a mixin, since method liveOneDay in trait CarnivoreAnimal of type => Seq[String] is marked `abstract' and `override' and overrides incomplete superclass member method liveOneDay in trait FlyingAnimal of type => Seq[String]
         * class Raven extends Animal with FlyingAnimal with CarnivoreAnimal
         *
         * we're missing an implementation of liveOneDay
         * therefore we are defining a trait EmptyAnimal to supply the implementation of liveOneday
         */

        //    class Raven extends Animal with FlyingAnimal with CarnivoreAnimal

        /**
         * This is the base parent trait that has the first implementation
         */
        trait EmptyAnimal extends Animal {
            override def liveOneDay: Seq[String] = Seq.empty
        }

        class Raven extends Animal with EmptyAnimal with FlyingAnimal with CarnivoreAnimal

        println((new Raven).liveOneDay)
    }

    ExcellentAbstraction
    println
}
