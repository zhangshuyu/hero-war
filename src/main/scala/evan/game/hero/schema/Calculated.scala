package evan.game.hero.schema

/**
  * Created by evan on 18-5-24.
  */
case class Calculated(
                       power: Double, // 统帅
                       force: Double, // 武力
                       intelligence: Double, // 智力
                       politics: Double, // 政治
                       charm: Double // 魅力
                     ) {
  val beGoodAt: (PropertyType.PropertyType, Double) = {
    val head = List(power, force, intelligence, politics, intelligence).sortWith((p1, p2) => p1 > p2).head
    ( {
      if (head == power) {
        PropertyType.POWER
      } else if (head == force) {
        PropertyType.FORCE
      } else if (head == intelligence) {
        PropertyType.INTELLIGENCE
      } else if (head == politics) {
        PropertyType.POLITICS
      } else {
        PropertyType.CHARM
      }
    }, head)
  }

  val warValue = power + force + intelligence
}
