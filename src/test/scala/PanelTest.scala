import citrusliquid.Entidades.{Chicken, Entidad, Player}
import citrusliquid.paneles.{BonusPanel, DropPanel, EncounterPanel, HomePanel, panel}
import munit.FunSuite
import citrusliquid.Dado

class PanelTest extends FunSuite {
  val name = "Sora"
  var entidad: Player = _
  var panel1: HomePanel = _
  var panel2: panel = _
  var dado: Dado = _
  var pollo: Entidad = _
  var dead_pollo: Entidad = _
  var Chapter: Int = 2
  var entidad2: Player = _
  var panel_drop: DropPanel = _
  var duelo_panel: EncounterPanel = _
  var bonus_panel: BonusPanel = _
  var home: HomePanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    dead_pollo = new Chicken(0)
    entidad = new Player(name,100, 50,25,15,10,"En Duelo", 10)
    pollo = new Chicken(10)
    panel1 = new HomePanel(Set(panel2), entidad ,entidad)
    panel2 = new EncounterPanel(Set(panel1),entidad, dead_pollo)
    dado = new Dado
    dead_pollo.HPact = 0
    dead_pollo.Derrotado(entidad)
    entidad2 = new Player()
    entidad2.dado = 5
    entidad.dado = 3
    panel_drop = new DropPanel(Set(), entidad2)
    duelo_panel = new EncounterPanel(Set(), entidad2, pollo)
    bonus_panel = new BonusPanel(Set(), entidad2)
    home = new HomePanel(Set(), entidad2, entidad2)
  }
  test("testearemos el obtener de buena manera los atributos de un jugador"){
    assertEquals(entidad.name, "Sora")
    assertEquals(entidad.HPmax, 100)
    assertEquals(entidad.HPact, 50)
    assertEquals(entidad.ATKp, 25)
    assertEquals(entidad.DEFp, 15)
    assertEquals(entidad.EVAp, 10)
    assertEquals(entidad.Estado, "En Duelo")
    assertEquals(entidad.estrellas, 10)
    assertEquals(entidad.Norma, 1)
  }
  test("testearemos 'OcupadoPor'"){
    panel1.OcupadoPor(entidad)
  }
  test("testearemos el caso que el panel esté vacío"){
    assertEquals(false, panel2.OcupadoPor(entidad))
  }
  test("testearemos que el dado funciona correctamente"){
    assertEquals(true, 1<=dado.generar())
    assertEquals(true, dado.generar()<=6)
  }
  test("entidad es el dueño de panel1 y que puede detenerse a voluntad"){
    assertEquals(entidad, panel1.owner)
    panel1.stop_owner(entidad, elección = true)
    assertEquals(entidad.dado, 0)
    panel1.OcupadoPor contains(entidad)
  }
  test("la function EndTurn hace el NormaCheck"){
    panel1.EndTurn(entidad)
    assertEquals(entidad.HPact, 51)
    assertEquals(entidad.Norma, 2)
  }
  test("probaremos la función drop"){
    val estrellas: Int = entidad2.estrellas
    panel_drop.drop(entidad2)
    val estrellas2: Int = entidad2.estrellas
    assertEquals(estrellas>estrellas2,true)
  }
  test("probaremos el traspaso de victorias y estrellas de WildUnit a Player"){
    assertEquals(entidad2.Victorias, 0)
    pollo.HPact = 0
    //val estrellas = entidad.estrellas
    duelo_panel.duelo()
    assertEquals(entidad2.Victorias,1)
    assertEquals(entidad2.estrellas, 30)
  }
  test("bonus de estrellas"){
    var estrellas: Int = entidad2.estrellas
    var roll = dado.generar()
    bonus_panel.addEstrellas(entidad2, roll)
    assertEquals(entidad2.estrellas>estrellas, true)
  }
}