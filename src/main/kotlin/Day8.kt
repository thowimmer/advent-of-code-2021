class Day8(input: List<String>) : Day<List<Day8.Display>, Int>(input) {

    class Display(val signalPatterns: List<String>, val outputValue: List<String>)

    override fun convertInput(input: List<String>): List<Display> {
        return input.map { it.split("|") }.map {
            val signalPatterns = it[0].split(" ").dropLast(1)
            val outputValue = it[1].split(" ").drop(1)
            Display(signalPatterns, outputValue)
        }
    }

    override fun runPart1(input: List<Display>) : Int {
        var digitsWithUniquePatterns = 0

        for(display in input){
            for(outputDigit in display.outputValue){
                when(outputDigit.length){
                    2, 4, 3, 7 -> digitsWithUniquePatterns++
                }
            }
        }

        return digitsWithUniquePatterns
    }

    override fun runPart2(input: List<Display>) : Int {
        return -1
    }
}