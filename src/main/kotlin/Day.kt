abstract class Day <INPUT, OUTPUT> (dayInput : List<String>) {
    private val convertedInput : INPUT = convertInput(dayInput)

    fun runPart1() : OUTPUT = runPart1(convertedInput) ?: throw RuntimeException("Part1 did not returned a result")
    fun runPart2() : OUTPUT = runPart2(convertedInput) ?: throw RuntimeException("Part2 did not returned a result")

    abstract fun convertInput(input: List<String>) : INPUT
    abstract fun runPart1(input: INPUT) : OUTPUT?
    abstract fun runPart2(input: INPUT) : OUTPUT?
}