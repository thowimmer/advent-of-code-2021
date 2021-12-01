class Day1(input: List<String>) : Day<List<Int>, Int>(input) {

    override fun convertInput(input: List<String>): List<Int> = input.map { it.toInt() }

    override fun runPart1(input: List<Int>): Int = countMeasurementIncreases(input)

    override fun runPart2(input: List<Int>): Int {
        val measurementWindows = parseMeasurementWindows(input)
        val measurementWindowSum = measurementWindows.map { it.sum() }
        return countMeasurementIncreases(measurementWindowSum)
    }

    data class MeasurementWindow(private val measurements: List<Int>) {
        fun sum() = measurements.sum()
    }

    private fun parseMeasurementWindows(input: List<Int>) : List<MeasurementWindow> {
        val windowSize = 3

        val measurementWindows = mutableListOf<MeasurementWindow>()
        var currentWindowIndex = 0

        while(input.size - currentWindowIndex >= windowSize){
            val measurementsInCurrentWindow = input.subList(currentWindowIndex, currentWindowIndex + windowSize)
            measurementWindows.add(MeasurementWindow(measurementsInCurrentWindow))
            currentWindowIndex ++
        }

        return measurementWindows.toList()
    }

    private fun countMeasurementIncreases(measurements: List<Int>) : Int {
        var lastMeasurement = measurements[0]
        var measurementWithDepthIncreases = 0

        for(i in 1 until measurements.size){
            val currentMeasurement = measurements[i]
            if(currentMeasurement > lastMeasurement){
                measurementWithDepthIncreases++
            }
            lastMeasurement = currentMeasurement
        }

        return measurementWithDepthIncreases
    }
}