class Day10(input: List<String>) : Day<List<List<Char>>, Long>(input) {

    override fun convertInput(input: List<String>): List<List<Char>> {
        return input.map { it.toCharArray().toList() }
    }

    override fun runPart1(input: List<List<Char>>) : Long {
        return scanNavigationSubsystem(input).illegalChars.mapNotNull { it?.syntaxErrorPoints() }.sum().toLong()
    }

    override fun runPart2(input: List<List<Char>>) : Long {
        val remainingClosingChars = scanNavigationSubsystem(input).remainingClosingCharsOfIncompleteLines

        val scores = mutableListOf<Long>()
        for(chars in remainingClosingChars){
            var score = 0L
            for(char in chars){
                score = score * 5 + char.closingCharPoints()
            }
            scores.add(score)
        }
        return scores.sorted()[scores.size/2]
    }

    private class ScanResult(val remainingClosingCharsOfIncompleteLines: List<List<Char>>, val illegalChars : List<Char?>)

    private fun scanNavigationSubsystem(system: List<List<Char>>) : ScanResult {
        val remainingClosingChars = mutableListOf<List<Char>>()
        val illegalChars = mutableListOf<Char?>()

        for (line in system) {
            val expectedClosingChars = mutableListOf<Char>()
            var illegalChar: Char? = null
            for (char in line) {
                when {
                    !char.isClosingChar() -> expectedClosingChars.add(char.closingChar())
                    expectedClosingChars.removeLast() != char -> {
                        illegalChar = char; break
                    }
                }
            }

            if(illegalChar == null){
                remainingClosingChars.add(expectedClosingChars.reversed())
            } else {
                illegalChars.add(illegalChar)
            }
        }

        return ScanResult(remainingClosingChars, illegalChars)
    }

    private fun Char.isClosingChar() = this in listOf(')', ']', '}', '>')

    private fun Char.closingChar() : Char {
        return when(this){
            '(' -> ')'
            '[' -> ']'
            '{' -> '}'
            '<' -> '>'
            else -> throw UnsupportedOperationException("$this is not in supported charset")
        }
    }

    private fun Char.syntaxErrorPoints() : Int {
        return when(this){
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> throw UnsupportedOperationException("$this is not in supported charset")
        }
    }

    private fun Char.closingCharPoints() : Int {
        return when(this){
            ')' -> 1
            ']' -> 2
            '}' -> 3
            '>' -> 4
            else -> throw UnsupportedOperationException("$this is not in supported charset")
        }
    }
}