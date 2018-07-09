package evan.game.hero.schema


/**
  * Created by evan on 18-5-21.
  */
case class GameScore(win: Int = 0, lose: Int = 0, dogfall: Int = 0) {
  override def toString: String = s"$win 胜 $lose 负 $dogfall 平"

  def +(g2: GameScore): GameScore = {
    GameScore(this.win + g2.win, this.lose + g2.lose, this.dogfall + g2.dogfall)
  }

  def sum(): Int = {
    this.win - this.lose + this.dogfall * 0
  }
}
