object Invoker {
  private var history: Seq[() => Unit] = Seq.empty

  def invoke(command: => Unit) { // by-name parameter
    command
    history :+= command _
  }
}

Invoker.invoke(println("foo"))

Invoker.invoke {
  println("bar 1")
  println("bar 2")
}

//************
class Button ( var click : ( ( ) => Unit ) )
val button = new Button ( ( ) => println ( " c l i c k ! " ) )
button.click()

//************
trait Undoable { def undo }
class CallBack extends ( ( ) => Unit ) with Undoable {
  def apply ( ) : Unit = println ( " callback ! " )
  def undo = println ( "undoing ! " )
}
object History {
  var commands: List[ ( () => Unit ) with Undoable ] = List()
  val undoAll = commands.foreach( _.undo)
}

History.undoAll