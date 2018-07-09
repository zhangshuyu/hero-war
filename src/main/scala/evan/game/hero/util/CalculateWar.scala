package evan.game.hero.util

import evan.game.hero.schema._

/**
  * Created by evan on 18-5-20.
  */
trait CalculateWar extends Random {

  def calculate(hero: Hero): Double = {
    hero.properties.map {
      p =>
        valueCalculate(p)
    }.sum
  }

  def transform2Calculated(hero: Hero): Calculated = {
    val transformedProperties: Map[PropertyType.PropertyType, Double] = hero.properties.map {
      p =>
        (p.propertyType, valueCalculate(p))
    }.toMap

    Calculated(
      transformedProperties.getOrElse(PropertyType.POWER, 0),
      transformedProperties.getOrElse(PropertyType.FORCE, 0),
      transformedProperties.getOrElse(PropertyType.INTELLIGENCE, 0),
      transformedProperties.getOrElse(PropertyType.POLITICS, 0),
      transformedProperties.getOrElse(PropertyType.CHARM, 0)
    )
  }

  private def valueCalculate(p: Property): Double = {
    p.propertyType match {
      case PropertyType.FORCE =>
        valuePlus(p.value) * random(1.21, 2.03)
      case PropertyType.INTELLIGENCE =>
        valuePlus(p.value) * random(1.16, 2.05)
      case PropertyType.POWER =>
        valuePlus(p.value) * random(1.23, 2.07)
      case _ =>
        valuePlus(p.value)
    }
  }

  private def valuePlus(value: Int): Double = {
    if (value == 100) {
      value * 1.5
    } else if (value >= 95) {
      value * random(1.15, 1.35)
    } else if (value >= 90) {
      value * random(1.08, 1.17)
    } else if (value >= 80) {
      value * random(0.98, 1.10)
    } else {
      value.toDouble
    }
  }
}
