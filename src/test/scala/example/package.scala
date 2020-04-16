package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

trait Spec extends AnyFlatSpec with Matchers
trait AsyncSpec extends AsyncFlatSpec with Matchers
