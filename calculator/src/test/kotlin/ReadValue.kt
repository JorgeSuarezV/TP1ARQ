import java.io.InputStream

class ReadValue(
  val left: String,
  val right: String,
  val result: String,
) {
  companion object {
    fun readCsv(inputStream: InputStream): List<ReadValue> {
      val reader = inputStream.bufferedReader()
      return reader.lineSequence()
        .filter { it.isNotBlank() }
        .map {
          val (left, right, result) = it.split(',', ignoreCase = false, limit = 3)
          ReadValue(left.trim(), right.trim(), result.trim())
        }.toList()
    }
  }
}

