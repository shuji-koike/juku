package example

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Success

class FutureAsyncSpec extends AsyncSpec {
  "Future" should "be easy?" in {
    val f = Future {
      Thread.sleep(100)
      "done"
    }
    f.isCompleted shouldEqual false
    f.map(_ shouldEqual "done")
  }
}

class FutureSpec extends Spec {
  import scala.concurrent.ExecutionContext.Implicits.global

  "Await.ready" should "be easy?" in {
    val f = Future {
      Thread.sleep(100)
      "done"
    }

    f.isCompleted shouldEqual false
    Await.ready(f, Duration.Inf)
    f.isCompleted shouldEqual true

    f.value shouldEqual Some(Success("done"))
  }

  "Await.result" should "be easy?" in {
    val f = Future {
      Thread.sleep(100)
      "done"
    }
    Await.result(f, Duration.Inf) shouldEqual "done"
  }

  "Future.sequence" should "be easy?" in {
    val seq = Seq(1, 2, 3).map(i =>
      Future {
        Thread.sleep(100 * i)
        println(s"done ${i}")
        i
      }
    )
    val f = Future.sequence(seq)
    Await.result(f, Duration.Inf) shouldEqual (Seq(1, 2, 3))
  }
}
