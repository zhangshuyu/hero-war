package evan.game.hero.json

import evan.game.hero.schema._
import evan.game.hero.util.Mapper
import org.junit.Test

/**
  * Created by evan on 18-5-20.
  */
class MapperTest extends Mapper {

  @Test
  def test(): Unit = {
    val json = mapper.writeValueAsString(Hero("1", "吕布", Some("奉先"), Country.群雄, HeroLevel.天级, List(DefaultProperty(PropertyType.FORCE, 100))))
    println(json)
    val hero = mapper.readValue[Hero](json)
    println(mapper.writeValueAsString(hero))
  }

}
