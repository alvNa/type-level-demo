import cats.effect.{IO, IOApp}
import fs2._

object StreamsApp extends IOApp.Simple  {

  override def run: IO[Unit] = {
    IO {
      val x = Stream(1,2,3,4).through(tk(2)).toList
      println(x)
    }
  }

  def tk[F[_],O](n: Long): Pipe[F,O,O] =
    in => in.scanChunksOpt(n) { n =>
      if (n <= 0) None
      else Some(c => c.size match {
        case m if m < n => (n - m, c)
        case _ => (0, c.take(n.toInt))
      })
    }
}
