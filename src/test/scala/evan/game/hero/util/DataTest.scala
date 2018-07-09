package evan.game.hero.util

import org.junit.Test


/**
  * Created by evan on 18-5-20.
  */
class DataTest extends Mapper {

  @Test
  def data(): Unit = {

    Data.fromFile("/opt/IdeaProjects/project/hero-war/src/main/resources").foreach {
      data =>
        println(data._1)
        data._2.foreach(hero => println(mapper.writeValueAsString(hero)))
    }
  }

}
