package evan.game.hero

import evan.game.hero.schema.Country
import evan.game.hero.service.WarService
import evan.game.hero.util.Data

/**
  * Created by evan on 18-5-20.
  */
object App {

  def main(args: Array[String]): Unit = {
    val server = new WarService()
    val data = Data.fromFile("/opt/IdeaProjects/project/hero-war/src/main/resources")

    /*val heroes = server.heroWar(data).grouped(10).toList.head
    heroes.foreach {
      d =>
        println(s"${d._1.country} ${d._1.fullname} : ${d._2}")
    }
    heroes.groupBy(_._1.country).map {
      d =>
        var count = d._2.head._2
        d._2.tail.foreach(dd => count = count.+(dd._2))
        (d._1, count)
    }.toList.sortBy(_._2.win).foreach(println(_))*/

    /*server.countryWar(data).foreach{
      d =>
        println(s"${d._1} 得分 ${d._2}")
    }*/

    server.teamWar(data)

  }
}
