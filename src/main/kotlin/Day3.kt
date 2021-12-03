class Day3(input: List<String>) : Day<List<String>, Int>(input) {

    override fun convertInput(input: List<String>): List<String>  {
        val flippedList = List(size = input[0].length, init = { StringBuffer() })
        for(line in input){
            val charsInLine = line.toCharArray()
            for(rowIndex in charsInLine.indices){
                flippedList[rowIndex].append(charsInLine[rowIndex])
            }
        }

        return flippedList.map { it.toString() }
    }

    data class Rate(val hex: StringBuffer = StringBuffer()){
        fun appendBit(bit : Boolean){
            when(bit){
                true -> hex.append('1')
                false -> hex.append('0')
            }
        }

        fun toDecimal() = hex.toString().toInt(radix = 2)
    }

    override fun runPart1(input: List<String>): Int {
        val gamaRate = Rate()
        val epsilonRate = Rate()

        for(bits in input){
            val mostCommonBit = findMostCommonBit(bits)
            gamaRate.appendBit(mostCommonBit)
            epsilonRate.appendBit(!mostCommonBit)
        }

        return gamaRate.toDecimal() * epsilonRate.toDecimal()
    }

    override fun runPart2(input: List<String>): Int {
        return -1
    }

    private fun findMostCommonBit(hex : String) : Boolean {
        val bits = hex.toCharArray()

        var zeroBitsCounter = 0
        var oneBitsCounter = 0

        for(bit in bits){
            when(bit){
                '0' -> zeroBitsCounter++
                '1' -> oneBitsCounter++
            }
        }

        return oneBitsCounter > zeroBitsCounter
    }
}