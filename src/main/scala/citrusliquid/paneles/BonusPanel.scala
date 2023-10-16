package citrusliquid.paneles

import citrusliquid.Dado
import citrusliquid.Entidades.Player

import scala.collection.mutable.Map
import scala.math.min

/** La clase simula un panel que le añade estrellas al Jugador
 *
 * Mediante el método 'addEstrellas', los jugadores, que están en este panel, reciben estrellas de acuerdo al resultado
 * de tirar el dado y la norma del jugador.
 *
 * @param siguientes Conjunto de el o los paneles que están a continuación del panel presente.
 * @param players Conjunto de los jugadores que habitan dicho panel
 */

class BonusPanel(siguientes: Map[String, panel], players: Player) extends panel {
  /** Función que añade estrellas al Jugador
   *
   * @param entidad Jugador al que se le añaden estrellas
   * @param dado Resultado de lanzar al dado que determinará las cantidades de estrellas a añadirle al jugador
   */
  def addEstrellas(entidad: Player, dado: Int): Unit = {
    if(this.OcupadoPor contains(entidad)){
      val roll: Int = dado
      val a1 = (entidad.Norma) * roll
      val b1 = roll * 3
      entidad.estrellas += min(a1, b1)
    }
  }

  override protected var Siguiente: Set[panel] = Set()
  def Siguiente(choice: String): panel = Siguiente(choice)
  def Siguiente_(choice: String, nextPanel: panel): Unit = Siguiente(choice)

  override var OcupadoPor: Set[Player] = Set()
  override var Pasando: Player = players

  /** Función que detiene al jugador y lo añade al panel por el cual está Pasando
   * En caso de que su dado sea 0, se añade al Panel.
   * En caso contrario, se le resta 1.
   *
   * @param player jugador al que se quiere detener en el panel
   */
  def stop(player: Player): Unit = {
    if(Pasando==player){
      if (player.dado == 0) {
        this.OcupadoPor += player
        apply(player)
      }
      else {
        player.dado-=1
      }
    }
  }

  def apply(player: Player): Unit = {
    val dice: Dado = new Dado
    val roll: Int = dice.generar()
    addEstrellas(player, roll)
  }
}

