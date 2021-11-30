class Day1(input: List<String>) : Day<List<Int>, Int>(input) {

    override fun convertInput(input: List<String>): List<Int> = input.map { it.toInt() }

    override fun runPart1(input: List<Int>): Int? {
        return -1
    }

    override fun runPart2(input: List<Int>): Int? {
        return -1
    }
}