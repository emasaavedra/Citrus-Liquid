package citrusliquid.paneles

import citrusliquid.Entidades.{Entidad, Player}


/** La clase simula un panel que genera un duelo entre el jugador que cae en este panel y un Wild Unit aleatorio
 *
 *  Al caer en este panel, un jugador recibe un enemigo Wild Unit generado aleatoriamente, con el cual tendrá un duelo
 *
 * @param siguientes Conjunto de el o los paneles que están a continuación del panel presente.
 * @param player Jugador que cae en el panel y participará del duelo
 * @param enemy Entidad Wild Unit que será el enemigo del jugador
 */

class EncounterPanel(siguientes: Set[panel], player: Player, enemy: Entidad) extends panel{
  /** Función que simula la acción a realizar al terminar un duelo
   * Toma el jugador en duelo y su enemigo, y determina cual tiene un HPact igual a 0, y en base
   * a eso, realiza transferencias de estrellas y añade victorias a la cuenta
   *
   */
  def duelo(): Unit = {
    if (player.HPact == 0){
      player.Derrotado(enemigo)
      val stars1: Int = enemigo.estrellas
      enemigo.estrellas_(stars1+(player.estrellas/2))
      player.estrellas_(player.estrellas/2)
    }
    else if (enemigo.HPact == 0){
      enemigo.Derrotado(player)
      val stars2: Int = player.estrellas
      player.estrellas_((enemigo.estrellas)+stars2)
    }
  }

  def attack()

  override var Siguiente: Set[panel] = siguientes
  override var OcupadoPor: Set[Player] = Set()
  override var Pasando: Player = player
  protected var enemigo: Entidad = enemy
  override protected val frente: panel = ???
  override protected val arriba: panel = ???
  override protected val abajo: panel = ???
}
