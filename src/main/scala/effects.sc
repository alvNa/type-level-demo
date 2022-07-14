import cats.effect.IO
import fs2.Stream

val eff = Stream.eval(IO { println("BEING RUN!!"); 1 + 1 })

//eff.toList

import cats.effect.unsafe.implicits.global

eff.compile.toVector.unsafeRunSync()

val ra = eff.compile.toVector // gather all output into a Vector
// ra: IO[Vector[Int]] = IO(...) // gather all output into a Vector
val rb = eff.compile.drain // purely for effects
// rb: IO[Unit] = IO(...) // purely for effects
val rc = eff.compile.fold(0)(_ + _) // run and accumulate some result
// rc: IO[Int] = IO(...)

ra.unsafeRunSync()
// BEING RUN!!
// res13: Vector[Int] = Vector(2)
rb.unsafeRunSync()
// BEING RUN!!
rc.unsafeRunSync()
// BEING RUN!!
// res15: Int = 2
rc.unsafeRunSync()
// BEING RUN!!
// res16: Int = 2