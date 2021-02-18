import scala.scalanative.unsafe._
import scala.scalanative.libc._

object Main {
  def main(args: Array[String]): Unit = {
    val str: Ptr[Byte] = c"hello world"
    val str_len = string.strlen(str)

    stdio.printf(c"the csting '%s' at address '%p' is %d bytes long\n", str, str, str_len)
    stdio.printf(c"the Ptr[Bytes] value 'str' itself is %d bytes long\n", sizeof[Ptr[Byte]])

    for(offset <- 0L to str_len.toLong) {
      val chr_address: Ptr[Byte] = str + offset
      val ch: Byte = !chr_address
      stdio.printf(c"'%c'\t(%d) at address %p is %d bytes long\n", ch, ch, chr_address, sizeof[CChar])
    }
  }
}
