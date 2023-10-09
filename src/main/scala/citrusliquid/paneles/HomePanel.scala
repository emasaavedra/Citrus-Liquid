package citrusliquid.paneles

import citrusliquid.Entidades.Player

/** La clase simula un panel default, pero está asociada a un Jugador, al cual le otorga la opción de detenerse a voluntad si pasa por él.
 *
 * @param siguientes Conjunto de el o los paneles que están a continuación del panel presente.
 * @param dueño Dueño del panel, el cual puede detenerse en este panel a voluntad
 * @param player Conjunto de los jugadores que habitan dicho panel
 *
 */

class HomePanel(siguientes: Set[panel], dueño: Player, player: Player) extends panel {
  private val _owner: Player = dueño
  def owner: Player = _owner

  /** Función que detiene al jugador dueño del Home Panel
   *Chequea que el player ejecutando la detención sea efectivamente el dueño, y si lo es le resta sus movimientos restantes y lo añade al Panel
   *
   * @param player El Player intentando detenerse, aún teniendo movimientos restantes
   * @param elección Elección booleana del Jugador a Detenerse
   */
  def stop_owner(player: Player, elección: Boolean): Unit = {
    if (elección & (player==this._owner)) {
      player.dado = 0
      this.OcupadoPor+=player
      this.EndTurn(player)
    }
  }
  override var Siguiente: Set[panel] = siguientes
  override var OcupadoPor: Set[Player] = Set()
  override var Pasando: Player = player

  /** Función que actualiza la Norma del Jugador en caso de cumplir los requisitos necesarios para avanzar a la Siguiente Norma
   *
   * @param player Jugador que se le realizará un Norma Check
   */
  private def NormaCheck(player: Player): Unit = {
    if (player.Norma == 1) {
      if (player.estrellas >= 10 || player.Victorias >= 1) {
        player.Norma = 2
      }
    }
    else if (player.Norma == 2) {
      if (player.estrellas >= 30 || player.Victorias >= 3) {
        player.Norma = 3
      }
    }
    else if (player.Norma == 3) {
      if (player.estrellas >= 70 || player.Victorias >= 6) {
        player.Norma = 4
      }
    }
    else if (player.Norma == 4) {
      if (player.estrellas >= 120 || player.Victorias >= 10) {
        player.Norma = 5
      }
    }
    else if (player.Norma == 5) {
      if (player.estrellas >= 200 || player.Victorias >= 14) {
        player.Norma = 6
      }
    }
  }

  /**Función que termina el turno del jugador, independiente de si es dueño o no
   * Termina el turno del jugador, recuperándole 1 punto de vida y realizando un Norma Check
   *
   * @param player El jugador al que se le terminará el turno
   */
  protected def EndTurn(player: Player) : Unit = {
    if (this.OcupadoPor contains(player)){
      if (player.HPact + 1 > player.HPmax) { //en caso de que al añadirle 1 punto vida supere el max de vida
        player.HPact = player.HPmax
      }
      else {
        player.HPact += 1
      }
      NormaCheck(player)
    }
  }
}

