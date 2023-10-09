package citrusliquid.Entidades

/** Clase que simula el Wild Unit "RoboBall"
 *
 * @param stars Cantidad de estrellas que se le asignaran como atributo
 */
class RoboBall(stars: Int) extends Entidad {
  override val name: String = "Robo Ball"
  override val HPmax: Int = 3
  override var HPact: Int = 3
  override val ATKp: Int = -1
  override val DEFp: Int = +1
  override val EVAp: Int = -1
  override var estrellas: Int = stars
  override var Victorias: Int = 0
  override def Derrotado(enemy: Entidad): Unit = {
    if (this.HPact == 0) {
      this.Estado = "K.O."
      enemy.Victorias += 1
    }
  }
  override var Estado: String = "Esperando"
}
