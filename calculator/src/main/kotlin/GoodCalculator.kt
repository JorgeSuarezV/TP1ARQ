class GoodCalculator : Calculator {
  override fun sum(a: String, b: String): String {
    val (carry, result) = sumHelper(a, b)
    return if (carry == '1') carry + result else result
  }

  private fun sumHelper(a: String, b: String) =
    a.zip(b).foldRight(Pair('0', "")) { (leftOperator, rightOperator), (carry, previousValues) ->
      val (newCarry, result) = sum(leftOperator, rightOperator, carry)
      Pair(newCarry, result + previousValues)
    }

  private fun sum(leftOperator: Char, rightOperator: Char, carry: Char): Pair<Char, Char> {
    return when (Triple(leftOperator, rightOperator, carry)) {
      Triple('1', '1', '1') -> {
        Pair('1', '1')
      }

      Triple('1', '0', '1'),
      Triple('1', '1', '0'),
      Triple('0', '1', '1') -> {
        Pair('1', '0')
      }

      Triple('1', '0', '0'),
      Triple('0', '0', '1'),
      Triple('0', '1', '0') -> {
        Pair('0', '1')
      }

      Triple('0', '0', '0') -> {
        Pair('0', '0')
      }

      else -> throw Exception("($leftOperator, $rightOperator, $carry)")
    }
  }

  override fun sub(a: String, b: String): String {
    val sumHelper = sumHelper(a, opposite(b))
    val second = sumHelper.second
    val inverseResult = if (sumHelper.first == '1') {
      sum(second, "0".repeat(second.length - 1) + "1")
        .dropWhile { it == '0' }
    } else {
      opposite(second)
    }
    return inverseResult
  }

  private fun opposite(b: String): String {
    return b.map {
      if (it == '1') '0' else '1'
    }.joinToString(
      separator = ""
    )
  }

  override fun mult(a: String, b: String): String {
    val partialProducts = b.mapIndexed { index, bitB ->
      a.map { bitA -> mult(bitB, bitA) }.joinToString(separator = "") + "0".repeat(index) //1110, 1100
    }
    val maxLength = partialProducts.maxBy { it.length }.length
    return partialProducts.map {
      "0".repeat(maxLength - it.length) + it
    }.reduce { left, right -> sum(left, right) }
  }

  private fun mult(multiplicand: Char, multiplier: Char): Char {
    return if (multiplicand == '0' || multiplier == '0') '0'
    else '1'
  }

  override fun div(dividend: String?, divisor: String?): String {
    if (dividend == null || divisor == null) {
      throw Exception("Input cannot be null")
    }
    var result : String = dividend
    var i: Int = 0
    while (compareNumbers(result,divisor) >=0)  {
      result= sub(result,divisor)
      i++
    }
    return fromIntToBinary(i);
  }

  private fun fromIntToBinary(bits: Int) : String{
    var result : String = ""
    var i : Int = bits
    while (i > 0) {
      result = if (i % 2 == 0) "0" + result else "1" + result
      i /= 2
    }
    return result
  }


  private fun compareNumbers(a:String,b: String) : Int{
    var parsedNumberA = cut0inFront(a)
    var parsedNumberB = cut0inFront(b)
    if(parsedNumberA.length>parsedNumberB.length) return 1;
    if(parsedNumberB.length>parsedNumberA.length) return -1;
    else {
      for(i in parsedNumberA.length..0){
        if((parsedNumberA.get(i).equals("0") && parsedNumberB.get(i).equals("1") )){
          return -1;
        }
        else if((parsedNumberA.get(i).equals("1") && parsedNumberB.get(i).equals("0") )){
          return 1;
        }
      }
    }
    return 0;

  }

  private fun cut0inFront(binary : String ) : String{
    val firstNonZeroIndex = binary.indexOfFirst { it != '0' }

    if (firstNonZeroIndex == -1) {
      return "0"
    }
    if (firstNonZeroIndex == 0) {
      return binary
    }
    return binary.substring(firstNonZeroIndex)
  }

  override fun toHex(binary: String?): String {
    if (binary != "") {
      if (binary != null) {
        if (binary.length % 4 != 0) {
          return toHex("0$binary") //lo hace de longitud multiplo de 4
        }

        val binaryDividedIn4: List<String> = binary.chunked(4) //divide en grupos de 4
        var result: String = ""
        for (i in binaryDividedIn4) {
          result = fourBinaryToHex(i) + result //convierte cada grupo de 4 a hexadecimal
        }

        return result
      }
      } else {
        throw Exception("Input cannot be null")
    }
    return ""
  }

  private fun fourBinaryToHex(binary : String ) : String {
    val conversionTable = mapOf(
            "0000" to "0000", "0001" to "1", "0010" to "2", "0011" to "3",
            "0100" to "4", "0101" to "5", "0110" to "6", "0111" to "7",
            "1000" to "8", "1001" to "9", "1010" to "A", "1011" to "B",
            "1100" to "C", "1101" to "D", "1110" to "E", "1111" to "F"
    )
    return conversionTable.getValue(binary)
  }

  override fun fromHex(hex: String?): String {
    if (hex != "") {
      if (hex != null) {
        var result: String = ""
        for (i in hex.length-1 downTo  0) {
          result = hexToFourBinary(hex[i]) + result //convierte hex a binario
        }
        return cut0inFront(result)
      }
    } else {
      throw Exception("Input cannot be null")
    }
    return ""
  }

  private fun hexToFourBinary (hex: Char) : String {
    val conversionTable = mapOf(
            "0" to "0000", "1" to "0001", "2" to "0010", "3" to "0011",
            "4" to "0100", "5" to "0101", "6" to "0110", "7" to "0111",
            "8" to "1000", "9" to "1001", "A" to "1010", "B" to "1011",
            "C" to "1100", "D" to "1101", "E" to "1110", "F" to "1111"
    )
    return conversionTable.getValue(hex.toString())
  }
}