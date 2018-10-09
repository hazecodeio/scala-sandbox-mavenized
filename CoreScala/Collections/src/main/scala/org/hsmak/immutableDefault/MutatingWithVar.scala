package org.hsmak.immutableDefault

/**
  * Mutating an immutable collection via Var is:
  *     - benefiting Thread Safety. However,
  *     - this is not the best approach for threads coordination for this might cause thread contentions, race conditions, etc
  *     - and not of a good performance since containers are being reinstantiated and created many times
  *     - so, instead use synchronization mechanisms
  *
  * Links:
  *     - https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256948
  */
object MutatingWithVar extends App {

  var l = List(1, 2, 3)

  // because it's a Var, we can reassign to the same variable
  l :+= 123 // shorhand for the below
  l = l :+ 123 // remember that ':+' is to appending, and it returns a new list

  println(l)

}
