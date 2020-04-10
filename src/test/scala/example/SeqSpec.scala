package example

class SeqSpec extends Spec {
  "Seq" should "be easy" in {
    Seq.apply(1, 2, 3) shouldEqual Seq(1, 2, 3)
    Seq() shouldEqual Nil

    1 :: 2 :: 3 :: Nil shouldEqual Seq(1, 2, 3)

    (1 to 3).toSeq shouldEqual Seq(1, 2, 3)

    Seq(1, 2, 3) shouldBe a[Seq[_]]
    List(1, 2, 3) shouldBe a[Seq[_]]
    Seq(1, 2, 3) shouldBe a[List[_]]
    Seq(1, 2, 3) shouldBe a[Iterable[_]]

    Seq(1, 2, 3) should not be a[Vector[_]]
    Vector(1, 2, 3) shouldBe a[Seq[_]]
    // https://docs.scala-lang.org/tutorials/FAQ/collections.html
    // https://docs.scala-lang.org/resources/images/tour/collections-immutable-diagram.svg

    Nil shouldEqual Seq()
    Nil should be theSameInstanceAs Seq()
    Seq(1) should not be theSameInstanceAs(Seq(1))

    Nil shouldBe a[Seq[_]]

    1 +: Seq(2, 3) shouldEqual Seq(1, 2, 3)
    Seq(1, 2) :+ 3 shouldEqual Seq(1, 2, 3)

    Seq(1, 2) ++ Seq(3, 4) shouldEqual Seq(1, 2, 3, 4)
    Seq(1, 2) ++: Seq(3, 4) shouldEqual Seq(1, 2, 3, 4)

    Seq(1, 2) ++: Vector(3, 4) shouldBe a[Vector[_]];
    Seq(1, 2) ++ Vector(3, 4) should not be a[Vector[_]];
    Seq(1, 2) ++ Vector(3, 4) shouldBe a[List[_]];

    {
      val Seq(a, b, c) = Seq(1, 2, 3)
      a shouldEqual 1
      Seq(a, b, c) shouldEqual Seq(1, 2, 3)
    }

    {
      val a :: b :: c :: Nil = Seq(1, 2, 3)
      a shouldEqual 1
      Seq(a, b, c) shouldEqual Seq(1, 2, 3)
    }

    an[scala.MatchError] should be thrownBy {
      val a :: b :: Nil = Seq(1, 2, 3)
    }

    Seq(1, 2, 3)(0) shouldEqual 1
    Seq(1, 2, 3)(1) shouldEqual 2
    Seq(1, 2, 3)(2) shouldEqual 3

    an[java.lang.IndexOutOfBoundsException] should be thrownBy {
      Seq(1, 2, 3)(4)
    }

    Seq(1, 2, 3).lift(0) shouldEqual Some(1)
    Seq(1, 2, 3).lift(1) shouldEqual Some(2)
    Seq(1, 2, 3).lift(2) shouldEqual Some(3)
    Seq(1, 2, 3).lift(3) shouldEqual None

    Seq(1, 2, 3).head shouldEqual 1
    Seq(1, 2, 3).headOption shouldEqual Some(1)

    Nil.headOption shouldEqual None
    an[java.util.NoSuchElementException] should be thrownBy {
      Seq.empty[Int].head
    }
  }

  "Seq.map" should "be easy" in {
    Seq(1, 2, 3).map(_ * 2) shouldEqual Seq(2, 4, 6)

    Seq(Some(1), None, Some(3))
      .map(_.map(_ * 2)) shouldEqual Seq(Some(2), None, Some(6))

    Seq(Some(1), None, Some(3)).map {
      case Some(a) => Some(a * 2)
      case _       => None
    } shouldEqual Seq(Some(2), None, Some(6))

    Seq(Some(1), None, Some(3))
      .collect { case Some(a) => a * 2 } shouldEqual Seq(2, 6)
  }

  "sum" should "sum using pattern match" in {
    def sum(seq: Seq[Int]): Int = seq match {
      case head :: rest => head + sum(rest)
      case Nil          => 0
    }

    sum(Seq(1, 2, 3)) shouldEqual 6

    an[java.lang.StackOverflowError] should be thrownBy {
      sum((1 to 100000) ++: Nil)
    }
  }

  "sum" should "be stack safe" in {
    @scala.annotation.tailrec
    def sum(seq: Seq[Int], acc: Int = 0): Int = {
      seq match {
        case head :: rest => sum(rest, acc + head)
        case Nil          => acc
      }
    }

    sum(Seq(1, 2, 3)) shouldEqual 6

    sum((1 to 100000) ++: Nil) shouldEqual (1 to 100000).sum
  }
}
