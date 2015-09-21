object FizzBuzzPatternMatching extends App {
  def nextStudent(tuple: (Int, Int, Int)): String = tuple match {
    case (_, 0, 0)=> "fizzbuzz"
    case (_, 0, _) => "fizz"
    case (_, _, 0) => "buzz"
    case (_,_,_) => tuple._1 + ""
  }
  
  (1 to 100).foreach(count => println(nextStudent((count, count%3, count%5))))
}
