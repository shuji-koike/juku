package example

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Success

class FutureAsyncSpec extends AsyncSpec {
  "Future" should "be easy" in {
    val f1 = Future {
      Thread.sleep(100)
      "done"
    }
    f1.isCompleted shouldEqual false
    f1.map(_ shouldEqual "done")
  }
}

class FutureSpec extends Spec {
  import scala.concurrent.ExecutionContext.Implicits.global
  "Future" should "be easy" in {
    val f1 = Future {
      Thread.sleep(100)
      "done"
    }
    f1.isCompleted shouldEqual false

    f1.map(_ shouldEqual "!?!?")

    Await.ready(f1, Duration.Inf)
    f1.isCompleted shouldEqual true

    f1.value shouldEqual Some(Success("done"))
  }
}
