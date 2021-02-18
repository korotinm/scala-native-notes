import scala.scalanative.unsafe._
import scala.scalanative.libc._

object ParseIntSscanf {

  def main(args: Array[String]): Unit = {
    val line_in_buffer = stackalloc[Byte](1024)
    while(stdio.fgets(line_in_buffer, 1023, stdio.stdin) != null) {
      parse_int_line(line_in_buffer)
    }
    println("done")
  }

  def parse_int_line(line: CString): Int = {
    val int_pointer: Ptr[Int] = stackalloc[Int]
    val scan_result = stdio.sscanf(line, c"%d\n", int_pointer)
    if(scan_result == 0) {
      throw new Exception("Parse error in sscanf!")
    }
    stdio.printf(c"read value %d into address %p\n", !int_pointer, int_pointer)
    val int_val: Int = !int_pointer
    return int_val
  }
  
}
