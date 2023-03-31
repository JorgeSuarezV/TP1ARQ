import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.FileInputStream

class SumTest {

  @Test
  fun test() {
    val inputStream = FileInputStream("/home/indigo/proyects/TP1ARQ/calculator/src/test/resources/sumTest")
    val readCsv = ReadValue.readCsv(inputStream)
    inputStream.close()
    readCsv.forEach {
      assertEquals(GoodCalculator().sum(it.left, it.right), it.result)
    }
  }
}