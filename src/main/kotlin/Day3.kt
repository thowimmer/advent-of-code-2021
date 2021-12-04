class Day3(input: List<String>) : Day<List<String>, Int>(input) {

    override fun convertInput(input: List<String>): List<String>  = input

    override fun runPart1(input: List<String>): Int {
        val flippedBinaryStrings = input.flip()
        val gamaRate = StringBuffer()
        val epsilonRate = StringBuffer()

        for(flippedBinaryString in flippedBinaryStrings){
            val commonBits = CommonBits.fromBinaryString(flippedBinaryString)
            gamaRate.append(commonBits.mostCommonBit())
            epsilonRate.append(commonBits.leastCommonBit())
        }

        return gamaRate.toString().toDecimal() * epsilonRate.toString().toDecimal()
    }

    override fun runPart2(input: List<String>): Int {
        val oxygenGeneratorRating = getRatingWithCriteria(input) { it.mostCommonBit() ?: '1'}
        val co2ScrubberRating = getRatingWithCriteria(input) { it.leastCommonBit() ?: '0'}
        return oxygenGeneratorRating * co2ScrubberRating
    }

    private fun getRatingWithCriteria(input: List<String>, bitToKeepCriteria: (commonBits : CommonBits) -> Char ) : Int {
        var remainingBinaryStrings = input
        var nthBitsOfRemaining : String?
        var bitIndex = 0

        while (remainingBinaryStrings.size > 1){
            nthBitsOfRemaining = remainingBinaryStrings.flip()[bitIndex]
            val commonBits = CommonBits.fromBinaryString(nthBitsOfRemaining)
            val bitToKeep = bitToKeepCriteria(commonBits)
            remainingBinaryStrings = remainingBinaryStrings.filter { it[bitIndex] == bitToKeep }
            bitIndex++
        }

        return remainingBinaryStrings.first().toDecimal()
    }

    private fun String.toDecimal() : Int = this.toInt(radix = 2)

    private fun List<String>.flip() : List<String> {
        val flippedList = List(size = this[0].length, init = { StringBuffer() })
        for(line in this){
            val charsInLine = line.toCharArray()
            for(rowIndex in charsInLine.indices){
                flippedList[rowIndex].append(charsInLine[rowIndex])
            }
        }

        return flippedList.map { it.toString() }
    }

    private data class CommonBits(val zeroBits: Int, val oneBits: Int){
        companion object {
            fun fromBinaryString(binaryString: String) : CommonBits{
                val bits = binaryString.toCharArray()

                var zeroBitsCounter = 0
                var oneBitsCounter = 0

                for(bit in bits){
                    when(bit){
                        '0' -> zeroBitsCounter++
                        '1' -> oneBitsCounter++
                    }
                }

                return CommonBits(zeroBitsCounter, oneBitsCounter)
            }
        }

        fun mostCommonBit() : Char? {
            return when {
                zeroBits > oneBits -> '0'
                oneBits > zeroBits -> '1'
                else -> null
            }
        }

        fun leastCommonBit() : Char? {
            return when {
                zeroBits > oneBits -> '1'
                oneBits > zeroBits -> '0'
                else -> null
            }
        }
    }
}