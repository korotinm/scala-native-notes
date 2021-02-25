import scala.scalanative.unsafe._
import scala.scalanative.libc._

object MaxNGram {

  def main(args: Array[String]): Unit = {
    val size = 1024

    val maxWord = stackalloc[Byte](size)
    val maxCount = stackalloc[Int]
    val maxYear = stackalloc[Int]

    val lineBuffer = stackalloc[Byte](size)
    val tempWord = stackalloc[Byte](size)
    val tempCount = stackalloc[Int]
    val tempYear = stackalloc[Int]
    val tempDocCount = stackalloc[Int]

    var linesRead: Int = 0
    !maxCount = 0
    !maxYear = 0

    while (stdio.fgets(lineBuffer, size, stdio.stdin) != null) {
      linesRead += 1
      parseNGram(
        lineBuffer,
        maxWord,
        tempWord,
        size,
        maxCount,
        tempCount,
        maxYear,
        tempYear,
        tempDocCount
      )
    }

    stdio.printf(c"Done. Read %d lines\n", linesRead)
    stdio.printf(c"Maximum word count: %d for %s @ %d", !maxCount, maxWord, !maxYear)
  }

  def parseNGram(lineBuffer: CString,
                 maxWord: CString,
                 tempWord: CString,
                 maxBufferSize: Int,
                 maxCount: Ptr[Int],
                 tempCount: Ptr[Int],
                 maxYear: Ptr[Int],
                 tempYear: Ptr[Int],
                 tempDocCount: Ptr[Int]): Unit = {
    val scanRes = stdio.sscanf(lineBuffer, c"%1023s %d %d %d\n", tempWord, tempYear, tempCount, tempDocCount)
    if(scanRes < 4) {
      throw new Exception(s"Bad input: count of elements less than 4 [res = $scanRes].")
    }

    if(!tempCount <= !maxCount){
      return
    } else {
      stdio.printf(c"found new max: %s %d, year %d\n", tempWord, !tempCount, !tempYear)
      val wordLen = string.strlen(tempWord)
      if(wordLen >= (maxBufferSize - 1)) {
        throw new Exception(s"length $wordLen exceeded buffer size $maxBufferSize")
      }
      string.strncpy(maxWord, tempWord, maxBufferSize)
      !maxCount = !tempCount
      !maxYear = !tempYear
    }
  }
}
