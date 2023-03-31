import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.FileInputStream

class CalculatorTest {

  @Test
  fun sum() {
    val inputStream = FileInputStream(("src/test/resources/sumTest"))
    val readCsv = ReadValue.readCsv(inputStream)
    inputStream.close()
    readCsv.forEach {
      assertEquals(GoodCalculator().sum(it.left, it.right), it.result)
    }
  }

  @Test
  fun sub() {
    val inputStream = FileInputStream(("src/test/resources/subTest"))
    val readCsv = ReadValue.readCsv(inputStream)
    inputStream.close()
    readCsv.forEach {
      val sub = GoodCalculator().sub(it.left, it.right)
      assertEquals(it.result, sub)
    }
  }
}