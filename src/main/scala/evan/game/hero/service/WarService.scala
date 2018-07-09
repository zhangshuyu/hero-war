package evan.game.hero.service

import evan.game.hero.schema._
import evan.game.hero.util.CalculateWar

import scala.collection.mutable

import org.apache.commons.httpclient.protocol.ProtocolSocketFactory

/**
  * Created by evan on 18-5-20.
  */
class WarService extends CalculateWar {
  private final val TOP: Int = 5

  def heroWar(data: Map[Country.Country, List[Hero]]): List[(Hero, GameScore)] = {
    heroWar(Country.values.flatMap(data).toList)
  }

  private def heroWar(heroes: List[Hero]): List[(Hero, GameScore)] = {
    def score(hero1: Hero, data: List[Hero]): List[(Hero, GameScore)] = {
      data.flatMap {
        hero2 =>
          if (!hero1.id.equals(hero2.id)) {
            val c1 = calculate(hero1)
            val c2 = calculate(hero2)
            if (c1 > c2) {
              List(Tuple2(hero1, GameScore(1)), Tuple2(hero2, GameScore(0, 1)))
            } else if (c1 < c2) {
              List(Tuple2(hero2, GameScore(1)), Tuple2(hero1, GameScore(0, 1)))
            } else {
              List(Tuple2(hero2, GameScore(0, 0, 1)), Tuple2(hero1, GameScore(0, 0, 1)))
            }
          } else List(Tuple2(hero2, GameScore()), Tuple2(hero1, GameScore()))
      }
    }

    var tail = heroes.tail
    heroes.flatMap {
      hero1 =>
        val s = score(hero1, tail)
        if (tail.nonEmpty) tail = tail.tail
        s
    }.groupBy(_._1).map {
      d =>
        var first = d._2.head._2
        d._2.tail.foreach(second => first = second._2.+(first))
        (d._1, first)
    }.toList.sortWith((h1, h2) => h1._2.sum() >= h2._2.sum())
  }

  def countryWar(data: Map[Country.Country, List[Hero]]): Map[Country.Country, Int] = {
    val topN = data.map {
      d =>
        (d._1, heroWar(d._2).grouped(TOP).toList.head)
    }.flatMap(_._2).keys.toList
    println("====参战人员====")
    topN.sortBy((_: Hero).country.toString).foreach {
      d =>
        println(s"${d.country}:  ${d.fullname}")
    }
    println("====个人英勇====")
    val heroes = heroWar(topN)
    heroes /*.grouped(TOP).toList.head*/ .foreach {
      h =>
        println(s"${h._1.fullname} : ${h._2}")
    }
    println("====国家荣誉====")
    heroes.groupBy(_._1.country).map {
      d =>
        (d._1, d._2.map(_._2.sum()).sum)
    }
  }

  def teamWar(data: Map[Country.Country, List[Hero]]): Unit = {
    def teamFromCountry(country: Country.Country, heroes: List[Hero]): Team = {
      val teamData: Map[PropertyType.PropertyType, TeamMember
        ] = heroes.map {
        h =>
          TeamMember(h)
      }.groupBy(_.value.beGoodAt._1).map {
        d =>
          (d._1, d._2.sortWith((d1, d2) => d1.value.beGoodAt._2 > d2.value.beGoodAt._2).head)
      }
      Team(country, teamData(PropertyType.POWER), teamData(PropertyType.FORCE), teamData(PropertyType.INTELLIGENCE))
    }

    def competition(teamList: List[Team]): Team = {
      val twoTeam = teamList.toArray
      val first = twoTeam(0).reCalculate()
      val second = twoTeam(1).reCalculate()

      val t = {
        if (first.warValue > second.warValue) twoTeam(0)
        else if (first.warValue < second.warValue) twoTeam(1)
        else twoTeam(random(0, twoTeam.size))
      }
      println(s"$first VS $second: $t WIN")
      t
    }

    val teams = data.map {
      d =>
        teamFromCountry(d._1, d._2)
    }.toList
    competition(group[Team](teams).map(competition))
  }

  def group[T](list: List[T], size: Int = 2): List[List[T]] = {
    def g(l: mutable.Buffer[T]): List[T] = {
      var g: List[T] = Nil
      for (_ <- 0 until size) {
        g = g.::(l.remove(random(0, l.size)))
      }
      g
    }

    val b = list.toBuffer
    var data: List[List[T]] = Nil
    while (b.size > size) {
      data = data.::(g(b))
    }
    data.::(b.toList)
  }
}
