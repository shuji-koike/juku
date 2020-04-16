package example

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Success

class FutureAsyncSpec extends AsyncSpec {
  "Future" should "be easy" in {
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

  "Future" should "be easy" in {
    val f = Future {
      Thread.sleep(100)
      "done"
    }

    f.isCompleted shouldEqual false
    Await.ready(f, Duration.Inf)
    f.isCompleted shouldEqual true

    f.value shouldEqual Some(Success("done"))
  }
}
