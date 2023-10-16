package citrusliquid.Entidades

import citrusliquid.Dado
import citrusliquid.paneles.panel

abstract class Entidad {
  var dado: Dado
  def name: String
  def HPmax: Int
  def HPact: Int
  def HPact_(num: Int): Unit
  def ATKp: Int
  def DEFp: Int
  def EVAp: Int
  def estrellas: Int
  def estrellas_(num: Int) : Unit
  def Victorias_(num: Int): Unit
  def Victorias: Int
  def Derrotado(enemy: Entidad): Unit
  def panel: panel
  def attack(enemy: Entidad): Unit
  def underAttack(atkp: Int): Unit
  def defend(enemy: Entidad): Unit
  def evade(enemy: Entidad): Unit
  def answerAttack(enemy: Entidad): Unit

  var Estado: String
}
