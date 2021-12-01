class Day1(input: List<String>) : Day<List<Int>, Int>(input) {

    override fun convertInput(input: List<String>): List<Int> = input.map { it.toInt() }

    override fun runPart1(input: List<Int>): Int? {
        var lastMeasurement = input[0]
        var measurementWithDepthIncreases = 0

        for(i in 1 until input.size){
            val currentMeasurement = input[i]
            if(currentMeasurement > lastMeasurement){
                measurementWithDepthIncreases++
            }
            lastMeasurement = currentMeasurement
        }

        return measurementWithDepthIncreases
    }

    override fun runPart2(input: List<Int>): Int? {
        return -1
    }
}