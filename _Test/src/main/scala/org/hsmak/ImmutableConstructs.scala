package org.hsmak

object ImmutableConstructs extends App {

  def function(input: String, count: Int) = {

    def _function(remaining: Int, out: List[String]): List[String] = {

      if (remaining == 0)
        out
      else
        _function(remaining - 1, s"$remaining: $input" :: out) // every recursive call will create a new list and prepend the new values to it

    }

    _function(count, List())
  }

  println(function("Foo", 10))
}
