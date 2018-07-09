package evan.game.hero.schema

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration


/**
  * Created by evan on 18-5-20.
  */
case class Hero(
                 id: String,
                 fullname: String,
                 nickname: Option[String],
                 @JsonScalaEnumeration(classOf[Country])
                 country: Country.Country,
                 @JsonScalaEnumeration(classOf[HeroLevel])
                 heroType: HeroLevel.HeroLevel,
                 properties: List[Property],
                 treasure: Option[Treasure] = None,
                 skill: Option[Skill] = None
               )

class HeroLevel extends TypeReference[HeroLevel.type]

object HeroLevel extends Enumeration {
  type HeroLevel = Value
  val 神级, 天级, 地级, 人级 = Value
}
