import cats.effect.IO
import fs2.{Chunk, Stream}
import cats.effect.unsafe.implicits.global

val s1c = Stream.chunk(Chunk.array(Array(1.0, 2.0, 3.0)))
// s1c: Stream[[x]fs2.package.Pure[x], Double] = Stream(..)

val appendEx1 = Stream(1,2,3) ++ Stream.emit(42)
// appendEx1: Stream[[x]fs2.package.Pure[x], Int] = Stream(..)
val appendEx2 = Stream(1,2,3) ++ Stream.eval(IO.pure(4))
// appendEx2: Stream[[A]IO[A], Int] = Stream(..)

appendEx1.toVector
// res17: Vector[Int] = Vector(1, 2, 3, 42)
val x = appendEx2.compile.toList

appendEx2.compile.toVector.unsafeRunSync()
// res18: Vector[Int] = Vector(1, 2, 3, 4)

appendEx1.map(_ + 1).toList
// res19: List[Int] = List(2, 3, 4, 43)

appendEx1.flatMap(i => Stream.emits(List(i,i))).toList

val err = Stream.raiseError[IO](new Exception("oh noes!"))
// err: Stream[IO, Nothing] = Stream(..)
val err2 = Stream(1,2,3) ++ (throw new Exception("!@#$"))
// err2: Stream[[x]fs2.package.Pure[x], Int] = Stream(..)
val err3 = Stream.eval(IO(throw new Exception("error in effect!!!")))
// err3: Stream[IO, Nothing] = Stream(..)

val err = Stream.raiseError[IO](new Exception("oh noes!"))
// err: Stream[IO, Nothing] = Stream(..)
val err2 = Stream(1,2,3) ++ (throw new Exception("!@#$"))
// err2: Stream[[x]fs2.package.Pure[x], Int] = Stream(..)
val err3 = Stream.eval(IO(throw new Exception("error in effect!!!")))
// err3: Stream[IO, Nothing] = Stream(..)

try err.compile.toList.unsafeRunSync() catch { case e: Exception => println(e) }

try err2.toList catch { case e: Exception => println(e) }

try err3.compile.drain.unsafeRunSync() catch { case e: Exception => println(e) }

