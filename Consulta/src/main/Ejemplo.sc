import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import scala.concurrent.duration.*

object MiStream extends App {

  implicit val system = ActorSystem("MiSistema")
  import system.dispatcher

  val nums = Source(1 to 5)

  nums
    .map(num => {
      println(s"Emitiendo $num")
      num
    })
    .runForeach(num => println(s"Recibí $num"))

  system.scheduler.scheduleOnce(500.milliseconds){
    println("Finalizando stream...")
    system.terminate()
  }

}