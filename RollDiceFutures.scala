import scala.util.Random;
import java.util.concurrent._
// cerner_2^5_2019
object RollDiceFutures extends App {
  val executorService: ExecutorService = Executors.newFixedThreadPool(4);
  val NUMBER_OF_ROLLS = 100000;
  def rollDice(sides: Int, rolls: Int): (Int,Int) = {
    var results = collection.mutable.Map[Int, Int]();
    for(i <- 1 until sides + 1) {results += (i -> 0)} // create empty map of possible results
    for(i <- 0 until rolls) {
      val roll = new Random().nextInt(sides) + 1;
      results(roll) = results(roll) + 1;
    }
    return results.maxBy(item => item._2);
  }
  def concurrentRolls(sides: Int, rolls: Int): Unit = {
    val task = new FutureTask[Unit](new Callable[Unit]() {
      def call(): Unit = {
        val winner = rollDice(sides, rolls);
        println ("Sides: " + sides + " Rolls: " + rolls + " Winner: " + winner._1 + " Rolled times: " + winner._2)
      }
    });
    executorService.execute(task);
  }
  concurrentRolls(4, NUMBER_OF_ROLLS)
  concurrentRolls(6, NUMBER_OF_ROLLS)
  concurrentRolls(8, NUMBER_OF_ROLLS)
  concurrentRolls(10, NUMBER_OF_ROLLS)
  concurrentRolls(12, NUMBER_OF_ROLLS)
  concurrentRolls(20, NUMBER_OF_ROLLS)
  executorService.shutdown();
}