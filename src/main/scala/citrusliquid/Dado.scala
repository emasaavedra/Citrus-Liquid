package citrusliquid

/** Clase que simula un dado
 *
 */
class Dado {
  /** Función que genera un numero aleatorio entre 1 y 6
   *
   * @return Un entero aleatorio entre 1 y 6
   */
  def generar(): Int = {
    var rand: scala.util.Random = new scala.util.Random
    var resultado: Int = rand.between(1, 6)
    return resultado
  }
}
