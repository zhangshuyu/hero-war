package evan.game.hero.schema

import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}
import com.fasterxml.jackson.annotation.JsonTypeInfo.{As, Id}
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration

/**
  * Created by evan on 18-5-20.
  */
@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "_type", defaultImpl = classOf[DefaultProperty])
@JsonSubTypes(
  Array(
    new Type(value = classOf[SpecialProperty], name = "special")
  )
)
trait Property {
  val _type: String = this.getClass.getSimpleName.replace("Property", "").toLowerCase()
  val propertyType: PropertyType.PropertyType
  val value: Int
}

case class DefaultProperty(
                            @JsonScalaEnumeration(classOf[PropertyType])
                            override val propertyType: PropertyType.PropertyType,
                            override val value: Int
                          ) extends Property

class PropertyType extends TypeReference[PropertyType.type]
object PropertyType extends Enumeration {
  type PropertyType = Value
  val POWER: PropertyType = Value("power")
  val FORCE: PropertyType = Value("force")
  val INTELLIGENCE: PropertyType = Value("intelligence")
  val POLITICS: PropertyType = Value("politics")
  val CHARM: PropertyType = Value("charm")
}

case class SpecialProperty(
                            @JsonScalaEnumeration(classOf[PropertyType])
                            override val propertyType: PropertyType.PropertyType,
                            override val value: Int
                          ) extends Property
