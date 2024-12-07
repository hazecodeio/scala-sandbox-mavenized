package io.queerfolxcode.utils.sttpHttp

/*
 * Running scala-built jar from terminal, then piping the output to `jq`
 *    > v=$(scala  -cp ./scala-http/target/scala-http-1.0-SNAPSHOT-jar-with-dependencies.jar RunnerOfGitHub)
 *    > echo $v | jq
 */
object RunnerOfGitHub extends App {

  import sttp.client3._

  val sort: Option[String] = None
  val query = "http language:scala"

  // the `query` parameter is automatically url-encoded
  // `sort` is removed, as the value is not defined
  val request = basicRequest.get(uri"""https://api.github.com/search/repositories?q=$query&sort=$sort""")

  val backend = HttpClientSyncBackend()
  val response = request.send(backend)

  // response.header(...): Option[String]
  //  println(response.header("Content-Length"))

  // response.body: by default read into an Either[String, String] to indicate failure or success
  println(response.body.getOrElse("{}"))
}


object RunnerOfGitHubWithCirce extends App {

  // In addition to the usual values brought into scope by `sttp.client3._`,
  // the `quick` version also defines a default synchronous `backend`.

  import sttp.client3.quick._
  // Circe integration: `asJson` response description.
  import io.circe.generic.auto._
  import sttp.client3.circe._

  // Case classes corresponding to the json returned by GitHub (just the
  // fields that interest us).
  case class GitHubResponse(total_count: Int, items: List[GitHubItem])

  case class GitHubItem(name: String, stargazers_count: Int)

  val query = "language:scala"
  val sort: Option[String] = Some("stars")

  // Describing the request: specifying the method, uri and how to handle
  // the response. The `query` parameter is automatically url-encoded
  // `sort` will be unwrapped if `Some(_)`, and removed if `None`.
  val request = basicRequest
    .get(uri"https://api.github.com/search/repositories?q=$query&sort=$sort")
    .response(asJson[GitHubResponse])

  // As we are using the synchronous `HttpURLConnectionBackend`, `send()` will
  // return `Response[_]`. Async backends return e.g. `Future[Response[_]]`.
  val response = request.send(backend)
  // The body will be a `Left(_)` in case of a non-2xx response, or a json
  // deserialization error. It will be `Right(_)` otherwise.
  response.body match {
    case Left(error) => println(s"Error when executing request: $error")
    case Right(data) =>
      println(s"Found ${data.total_count} Scala projects.")
      println(s"Showing ${data.items.size} with most stars:")
      data.items.foreach { item =>
        println(s"  ${item.name} (${item.stargazers_count})")
      }
  }
}
