package evan.game.hero.schema

import java.util.concurrent.TimeUnit

import evan.game.hero.util.Random
import org.junit.Test

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by evan on 18-5-24.
  */
class CalculatedTest extends Random {

  case class Group(f: Int, s: Int)

  @Test
  def test(): Unit = {
    val list = List(0, 1, 2, 3, 4, 5, 6, 7, 8)
    println(group[Int](list, 3))
  }

  def group[T](list: List[T], size: Int): List[List[T]] = {
    def g[T](l: mutable.Buffer[T]): List[T] = {
      var g: List[T] = Nil
      for (_ <- 0 until size) {
        g = g.::(l.remove(random(0, l.size)))
      }
      g
    }

    val b = list.toBuffer
    var data: List[List[T]] = Nil
    while (b.size > size) {
      data = data.::(g[T](b))
    }
    data.::(b.toList)
  }


}
