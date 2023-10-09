package citrusliquid.Entidades

import citrusliquid.Dado
import citrusliquid.paneles.panel

import scala.math.floor

/** Genera los atributos de las Entidades, tanto los jugadores como Wild Unit
 *
 * @param _panel Panel que señala la posición del Jugador, al inicio de la partida deberá señalar el panel inicial
 * @param _nombre Nombre de la entidad para poder diferenciarlos y hacer un display en caso de que sea necesario
 * @param _HPmax Asumiremos que la cantidad de vida máxima es constante durante la partida, y la almacenamos
 * @param _HPact Guardaremos la vida de la entidad en cada momento
 * @param _ATKp Asumiremos como constante la cantidad de puntos de Ataque de la Entidad
 * @param _DEFp Asumiremos como constante la cantidad de puntos de Defensa de la Entidad
 * @param _EVAp Asumiremos como constante la cantidad de puntos de Evasión de la Entidad
 * @param _Estad Le atribuimos un estado para saber si está "JUGANDO" o "EN DUELO"
 * @param _estrella almacenamos la cantidad de estrellas en cada momento
 */

class Player(private var _panel: panel, private var _nombre: String, private var _HPmax: Int, private var _HPact: Int, private var _ATKp: Int, private var _DEFp: Int, private var _EVAp: Int, private var _Estad: String, private var _estrella: Int) extends Entidad {
  def dado: Dado = new Dado
  def roll: Int = 0

  private var _Norma: Int = 1
  def Norma: Int = _Norma //getter de 'Norma'
  def Norma_(norma: Int) : Unit = { //setter de 'Norma'
    this._Norma = norma
  }

  override def name: String = _nombre
  override def HPmax: Int = _HPmax
  override def panel: panel = _panel
  override def HPact: Int = _HPact
  override def ATKp: Int = _ATKp
  override def DEFp: Int = _DEFp
  override def EVAp: Int = _EVAp

  private var _estrellas: Int = _estrella
  override def estrellas: Int = _estrellas //getter de 'Estrellas'
  def estrellas_(num: Int): Unit = { //setter de 'Estrellas'
    this._estrellas = num
  }

  private var _Estado: String = _Estad
  def Estado: String = _Estado //getter de 'Estado'
  def Estado_(str: String): Unit = {//setter de 'Estado'
    this._Estado = str
  }

  private var _Victorias: Int = 0
  def Victorias: Int = _Victorias//getter de 'Victorias'
  def Victorias_(num: Int): Unit = {//setter de 'Victorias'
    this._Victorias=num
  }

  override def Derrotado(enemy: Entidad): Unit = {
    if(this.HPact==0){
      this.Estado_("K.O.")
      enemy.Victorias_(enemy.Victorias+2)
    }
  }
/*
  /** Función que define estadísticas por defecto, en caso de que el constructor no recibe atributos
   *
   */
  def this() = {
    this("Jugador",100, 100,25,15,10,"Esperando", 20)
  }

 */

  /** Función que Añade estrellas y tira una dado para el jugador
   *
   * @param chapter Capítulo actual de la partida
   */
  def JugarTurno(chapter: Int): Unit = {
    this.estrellas += floor((chapter/5)+1).toInt
    var rol = this.dado.generar()
    this.roll = rol
  }

  /** Función que simula el estado de Recovery del jugador
   *
   * @param chapter Capítulo actual de la partida
   */
  def Recovery(chapter: Int): Unit = {
    val dice: Dado = new Dado
    val dado = dice.generar()
    if (dado == 6-chapter+1){
      println("Te has recuperado!")
      this.HPact = 1
      val dado2: Dado = new Dado
      this.JugarTurno(chapter)
    }
    else{
      println("No te has podido recuperar :(")
    }
  }
}

