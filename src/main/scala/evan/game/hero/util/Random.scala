package evan.game.hero.util

import scala.util.Random

/**
  * Created by evan on 18-5-20.
  */
trait Random {

  def random(min: Double, max: Double): Double = {
    Random.nextDouble().*(max - min) + min
  }

  def random(min: Int, max: Int): Int = {
    Random.nextInt(max - min) + min
  }
}
