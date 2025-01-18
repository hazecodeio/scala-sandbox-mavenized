package org.haze

object CakePattern extends App {

  println("---------------------------- Example 1 ----------------------------\n")


  object Example_1 {


    val barWithFoo = new BarUsingFooAble with FooAbleComponent with BazAbleComponent {
      val bazAble = new BazAble() //or any other implementation
      val fooAble = new FooAble() //or any other implementation
    }
    println(barWithFoo.bar())

    trait FooAbleComponent {
      val fooAble: FooAble

      class FooAble {
        def foo() = "here is your foo"
      }

    }

    trait BazAbleComponent {
      val bazAble: BazAble

      class BazAble {
        def baz() = "baz too"
      }

    }

    class BarUsingFooAble {
      this: FooAbleComponent with BazAbleComponent =>
      def bar() = s"bar calls foo: ${fooAble.foo()} and baz: ${bazAble.baz()}"
    }

  }

  Example_1
  println

  println("---------------------------- Example 2 ----------------------------\n")

  /*
   * Link: https://github.com/Baeldung/scala-tutorials/tree/master/scala-design-patterns/src/main/scala/com/baeldung/scala/cakepattern
   */
  object Example_2 {

    import scala.jdk.CollectionConverters._

    val test = Test("test-1", { environment =>
      environment.getOrElse("ENV", "false").toBoolean
    })

    trait TestEnvironmentComponent {
      val env: TestEnvironment

      trait TestEnvironment {
        val envName: String

        def readEnvironmentProperties: Map[String, String]
      }

      class WindowsTestEnvironment extends TestEnvironment {
        override val envName: String = "Windows"

        override def readEnvironmentProperties: Map[String, String] =
          System.getenv().asScala.toMap
      }

    }

    trait TestExecutorComponent {
      this: TestEnvironmentComponent =>
      val testExecutor: TestExecutor

      class TestExecutor {
        def execute(tests: List[Test]): Boolean = {
          println(s"Executing test with ${env.envName} environment")
          tests.forall(_.execute(env.readEnvironmentProperties))
        }
      }

    }

    trait LoggingComponent {
      val logger: Logger

      class Logger {
        def log(level: String, message: String): Unit =
          println(s"$level   - $message")
      }

    }

    trait TestExecutorComponentWithLogging {
      this: TestEnvironmentComponent with LoggingComponent =>
      val testExecutor: TestExecutor

      class TestExecutor {
        def execute(tests: List[Test]): Boolean = {
          logger.log("INFO", s"Executing test with ${env.envName} environment")
          tests.forall(_.execute(env.readEnvironmentProperties))
        }
      }

    }

    case class Test(name: String, assertion: Map[String, String] => Boolean) {
      def execute(env: Map[String, String]): Boolean = {
        println(s"Execute test $name with environment $env")
        assertion.apply(env)
      }
    }

    object Registry extends TestExecutorComponent with TestEnvironmentComponent {
      override val env: Registry.TestEnvironment = new WindowsTestEnvironment
      override val testExecutor: Registry.TestExecutor = new TestExecutor
    }

  }

  Example_2
}
