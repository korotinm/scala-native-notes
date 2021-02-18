import scala.scalanative.unsafe._
import scala.scalanative.libc._

object ParseStringSscanf {
  
  def main(args: Array[String]): Unit = {
    val lineInBuffer = stackalloc[Byte](1024)
    while(stdio.fgets(lineInBuffer, 1023, stdio.stdin) != null) {
      parse_string(lineInBuffer)
    }
  }

  def parse_string(line: CString): Unit = {
    val string_pointer = stackalloc[Byte](1024)
    val scan_res = stdio.sscanf(line, c"%s\n", string_pointer)
    if(scan_res < 1)
      throw new Exception(s"Parse failed during in processing sscanf: $scan_res")

    stdio.printf(c"scan result '%s'\n", string_pointer)
  }

}
