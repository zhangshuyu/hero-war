package evan.game.hero.util


import java.util.UUID

import evan.game.hero.schema.HeroLevel.HeroLevel
import evan.game.hero.schema._

import scala.io.Source

/**
  * Created by evan on 18-5-20.
  */
object Data extends Mapper {
  def fromFile(path: String): Map[Country.Country, List[Hero]] = {
    Country.values.toList.map {
      country =>
        val file = Source.fromFile(s"$path/$country")
        val heroes = file.getLines().map {
          line =>
            val d = line.split(" ")
            Hero(
              UUID.randomUUID().toString,
              d(0), {
                if (d(1).equals("无")) None else Some(d(1))
              },
              country,
              getHeroType(d.tail.tail.map(_.toInt)),
              List(
                DefaultProperty(PropertyType.FORCE, d(2).toInt),
                DefaultProperty(PropertyType.POWER, d(3).toInt),
                DefaultProperty(PropertyType.INTELLIGENCE, d(4).toInt),
                DefaultProperty(PropertyType.POLITICS, d(5).toInt),
                DefaultProperty(PropertyType.CHARM, d(6).toInt)
              ))
        }.toList
        file.close()
        (country, heroes)
    }.toMap
  }

  private def getHeroType(properties: Array[Int]): HeroLevel = {
    if (properties.exists(_ >= 100)) {
      HeroLevel.神级
    } else if (properties.exists(p => p >= 95 && p < 100)) {
      HeroLevel.天级
    } else if (properties.exists(p => p >= 90 && p < 95)) {
      HeroLevel.地级
    } else {
      HeroLevel.人级
    }
  }
}
