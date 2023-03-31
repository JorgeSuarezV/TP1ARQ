class GoodCalculator: Calculator {
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
                Triple('1', '1', '1') ->   {
                        Pair('1', '1')
                    }
                Triple('1', '0', '1'),
                Triple('1', '1', '0'),
                Triple('0', '1', '1') ->   {
                        Pair('1', '0')
                    }
                Triple('1', '0', '0'),
                Triple('0', '0', '1'),
                Triple('0', '1', '0') ->   {
                        Pair('0', '1')
                    }
                Triple('0', '0', '0') ->   {
                        Pair('0', '0')
                    }
                else -> throw Exception("($leftOperator, $rightOperator, $carry)")
        }
  }
  override fun sub(a: String, b: String): String {
    val sumHelper = sumHelper(a, opposite(b))
    val second = sumHelper.second
    val inverseResult = if (sumHelper.first == '1') {
      sum(second, "0".repeat(second.length-1)+ "1")
        .dropWhile { it == '0' }
    } else {
      opposite(second)
    }
    return inverseResult
  }

  private fun opposite(b: String):String {
    return b.map {
      if (it == '1') '0' else '1'
    }.joinToString(
      separator = ""
    )
  }

  override fun mult(multiplicand: String, multiplier: String): String {
      val valuesToBeSum = multiplicand.fold(listOf<String>()) { allMultValues, multiplicandValue ->
          val value = multiplier.foldRight("") { c, acc -> acc + mult(multiplicandValue, c) }
          allMultValues + value
      }
      return valuesToBeSum.reduce {left, right -> sum(left, right)}
  }

  private fun mult(multiplicand: Char, multiplier: Char): Char {
      return if (multiplicand == '0' || multiplier == '0') '0'
      else '1'
  }

  override fun div(a: String?, b: String?): String {
      TODO("Not yet implemented")
  }

  override fun toHex(binary: String?): String {
      TODO("Not yet implemented")
  }

  override fun fromHex(hex: String?): String {
      TODO("Not yet implemented")
  }
}