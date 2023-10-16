package citrusliquid.Entidades

import citrusliquid.Dado

import scala.math

abstract class Unit extends Entidad {
  var dado: Dado = new Dado
  def attack(enemy: Entidad): Unit = {
    val roll: Int = this.dado.generar()
    val atk: Int = this.ATKp + roll
    enemy.underAttack(atk)
  }

  def underAttack(atkp: Int): Unit = {
    if(this.Estado=="Defending"){
      this.defend(atkp)
    }
    else if(this.Estado=="Evading"){
      this.evade(atkp)
    }
    else{
      this.HPact_(atkp)
    }
  }

  def answerAttack(enemy: Entidad): Unit = {
    if (this.HPact>0){
      this.attack(enemy)
    }
    else{
      this.Derrotado(enemy)
    }
  }

  def defend(atkp: Int): Unit = {
    val roll: Int = this.dado.generar()
    this.HPact -= math.max(1, atkp-(roll+this.DEFp))
  }
  def evade(atkp: Int): Unit = {
    val roll: Int = this.dado.generar()
    if (roll+this.EVAp>atkp){

    }
    else{
      this.HPact -= atkp
    }
  }
}

