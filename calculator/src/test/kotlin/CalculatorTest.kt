import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.util.*

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

  @Test
  fun mult() {
    val inputStream = FileInputStream(("src/test/resources/multTest"))
    val readCsv = ReadValue.readCsv(inputStream)
    inputStream.close()
    readCsv.forEach {
      val sub = GoodCalculator().mult(it.left, it.right)
      assertEquals(it.result, sub)
    }
  }

  @Test
  fun toHex(){
    val inputStream = FileInputStream(("src/test/resources/toHexTest"))
    val readCsv = ReadValue.readCsv(inputStream)
    inputStream.close()
    readCsv.forEach {
      val sub = GoodCalculator().toHex(it.left)
      assertEquals(it.right, sub)
    }

    val exception: Exception = Assertions.assertThrows(Exception::class.java) { GoodCalculator().toHex("") }
    assertEquals("Input cannot be null", exception.message)
  }
}