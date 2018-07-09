package evan.game.hero.util

import org.junit.Test

/**
  * Created by evan on 18-5-20.
  */
class RandomUtilTest extends Random{

  @Test
  def random(): Unit = {
    for (i <- 1 to 20) {
      println(random(0.5, 0.9))
    }
  }
}
