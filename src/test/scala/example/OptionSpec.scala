package example

class OptionSpec extends Spec {
  "Option#apply" should "be easy" in {
    Option(1) shouldEqual Some(1)
    Option("abc") shouldEqual Some("abc")
    Option(null) shouldEqual None
  }

  "Some#apply" should "be used with care" in {
    Some(1) shouldEqual Option(1)
    Some(null) shouldEqual Some(null)
    Some(null) should not equal None
  }

  "Option#get" should "not be used" in {
    Option(1).get shouldEqual 1

    Some(null).get.asInstanceOf[AnyRef] shouldEqual null

    an[java.util.NoSuchElementException] should be thrownBy {
      Option(null).get
    }
    an[java.util.NoSuchElementException] should be thrownBy {
      None.get
    }
  }

  "Option#getOrElse" should "be null safe" in {
    Option(1).getOrElse(0) shouldEqual 1
    Option(null).getOrElse(0) shouldEqual 0

    Some(1).getOrElse(throw new Exception) shouldEqual 1

    an[java.lang.Exception] should be thrownBy {
      None.getOrElse(throw new Exception)
    }
  }

  "Option#map" should "be easy" in {
    Option(1).map(_ * 2) shouldEqual Some(2)
    Option.empty[Int].map(_ * 2) shouldEqual None

    Option.empty[String].map(_ + "-test") shouldEqual None

    Option("hoge").map(_ + "-test") shouldEqual Some("hoge-test")

    Option(1).map(a => Option(a * 2)) shouldEqual Some(Some(2))
    Option(1).map(a => Option(a * 2)).flatten shouldEqual Some(2)
    Option(1).flatMap(a => Option(a * 2)) shouldEqual Some(2)

    Option("abc").map(_.length).getOrElse(0) shouldEqual 3
    Option.empty[String].map(_.length).getOrElse(0) shouldEqual 0
  }

  "Option#fold" should "be easy" in {
    Option("abc").fold(0)(_.length) shouldEqual 3
    Option.empty[String].fold(0)(_.length) shouldEqual 0
  }

  "Option" should "be composable" in {
    {
      for {
        a <- Option("a")
        b <- Option(2)
      } yield {
        a -> b
      }
    } shouldEqual Some("a" -> 2)

    {
      (Option("a"), Option(2)) match {
        case (Some(a), Some(b)) => Some(a -> b)
        case (Some(a), None)    => Some(a -> 0)
        case _                  => None
      }
    } shouldEqual Some("a" -> 2)

    {
      Option("a").flatMap(a => Some(2).map(b => a -> b))
    } shouldEqual Some("a" -> 2)
  }

  {
    Option(1).isDefined shouldEqual true
    Option(1).isEmpty shouldEqual false

    None.isDefined shouldEqual false
    None.isEmpty shouldEqual true
  }

  {
    Option.empty[Int].contains(1) shouldEqual false
    Option.empty[Int].exists(_ == 1) shouldEqual false
    Option.empty[Int].forall(_ == 1) shouldEqual true

    Option(1).contains(1) shouldEqual true
    Option(1).exists(_ == 1) shouldEqual true
    Option(1).forall(_ == 1) shouldEqual true

    Option(1).contains(2) shouldEqual false
    Option(1).exists(_ == 2) shouldEqual false
    Option(1).forall(_ == 2) shouldEqual false
  }

  "Option" should "be conditional" in {
    if (true) Some(1) else None shouldEqual Some(1)
    if (false) Some(1) else None shouldEqual None

    Option.when(true)(1) shouldEqual Some(1)
    Option.when(false)(1) shouldEqual None

    Option.unless(false)(1) shouldEqual Some(1)
    Option.unless(true)(1) shouldEqual None
  }
}
