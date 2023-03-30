class GoodCalculator: Calculator {
    override fun sum(a: String, b: String): String {
        return a.zip(b).fold(Pair('0', "")) { carryResult, pair ->
            val leftOperator = pair.first
            val rightOperator = pair.second
            val carry = carryResult.first
            val sum = sum(leftOperator, rightOperator, carry)
            Pair(sum.first, sum.second + carryResult.second)
        }.second
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
                else -> throw Exception()
        }
    }


    override fun sub(a: String, b: String): String {
        return sum (opposite(a), opposite(b))
    }

    private fun opposite(b: String) = b.map { c -> if (c == '1') '0' else '1' }.toString()
    override fun mult(a: String, b: String): String {
        TODO("Not yet implemented")
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