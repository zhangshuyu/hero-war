package evan.game.hero.schema

import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}
import com.fasterxml.jackson.annotation.JsonTypeInfo.{As, Id}

/**
  * Created by evan on 18-5-20.
  */
@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "_type", defaultImpl = classOf[DefaultTreasure])
@JsonSubTypes(
  Array(
    new Type(value = classOf[SpecialTreasure], name = "special")
  )
)
trait Treasure {
  val _type: String = this.getClass.getSimpleName.replace("Property", "").toLowerCase()
  val id: String
  val name: String
  val property: Property
}

case class DefaultTreasure(
                            override val id: String,
                            override val name: String,
                            override val property: Property
                          ) extends Treasure

case class SpecialTreasure(
                            override val id: String,
                            override val name: String,
                            override val property: Property,
                            heroId: String,
                            specialValue: Int
                          ) extends Treasure
