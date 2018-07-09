package evan.game.hero.schema

import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}
import com.fasterxml.jackson.annotation.JsonTypeInfo.{As, Id}

/**
  * Created by evan on 18-5-20.
  */
@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "_type", defaultImpl = classOf[DefaultSkill])
@JsonSubTypes(
  Array(
    new Type(value = classOf[SpecialSkill], name = "special")
  )
)
trait Skill {
  val _type: String = this.getClass.getSimpleName.replace("Property", "").toLowerCase()
  val id: String
  val name: String
  val property: Property
}
case class DefaultSkill(
                         override val id: String,
                         override val name: String,
                         override val property: Property
                       ) extends Skill

case class SpecialSkill(
                         override val id: String,
                         override val name: String,
                         override val property: Property
                       ) extends Skill
