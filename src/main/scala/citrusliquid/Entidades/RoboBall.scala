package citrusliquid.Entidades

/** Clase que simula el Wild Unit "RoboBall"
 *
 * @param stars Cantidad de estrellas que se le asignaran como atributo
 */
class RoboBall(stars: Int) extends Unit {
  val name: String = "Robo Ball"
  val HPmax: Int = 3
  var HPact: Int = 3
  val ATKp: Int = -1
  val DEFp: Int = +1
  val EVAp: Int = -1
  var estrellas: Int = stars
  var Victorias: Int = 0
  def Derrotado(enemy: Entidad): Unit = {
    if (this.HPact == 0) {
      this.Estado = "K.O."
      enemy.Victorias += 1
    }
  }
  var Estado: String = "Esperando"

  def attack(enemy: Entidad): Unit = {
    val roll: Int = this.dado.generar()
    val atk: Int = this.ATKp + roll
    enemy.underAttack(atk)
  }

  def underAttack(atkp: Int): Unit = {
    if (this.Estado == "Defending") {
      this.defend(atkp)
    }
    else if (this.Estado == "Evading") {
      this.evade(atkp)
    }
  }

  def answerAttack(enemy: Entidad): Unit = {
    if (this.HPact > 0) {
      this.attack(enemy)
    }
    else {
      this.Derrotado(enemy)
    }
  }

  def defend(atkp: Int): Unit = {
    val roll: Int = this.dado.generar()
    this.HPact -= math.max(1, atkp - (roll + this.DEFp))
  }

  def evade(atkp: Int): Unit = {
    val roll: Int = this.dado.generar()
    if (roll + this.EVAp > atkp) {

    }
    else {
      this.HPact -= atkp
    }
  }
}
