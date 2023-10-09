package citrusliquid.paneles

import citrusliquid.Entidades.Player

/** La clase simula un panel que resta estrellas al Jugador
 *
 * Los jugadores que caen en este panel reciben un descuento de estrellas de acuerdo al valor de tirar un dado y
 * la norma de dicho jugador
 *
 * @param siguientes Conjunto de el o los paneles que están a continuación del panel presente.
 * @param players Conjunto de los jugadores que habitan dicho panel
 */
class DropPanel(siguientes: Set[panel], players: Player) extends panel{
  /** Función que realiza una sustracción de estrellas al Jugador que cae en el Panel
   *
   * @param entidad Jugador al que se le descontarán estrellas
   */
  private def drop(entidad: Player): Unit = {
    if(this.OcupadoPor contains(entidad)){
      val rand = scala.util.Random
      val roll: Int = rand.between(1, 6)
      entidad.estrellas -= roll * entidad.Norma
    }
  }
  override var Siguiente: Set[panel] = siguientes
  override var OcupadoPor: Set[Player] = Set()
  override var Pasando: Player = players
}

