class Day1(input: List<String>) : Day<List<Int>, Int>(input) {

    override fun convertInput(input: List<String>): List<Int> = input.map { it.toInt() }

    override fun runPart1(input: List<Int>): Int = countMeasurementIncreases(input)

    override fun runPart2(input: List<Int>): Int {
        val slidingWindowMeasurements = calculateSlidingWindowMeasurements(input)
        return countMeasurementIncreases(slidingWindowMeasurements)
    }

    private fun calculateSlidingWindowMeasurements(input: List<Int>, windowSize: Int = 3) : List<Int> {
        val measurementWindows = mutableListOf<Int>()
        var currentWindowIndex = 0

        while(input.size - currentWindowIndex >= windowSize){
            val measurementsInCurrentWindow = input.subList(currentWindowIndex, currentWindowIndex + windowSize)
            measurementWindows.add(measurementsInCurrentWindow.sum())
            currentWindowIndex++
        }

        return measurementWindows.toList()
    }

    private fun countMeasurementIncreases(measurements: List<Int>) : Int {
        var measurementWithDepthIncreases = 0

        for(i in 0 until measurements.size-1){
            if(measurements[i+1] > measurements[i]){
                measurementWithDepthIncreases++
            }
        }

        return measurementWithDepthIncreases
    }
}