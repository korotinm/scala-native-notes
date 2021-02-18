import scala.scalanative.unsafe._
import scala.scalanative.libc._

object Hello {

  def main(args: Array[String]): Unit = {
    val str: CString = c"hello, world!"
    val str_len = string.strlen(str)
    stdio.printf(c"the string '%s' at address %p is %d bytes long\n", str, str, str_len)

    stdio.printf(c"the CString value 'str' itself is %d bytes long\n", sizeof[CString])

    for (offset <- 0L to str_len.toLong) {
      val ch: CChar = str(offset)
      stdio.printf(c"""the character '%c' is %d bytes long and has binary value %d\n""", ch, sizeof[CChar], ch)
    }
  }
}
