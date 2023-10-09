package citrusliquid.Entidades

import citrusliquid.paneles.panel

abstract class Entidad {
  def name: String
  def HPmax: Int
  def HPact: Int
  def ATKp: Int
  def DEFp: Int
  def EVAp: Int
  def estrellas: Int
  def estrellas_(num: Int) : Unit
  def Victorias_(num: Int): Unit
  def Victorias: Int
  def Derrotado(enemy: Entidad): Unit
  def panel: panel
}
