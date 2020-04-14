package example

class TupleSpec extends Spec {
  "Tuple" should "be easy" in {}

  ("a", 1)._1 shouldEqual "a"
  ("a", 1)._2 shouldEqual 1

  Tuple2("a", 1) shouldEqual ("a", 1)
  "a" -> 1 shouldEqual ("a", 1)

  def hoge(s: String, i: Int, d: Double) = s -> i * d
  val tuple = ("a", 2, 3.0)
  (hoge _).tupled(tuple) shouldEqual "a" -> 6.0
}
