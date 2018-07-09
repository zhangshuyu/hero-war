package evan.game.hero.schema

import evan.game.hero.util.CalculateWar

/**
  * Created by evan on 18-5-24.
  */
case class Team(
                 country: Country.Country,
                 power: TeamMember,
                 force: TeamMember,
                 intelligence: TeamMember
               ) {
  override def toString: String = s"$country: (${power.key.fullname}, ${force.key.fullname}, ${intelligence.key.fullname})"

  val warValue = power.value.warValue + force.value.warValue + intelligence.value.warValue

  def reCalculate(): Team = {
    Team(
      country,
      power.reCalculate(),
      force.reCalculate(),
      intelligence.reCalculate()
    )
  }
}

case class TeamMember(key: Hero) extends CalculateWar {
  val value = transform2Calculated(key)

  def reCalculate(): TeamMember = {
    TeamMember(key)
  }
}
