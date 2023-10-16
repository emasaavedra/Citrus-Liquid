package citrusliquid.paneles

import citrusliquid.Entidades.Player

import java.util

trait panel {

  protected var Siguiente: Set[panel]
  protected var OcupadoPor: Set[Player]
  protected var Pasando: Player
}

