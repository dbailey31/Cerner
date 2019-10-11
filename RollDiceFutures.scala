// cerner_2^5_2019
object RollDiceFutures extends App {
  val executorService: java.util.concurrent.ExecutorService = java.util.concurrent.Executors.newFixedThreadPool(4);
  val NUMBER_OF_ROLLS = 100000;
  def rollDice(sides: Int, rolls: Int): (Int,Int) = {
    var results = collection.mutable.Map[Int, Int]();
    for(i <- 1 until sides + 1) {results += (i -> 0)} // create empty map of possible results
    for(i <- 0 until rolls) {
      val roll = new scala.util.Random().nextInt(sides) + 1;
      results(roll) = results(roll) + 1;
    }
    return results.maxBy(item => item._2);
  }
  def concurrentRolls(sides: Int, rolls: Int): Unit = {
    val task = new java.util.concurrent.FutureTask[Unit](new java.util.concurrent.Callable[Unit]() {
      def call(): Unit = {
        val winner = rollDice(sides, rolls);
        println ("Sides: " + sides + " Rolls: " + rolls + " Winner: " + winner._1 + " Rolled times: " + winner._2)
      }
    });
    executorService.execute(task);
  }
  concurrentRolls(4, NUMBER_OF_ROLLS)
  concurrentRolls(8, NUMBER_OF_ROLLS)
  concurrentRolls(10, NUMBER_OF_ROLLS)
  concurrentRolls(20, NUMBER_OF_ROLLS)
  executorService.shutdown();
}
/*  Sides: 8 Rolls: 100000 Winner: 5 Rolled times: 12614
    Sides: 10 Rolls: 100000 Winner: 1 Rolled times: 10081
    Sides: 4 Rolls: 100000 Winner: 2 Rolled times: 25117
    Sides: 20 Rolls: 100000 Winner: 10 Rolled times: 5145 */