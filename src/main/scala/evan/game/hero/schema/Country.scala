package evan.game.hero.schema

import com.fasterxml.jackson.core.`type`.TypeReference

/**
  * Created by evan on 18-5-20.
  */
class Country extends TypeReference[Country.type]

object Country extends Enumeration {
  type Country = Value
  val 蜀国, 魏国, 吴国, 群雄 = Value
}