class Day10(input: List<String>) : Day<List<List<Char>>, Int>(input) {

    override fun convertInput(input: List<String>): List<List<Char>> {
        return input.map { it.toCharArray().toList() }
    }

    override fun runPart1(input: List<List<Char>>) : Int {
       var sum = 0
       for(line in input){

           val expectedClosingChars = mutableListOf<Char>()
           var illegalChar : Char? = null

           for(char in line){
               when {
                   !char.isClosingChar() -> expectedClosingChars.add(char.closingChar())
                   expectedClosingChars.removeLast() != char -> { illegalChar = char; break }
               }
           }

           if(illegalChar != null) {
               sum += illegalChar.errorScore()
           }
       }

       return sum
    }

    private fun Char.errorScore() : Int {
        return when(this){
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> throw UnsupportedOperationException("$this is not in supported char set")
        }
    }
    private fun Char.isClosingChar() = this in listOf(')', ']', '}', '>')

    private fun Char.closingChar() : Char {
        return when(this){
            '(' -> ')'
            '[' -> ']'
            '{' -> '}'
            '<' -> '>'
            else -> throw UnsupportedOperationException("$this is not in supported char set")
        }
    }

    override fun runPart2(input: List<List<Char>>) : Int {
        return -1
    }
}