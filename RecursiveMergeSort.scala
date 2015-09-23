object RecursiveMergeSort extends App {
  def sort(list: List[Int]) : List[Int] = list.length match {
    case 0 => list;
    case 1 => list;
    case l => merge(sort(list.take(l/2)), sort(list.takeRight(l/2 + l % 2)));
  }
  def merge(a: List[Int], b: List[Int]) : List[Int] =  {
    if(a.length == 0) return b
    if(b.length == 0) return a
    if(a.head < b.head) {
      a.head :: merge(a.tail, b)
    } else {
      b.head :: merge(a, b.tail)
    }
  }

  val testCase = List(99,33,2,1,77,21,41,14,31,76,42,9,27,4,16,7);
  println(sort(testCase));
}