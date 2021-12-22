class Day8(input: List<String>) : Day<List<Day8.Display>, Int>(input) {

    class Display(val signals: List<Set<Char>>, val output: List<Set<Char>>)

    override fun convertInput(input: List<String>): List<Display> {
        return input.map { it.split("|") }.map {
            val signalPatterns = it[0].split(" ").dropLast(1).map { it.toCharArray().toSet() }
            val outputValue = it[1].split(" ").drop(1).map { it.toCharArray().toSet() }
            Display(signalPatterns, outputValue)
        }
    }

    override fun runPart1(input: List<Display>) : Int {
        var digitsWithUniquePatterns = 0

        for(display in input){
            for(outputValue in display.output){
                when(outputValue.size){
                    2, 4, 3, 7 -> digitsWithUniquePatterns++
                }
            }
        }

        return digitsWithUniquePatterns
    }

    override fun runPart2(input: List<Display>) : Int {
        var sum = 0

        for(display in input){
            val config = deriveConfiguration(display.signals)

            val numberBuilder = StringBuilder()
            for(outputValue in display.output){
                val digit = config.indexOfFirst { it.containsAll(outputValue) && it.size == outputValue.size }
                numberBuilder.append(digit)
            }

            sum += numberBuilder.toString().toInt()
        }

        return sum
    }

    private fun deriveConfiguration(signals: List<Set<Char>>) : Array<Set<Char>> {
        val config = Array(10) { setOf<Char>() }
        //1 has 2 segments on
        config[1] = signals.first { it.size == 2 }
        //1 has 4 segments on
        config[4] = signals.first { it.size == 4 }
        //7 has 3 segments on
        config[7] = signals.first { it.size == 3 }
        //8 has 7 segments on
        config[8] = signals.first { it.size == 7 }
        //3 has 5 segments on and overlaps with 1
        config[3] = signals.first { it.size == 5 && config[1].subtract(it).isEmpty() }
        //9 has 6 segments on and overlaps with 3
        config[9] = signals.first { it.size == 6 && config[3].subtract(it).isEmpty() }
        //0 has 6 segments on and overlaps with 1 and is not 9
        config[0] = signals.first { it.size == 6 && config[1].subtract(it).isEmpty() && it != config[9] }
        //6 has 6 segments on and is not 0 and not 9
        config[6] = signals.first { it.size == 6 && it != config[0] && it != config[9] }
        //5 has 5 segments on overlaps with 6 by 1 and is not 3
        config[5] = signals.first { it.size == 5 && config[6].subtract(it).size == 1 && it != config[3] }
        //2 has 5 segments on and is not 3 and not 5
        config[2] = signals.first { it.size == 5 && it != config[3] && it != config[5] }
        return config
    }
}