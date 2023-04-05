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
      assertEquals( it.result, GoodCalculator().sum(it.left, it.right))
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
  fun div(){
    val inputStream = FileInputStream(("src/test/resources/divTest"))
    val readCsv = ReadValue.readCsv(inputStream)
    inputStream.close()
    readCsv.forEach {
      val sub = GoodCalculator().div(it.left, it.right)
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

  @Test
  fun fromHex(){
    val inputStream = FileInputStream(("src/test/resources/fromHexTest"))
    val readCsv = ReadValue.readCsv(inputStream)
    inputStream.close()
    readCsv.forEach {
      val sub = GoodCalculator().fromHex(it.left)
      assertEquals(it.right, sub)
    }

    val exception: Exception = Assertions.assertThrows(Exception::class.java) { GoodCalculator().fromHex("") }
    assertEquals("Input cannot be null", exception.message)
  }
}