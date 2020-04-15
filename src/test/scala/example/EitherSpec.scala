package example

class EitherSpec extends Spec {
  "Either" should "be easy" in {
    def validate(num: Int): Either[String, Int] =
      if (num < 1000) Right(num) else Left("exceeded")
    validate(1) shouldEqual Right(1)
    validate(999) shouldEqual Right(999)
    validate(1000) shouldEqual Left("exceeded")

    {
      for {
        e1 <- validate(1)
        e2 <- validate(10)
        e3 <- validate(100)
      } yield {
        e1 + e2 + e3
      }
    } shouldEqual Right(111)

    {
      for {
        e1 <- validate(1)
        e2 <- validate(10)
        e3 <- validate(1000)
      } yield {
        e1 + e2 + e3
      }
    } shouldEqual Left("exceeded")
  }

  "Either#value" should "not be used" in {
    Right(1).value shouldEqual 1
  }

  "Either#map" should "be easy" in {
    Right(1).map(_ * 2) shouldEqual Right(2)
    Left("hoge").withRight[Int].map(_ * 2) shouldEqual Left("hoge")
    Left("hoge").left.map(_ * 2) shouldEqual Left("hogehoge");
    Right(1)
      .withLeft[String]
      .map(_ * 2)
      .left
      .map(_ + "hoge") shouldEqual Right(2)
  }

  "Either#toOption" should "be easy" in {
    Right(1).toOption shouldEqual Some(1)
    Left("hoge").toOption shouldEqual None
    Left("hoge").left.toOption shouldEqual Some("hoge")

    Option(1).toRight("empty") shouldEqual Right(1)
    Option.empty[Int].toRight("empty") shouldEqual Left("empty")
  }
}
