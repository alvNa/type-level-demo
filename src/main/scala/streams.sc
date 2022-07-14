import fs2.Stream

val s0 = Stream.empty
// s0: Stream[fs2.package.Pure, Nothing] = Stream(..)
val s1 = Stream.emit(1)
// s1: Stream[[x]fs2.package.Pure[x], Int] = Stream(..)
val s1a = Stream(1,2,3) // variadic
// s1a: Stream[[x]fs2.package.Pure[x], Int] = Stream(..) // variadic
val s1b = Stream.emits(List(1,2,3)) // accepts any Seq
// s1b: Stream[[x]fs2.package.Pure[x], Int] = Stream(..)

s1.toList
// res0: List[Int] = List(1)
s1.toVector

(Stream(1,2,3) ++ Stream(4,5)).toList
// res2: List[Int] = List(1, 2, 3, 4, 5)
Stream(1,2,3).map(_ + 1).toList
// res3: List[Int] = List(2, 3, 4)
Stream(1,2,3).filter(_ % 2 != 0).toList
// res4: List[Int] = List(1, 3)
Stream(1,2,3).fold(0)(_ + _).toList
// res5: List[Int] = List(6)
Stream(None,Some(2),Some(3)).collect { case Some(i) => i }.toList
// res6: List[Int] = List(2, 3)
Stream.range(0,5).intersperse(42).toList
// res7: List[Int] = List(0, 42, 1, 42, 2, 42, 3, 42, 4)
Stream(1,2,3).flatMap(i => Stream(i,i)).toList
// res8: List[Int] = List(1, 1, 2, 2, 3, 3)
Stream(1,2,3).repeat.take(9).toList
// res9: List[Int] = List(1, 2, 3, 1, 2, 3, 1, 2, 3)
Stream(1,2,3).repeatN(3).toList
// res10: List[Int] = List(1, 2, 3, 1, 2, 3)